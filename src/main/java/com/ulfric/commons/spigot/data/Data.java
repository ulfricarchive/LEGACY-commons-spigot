package com.ulfric.commons.spigot.data;

import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.service.ServiceUtils;
import com.ulfric.dragoon.container.Container;

public interface Data extends Service {

	public static DataStore getDataStore(Container container)
	{
		return Data.getService().getDataStore(container.getName());
	}

	public static Data getService()
	{
		return ServiceUtils.getService(Data.class);
	}

	DataStore getDataStore(String category);

}