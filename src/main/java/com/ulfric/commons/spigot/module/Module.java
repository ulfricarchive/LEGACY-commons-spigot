package com.ulfric.commons.spigot.module;

import com.ulfric.commons.naming.Named;

public class Module implements Named {

	@Load
	public void onLoad() { }

	@Enable
	public void onEnable() { }

	@Disable
	public void onDisable() { }

}