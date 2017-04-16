package com.ulfric.commons.spigot.command.argument;

import com.ulfric.commons.spigot.command.Context;

public class StringArgumentResolver implements ArgumentResolver<String> {

	@Override
	public String apply(Context context, String argument)
	{
		return argument;
	}

}