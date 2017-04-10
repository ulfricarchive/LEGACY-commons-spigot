package com.ulfric.commons.spigot.metadata;

import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.ClassUtils;
import org.bukkit.metadata.Metadatable;

public enum Metadata {

	;

	private static final Map<Class<?>, MetadataIdentifier<?>> IDENTIFIERS = new IdentityHashMap<>();
	private static final Map<Class<?>, Set<MetadataHolder<?>>> METADATA = new IdentityHashMap<>();

	static
	{
		Metadata.registerIdentifier(Object.class, Objects::equals);
	}

	public static <T> void registerIdentifier(Class<T> type, MetadataIdentifier<T> identifier)
	{
		Metadata.IDENTIFIERS.put(type, identifier);
	}

	public static <T> void write(T holder, String key, Object value)
	{
		MetadataIdentifier<T> identifier = Metadata.findIdentifier(holder);
		MetadataHolder<T> dataHolder = Metadata.getOrCreateHolder(holder, identifier);

		dataHolder.set(key, value);
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

	public static <T> Object read(T holder, String key)
	{
		MetadataIdentifier<T> identifier = Metadata.findIdentifier(holder);
		MetadataHolder<T> dataHolder = Metadata.getOrCreateHolder(holder, identifier);

		return dataHolder.get(key);
	}

	private static <T> MetadataIdentifier<T> findIdentifier(T holder)
	{
		@SuppressWarnings("unchecked")
		MetadataIdentifier<T> identifier = (MetadataIdentifier<T>)
				Metadata.IDENTIFIERS.computeIfAbsent(holder.getClass(), ignored -> Metadata.identifierOf(holder));

		return identifier;
	}

	private static <T> MetadataHolder<T> getOrCreateHolder(T holder, MetadataIdentifier<T> identifier)
	{
		Set<MetadataHolder<?>> metadata =
				Metadata.METADATA.computeIfAbsent(holder.getClass(), ignored -> new LinkedHashSet<>());

		MetadataHolder<T> dataHolder = Metadata.getHolder(holder, identifier, metadata);

		if (dataHolder == null)
		{
			dataHolder = Metadata.createHolder(holder);
			metadata.add(dataHolder);
		}

		return dataHolder;
	}

	private static <T> MetadataHolder<T> getHolder(T holder, MetadataIdentifier<T> identifier, Set<MetadataHolder<?>> metadata)
	{
		@SuppressWarnings("unchecked")
		MetadataHolder<T> dataHolder = (MetadataHolder<T>)
				metadata.stream()
						.filter(meta -> holder.getClass() == meta.type() && identifier.equal((T) meta.getInstance(), holder))
						.findFirst()
						.orElse(null);

		return dataHolder;
	}

	private static <T> MetadataHolder<T> createHolder(T holder)
	{
		return new MetadataHolder<>(holder);
	}

	private static <T> MetadataIdentifier<? super T> identifierOf(T holder)
	{
		@SuppressWarnings("unchecked")
		MetadataIdentifier<? super T> identifier = (MetadataIdentifier<? super T>) Metadata.IDENTIFIERS.get(holder.getClass());

		if (identifier == null)
		{
			identifier = Metadata.identifierOfSuperclasses(holder);

			if (identifier == null)
			{
				identifier = Metadata.identifierOfInterfaces(holder);

				if (identifier == null)
				{
					identifier = Metadata.genericIdentifier();
				}
			}
		}

		return identifier;
	}

	private static <T> MetadataIdentifier<? super T> identifierOfSuperclasses(T holder)
	{
		@SuppressWarnings("unchecked")
		MetadataIdentifier<? super T> identifier =
				(MetadataIdentifier<? super T>) ClassUtils.getAllSuperclasses(holder.getClass())
				.stream()
				.filter(clazz -> clazz != Object.class)
				.map(Metadata.IDENTIFIERS::get)
				.findFirst()
				.orElse(null);

		return identifier;
	}

	private static <T> MetadataIdentifier<? super T> identifierOfInterfaces(T holder)
	{
		@SuppressWarnings("unchecked")
		MetadataIdentifier<? super T> identifier =
				(MetadataIdentifier<? super T>) ClassUtils.getAllInterfaces(holder.getClass())
				.stream()
				.map(Metadata.IDENTIFIERS::get)
				.findFirst()
				.orElse(null);

		return identifier;
	}

	private static <T> MetadataIdentifier<? super T> genericIdentifier()
	{
		@SuppressWarnings("unchecked")
		MetadataIdentifier<? super T> identifier =
				(MetadataIdentifier<? super T>) Metadata.IDENTIFIERS.get(Object.class);

		return identifier;
	}

}