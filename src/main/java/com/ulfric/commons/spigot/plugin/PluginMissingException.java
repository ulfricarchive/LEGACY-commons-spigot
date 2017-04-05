package com.ulfric.commons.spigot.plugin;

public class PluginMissingException extends RuntimeException {

	public PluginMissingException(Class<?> lookupClass)
	{
		super("Could not find plugin from class: " + lookupClass);
	}

}