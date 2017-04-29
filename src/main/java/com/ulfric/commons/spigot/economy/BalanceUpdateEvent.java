package com.ulfric.commons.spigot.economy;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BalanceUpdateEvent extends Event {

	private static final HandlerList HANDLERS = new HandlerList();

	private final BankAccount account;
	private final CurrencyAmount newBalance;

	public BalanceUpdateEvent(BankAccount account, CurrencyAmount newBalance)
	{
		this.account = account;
		this.newBalance = newBalance;
	}

	@Override
	public HandlerList getHandlers()
	{
		return BalanceUpdateEvent.HANDLERS;
	}

	public BankAccount getAccount()
	{
		return this.account;
	}

	public CurrencyAmount getNewBalance()
	{
		return this.newBalance;
	}

}