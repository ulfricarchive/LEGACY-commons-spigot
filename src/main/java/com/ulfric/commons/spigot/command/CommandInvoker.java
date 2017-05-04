package com.ulfric.commons.spigot.command;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

import com.ulfric.commons.exception.Try;
import com.ulfric.commons.reflect.AnnotationUtils;
import com.ulfric.commons.spigot.command.argument.Argument;
import com.ulfric.commons.spigot.command.argument.ArgumentRequiredException;
import com.ulfric.commons.spigot.command.argument.Arguments;
import com.ulfric.commons.spigot.metadata.Metadata;
import com.ulfric.commons.spigot.metadata.MetadataDefaults;
import com.ulfric.commons.spigot.text.Text;
import com.ulfric.dragoon.construct.InstanceUtils;

final class CommandInvoker implements CommandExecutor {

	private static final Pattern ARGUMENT_SPLIT_PATTERN = Pattern.compile("([^\"]\\S*|\".+?\")\\s*");

	private final Class<? extends Command> command;
	private final List<String> permissions;
	private final List<RuleEnforcement> rules;
	private final Map<String, CommandInvoker> subcommands = new HashMap<>();
	private final List<Field> arguments;

	CommandInvoker(Class<? extends Command> command)
	{
		this.command = command;
		this.permissions = this.permissions();
		this.rules = this.rules();
		this.arguments = this.arguments();
	}

	private List<String> permissions()
	{
		Permission permissions = this.command.getAnnotation(Permission.class);
		if (permissions == null)
		{
			return Collections.emptyList();
		}
		return Arrays.asList(permissions.value());
	}

	private List<RuleEnforcement> rules()
	{
		List<Annotation> annotations = AnnotationUtils.getLeafAnnotations(this.command, Rule.class);

		return annotations.stream()
				.map(annotation -> annotation.annotationType().getAnnotationsByType(Rule.class))
				.flatMap(Stream::of)
				.map(Rule::value)
				.distinct()
				.map(InstanceUtils::createOrNull)
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
	}

	private List<Field> arguments()
	{
		List<List<Field>> arguments = this.getAllArgumentFields();
		Collections.reverse(arguments);
		return arguments.stream()
				.flatMap(List::stream)
				.filter(field -> field.isAnnotationPresent(Argument.class))
				.peek(field -> field.setAccessible(true))
				.collect(Collectors.toList());
	}

	private List<List<Field>> getAllArgumentFields()
	{
		List<List<Field>> arguments = new ArrayList<>();
		Class<?> command = this.command;
		do
		{
			List<Field> fields = Arrays.asList(command.getDeclaredFields());
			arguments.add(fields);

			command = command.getSuperclass();
		}
		while (command != Object.class);
		return arguments;
	}

	void addSubcommand(PluginCommand command)
	{
		CommandExecutor executor = command.getExecutor();
		if (executor instanceof CommandInvoker)
		{
			CommandInvoker subcommand = (CommandInvoker) executor;
			if (this.command.isAssignableFrom(subcommand.command))
			{
				this.subcommands.put(command.getName(), subcommand);
				command.getAliases().forEach(alias -> this.subcommands.put(alias, subcommand));
				return;
			}
		}

		throw new IllegalArgumentException(command.getPlugin().getName() + ':' + command.getName());
	}

	void removeSubcommand(PluginCommand command)
	{
		this.subcommands.remove(command.getName(), command.getExecutor());
		command.getAliases().forEach(alias -> this.subcommands.remove(alias, command.getExecutor()));
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] arguments)
	{
		try
		{
			Context context = this.createContext(sender, command, label, arguments);
			this.handleCommand(context, new ArrayList<>(context.getArguments()));
		}
		catch (RuleNotPassedException ruleNotPassed)
		{
			Text.getService().sendMessage(sender, ruleNotPassed.getMessage(),
					MetadataDefaults.RULE_FAILED, ruleNotPassed.getDetail());
		}
		catch (PermissionRequiredException permissionRequired)
		{
			Text.getService().sendMessage(sender, permissionRequired.getMessage(),
					MetadataDefaults.PERMISSION_FAILED, permissionRequired.getDetail());
		}
		catch (ArgumentRequiredException argumentRequired)
		{
			Text.getService().sendMessage(sender, argumentRequired.getMessage(),
					MetadataDefaults.ARGUMENT_FAILED, argumentRequired.getDetail());
		}
		catch (Exception ignore)
		{
			// TODO handle exception
			ignore.printStackTrace();
		}
		return true;
	}

	private void handleCommand(Context context, List<String> remainingArguments)
	{
		this.enforcePermissions(context.getSender());
		this.enforceRules(context);

		CommandInvoker subcommand = this.getSubCommand(remainingArguments);
		if (subcommand != null)
		{
			subcommand.handleCommand(context, remainingArguments);
			return;
		}

		this.createCommand(context, remainingArguments).run(context);
	}

	private Command createCommand(Context context, List<String> remainingArguments)
	{
		Command command = InstanceUtils.createOrNull(this.command);

		// TODO allow arguments to contest each other for the best match
		arguments: for (Field argument : this.arguments)
		{
			Argument argumentDefinition = argument.getAnnotation(Argument.class);

			Permission permission = argument.getAnnotation(Permission.class);
			if (permission != null)
			{
				CommandSender sender = context.getSender();
				for (String node : permission.value())
				{
					if (sender.hasPermission(node))
					{
						continue;
					}

					if (argumentDefinition.optional())
					{
						continue arguments;
					}

					throw new PermissionRequiredException(node);
				}
			}

			Iterator<String> arguments = remainingArguments.iterator();
			while (arguments.hasNext())
			{
				Object pojoArgument = Arguments.resolve(context, arguments.next(), argument.getType());
				if (pojoArgument != null)
				{
					Try.to(() -> argument.set(command, pojoArgument));
					arguments.remove();
					continue arguments;
				}
			}

			if (argumentDefinition.optional())
			{
				continue;
			}

			throw new ArgumentRequiredException(argument.getName());
		}

		return command;
	}

	private CommandInvoker getSubCommand(List<String> remainingArguments)
	{
		if (remainingArguments.isEmpty() || this.subcommands.isEmpty())
		{
			return null;
		}

		Iterator<String> arguments = remainingArguments.iterator();
		while (arguments.hasNext())
		{
			CommandInvoker subcommand = this.subcommands.get(arguments.next());
			if (subcommand == null)
			{
				continue;
			}
			arguments.remove();
			return subcommand;
		}
		return null;
	}

	private void enforcePermissions(CommandSender sender)
	{
		for (String permission : this.permissions)
		{
			if (sender.hasPermission(permission))
			{
				continue;
			}

			Metadata.write(sender, MetadataDefaults.NO_PERMISSION, permission);
			throw new PermissionRequiredException(permission);
		}
	}

	private void enforceRules(Context context)
	{
		for (RuleEnforcement rule : this.rules)
		{
			rule.accept(context);
		}
	}

	private Context createContext(CommandSender sender, org.bukkit.command.Command command, String label, String[] arguments)
	{
		return Context.builder()
				.setSender(sender)
				.setCommand(command)
				.setLabel(label)
				.setEnteredArguments(arguments)
				.setArguments(this.getArguments(arguments))
				.build();
	}

	private List<String> getArguments(String[] enteredArguments)
	{
		String fullMessage = this.getFullMessage(enteredArguments);

		List<String> arguments = new ArrayList<>();
		Matcher matcher = CommandInvoker.ARGUMENT_SPLIT_PATTERN.matcher(fullMessage);
		while (matcher.find())
		{
			String argument = matcher.group(1);
			if (this.isMultiWordArgument(argument))
			{
				argument = this.cleanMultiWordArgument(argument);
			}
			arguments.add(argument);
		}
		return arguments;
	}

	private String getFullMessage(String[] enteredArguments)
	{
		return Arrays.stream(enteredArguments).collect(Collectors.joining(" "));
	}

	private boolean isMultiWordArgument(String argument)
	{
		return argument.startsWith("\"") && argument.endsWith("\"") && argument.contains(" ");
	}

	private String cleanMultiWordArgument(String argument)
	{
		return argument.substring(1, argument.length() - 1);
	}

}