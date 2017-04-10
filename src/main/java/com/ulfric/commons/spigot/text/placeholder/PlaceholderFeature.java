package com.ulfric.commons.spigot.text.placeholder;

import com.ulfric.commons.spigot.text.Text;
import com.ulfric.dragoon.container.ChildFeature;
import com.ulfric.dragoon.container.Feature;

public final class PlaceholderFeature<T extends Placeholder> extends ChildFeature {

	private final T implementation;
	private Text service;

	public PlaceholderFeature(Feature parent, T implementation)
	{
		super(parent);
		this.implementation = implementation;
	}

	@Override
	public void onEnable()
	{
		this.getService().registerPlaceholder(this.implementation);
	}

	@Override
	public void onDisable()
	{
		this.getService().unregisterPlaceholder(this.implementation);
	}

	private Text getService()
	{
		if (this.service != null)
		{
			return this.service;
		}

		return this.service = Text.getService();
	}

}