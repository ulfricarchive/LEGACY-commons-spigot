package com.ulfric.commons.spigot.container;

import java.util.logging.Logger;

import org.bukkit.plugin.Plugin;

import com.ulfric.commons.cdi.scope.Supplied;

@Supplied
public final class ContainerLogger extends Logger {

	public ContainerLogger(Plugin plugin, Logger parent)
	{
		super(plugin.getName(), parent.getResourceBundleName());
	}

}