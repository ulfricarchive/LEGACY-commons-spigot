package com.ulfric.commons.spigot.guard;

import com.ulfric.commons.bean.Bean;
import com.ulfric.commons.naming.Name;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Name("InclusiveCuboid")
public final class InclusiveCuboid extends Bean implements Shape {

	private final Point min;
	private final Point max;
	private final int minX;
	private final int minY;
	private final int minZ;
	private final int maxX;
	private final int maxY;
	private final int maxZ;
	private final List<Point> verticies;

	public InclusiveCuboid()
	{
		this(Point.ZERO, Point.ZERO);
	}

	public InclusiveCuboid(Point min, Point max)
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
	public boolean containsPoint(int x, int y, int z)
	{
		return (x > this.minX && x < this.maxX
				&& y > this.minY && y < this.maxY
				&& z > this.minZ && z < this.maxZ);
	}

	@Override
	public Point getMin()
	{
		return this.min;
	}

	@Override
	public Point getMax()
	{
		return this.max;
	}

	@Override
	public Iterator<Point> verticies()
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

	@Override
	public Shape fromMap(Map<String, Object> map)
	{
		Point minPoint = Point.builder()
				.setX((int) map.get("min-x"))
				.setY((int) map.get("min-y"))
				.setZ((int) map.get("min-z")).build();

		Point maxPoint = Point.builder()
				.setX((int) map.get("max-x"))
				.setY((int) map.get("max-y"))
				.setZ((int) map.get("max-z")).build();

		return new InclusiveCuboid(minPoint, maxPoint);
	}

}