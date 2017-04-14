package com.ulfric.commons.spigot.data;

import java.util.stream.Stream;

public interface DataStore {

	PersistentData getData(String pointer);

	Stream<PersistentData> loadAllData();

	DataStore getDataStore(String name);

}