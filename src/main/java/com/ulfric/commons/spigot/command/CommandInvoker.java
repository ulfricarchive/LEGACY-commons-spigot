package com.ulfric.commons.spigot.command;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.ulfric.commons.exception.Try;
import com.ulfric.commons.spigot.command.argument.Argument;
import com.ulfric.commons.spigot.command.argument.ArgumentRequiredException;
import com.ulfric.commons.spigot.command.argument.Arguments;
import com.ulfric.dragoon.construct.InstanceUtils;

final class CommandInvoker implements CommandExecutor {

	private static final Pattern ARGUMENT_SPLIT_PATTERN = Pattern.compile("([^\"]\\S*|\".+?\")\\s*");
	private final Class<? extends Command> command;
	private final List<Permission> permissions;

	CommandInvoker(Class<? extends Command> command)
	{
		this.command = command;
		this.permissions = this.permissions();
	}

	private List<Permission> permissions()
	{
		Permissions permissions = this.command.getAnnotation(Permissions.class);
		if (permissions == null)
		{
			return Collections.emptyList();
		}
		return Arrays.asList(permissions.value());
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] arguments)
	{
		Context context = Context.builder()
				.setSender(sender)
				.setCommand(command)
				.setLabel(label)
				.setEnteredArguments(arguments)
				.setArguments(this.getArguments(arguments))
				.build();

		try
		{
			this.onCommand(context);
		}
		catch (CommandException permissionFailure)
		{
			// TODO tell the player
		}
		catch (Exception exception)
		{
			// TODO log exception, tell sender there was an error
		}

		return true;
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

	private void onCommand(Context context)
	{
		this.enforcePermissions(context);

		Command statefulCommand = this.createStatefulCommand(context);
		statefulCommand.run(context);
	}

	private void enforcePermissions(Context context)
	{
		CommandSender sender = context.getSender();
		for (Permission permission : this.permissions)
		{
			String node = permission.value();
			if (sender.hasPermission(node))
			{
				continue;
			}

			String name = permission.name();
			throw new PermissionRequiredException(name.isEmpty() ? node : name);
		}
	}

	private Command createStatefulCommand(Context context)
	{
		Command command = InstanceUtils.createOrNull(this.command);
		this.injectArguments(command, context);
		return command;
	}

	// TODO cleanup
	// TODO double lookups -- if a missing argument is found, first re-search used arguments for a match before failing
	private void injectArguments(Command command, Context context)
	{
		CommandSender sender = context.getSender();
		List<String> availableArguments = new ArrayList<>(context.getArguments());
		// TODO caching
		arguments: for (Field field : command.getClass().getDeclaredFields())
		{
			Argument argument = field.getAnnotation(Argument.class);
			if (argument == null)
			{
				continue;
			}

			Permissions permissions = field.getAnnotation(Permissions.class);
			if (permissions != null)
			{
				for (Permission permission : permissions.value())
				{
					String node = permission.value();
					if (sender.hasPermission(node))
					{
						continue arguments;
					}
				}
			}

			Class<?> argumentType = field.getType();
			Iterator<String> arguments = availableArguments.iterator();
			while (arguments.hasNext())
			{
				String availableArgument = arguments.next();
				Object resolved = Arguments.resolve(context, availableArgument, argumentType);
				if (resolved == null)
				{
					if (argument.optional())
					{
						continue;
					}

					throw new ArgumentRequiredException(field.getName());
				}
				Try.to(() -> FieldUtils.writeField(field, command, resolved, true));
				break;
			}
		}
	}

}