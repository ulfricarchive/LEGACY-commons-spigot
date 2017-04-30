package com.ulfric.commons.spigot.shape;

import java.util.Map;

import com.ulfric.commons.naming.Name;

@Name("InclusiveCuboid")
public final class InclusiveCuboid extends SkeletalCuboid {

	public InclusiveCuboid()
	{
		this(Point.ZERO, Point.ZERO);
	}

	public InclusiveCuboid(Point min, Point max)
	{
		super(min, max);
	}

	@Override
	public boolean containsPoint(int x, int y, int z)
	{
		return (x > this.minX && x < this.maxX
				&& y > this.minY && y < this.maxY
				&& z > this.minZ && z < this.maxZ);
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