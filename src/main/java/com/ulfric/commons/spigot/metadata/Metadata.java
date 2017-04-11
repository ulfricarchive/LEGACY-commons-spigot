package com.ulfric.commons.spigot.metadata;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public enum Metadata {

	;

	private static final Map<Class<?>, Map<Object, Map<Object, Object>>> METADATA = new IdentityHashMap<>();

	public static void write(Object holder, String key, Object value)
	{
		Map<Object, Object> data = Metadata.getOrCreateHolder(holder);

		data.put(key, value);
	}

	public static Object read(Entity holder, String key)
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

	private static Map<Object, Object> getOrCreateHolder(Object holder)
	{
		Map<Object, Map<Object, Object>> metadatables =
				Metadata.METADATA.computeIfAbsent(holder.getClass(), ignored -> new HashMap<>());

		Object key;

		if (holder instanceof Entity)
		{
			key = ((Entity) holder).getUniqueId();
		}
		else if (holder instanceof CommandSender)
		{
			key = ((CommandSender) holder).getName();
		}
		else
		{
			throw new IllegalArgumentException("Must be Entity / CommandSender");
		}

		return metadatables.computeIfAbsent(key, ignored -> new HashMap<>());
	}

}