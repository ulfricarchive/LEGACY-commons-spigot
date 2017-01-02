package com.ulfric.spigot.commons.plugin;

import com.ulfric.spigot.commons.module.Module;

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