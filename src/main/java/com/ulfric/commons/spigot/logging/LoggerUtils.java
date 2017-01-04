package com.ulfric.commons.spigot.logging;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Server;

public enum LoggerUtils {

	;

	public static Logger getGlobalLogger()
	{
		Server server = Bukkit.getServer();

		if (server == null)
		{
			return Logger.getGlobal();
		}

		return server.getLogger();
	}

}