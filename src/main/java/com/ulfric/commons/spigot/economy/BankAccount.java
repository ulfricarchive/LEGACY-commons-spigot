package com.ulfric.commons.spigot.economy;

import com.ulfric.commons.identity.Unique;

public interface BankAccount extends Unique {

	long getBalance();

	void setBalance(long balance);

	BalanceDeductionResult deduct(long amount);

	BalanceChangeResult deposit(long amount);

}