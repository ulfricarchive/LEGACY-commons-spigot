package com.ulfric.commons.spigot.guard;

import com.ulfric.dragoon.container.ChildFeature;
import com.ulfric.dragoon.container.Feature;

public class FlagFeature extends ChildFeature {

	private final Flag<?> flag;

	public FlagFeature(Feature parent, Flag<?> flag)
	{
		super(parent);
		this.flag = flag;
	}

	@Override
	public void onEnable()
	{
		Guard.getService().registerFlag(this.flag);
	}

	@Override
	public void onDisable()
	{
		Guard.getService().unregisterFlag(this.flag);
	}

}