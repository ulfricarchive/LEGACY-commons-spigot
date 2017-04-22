package com.ulfric.commons.spigot.task;

final class TaskRunnable implements Runnable {

	private final Task task;
	private final Runnable delegate;

	TaskRunnable(Task task, Runnable delegate)
	{
		this.task = task;
		this.delegate = delegate;
	}

	@Override
	public void run()
	{
		this.task.taskQueued();

		this.delegate.run();

		this.task.taskFinished();
	}

}
