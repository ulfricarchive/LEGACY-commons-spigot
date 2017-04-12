package com.ulfric.commons.spigot.data;

import java.util.UUID;

import com.ulfric.commons.spigot.service.ServiceUtils;

public interface PlayerData extends Data {

	public static PlayerData getService()
	{
		return ServiceUtils.getService(PlayerData.class);
	}

	DataStore getDataStore();

	PersistentData getData(UUID pointer);

}