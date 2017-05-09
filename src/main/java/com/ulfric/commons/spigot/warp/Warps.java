package com.ulfric.commons.spigot.warp;

import java.util.UUID;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.service.ServiceUtils;
import com.ulfric.commons.version.Version;

@Name("warps")
@Version(1)
public interface Warps extends Service {

	static Warps getService()
	{
		return ServiceUtils.getService(Warps.class);
	}

	WarpAccount getGlobalAccount();

	WarpAccount getAccount(UUID uniqueId);

}