package com.ulfric.commons.spigot.warp;

import java.util.Objects;
import java.util.regex.Pattern;

import org.bukkit.Location;

import com.ulfric.commons.bean.Bean;
import com.ulfric.commons.naming.Named;

public final class Warp extends Bean implements Named {

	private static final Pattern VALID_WARP_NAME = Pattern.compile("[a-zA-Z0-9_-]+");

	public static boolean isValidName(String name)
	{
		return Warp.VALID_WARP_NAME.matcher(name).matches();
	}

	public static Builder builder()
	{
		return new Builder();
	}

	public static final class Builder implements org.apache.commons.lang3.builder.Builder<Warp>
	{
		private String name;
		private Location location;

		Builder() { }

		@Override
		public Warp build()
		{
			Objects.requireNonNull(this.name, "name");
			Objects.requireNonNull(this.location, "location");

			if (!Warp.isValidName(this.name))
			{
				throw new IllegalArgumentException(this.name + " must match " + Warp.VALID_WARP_NAME.pattern());
			}

			return new Warp(this.name, this.location);
		}

		public Builder setName(String name)
		{
			this.name = name;
			return this;
		}

		public Builder setLocation(Location location)
		{
			this.location = location;
			return this;
		}
	}

	private final String name;
	private final Location location;

	Warp(String name, Location location)
	{
		this.name = name;
		this.location = location;
	}

	@Override
	public String getName()
	{
		return this.name;
	}

	public Location getLocation()
	{
		return this.location.clone();
	}

}