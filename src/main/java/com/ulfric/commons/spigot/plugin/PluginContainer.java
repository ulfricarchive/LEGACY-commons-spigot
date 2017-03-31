package com.ulfric.commons.spigot.plugin;

import org.bukkit.plugin.Plugin;

import com.ulfric.dragoon.container.Container;
import com.ulfric.dragoon.inject.Inject;

class PluginContainer extends Container {

	@Inject
	private Plugin plugin;

	@Override
	public String getName()
	{
		return this.plugin.getName();
	}

}