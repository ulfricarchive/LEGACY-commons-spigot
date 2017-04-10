package com.ulfric.commons.spigot.intercept;

import java.util.Optional;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;

import com.ulfric.commons.spigot.text.Text;
import com.ulfric.dragoon.intercept.Context;
import com.ulfric.dragoon.intercept.Interceptor;

public class RequirePermissionInterceptor implements Interceptor {

	private final Text text = Text.getService();

	@Override
	public Object intercept(Context context)
	{
		RequirePermission annotation = context.getDestinationExecutable().getAnnotation(RequirePermission.class);
		Player player = this.extractPlayerFromArguments(context.getArguments()).orElseThrow(this::cantFindPlayer);

		if (!player.hasPermission(annotation.permission()))
		{
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

		if (argument instanceof PlayerEvent)
		{
			return ((PlayerEvent) argument).getPlayer();
		}

		return null;
	}

	private IllegalArgumentException cantFindPlayer()
	{
		return new IllegalArgumentException("Player could not be extracted from arguments");
	}

}
