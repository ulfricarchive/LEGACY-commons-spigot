package com.ulfric.commons.spigot.event.server;

import org.bukkit.event.HandlerList;
import org.bukkit.event.server.ServerEvent;

public class ServerShutdownEvent extends ServerEvent {

	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList()
	{
		return ServerShutdownEvent.HANDLERS;
	}

	@Override
	public HandlerList getHandlers()
	{
		return ServerShutdownEvent.HANDLERS;
	}

}