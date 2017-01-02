package com.ulfric.spigot.commons.logging;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Server;

import com.ulfric.commons.api.UtilInstantiationException;

public class LoggerUtils {

	public static Logger getGlobalLogger()
	{
		Server server = Bukkit.getServer();

		if (server == null)
		{
			return Logger.getGlobal();
		}

		return server.getLogger();
	}

	private LoggerUtils()
	{
		throw new UtilInstantiationException();
	}

}