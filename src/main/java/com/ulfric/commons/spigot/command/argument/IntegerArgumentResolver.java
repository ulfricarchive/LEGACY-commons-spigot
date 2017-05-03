package com.ulfric.commons.spigot.command.argument;

import com.ulfric.commons.spigot.command.Context;

public class IntegerArgumentResolver implements ArgumentResolver<Integer> {
	
	@Override
	public Integer apply(Context context, String argument)
	{
		return Integer.getInteger(argument);
	}
	
}
