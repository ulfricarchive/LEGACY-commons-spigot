package com.ulfric.commons.spigot.guard;

import org.bukkit.event.Listener;

import com.ulfric.commons.naming.Named;

public interface Flag<T> extends Named, Listener {

	T getDefaultData();

	T parseData(Object parse);

}