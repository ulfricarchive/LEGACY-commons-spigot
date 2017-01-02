package com.ulfric.spigot.commons.logging;

import com.ulfric.commons.cdi.construct.scope.Shared;
import com.ulfric.commons.logging.Logger;

@Shared
public class ConsoleLogger implements Logger {

	private final java.util.logging.Logger logger = LoggerUtils.getGlobalLogger();

	@Override
	public void info(String message)
	{
		this.logger.info(message);
	}

	@Override
	public void error(String message)
	{
		this.logger.severe(message);
	}

}