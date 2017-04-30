package com.ulfric.commons.spigot.shape;

import java.util.Iterator;
import java.util.Map;

import com.ulfric.commons.naming.Named;

public interface Shape extends Named {

	boolean containsPoint(int x, int y, int z);

	Point getMin();

	Point getMax();

	Iterator<Point> verticies();

	Map<String, Object> toMap();

	Shape fromMap(Map<String, Object> map);

}