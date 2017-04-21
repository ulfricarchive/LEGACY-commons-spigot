package com.ulfric.commons.spigot.economy;

import java.util.UUID;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.service.ServiceUtils;
import com.ulfric.commons.version.Version;

@Name("Economy")
@Version(1)
public interface Economy extends Service {

	static Economy getService()
	{
		return ServiceUtils.getService(Economy.class);
	}

	Currency getCurrency(String name);

	CurrencyAmount getCurrencyAmount(String text);

	long getBalance(UUID uniqueId, Currency currency);

	void setBalance(UUID uniqueId, CurrencyAmount amount);

}