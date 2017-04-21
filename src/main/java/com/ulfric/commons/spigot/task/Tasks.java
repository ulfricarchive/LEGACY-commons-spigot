package com.ulfric.commons.spigot.task;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import com.ulfric.commons.spigot.plugin.PluginUtils;

public enum Tasks {

	;

	public static long secondsToTicks(int seconds)
	{
		return seconds * 20;
	}

	public static Task run(Runnable runnable)
	{
		BukkitTask task = Tasks.getScheduler().runTask(Tasks.getPlugin(runnable), runnable);
		return new Task(task.getTaskId());
	}

	public static Task runAsync(Runnable runnable)
	{
		BukkitTask task = Tasks.getScheduler().runTaskAsynchronously(Tasks.getPlugin(runnable), runnable);
		return new Task(task.getTaskId());
	}

	public static Task runLater(Runnable runnable, long ticks)
	{
		BukkitTask task = Tasks.getScheduler().runTaskLater(Tasks.getPlugin(runnable), runnable, ticks);
		return new Task(task.getTaskId());
	}

	private static BukkitScheduler getScheduler()
	{
		return Bukkit.getScheduler();
	}

	private static Plugin getPlugin(Object object)
	{
		return PluginUtils.getProvidingPlugin(object.getClass())
				.orElseGet(PluginUtils::getMainPlugin);
	}

}