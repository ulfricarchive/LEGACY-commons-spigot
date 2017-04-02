package com.ulfric.commons.spigot.command;

import com.ulfric.commons.spigot.locale.LocaleDefaults;

public class PermissionRequiredException extends CommandException {

	public PermissionRequiredException(String node)
	{
		super(LocaleDefaults.PERMISSION_MISSING, node);
	}

}