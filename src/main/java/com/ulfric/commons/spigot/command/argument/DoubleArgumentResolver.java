package com.ulfric.commons.spigot.command.argument;

import com.ulfric.commons.arithmetic.NumericUtils;
import com.ulfric.commons.spigot.command.Context;

public class DoubleArgumentResolver implements ArgumentResolver<Double> {
	
	@Override
	public Double apply(Context context, String argument)
	{
		return NumericUtils.isDouble(argument) ? Double.valueOf(argument) : null;
	}
	
}
