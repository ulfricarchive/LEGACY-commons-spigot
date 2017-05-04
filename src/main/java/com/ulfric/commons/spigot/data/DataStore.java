package com.ulfric.commons.spigot.data;

import java.util.stream.Stream;

public interface DataStore {

	PersistentData getData(String pointer);

	void deleteData(String pointer);

	Stream<PersistentData> loadAllData();

	DataStore getDataStore(String name);
	
	default PersistentData getDefault()
	{
		return this.getData("config");
	}

}