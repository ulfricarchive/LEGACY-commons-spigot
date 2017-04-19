package com.ulfric.commons.spigot.data;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.ulfric.commons.data.Persistent;
import com.ulfric.commons.identity.UniqueIdUtils;
import com.ulfric.commons.naming.Named;

public interface PersistentData extends Named, Persistent {

	void set(String path, Object value);

	Object getObject(String path);

	PersistentData getSection(String path);

	default List<PersistentData> getSections()
	{
		return this.getKeys().stream()
				.map(this::getSection)
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
	}

	default UUID getUniqueId(String path)
	{
		return UniqueIdUtils.parseUniqueId(path);
	}

	String getString(String path);

	List<String> getStringList(String path);

	int getInt(String path);

	Set<String> getKeys();

}