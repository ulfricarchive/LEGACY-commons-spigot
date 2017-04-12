package com.ulfric.commons.spigot.data;

import com.ulfric.commons.service.Service;

public interface Data extends Service {

	DataStore getDataStore(String category);

}