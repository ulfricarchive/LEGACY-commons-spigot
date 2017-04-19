package com.ulfric.commons.spigot.guard;

public abstract class SkeletalFlag<T> implements Flag<T> {

	private final T defaultValue;

	public SkeletalFlag(T defaultValue)
	{
		this.defaultValue = defaultValue;
	}

	@Override
	public T getDefaultData()
	{
		return this.defaultValue;
	}

}