package com.ulfric.commons.spigot.point;

import com.ulfric.commons.spigot.shape.Point;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

public enum PointUtils {

	;

	public static Vector substract(Point from, Point to)
	{
		return new Vector(to.getX() - from.getX(), to.getY() - from.getY(), to.getZ() - from.getZ());
	}

	public static Point add(Point base, Vector direction)
	{
		return Point.builder().setX((int) (base.getX() + direction.getX()))
				.setY((int) (base.getY() + direction.getY())).setZ((int) (base.getZ() + direction.getZ()))
				.build();
	}

	public static Location location(World world, Point point)
	{
		return new Location(world, point.getX(), point.getY(), point.getZ());
	}

}
