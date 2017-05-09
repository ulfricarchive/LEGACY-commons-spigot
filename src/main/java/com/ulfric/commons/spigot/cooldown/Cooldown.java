package com.ulfric.commons.spigot.cooldown;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import com.ulfric.commons.bean.Bean;

public final class Cooldown extends Bean {
	
	public static Builder builder()
	{
		return new Builder();
	}
	
	public static final class Builder implements org.apache.commons.lang3.builder.Builder<Cooldown>
	{
		
		private UUID uniqueId;
		private Instant start;
		private Instant expiry;
		private String name;
		
		@Override
		public Cooldown build()
		{
			Objects.requireNonNull(this.uniqueId);
			Objects.requireNonNull(this.name);
			Objects.requireNonNull(this.expiry);
			
			if (this.start == null)
			{
				return new Cooldown(this.uniqueId, this.name, this.expiry);
			}
			
			return new Cooldown(this.uniqueId, this.name, this.start, this.expiry);
		}
		
		public Builder setUniqueId(UUID uniqueId)
		{
			this.uniqueId = uniqueId;
			return this;
		}
		
		public Builder setName(String name)
		{
			this.name = name;
			return this;
		}
		
		public Builder setStart(Instant start)
		{
			this.start = start;
			return this;
		}
		
		public Builder setExpiry(Instant expiry)
		{
			this.expiry = expiry;
			return this;
		}
		
	}
	
	private final UUID uniqueId;
	private final Instant start;
	private final Instant expiry;
	private final String name;
	
	Cooldown(UUID uniqueId, String name, Instant start, Instant expiry)
	{
		this.uniqueId = uniqueId;
		this.name = name;
		this.start = start;
		this.expiry = expiry;
	}
	
	Cooldown(UUID uniqueId, String name, Instant expiry)
	{
		this(uniqueId, name, Instant.now(), expiry);
	}
	
	public UUID getUniqueId()
	{
		return this.uniqueId;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public Instant getStart()
	{
		return this.start;
	}
	
	public Instant getExpiry()
	{
		return this.expiry;
	}
	
	public Instant getRemaining()
	{
		return this.getExpiry().minusMillis(Instant.now().toEpochMilli());
	}
	
	public boolean isExpired()
	{
		return Instant.now().isAfter(this.getExpiry());
	}
	
}
