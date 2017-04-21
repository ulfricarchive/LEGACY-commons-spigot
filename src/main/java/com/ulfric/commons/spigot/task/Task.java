package com.ulfric.commons.spigot.task;

import org.bukkit.Bukkit;

public final class Task {

	private final int task;

	Task(int task)
	{
		this.task = task;
	}

	public void cancel()
	{
		Bukkit.getScheduler().cancelTask(this.task);
	}

	public boolean isComplete()
	{
		return !Bukkit.getScheduler().isCurrentlyRunning(this.task) && !this.isQueued();
	}

	public boolean isQueued()
	{
		return Bukkit.getScheduler().isQueued(this.task);
	}

}