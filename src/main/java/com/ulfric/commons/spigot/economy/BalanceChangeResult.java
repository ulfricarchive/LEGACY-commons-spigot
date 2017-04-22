package com.ulfric.commons.spigot.economy;

import com.ulfric.commons.bean.Bean;

public class BalanceChangeResult extends Bean {

	public static final BalanceChangeResult SUCCESS = new BalanceChangeResult(true);
	public static final BalanceChangeResult FAILURE = new BalanceChangeResult(false);

	private final boolean success;

	protected BalanceChangeResult(boolean success)
	{
		this.success = success;
	}

	public final boolean isSuccess()
	{
		return this.success;
	}

}