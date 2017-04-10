package com.ulfric.commons.spigot.container;

import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.bukkit.plugin.Plugin;

import com.ulfric.dragoon.scope.Supplied;

@Supplied
public final class ContainerLogger extends Logger {

	private final Plugin plugin;

	public ContainerLogger(Plugin plugin)
	{
		super(plugin.getName(), plugin.getLogger().getResourceBundleName());

		this.plugin = plugin;
	}

	@Override
	public void log(LogRecord record)
	{
		plugin.getLogger().log(record);
	}

}