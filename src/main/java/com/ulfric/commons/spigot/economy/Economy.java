package com.ulfric.commons.spigot.economy;

import java.util.List;
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

	List<Currency> getCurrencies();

	BankAccount getAccount(UUID uniqueId);

}