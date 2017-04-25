package com.ulfric.commons.spigot.command.argument;

import com.ulfric.commons.spigot.command.Context;

public class LongArgumentResolver implements ArgumentResolver<Long> {
	
	@Override
	public Long apply(Context context, String argument)
	{
		return Long.getLong(argument);
	}
	
}
