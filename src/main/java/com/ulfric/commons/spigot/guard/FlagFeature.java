package com.ulfric.commons.spigot.guard;

import com.ulfric.dragoon.container.ChildFeature;
import com.ulfric.dragoon.container.Feature;

public class FlagFeature extends ChildFeature {

	private final Flag<?> flag;
	private final Guard service;

	public FlagFeature(Feature parent, Flag<?> flag)
	{
		super(parent);
		this.flag = flag;
		this.service = Guard.getService();
	}

	@Override
	public void onEnable()
	{
		this.service.registerFlag(this.flag);
	}

	@Override
	public void onDisable()
	{
		this.service.unregisterFlag(this.flag);
	}

}