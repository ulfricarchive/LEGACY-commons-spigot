package com.ulfric.commons.spigot.module;

import com.ulfric.commons.naming.Named;

public class Module implements Named {

	private boolean loaded;
	private boolean enabled;

	public boolean isLoaded()
	{
		return this.loaded;
	}

	public boolean isEnabled()
	{
		return this.enabled;
	}

	public final void load()
	{
		this.verifyIsNotLoaded();
		this.onLoad();
		this.loaded = true;
	}

	public final void enable()
	{
		this.verifyIsNotEnabled();

		if (!this.isLoaded())
		{
			this.load();
			this.enable();
			return;
		}

		this.onEnable();
		this.enabled = true;
	}

	public void disable()
	{
		this.verifyIsNotDisabled();
		this.onDisable();
		this.enabled = false;
	}

	private void verifyIsNotLoaded()
	{
		if (this.isLoaded())
		{
			throw new IllegalStateException("The module is already loaded!");
		}
	}

	private void verifyIsNotEnabled()
	{
		if (this.isEnabled())
		{
			throw new IllegalStateException("The module is already enabled!");
		}
	}

	private void verifyIsNotDisabled()
	{
		if (!this.isEnabled())
		{
			throw new IllegalStateException("The module is already disabled!");
		}
	}

	@Load
	public void onLoad() { }

	@Enable
	public void onEnable() { }

	@Disable
	public void onDisable() { }

}