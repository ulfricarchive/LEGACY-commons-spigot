package com.ulfric.commons.spigot.command;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang.reflect.FieldUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;

import com.ulfric.commons.exception.Try;
import com.ulfric.commons.naming.Named;
import com.ulfric.commons.spigot.plugin.PluginUtils;
import com.ulfric.dragoon.construct.InstanceUtils;
import com.ulfric.dragoon.container.ChildFeature;
import com.ulfric.dragoon.container.Feature;

import javassist.Modifier;

public final class CommandFeature extends ChildFeature {

	private static final Map<String, PluginCommand> BUKKIT_COMMANDS = CommandFeature.commandMap();
	private static final Map<Class<?>, PluginCommand> CLASS_TO_COMMAND = new IdentityHashMap<>();

	private static Map<String, PluginCommand> commandMap()
	{
		Object commandMapHolder = Try.to(() ->
			FieldUtils.readDeclaredField(Bukkit.getPluginManager(), "commandMap", true));

		@SuppressWarnings("unchecked")
		Map<String, PluginCommand> commandMap = (Map<String, PluginCommand>)
				Try.to(() -> FieldUtils.readDeclaredField(commandMapHolder, "knownCommands", true));

		return commandMap;
	}

	private final Class<? extends Command> command;
	private final CommandInvoker schema;
	private final PluginCommand bukkitCommand;

	public CommandFeature(Feature parent, Command command)
	{
		super(parent);
		this.command = command.getClass();
		this.schema = this.schema();
		this.bukkitCommand = this.getBukkitCommand();
	}

	@Override
	public void onEnable()
	{
		this.registerCommand();
	}

	private void registerCommand()
	{
		PluginCommand command = this.bukkitCommand;

		CommandInvoker parent = this.getParentCommand();
		if (parent != null)
		{
			parent.addSubcommand(command);
			return;
		}

		CommandFeature.BUKKIT_COMMANDS.put(command.getName(), command);
		command.getAliases().forEach(alias -> CommandFeature.BUKKIT_COMMANDS.put(alias, command));
	}

	@Override
	public void onDisable()
	{
		this.unregisterCommand();
	}

	private void unregisterCommand()
	{
		PluginCommand command = this.bukkitCommand;

		CommandInvoker parent = this.getParentCommand();
		if (parent != null)
		{
			parent.removeSubcommand(command);
			return;
		}

		CommandFeature.BUKKIT_COMMANDS.remove(command.getName(), command);
		command.getAliases().forEach(alias -> CommandFeature.BUKKIT_COMMANDS.remove(alias, command));
	}

	private CommandInvoker getParentCommand()
	{
		Class<?> superCommand = this.getSuperCommand(this.command);

		if (!Command.class.isAssignableFrom(superCommand))
		{
			return null;
		}

		PluginCommand parent = CommandFeature.CLASS_TO_COMMAND.get(superCommand);

		if (parent == null)
		{
			return null;
		}

		CommandExecutor parentExecutor = parent.getExecutor();

		if (!(parentExecutor instanceof CommandInvoker))
		{
			return null;
		}

		return (CommandInvoker) parentExecutor;
	}

	private Class<?> getSuperCommand(Class<?> type)
	{
		Class<?> superCommand = type.getSuperclass();

		if (!Command.class.isAssignableFrom(superCommand))
		{
			return null;
		}

		if (Modifier.isAbstract(superCommand.getModifiers()))
		{
			return this.getSuperCommand(superCommand);
		}

		return superCommand;
	}

	private CommandInvoker schema()
	{
		return new CommandInvoker(this.command);
	}

	private PluginCommand getBukkitCommand()
	{
		return CommandFeature.CLASS_TO_COMMAND.computeIfAbsent(this.command, this::createBukkitCommand);
	}

	private PluginCommand createBukkitCommand(Class<?> command)
	{
		String name = Named.tryToGetNameFromAnnotation(this.command)
				.orElse(command.getClass().getSimpleName().replace("Command", ""));
		Plugin plugin = PluginUtils.getProvidingPluginOrFail(this.command);
		return this.createPluginCommand(name, plugin);
	}

	private PluginCommand createPluginCommand(String name, Plugin plugin)
	{
		Constructor<PluginCommand> constructor =
				Try.to(() -> PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class));
		constructor.setAccessible(true);
		org.bukkit.command.PluginCommand command = Try.to(() -> constructor.newInstance(name, plugin));
		this.setupCommand(command);
		return command;
	}

	private void setupCommand(org.bukkit.command.PluginCommand command)
	{
		command.setExecutor(this.schema);
		command.setAliases(this.getCommandAliases());
		String name = this.getCommandName();
		command.setLabel(name);
		command.setName(name);
	}

	private List<String> getCommandAliases()
	{
		Alias alias = this.command.getAnnotation(Alias.class);
		return alias == null ? Collections.emptyList() : Arrays.asList(alias.value());
	}

	private String getCommandName()
	{
		Command command = InstanceUtils.createOrNull(this.command);
		Objects.requireNonNull(command, this.command + " must have a no-args constructor");
		return command.getName();
	}

}