package com.ulfric.commons.spigot.command.argument;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.function.BiFunction;

import com.ulfric.commons.spigot.command.Context;

public interface ArgumentResolver<T> extends BiFunction<Context, String, T> {

	default Class<T> getType()
	{
		for (Type type : this.getClass().getGenericInterfaces())
		{
			if (type instanceof ParameterizedType)
			{
				ParameterizedType argumentResolverInterface = (ParameterizedType) type;
				Type[] types = argumentResolverInterface.getActualTypeArguments();
				for (Type typeParameter : types)
				{
					if (typeParameter instanceof Class)
					{
						@SuppressWarnings("unchecked")
						Class<T> casted = (Class<T>) typeParameter;
						return casted;
					}
				}
			}
		}

		throw new IllegalStateException("Unable to find type: " + this.getClass());
	}

}