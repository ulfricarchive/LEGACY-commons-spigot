package com.ulfric.commons.spigot.data;

public interface PersistentData {

	void save();

	void set(String path, Object value);

	Object get(String path);

	String getString(String path);

	int getInt(String path);

}