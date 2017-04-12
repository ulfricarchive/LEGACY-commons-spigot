package com.ulfric.commons.spigot.data;

import java.util.UUID;
import java.util.stream.Stream;

public interface DataStore {

	PersistentData getData(UUID pointer);

	Stream<PersistentData> loadAllData();

}