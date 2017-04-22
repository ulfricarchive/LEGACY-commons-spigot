package com.ulfric.commons.spigot.economy;

public class BalanceDeductionResult extends BalanceChangeResult {

	private final long amountMissing;

	public BalanceDeductionResult()
	{
		this(true, 0);
	}

	public BalanceDeductionResult(long amountMissing)
	{
		this(false, amountMissing);
	}

	private BalanceDeductionResult(boolean success, long amountMissing)
	{
		super(success);
		this.amountMissing = amountMissing;
	}

	public final long getAmountMissing()
	{
		return this.amountMissing;
	}

}