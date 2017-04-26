package com.ulfric.commons.spigot.naming;

import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.item.MaterialType;
import com.ulfric.commons.spigot.service.ServiceUtils;

public interface FriendlyName extends Service {

	static FriendlyName getService()
	{
		return ServiceUtils.getService(FriendlyName.class);
	}

	MaterialType materialTypeByName(String name);

}