package com.ulfric.commons.spigot.economy;

import java.util.Objects;
import java.util.regex.Pattern;

import com.ulfric.commons.bean.Bean;

public final class CurrencyAmount extends Bean {

	private static final Pattern NUMBER = Pattern.compile("[^0-9]+");

	public static CurrencyAmount parseCurrencyAmount(String text)
	{
		Currency currency = CurrencyAmount.matchCurrency(text);

		if (currency == null)
		{
			return null;
		}

		// TODO utility to do this faster
		String parsable = CurrencyAmount.NUMBER.matcher(text).replaceAll("");

		if (parsable.isEmpty())
		{
			return null;
		}

		long amount = Long.valueOf(parsable);

		if (amount <= 0)
		{
			return null;
		}

		return CurrencyAmount.valueOf(currency, amount);
	}

	private static Currency matchCurrency(String text)
	{
		for (Currency currency : Economy.getService().getCurrencies())
		{
			if (currency.getPattern().matcher(text).matches())
			{
				return currency;
			}
		}

		return null;
	}

	public static CurrencyAmount valueOf(Currency currency, long amount)
	{
		Objects.requireNonNull(currency, "currency");
		if (amount < 0)
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