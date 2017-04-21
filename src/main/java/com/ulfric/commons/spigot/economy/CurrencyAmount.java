package com.ulfric.commons.spigot.economy;

import java.util.Objects;

import com.ulfric.commons.bean.Bean;

public final class CurrencyAmount extends Bean {

	public static CurrencyAmount valueOf(Currency currency, long amount)
	{
		Objects.requireNonNull(currency, "currency");
		if (amount <= 0)
		{
			throw new IllegalArgumentException("Non-positive amount: " + amount);
		}

		return new CurrencyAmount(currency, amount);
	}

	private final Currency currency;
	private final long amount;

	private CurrencyAmount(Currency currency, long amount)
	{
		this.currency = currency;
		this.amount = amount;
	}

	public Currency getCurrency()
	{
		return this.currency;
	}

	public long getAmount()
	{
		return this.amount;
	}

}