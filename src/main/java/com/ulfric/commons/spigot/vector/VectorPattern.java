package com.ulfric.commons.spigot.vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import org.apache.commons.lang3.Validate;
import org.bukkit.util.Vector;

import com.google.common.collect.ImmutableList;

public final class VectorPattern implements Function<Vector, List<Vector>> {

	public static Builder builder()
	{
		return new Builder();
	}

	private final List<Vector> vectors;

	private VectorPattern(List<Vector> vectors)
	{
		this.vectors = vectors;
	}

	@Override
	public List<Vector> apply(Vector vector)
	{
		Objects.requireNonNull(vector);

		List<Vector> transformedVectors = new ArrayList<>();

		this.vectors.forEach(transformation ->
				transformedVectors.add(vector.clone().add(transformation)));

		return transformedVectors;
	}

	public static class Builder implements org.apache.commons.lang3.builder.Builder<VectorPattern>
	{

		private final ImmutableList.Builder<Vector> patterns = ImmutableList.builder();

		private Builder()
		{}

		public Builder addVector(Vector vector)
		{
			Objects.requireNonNull(vector);

			this.patterns.add(vector);

			return this;
		}

		@Override
		public VectorPattern build()
		{
			List<Vector> builtPatterns = this.patterns.build();

			Validate.notEmpty(builtPatterns);

			return new VectorPattern(builtPatterns);
		}

	}

}
