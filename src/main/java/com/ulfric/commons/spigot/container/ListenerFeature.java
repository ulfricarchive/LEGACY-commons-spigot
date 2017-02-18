package com.ulfric.commons.spigot.container;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import com.ulfric.commons.cdi.container.ChildFeature;
import com.ulfric.commons.cdi.container.Feature;
import com.ulfric.commons.spigot.plugin.PluginUtils;

public final class ListenerFeature extends ChildFeature {

	public ListenerFeature(Feature parent, Listener listener)
	{
		super(parent);
		this.listener = listener;
	}

	private final Listener listener;

	@Override
	public void onEnable()
	{
		Plugin owningPlugin = PluginUtils.getProvidingPlugin(this.parent.getClass());
		Bukkit.getPluginManager().registerEvents(this.listener, owningPlugin);
	}

	@Override
	public void onDisable()
	{
		HandlerList.unregisterAll(this.listener);
	}

}