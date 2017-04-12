package com.ulfric.commons.spigot.event;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

public enum Events {

	;

	public static <T extends Event> T fire(T event)
	{
		Bukkit.getPluginManager().callEvent(event);
		return event;
	}

}