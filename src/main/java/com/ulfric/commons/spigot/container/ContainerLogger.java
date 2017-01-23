package com.ulfric.commons.spigot.container;

import java.util.logging.Logger;

import com.ulfric.commons.cdi.scope.Supplied;

@Supplied
public final class ContainerLogger extends Logger {

	public ContainerLogger(Logger parent)
	{
		super(parent.getName(), parent.getResourceBundleName());
	}

}