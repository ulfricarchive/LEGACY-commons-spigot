package com.ulfric.commons.spigot.economy;

import com.ulfric.commons.identity.Unique;

public interface BankAccount extends Unique {

	long getBalance(Currency currency);

	void setBalance(CurrencyAmount amount);

	BalanceDeductionResult deduct(CurrencyAmount amount);

	BalanceChangeResult deposit(CurrencyAmount amount);

}