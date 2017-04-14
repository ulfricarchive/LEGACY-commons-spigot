package com.ulfric.commons.spigot.data;

import com.ulfric.commons.data.Persistent;

public interface PersistentData extends Persistent {

	void set(String path, Object value);

	Object get(String path);

	String getString(String path);

	int getInt(String path);

}