package com.ulfric.commons.spigot.intercept;

import java.lang.reflect.Field;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.bukkit.entity.Player;

import com.ulfric.commons.exception.Try;
import com.ulfric.commons.spigot.metadata.Metadata;
import com.ulfric.commons.spigot.metadata.MetadataDefaults;
import com.ulfric.commons.spigot.text.Text;
import com.ulfric.dragoon.intercept.Context;
import com.ulfric.dragoon.intercept.Interceptor;

public class RequirePermissionInterceptor implements Interceptor {

	private static final Map<Class<?>, List<Field>> PLAYER_FIELDS = new IdentityHashMap<>();

	private final Text text = Text.getService();

	@Override
	public Object intercept(Context context)
	{
		RequirePermission annotation = context.getDestinationExecutable().getAnnotation(RequirePermission.class);

		Player player = this.extractPlayerFromArguments(context.getArguments())
				.orElseThrow(() -> new IllegalArgumentException("Player could not be extracted from arguments"));

		if (!player.hasPermission(annotation.permission()))
		{
			Metadata.write(player, MetadataDefaults.NO_PERMISSION, annotation.permission());
			this.text.sendMessage(player, annotation.message());
			return null;
		}

		return context.proceed();
	}

	private Optional<Player> extractPlayerFromArguments(Object[] arguments)
	{
		for (Object argument : arguments)
		{
			Player player = this.extractPlayerFromArgument(argument);

			if (player != null)
			{
				return Optional.of(player);
			}
		}

		return Optional.empty();
	}

	private Player extractPlayerFromArgument(Object argument)
	{
		if (argument instanceof Player)
		{
			return (Player) argument;
		}

		return RequirePermissionInterceptor.PLAYER_FIELDS.computeIfAbsent(argument.getClass(), this::findPlayerFields)
				.stream()
				.map(field -> (Player) this.getFieldValue(field, argument))
				.filter(Objects::nonNull)
				.findFirst()
				.orElse(null);
	}

	private List<Field> findPlayerFields(Class<?> clazz)
	{
		return FieldUtils.getAllFieldsList(clazz)
				.stream()
				.filter(this::fieldTypeIsPlayer)
				.peek(field -> field.setAccessible(true))
				.collect(Collectors.toList());
	}

	private boolean fieldTypeIsPlayer(Field field)
	{
		return Player.class.isAssignableFrom(field.getType());
	}

	private Object getFieldValue(Field field, Object instance)
	{
		return Try.to(() -> field.get(instance));
	}

}
