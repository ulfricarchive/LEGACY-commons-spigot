package com.ulfric.commons.spigot.command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.ulfric.commons.bean.Bean;

public final class Context extends Bean {

	static Builder builder()
	{
		return new Builder();
	}

	static final class Builder extends Bean implements org.apache.commons.lang3.builder.Builder<Context>
	{
		private CommandSender sender;
		private Command command;
		private String label;
		private String[] enteredArguments;
		private List<String> arguments;

		Builder() { }

		@Override
		public Context build()
		{
			return new Context(this.sender, this.command, this.label, this.enteredArguments, this.arguments);
		}

		public Builder setSender(CommandSender sender)
		{
			this.sender = sender;
			return this;
		}

		public Builder setCommand(Command command)
		{
			this.command = command;
			return this;
		}

		public Builder setLabel(String label)
		{
			this.label = label;
			return this;
		}

		public Builder setEnteredArguments(String[] enteredArguments)
		{
			this.enteredArguments = enteredArguments;
			return this;
		}

		public Builder setArguments(List<String> arguments)
		{
			this.arguments = arguments;
			return this;
		}
	}

	private final CommandSender sender;
	private final Command command;
	private final String label;
	private final String[] enteredArguments;
	private final List<String> arguments;

	Context(CommandSender sender, Command command, String label, String[] enteredArguments, List<String> arguments)
	{
		this.sender = sender;
		this.command = command;
		this.label = label;
		this.enteredArguments = enteredArguments;
		this.arguments = arguments;
	}

	public CommandSender getSender()
	{
		return this.sender;
	}

	public Command getCommand()
	{
		return this.command;
	}

	public String getLabel()
	{
		return this.label;
	}

	public String[] getEnteredArguments()
	{
		return this.enteredArguments;
	}

	public List<String> getArguments()
	{
		return this.arguments;
	}

}