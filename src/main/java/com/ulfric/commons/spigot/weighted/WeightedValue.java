package com.ulfric.commons.spigot.weighted;

import com.ulfric.commons.bean.Bean;
import com.ulfric.commons.bean.Weighted;

public final class WeightedValue<T> extends Bean implements Weighted<WeightedValue<T>> {

	private final T value;
	private final int weight;

	public WeightedValue(T value, int weight)
	{
		this.value = value;
		this.weight = weight;
	}

	public T getValue()
	{
		return value;
	}

	@Override
	public int getWeight()
	{
		return weight;
	}

}