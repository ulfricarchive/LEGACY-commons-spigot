package com.ulfric.commons.spigot.command.argument;

import com.ulfric.commons.spigot.command.Context;
import com.ulfric.commons.spigot.economy.CurrencyAmount;

public class CurrencyAmountArgumentResolver implements ArgumentResolver<CurrencyAmount> {

	@Override
	public CurrencyAmount apply(Context context, String argument)
	{
		return CurrencyAmount.parseCurrencyAmount(argument);
	}

}