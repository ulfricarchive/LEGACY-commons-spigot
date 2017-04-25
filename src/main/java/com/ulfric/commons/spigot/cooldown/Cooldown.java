package com.ulfric.commons.spigot.cooldown;

import com.ulfric.commons.bean.Bean;
import com.ulfric.commons.naming.Named;

import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.Objects;
import java.util.UUID;

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
		private Class<? extends Named> owner;
		
		@Override
		public Cooldown build()
		{
			Objects.requireNonNull(this.uniqueId);
			Objects.requireNonNull(this.owner);
			Objects.requireNonNull(this.expiry);
			
			if (this.start == null)
			{
				return new Cooldown(this.uniqueId, this.owner, this.expiry);
			}
			
			return new Cooldown(this.uniqueId, this.owner, this.start, this.expiry);
		}
		
		public Builder setUniqueId(UUID uniqueId)
		{
			this.uniqueId = uniqueId;
			return this;
		}
		
		public Builder setOwner(Class<? extends Named> owner)
		{
			this.owner = owner;
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
	private final Class<? extends Named> owner;
	
	Cooldown(UUID uniqueId, Class<? extends Named> owner, Instant start, Instant expiry)
	{
		this.uniqueId = uniqueId;
		this.owner = owner;
		this.start = start;
		this.expiry = expiry;
	}
	
	Cooldown(UUID uniqueId, Class<? extends Named> owner, Instant expiry)
	{
		this(uniqueId, owner, Instant.now(), expiry);
	}
	
	public UUID getUniqueId()
	{
		return this.uniqueId;
	}
	
	public Class<? extends Named> getOwner()
	{
		return this.owner;
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
		return Instant.now().minusMillis(this.getExpiry().getLong(ChronoField.MILLI_OF_SECOND));
	}
	
	public boolean isExpired()
	{
		return this.getExpiry().isAfter(Instant.now());
	}
	
}
