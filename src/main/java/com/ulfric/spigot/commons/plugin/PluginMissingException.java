package com.ulfric.spigot.commons.plugin;

@SuppressWarnings("serial")
public class PluginMissingException extends RuntimeException {

	public PluginMissingException(Class<?> lookupClass)
	{
		super("Could not find plugin from class: " + lookupClass);
	}

}