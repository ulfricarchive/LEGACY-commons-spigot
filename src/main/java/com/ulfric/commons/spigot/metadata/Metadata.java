package com.ulfric.commons.spigot.metadata;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

public enum Metadata {

	;

	private static final Map<Class<?>, Map<Object, Map<String, Object>>> METADATA = new IdentityHashMap<>();

	public static void write(Object holder, String key, Object value)
	{
		Map<String, Object> data = Metadata.getOrCreateHolder(holder);

		data.put(key, value);
	}

	public static void delete(Object holder)
	{
		Map<Object, Map<String, Object>> metadata = Metadata.METADATA.get(holder.getClass());
		if (metadata == null)
		{
			return;
		}
		metadata.remove(Metadata.getKey(holder));
	}

	public static Object delete(Object holder, String key)
	{
		Map<String, Object> data = Metadata.getHolderOrNull(holder);
		if (data == null)
		{
			return null;
		}
		return data.remove(key);
	}

	public static Object delete(Object holder, String key, Object value)
	{
		Map<String, Object> data = Metadata.getHolderOrNull(holder);
		if (data == null)
		{
			return null;
		}
		return data.remove(key, value);
	}

	public static Object read(Object holder, String key)
	{
		Map<String, Object> data = Metadata.getOrCreateHolder(holder);

		return data.get(key);
	}

	public static String readString(Object holder, String key)
	{
		Object value = Metadata.read(holder, key);

		if (value instanceof String)
		{
			return (String) value;
		}

		return null;
	}

	public static boolean isPresent(Object holder, String key)
	{
		Map<String, Object> data = Metadata.getHolderOrNull(holder);

		return data != null && data.get(key) != null;
	}

	private static Map<String, Object> getOrCreateHolder(Object holder)
	{
		Map<Object, Map<String, Object>> metadatables =
				Metadata.METADATA.computeIfAbsent(holder.getClass(), ignored -> new HashMap<>());

		Object key = Metadata.getKey(holder);
		return metadatables.computeIfAbsent(key, ignored -> new HashMap<>());
	}

	private static Map<String, Object> getHolderOrNull(Object holder)
	{
		Map<Object, Map<String, Object>> metadatables = Metadata.METADATA.get(holder.getClass());
		if (metadatables == null)
		{
			return null;
		}
		Object key = Metadata.getKey(holder);
		return metadatables.get(key);
	}

	private static Object getKey(Object holder)
	{
		if (holder instanceof UUID)
		{
			return holder;
		}
		else if (holder instanceof Entity)
		{
			return ((Entity) holder).getUniqueId();
		}
		else if (holder instanceof CommandSender)
		{
			return ((CommandSender) holder).getName();
		}
		else
		{
			throw new IllegalArgumentException("Must be Entity / CommandSender");
		}
	}

}