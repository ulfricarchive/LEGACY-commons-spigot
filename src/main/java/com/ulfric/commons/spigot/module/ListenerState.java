package com.ulfric.commons.spigot.module;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

final class ListenerState extends SkeletalState {

	public ListenerState(Module parent, Listener listener)
	{
		super(parent);
		this.listener = listener;
	}

	private final Listener listener;

	@Override
	public void enable()
	{
		Bukkit.getPluginManager().registerEvents(this.listener, this.parent.getOwningPlugin());
	}

	@Override
	public void disable()
	{
		HandlerList.unregisterAll(this.listener);
	}

}