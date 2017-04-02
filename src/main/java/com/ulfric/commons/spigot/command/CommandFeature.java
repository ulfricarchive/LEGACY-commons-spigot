package com.ulfric.commons.spigot.command;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang.reflect.FieldUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;

import com.ulfric.commons.exception.Try;
import com.ulfric.commons.naming.Named;
import com.ulfric.commons.spigot.plugin.PluginUtils;
import com.ulfric.dragoon.construct.InstanceUtils;
import com.ulfric.dragoon.container.ChildFeature;
import com.ulfric.dragoon.container.Feature;

public final class CommandFeature extends ChildFeature {

	private final Class<? extends Command> command;
	private final CommandInvoker schema;
	private final Map<String, org.bukkit.command.Command> commandMap;
	private final org.bukkit.command.Command bukkitCommand;

	public CommandFeature(Feature parent, Command command)
	{
		super(parent);
		this.command = command.getClass();
		this.schema = this.schema();
		this.commandMap = this.commandMap();
		this.bukkitCommand = this.bukkitCommand();
	}

	private CommandInvoker schema()
	{
		return new CommandInvoker(this.command);
	}

	private Map<String, org.bukkit.command.Command> commandMap()
	{
		Object commandMapHolder = Try.to(() ->
			FieldUtils.readDeclaredField(Bukkit.getPluginManager(), "commandMap", true));

		@SuppressWarnings("unchecked")
		Map<String, org.bukkit.command.Command> commandMap = (Map<String, org.bukkit.command.Command>)
				Try.to(() -> FieldUtils.readDeclaredField(commandMapHolder, "knownCommands", true));

		return commandMap;
	}

	private org.bukkit.command.Command bukkitCommand()
	{
		String name = Named.tryToGetNameFromAnnotation(this.command)
				.orElse(command.getClass().getSimpleName().replace("Command", ""));
		Plugin plugin = PluginUtils.getProvidingPlugin(this.command);
		return this.createCommand(name, plugin);
	}

	private org.bukkit.command.Command createCommand(String name, Plugin plugin)
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

	@Override
	public void onEnable()
	{
		org.bukkit.command.Command command = this.bukkitCommand;
		this.commandMap.put(command.getName(), command);
		command.getAliases().forEach(alias -> this.commandMap.put(alias, command));
	}

	@Override
	public void onDisable()
	{
		org.bukkit.command.Command command = this.bukkitCommand;
		this.commandMap.remove(command.getName(), command);
		command.getAliases().forEach(alias -> this.commandMap.remove(alias, command));
	}

}