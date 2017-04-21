package com.ulfric.commons.spigot.event.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveBlockEvent extends PlayerMoveEvent {

	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList()
	{
		return PlayerMoveBlockEvent.HANDLERS;
	}

	public PlayerMoveBlockEvent(Player player, Location from, Location to)
	{
		super(player, from, to);
	}

	@Override
	public HandlerList getHandlers()
	{
		return PlayerMoveBlockEvent.HANDLERS;
	}

}