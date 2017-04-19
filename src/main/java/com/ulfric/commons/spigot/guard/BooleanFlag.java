package com.ulfric.commons.spigot.guard;

public abstract class BooleanFlag extends SkeletalFlag<Boolean> {

	public BooleanFlag(Boolean defaultValue)
	{
		super(defaultValue);
	}

	@Override
	public Boolean parseData(Object parse)
	{
		if (parse instanceof Boolean)
		{
			return (Boolean) parse;
		}

		return Boolean.getBoolean(String.valueOf(parse));
	}

}