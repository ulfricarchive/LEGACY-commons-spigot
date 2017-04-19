package com.ulfric.commons.spigot.guard;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import com.ulfric.commons.naming.Name;

@Name("Omnipresent")
public enum Omnipresent implements Shape {

	INSTANCE;

	@Override
	public boolean containsPoint(int x, int y, int z)
	{
		return true;
	}

	@Override
	public Point getMin()
	{
		return Point.ZERO;
	}

	@Override
	public Point getMax()
	{
		return Point.ZERO;
	}

	@Override
	public Iterator<Point> verticies()
	{
		return Collections.emptyIterator();
	}

	@Override
	public Map<String, Object> toMap()
	{
		return Collections.emptyMap();
	}

	@Override
	public Shape fromMap(Map<String, Object> map)
	{
		return Omnipresent.INSTANCE;
	}

}