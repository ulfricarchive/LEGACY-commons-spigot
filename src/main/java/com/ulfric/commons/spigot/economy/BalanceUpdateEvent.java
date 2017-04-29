package com.ulfric.commons.spigot.economy;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BalanceUpdateEvent extends Event implements Cancellable {

	private static final HandlerList HANDLERS = new HandlerList();

	private final BankAccount account;
	private final CurrencyAmount newBalance;
	private boolean cancel;

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

	@Override
	public boolean isCancelled()
	{
		return this.cancel;
	}

	@Override
	public void setCancelled(boolean cancel)
	{
		this.cancel = cancel;
	}

}