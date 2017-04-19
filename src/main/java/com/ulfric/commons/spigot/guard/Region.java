package com.ulfric.commons.spigot.guard;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.ulfric.commons.bean.Bean;
import com.ulfric.commons.bean.Weighted;
import com.ulfric.commons.naming.Named;

public final class Region extends Bean implements Named, Weighted<Region> {

	public static Builder builder()
	{
		return new Builder();
	}

	public static final class Builder implements org.apache.commons.lang3.builder.Builder<Region>
	{
		private String name;
		private UUID world;
		private Shape bounds;
		private int weight;
		private Map<Flag<?>, Object> flags;

		Builder() { }

		@Override
		public Region build()
		{
			Objects.requireNonNull(this.name, "name");
			Objects.requireNonNull(this.world, "world");
			Map<Flag<?>, Object> flags = this.flags == null ? Collections.emptyMap() : new HashMap<>(this.flags);
			return new Region(this.name, this.world, this.bounds, this.weight, flags);
		}

		public Builder setName(String name)
		{
			this.name = name;
			return this;
		}

		public Builder setWorld(UUID world)
		{
			this.world = world;
			return this;
		}

		public Builder setBounds(Shape bounds)
		{
			this.bounds = bounds;
			return this;
		}

		public Builder setWeight(int weight)
		{
			this.weight = weight;
			return this;
		}

		public Builder setFlags(Map<Flag<?>, Object> flags)
		{
			this.flags = flags;
			return this;
		}
	}

	private final String name;
	private final UUID world;
	private final Shape bounds;
	private final int weight;
	private final Map<Flag<?>, Object> flags;

	private Region(String name, UUID world, Shape bounds, int weight, Map<Flag<?>, Object> flags)
	{
		this.name = name;
		this.world = world;
		this.bounds = bounds;
		this.weight = weight;
		this.flags = flags;
	}

	@Override
	public String getName()
	{
		return this.name;
	}

	public UUID getWorld()
	{
		return this.world;
	}

	public Shape getBounds()
	{
		return this.bounds;
	}

	@Override
	public int getWeight()
	{
		return this.weight;
	}

	@SuppressWarnings("unchecked")
	public <T> T readFlag(Flag<T> flag)
	{
		return (T) this.flags.get(flag);
	}

	@Override
	public int compareTo(Region that)
	{
		int weight = Weighted.super.compareTo(that);
		if (weight != 0)
		{
			return weight;
		}

		return this.getName().compareToIgnoreCase(that.getName());
	}

}