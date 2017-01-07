package com.ulfric.commons.spigot.module;

public interface State {

	boolean isLoaded();

	boolean isEnabled();

	default boolean isDisabled()
	{
		return !this.isEnabled();
	}

	void load();

	void enable();

	void disable();

}