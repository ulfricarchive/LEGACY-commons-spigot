package com.ulfric.commons.spigot.metadata;

import java.util.HashMap;
import java.util.Map;

class MetadataHolder {

	private final Map<String, Object> metadata = new HashMap<>();

	void set(String key, Object value)
	{
		this.metadata.put(key, value);
	}

	Object get(String key)
	{
		return this.metadata.get(key);
	}

}
