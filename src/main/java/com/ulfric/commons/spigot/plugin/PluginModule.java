package com.ulfric.commons.spigot.plugin;

import com.ulfric.commons.spigot.module.Module;

final class PluginModule extends Module {

	private String name;

	@Override
	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

}