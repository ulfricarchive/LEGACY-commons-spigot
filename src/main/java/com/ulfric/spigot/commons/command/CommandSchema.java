package com.ulfric.spigot.commons.command;

import java.util.Arrays;
import java.util.Objects;

import org.bukkit.command.CommandSender;

final class CommandSchema {

	public static CommandSchema newInstance(Command command)
	{
		Objects.requireNonNull(command);

		return new CommandSchema(command);
	}

	private CommandSchema(Command command)
	{
		this.command = command;
	}

	private final Command command;

	public Object buildContext(CommandSender sender, String[] args)
	{
		// TODO temp
		return Arrays.asList(this.command, sender, args);
	}

}