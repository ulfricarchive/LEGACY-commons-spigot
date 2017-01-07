package com.ulfric.commons.spigot.module;

abstract class SkeletalState implements State {

	public SkeletalState(Module parent)
	{
		this.parent = parent;
	}

	protected final Module parent;

	@Override
	public final boolean isLoaded()
	{
		return this.parent.isLoaded();
	}

	@Override
	public final boolean isEnabled()
	{
		return this.parent.isEnabled();
	}

	@Override
	public final boolean isDisabled()
	{
		return State.super.isDisabled();
	}

	@Override
	public void load() { }

}