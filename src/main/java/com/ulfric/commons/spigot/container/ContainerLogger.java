package com.ulfric.commons.spigot.container;

import java.util.logging.Logger;

import com.ulfric.commons.cdi.scope.Supplied;
import com.ulfric.commons.spigot.plugin.UlfricPlugin;

@Supplied
public final class ContainerLogger extends Logger {

	public ContainerLogger(UlfricPlugin plugin, Logger parent)
	{
		super(plugin.getName(), parent.getResourceBundleName());
	}

}