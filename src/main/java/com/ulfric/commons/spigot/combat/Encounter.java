package com.ulfric.commons.spigot.combat;

import com.ulfric.commons.bean.Bean;
import com.ulfric.commons.identity.Unique;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Encounter extends Bean implements Unique {
	
	public static Builder builder()
	{
		return new Builder();
	}
	
	public static final class Builder implements org.apache.commons.lang3.builder.Builder<Encounter>
	{
		
		private UUID uniqueId;
		private UUID attackerId;
		private Instant start;
		private Instant expiry;
		
		Builder()
		{
			
		}
		
		@Override
		public Encounter build()
		{
			Objects.requireNonNull(this.uniqueId, "uniqueId");
			Objects.requireNonNull(this.attackerId, "attackerId");
			Objects.requireNonNull(this.expiry, "expiry");
			
			if (this.start == null)
			{
				return new Encounter(this.uniqueId, this.attackerId, this.expiry);
			}
			
			return new Encounter(this.uniqueId, this.attackerId, this.start, this.expiry);
		}
		
		public Builder setUniqueId(UUID uniqueId)
		{
			this.uniqueId = uniqueId;
			return this;
		}
		
		public Builder setAttackerId(UUID attackerId)
		{
			this.attackerId = attackerId;
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
	private final UUID attackerId;
	private final Instant start;
	private final Instant expiry;
	
	Encounter(UUID uniqueId, UUID attackerId, Instant start, Instant expiry)
	{
		this.uniqueId = uniqueId;
		this.attackerId = attackerId;
		this.start = start;
		this.expiry = expiry;
	}
	
	Encounter(UUID uniqueId, UUID attackerId, Instant expiry)
	{
		this(uniqueId, attackerId, Instant.now(), expiry);
	}
	
	@Override
	public UUID getUniqueId()
	{
		return this.uniqueId;
	}
	
	public UUID getAttackerId()
	{
		return this.attackerId;
	}
	
	public Instant getStart()
	{
		return this.start;
	}
	
	public Instant getExpiry()
	{
		return this.expiry;
	}
	
	public boolean isExpired()
	{
		return this.expiry.isAfter(this.start);
	}
	
	public long getRemaining()
	{
		return this.expiry.toEpochMilli() - this.start.toEpochMilli();
	}
	
}
