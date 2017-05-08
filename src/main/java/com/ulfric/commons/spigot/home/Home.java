package com.ulfric.commons.spigot.home;

import com.ulfric.commons.bean.Bean;
import org.bukkit.Location;

import java.util.Objects;

public class Home extends Bean {
	
	public static Builder builder()
	{
		return new Builder();
	}
	
	public static final class Builder implements org.apache.commons.lang3.builder.Builder<Home>
	{
		
		private String name;
		private Location location;
		
		Builder()
		{
			
		}
		
		@Override
		public Home build()
		{
			Objects.requireNonNull(this.name, "name");
			Objects.requireNonNull(this.location, "location");
			
			return new Home(this.name, this.location);
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
	
	Home(String name, Location location)
	{
		this.name = name;
		this.location = location;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public Location getLocation()
	{
		return this.location;
	}
	
}
