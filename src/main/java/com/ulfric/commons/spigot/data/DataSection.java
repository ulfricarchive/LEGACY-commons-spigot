package com.ulfric.commons.spigot.data;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.ulfric.commons.naming.Named;

public interface DataSection extends Named {

	@Override
	String getName();

	void set(String path, Object value);

	Object getObject(String path);

	UUID getUniqueId(String path);

	String getString(String path);

	String getString(String path, String defaultValue);

	List<String> getStringList(String path);

	int getInt(String path);

	int getInt(String path, int defaultValue);

	long getLong(String path);

	long getLong(String path, long defaultValue);

	boolean getBoolean(String path);

	boolean getBoolean(String path, boolean defaultValue);

	DataSection getSection(String path);

	DataSection createSection(String key);

	List<DataSection> getSections();

	Set<String> getKeys();

	boolean contains(String key);

}