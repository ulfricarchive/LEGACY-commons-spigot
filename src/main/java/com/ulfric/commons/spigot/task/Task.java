package com.ulfric.commons.spigot.task;

import org.bukkit.Bukkit;

public final class Task {

	private int task = -1;
	private boolean queued = false;
	private boolean finished = false;

	public void cancel()
	{
		Bukkit.getScheduler().cancelTask(this.task);
	}

	public boolean isComplete()
	{
		return this.finished;
	}

	public boolean isQueued()
	{
		return this.queued && !this.finished;
	}

	void setTask(int task)
	{
		this.task = task;
	}

	void taskQueued()
	{
		this.queued = true;
	}

	void taskFinished()
	{
		this.finished = true;
	}

}