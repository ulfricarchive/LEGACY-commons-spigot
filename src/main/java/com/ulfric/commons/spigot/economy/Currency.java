package com.ulfric.commons.spigot.economy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import com.ulfric.commons.naming.Named;

public final class Currency implements Named {

	public static Builder builder()
	{
		return new Builder();
	}

	public static final class Builder implements org.apache.commons.lang3.builder.Builder<Currency>
	{
		private String name;
		private String symbol;
		private String pattern;
		private String balanceCommandName;
		private List<String> balanceCommandAliases = new ArrayList<>();

		Builder() { }

		@Override
		public Currency build()
		{
			Objects.requireNonNull(this.name, "name");
			Objects.requireNonNull(this.symbol, "symbol");
			Objects.requireNonNull(this.pattern, "pattern");
			Objects.requireNonNull(this.balanceCommandName, "balanceCommandName");

			List<String> balanceCommandAliases = this.balanceCommandAliases == null
					? Collections.emptyList()
					: Collections.unmodifiableList(new ArrayList<>(this.balanceCommandAliases));

			return new Currency(this.name, this.symbol, Pattern.compile(this.pattern),
					this.balanceCommandName, balanceCommandAliases);
		}

		public Builder setName(String name)
		{
			this.name = name;
			return this;
		}

		public Builder setSymbol(String symbol)
		{
			this.symbol = symbol;
			return this;
		}

		public Builder setPattern(String pattern)
		{
			this.pattern = pattern;
			return this;
		}

		public Builder setBalanceCommandName(String balanceCommandName)
		{
			this.balanceCommandName = balanceCommandName;
			return this;
		}

		public Builder setBalanceCommandAliases(List<String> balanceCommandAliases)
		{
			this.balanceCommandAliases = balanceCommandAliases;
			return this;
		}
	}

	private final String name;
	private final String symbol;
	private final Pattern pattern;
	private final String balanceCommandName;
	private final List<String> balanceCommandAliases;

	Currency(String name, String symbol, Pattern pattern, String balanceCommandName, List<String> balanceCommandAliases)
	{
		this.name = name;
		this.symbol = symbol;
		this.pattern = pattern;
		this.balanceCommandName = balanceCommandName;
		this.balanceCommandAliases = balanceCommandAliases;
	}

	@Override
	public String getName()
	{
		return this.name;
	}

	public String getSymbol()
	{
		return this.symbol;
	}

	public Pattern getPattern()
	{
		return this.pattern;
	}

	public String getBalanceCommandName() {
		return this.balanceCommandName;
	}

	public List<String> getBalanceCommandAliases() {
		return this.balanceCommandAliases;
	}

}