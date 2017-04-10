package com.ulfric.commons.spigot.metadata;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.metadata.Metadatable;

public enum Metadata {

	;

	private static final Map<Object, MetadataHolder> METADATA = new HashMap<>();

	public static void write(Object holder, String key, Object value)
	{
		MetadataHolder data = Metadata.getOrCreateHolder(holder);

		data.set(key, value);
	}

	public static <T> Object read(T holder, String key)
	{
		MetadataHolder data = Metadata.getOrCreateHolder(holder);

		return data.get(key);
	}

	public static String readString(Metadatable holder, String key)
	{
		Object value = Metadata.read(holder, key);

		if (value instanceof String)
		{
			return (String) value;
		}

		return null;
	}

	private static MetadataHolder getOrCreateHolder(Object holder)
	{
		return Metadata.METADATA.computeIfAbsent(holder, ignored -> new MetadataHolder());
	}

}