package com.ulfric.commons.spigot.command.argument;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import com.ulfric.commons.spigot.command.Context;

public enum Arguments {

	;

	private static final Map<Class<?>, List<ArgumentResolver<?>>> RESOLVERS;

	static
	{
		RESOLVERS = new IdentityHashMap<>();
		Arguments.registerDefaultResolvers();
	}

	private static void registerDefaultResolvers()
	{
		Arguments.registerResolver(new StringArgumentResolver());
		Arguments.registerResolver(new NameToPlayerArgumentResolver());
		Arguments.registerResolver(new NameToOfflinePlayerArgumentResolver());
		Arguments.registerResolver(new PermissionEntityArgumentResolver());
		Arguments.registerResolver(new CurrencyAmountArgumentResolver());
	}

	public static void registerResolver(ArgumentResolver<?> argumentResolver)
	{
		Objects.requireNonNull(argumentResolver);
		Arguments.RESOLVERS.computeIfAbsent(argumentResolver.getType(),
				ignore -> new ArrayList<>())
			.add(argumentResolver);
	}

	public static <T> T resolve(Context context, String argument, Class<T> argumentType)
	{
		List<ArgumentResolver<?>> argumentResolvers = Arguments.RESOLVERS.get(argumentType);
		Arguments.validateArgumentResolvers(argumentResolvers, argumentType);
		for (ArgumentResolver<?> argumentResolver : argumentResolvers)
		{
			Object result = argumentResolver.apply(context, argument);

			if (result != null)
			{
				@SuppressWarnings("unchecked")
				T casted = (T) result;
				return casted;
			}
		}
		return null;
	}

	private static void validateArgumentResolvers(List<ArgumentResolver<?>> argumentResolvers, Class<?> argumentType)
	{
		if (CollectionUtils.isEmpty(argumentResolvers))
		{
			throw new IllegalArgumentException("No resolvers found for: " + argumentType);
		}
	}

}