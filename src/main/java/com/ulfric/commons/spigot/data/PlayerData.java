package com.ulfric.commons.spigot.data;

import java.util.UUID;

public interface PlayerData extends Data {

	public static PlayerData getService()
	{
		throw new UnsupportedOperationException();
	}

	DataStore getDataStore();

	PersistentData getData(UUID pointer);

}