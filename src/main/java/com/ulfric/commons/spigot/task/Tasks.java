package com.ulfric.commons.spigot.task;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import com.ulfric.commons.func.TriFunction;
import com.ulfric.commons.spigot.plugin.PluginUtils;

public enum Tasks {

	;

	public static Task runSync(Runnable runnable)
	{
		return Tasks.runLater(runnable, 0L);
	}

	public static Task runAsync(Runnable runnable)
	{
		return Tasks.scheduleTask(runnable, 0L, Tasks.getScheduler()::runTaskLaterAsynchronously);
	}

	public static Task runLater(Runnable runnable, long ticks)
	{
		return Tasks.scheduleTask(runnable, ticks, Tasks.getScheduler()::runTaskLater);
	}

	private static Task scheduleTask(Runnable runnable, long ticks,
	                                       TriFunction<Plugin, Runnable, Long, BukkitTask> schedulerMethod)
	{
		Task task = new Task();
		TaskRunnable taskRunnable = new TaskRunnable(task, runnable);

		BukkitTask bukkitTask = schedulerMethod.apply(Tasks.getPlugin(runnable), taskRunnable, ticks);

		task.setTask(bukkitTask.getTaskId());

		return task;
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