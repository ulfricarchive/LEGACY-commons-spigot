package com.ulfric.commons.spigot.location;

import java.util.StringJoiner;
import java.util.regex.Pattern;

import org.bukkit.Location;

public enum LocationUtils {

	;

	public static final Pattern SEPARATOR = Pattern.compile(",", Pattern.LITERAL);

	public static Location fromString(String context)
	{
		Location location = LocationUtils.getEmptyLocation();

		String[] sections = LocationUtils.SEPARATOR.split(context);
		int length = sections.length;
		if (length >= 1)
		{
			location.setWorld(WorldUtils.getWorld(sections[0]));
		}
		if (length >= 2)
		{
			location.setX(Double.valueOf(sections[1]));
		}
		if (length >= 3)
		{
			location.setY(Double.valueOf(sections[2]));
		}
		if (length >= 4)
		{
			location.setZ(Double.valueOf(sections[3]));
		}
		if (length >= 5)
		{
			location.setYaw(Float.valueOf(sections[4]));
		}
		if (length >= 6)
		{
			location.setPitch(Float.valueOf(sections[5]));
		}

		return location;
	}

	public static String toString(Location location)
	{
		StringJoiner joiner = new StringJoiner(LocationUtils.SEPARATOR.pattern());

		joiner.add(location.getWorld().getName());
		joiner.add(String.valueOf(location.getX()));
		joiner.add(String.valueOf(location.getY()));
		joiner.add(String.valueOf(location.getZ()));
		joiner.add(String.valueOf(location.getYaw()));
		joiner.add(String.valueOf(location.getPitch()));

		return joiner.toString();
	}

	public static Location getDefaultLocation()
	{
		Location location = LocationUtils.getEmptyLocation();
		location.setWorld(WorldUtils.getDefaultWorld());
		return location;
	}

	public static Location getEmptyLocation()
	{
		return new Location(null, 0, 0, 0);
	}

}