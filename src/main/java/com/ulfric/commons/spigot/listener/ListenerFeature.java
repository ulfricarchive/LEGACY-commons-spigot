package com.ulfric.commons.spigot.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import com.ulfric.commons.spigot.plugin.PluginUtils;
import com.ulfric.dragoon.container.ChildFeature;
import com.ulfric.dragoon.container.Feature;

public final class ListenerFeature extends ChildFeature {

	private final Listener listener;

	public ListenerFeature(Feature parent, Listener listener)
	{
		super(parent);
		this.listener = listener;
	}

	@Override
	public void onEnable()
	{
		Plugin owningPlugin = PluginUtils.getProvidingPluginOrFail(this.parent.getClass());
		Bukkit.getPluginManager().registerEvents(this.listener, owningPlugin);
	}

	@Override
	public void onDisable()
	{
		HandlerList.unregisterAll(this.listener);
	}

}