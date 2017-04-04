package com.ulfric.commons.spigot.command.argument;

import com.ulfric.commons.spigot.command.CommandException;
import com.ulfric.commons.spigot.text.LocaleDefaults;

public class ArgumentRequiredException extends CommandException {

	public ArgumentRequiredException(String argument)
	{
		super(LocaleDefaults.ARGUMENT_REQUIRED, argument);
	}

}