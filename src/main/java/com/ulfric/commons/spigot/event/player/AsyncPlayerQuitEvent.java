package com.ulfric.commons.spigot.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class AsyncPlayerQuitEvent extends PlayerEvent {

	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList()
	{
		return AsyncPlayerQuitEvent.HANDLERS;
	}

	public AsyncPlayerQuitEvent(Player who)
	{
		super(who);
	}

	@Override
	public HandlerList getHandlers()
	{
		return AsyncPlayerQuitEvent.HANDLERS;
	}

}