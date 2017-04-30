package com.ulfric.commons.spigot.shape;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

abstract class SkeletalCuboid extends SkeletalShape {

	protected final Point min;
	protected final Point max;
	protected final int minX;
	protected final int minY;
	protected final int minZ;
	protected final int maxX;
	protected final int maxY;
	protected final int maxZ;
	protected final List<Point> verticies;

	public SkeletalCuboid()
	{
		this(Point.ZERO, Point.ZERO);
	}

	public SkeletalCuboid(Point min, Point max)
	{
		Objects.requireNonNull(min, "min");
		Objects.requireNonNull(max, "max");

		this.min = min;
		this.max = max;
		this.minX = min.getX();
		this.minY = min.getY();
		this.minZ = min.getZ();
		this.maxX = max.getX();
		this.maxY = max.getY();
		this.maxZ = max.getZ();
		this.verticies = Collections.unmodifiableList(Arrays.asList(min, max));
	}

	@Override
	public final Point getMin()
	{
		return this.min;
	}

	@Override
	public final Point getMax()
	{
		return this.max;
	}

	@Override
	public final Iterator<Point> verticies()
	{
		return this.verticies.iterator();
	}

	@Override
	public Map<String, Object> toMap()
	{
		Map<String, Object> values = new HashMap<>();
		values.put("min-x", this.minX);
		values.put("min-y", this.minY);
		values.put("min-z", this.minZ);
		values.put("max-x", this.maxX);
		values.put("max-y", this.maxY);
		values.put("max-z", this.maxZ);
		return values;
	}

}