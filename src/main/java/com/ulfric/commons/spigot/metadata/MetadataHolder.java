package com.ulfric.commons.spigot.metadata;

import java.util.HashMap;
import java.util.Map;

class MetadataHolder<T> {

	private final Map<String, Object> metadata = new HashMap<>();

	private final T instance;

	MetadataHolder(T instance)
	{
		this.instance = instance;
	}

	T getInstance()
	{
		return this.instance;
	}

	Class<?> type()
	{
		return this.instance.getClass();
	}

	void set(String key, Object value)
	{
		this.metadata.put(key, value);
	}

	Object get(String key)
	{
		return this.metadata.get(key);
	}

}
