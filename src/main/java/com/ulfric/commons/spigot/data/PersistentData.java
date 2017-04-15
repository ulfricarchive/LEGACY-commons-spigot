package com.ulfric.commons.spigot.data;

import java.util.List;
import java.util.Set;

import com.ulfric.commons.data.Persistent;
import com.ulfric.commons.naming.Named;

public interface PersistentData extends Named, Persistent {

	void set(String path, Object value);

	PersistentData getSection(String path);

	String getString(String path);

	List<String> getStringList(String path);

	int getInt(String path);

	Set<String> getKeys();

}