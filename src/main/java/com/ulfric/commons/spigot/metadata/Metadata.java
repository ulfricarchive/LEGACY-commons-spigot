package com.ulfric.commons.spigot.metadata;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum Metadata {

	;

	private static final Map<Class<?>, Map<Object, Map<Object, Object>>> METADATA = new IdentityHashMap<>();

	public static void write(Player holder, String key, Object value)
	{
		Map<Object, Object> data = Metadata.getOrCreateHolder(holder);

		data.put(key, value);
	}

	public static void write(CommandSender holder, String key, Object value)
	{
		Map<Object, Object> data = Metadata.getOrCreateHolder(holder);

		data.put(key, value);
	}

	public static Object read(Player holder, String key)
	{
		Map<Object, Object> data = Metadata.getOrCreateHolder(holder);

		return data.get(key);
	}

	public static Object read(CommandSender holder, String key)
	{
		Map<Object, Object> data = Metadata.getOrCreateHolder(holder);

		return data.get(key);
	}

	public static String readString(Player holder, String key)
	{
		Object value = Metadata.read(holder, key);

		if (value instanceof String)
		{
			return (String) value;
		}

		return null;
	}

	public static String readString(CommandSender holder, String key)
	{
		Object value = Metadata.read(holder, key);

		if (value instanceof String)
		{
			return (String) value;
		}

		return null;
	}

	private static Map<Object, Object> getOrCreateHolder(Player holder)
	{
		Map<Object, Map<Object, Object>> metadatables =
				Metadata.METADATA.computeIfAbsent(holder.getClass(), ignored -> new HashMap<>());

		return metadatables.computeIfAbsent(holder.getUniqueId(), ignored -> new HashMap<>());
	}

	private static Map<Object, Object> getOrCreateHolder(CommandSender holder)
	{
		Map<Object, Map<Object, Object>> metadatables =
				Metadata.METADATA.computeIfAbsent(holder.getClass(), ignored -> new HashMap<>());

		return metadatables.computeIfAbsent(holder.getName(), ignored -> new HashMap<>());
	}

}