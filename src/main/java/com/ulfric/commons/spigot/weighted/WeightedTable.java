package com.ulfric.commons.spigot.weighted;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ulfric.commons.bean.Bean;
import com.ulfric.commons.spigot.random.XorShiftRandom;

public final class WeightedTable<T> extends Bean {

	public static <T> Builder<T> builder()
	{
		return new Builder<>();
	}

	public static final class Builder<T> implements org.apache.commons.lang3.builder.Builder<WeightedTable<T>>
	{
		private final List<WeightedValue<T>> entries = new ArrayList<>();

		Builder() { }

		@Override
		public WeightedTable<T> build()
		{
			return new WeightedTable<>(this.entries);
		}

		public Builder<T> add(WeightedValue<T> value)
		{
			this.entries.add(value);
			return this;
		}

		public Builder<T> addAll(Collection<? extends WeightedValue<T>> values)
		{
			this.entries.addAll(values);
			return this;
		}
	}

	private final XorShiftRandom random = new XorShiftRandom();
	private final List<Entry> entries;
	private int totalWeight;

	public WeightedTable(List<WeightedValue<T>> entries)
	{
		this.entries = this.calculateWeights(entries);
	}

	private List<Entry> calculateWeights(List<WeightedValue<T>> weightedValues)
	{
		List<Entry> entries = new ArrayList<>(weightedValues.size());
		Entry lastEntry = null;

		for (WeightedValue<T> value : weightedValues)
		{
			int min;
			int max;
			if (lastEntry == null)
			{
				min = 0;
				max = value.getWeight();
			}
			else
			{
				min = lastEntry.max;
				max = min + value.getWeight();
			}

			lastEntry = new Entry(value.getValue(), min, max);
			entries.add(lastEntry);
		}

		this.totalWeight = lastEntry == null ? 0 : lastEntry.max;
		return entries;
	}

	public T nextValue()
	{
		int roll = this.random.nextInt(this.totalWeight);

		for (Entry entry : this.entries)
		{
			if (roll >= entry.min && roll <= entry.max)
			{
				return entry.value;
			}
		}

		return null;
	}

	private final class Entry extends Bean
	{
		final T value;
		final int min;
		final int max;

		Entry(T value, int min, int max)
		{
			this.value = value;
			this.min = min;
			this.max = max;
		}
	}

}