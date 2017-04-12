package com.ulfric.commons.spigot.task;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import com.ulfric.commons.spigot.plugin.PluginUtils;

public enum Tasks {

	;

	public static void run(Runnable runnable)
	{
		Bukkit.getScheduler().runTask(Tasks.getPlugin(runnable), runnable);
	}

	public static void runAsync(Runnable runnable)
	{
		Bukkit.getScheduler().runTaskAsynchronously(Tasks.getPlugin(runnable), runnable);
	}

	private static Plugin getPlugin(Object object)
	{
		return PluginUtils.getProvidingPlugin(object.getClass())
				.orElseGet(PluginUtils::getMainPlugin);
	}

}