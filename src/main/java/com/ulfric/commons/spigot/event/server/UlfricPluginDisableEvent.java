package com.ulfric.commons.spigot.event.server;

import org.bukkit.event.HandlerList;
import org.bukkit.event.server.PluginDisableEvent;

import com.ulfric.commons.spigot.plugin.UlfricPlugin;

public class UlfricPluginDisableEvent extends PluginDisableEvent {

	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList()
	{
		return UlfricPluginDisableEvent.HANDLERS;
	}

	public UlfricPluginDisableEvent(UlfricPlugin plugin)
	{
		super(plugin);
	}

	@Override
	public UlfricPlugin getPlugin()
	{
		return (UlfricPlugin) super.getPlugin();
	}

	@Override
	public HandlerList getHandlers()
	{
		return UlfricPluginDisableEvent.HANDLERS;
	}

}