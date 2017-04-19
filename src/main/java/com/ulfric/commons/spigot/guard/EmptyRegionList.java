package com.ulfric.commons.spigot.guard;

import java.util.Collections;
import java.util.Iterator;

public enum EmptyRegionList implements RegionList {

	INSTANCE;

	@Override
	public Iterator<Region> iterator()
	{
		return Collections.emptyIterator();
	}

	@Override
	public <T> T getDominantFlag(Flag<T> flag)
	{
		return flag.getDefaultData();
	}

}