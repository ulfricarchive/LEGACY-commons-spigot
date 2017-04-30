package com.ulfric.commons.spigot.command.argument;

import com.ulfric.dragoon.container.ChildFeature;
import com.ulfric.dragoon.container.Feature;

public class ArgumentResolverFeature extends ChildFeature {

	private final ArgumentResolver<?> resolver;

	public ArgumentResolverFeature(Feature parent, ArgumentResolver<?> resolver)
	{
		super(parent);
		this.resolver = resolver;
	}

	@Override
	public void onEnable()
	{
		Arguments.registerResolver(this.resolver);
	}

}