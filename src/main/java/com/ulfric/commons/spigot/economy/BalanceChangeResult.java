package com.ulfric.commons.spigot.economy;

import com.ulfric.commons.bean.Bean;

public class BalanceChangeResult extends Bean {

	private final boolean success;

	public BalanceChangeResult(boolean success)
	{
		this.success = success;
	}

	public final boolean isSuccess()
	{
		return this.success;
	}

}