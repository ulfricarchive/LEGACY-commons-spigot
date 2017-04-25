package com.ulfric.commons.spigot.punishment;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import com.ulfric.commons.bean.Bean;

public final class Punishment extends Bean {

	public static Builder builder()
	{
		return new Builder();
	}

	public static final class Builder implements org.apache.commons.lang3.builder.Builder<Punishment>
	{
		private UUID uniqueId;
		private Punisher punisher;
		private String reason;
		private Instant beginning;
		private Instant expiry;

		Builder() { }

		@Override
		public Punishment build()
		{
			Objects.requireNonNull(this.punisher, "punisher");

			UUID uniqueId = this.uniqueId == null ? UUID.randomUUID() : this.uniqueId;
			Instant beginning = this.beginning == null ? Instant.now() : this.beginning;
			return new Punishment(uniqueId, this.punisher, this.reason, beginning, this.expiry);
		}

		public Builder setUniqueId(UUID uniqueId)
		{
			this.uniqueId = uniqueId;
			return this;
		}

		public Builder setPunisher(Punisher punisher)
		{
			this.punisher = punisher;
			return this;
		}

		public Builder setReason(String reason)
		{
			this.reason = reason;
			return this;
		}

		public Builder setBeginning(Instant beginning)
		{
			this.beginning = beginning;
			return this;
		}

		public Builder setExpiry(Instant expiry)
		{
			this.expiry = expiry;
			return this;
		}
	}

	private final UUID punishmentId;
	private final Punisher punisher;
	private final String reason;
	private final Instant beginning;
	private final Instant expiry;

	private Punishment(UUID uniqueId, Punisher punisher, String reason, Instant beginning, Instant expiry)
	{
		this.punishmentId = uniqueId;
		this.punisher = punisher;
		this.reason = reason;
		this.beginning = beginning;
		this.expiry = expiry;
	}

	public UUID getPunishmentId()
	{
		return this.punishmentId;
	}

	public Punisher getPunisher()
	{
		return this.punisher;
	}

	public String getReason()
	{
		return this.reason;
	}

	public Instant getBeginning()
	{
		return this.beginning;
	}

	public Instant getExpiry()
	{
		return this.expiry;
	}

}