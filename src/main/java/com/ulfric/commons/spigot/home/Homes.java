package com.ulfric.commons.spigot.home;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.service.ServiceUtils;
import com.ulfric.commons.version.Version;

import java.util.UUID;

@Name("homes")
@Version(1)
public interface Homes extends Service {
	
	public static Homes getHomes()
	{
		return ServiceUtils.getService(Homes.class);
	}
	
	HomeAccount getAccount(UUID uniqueId);
	
}
