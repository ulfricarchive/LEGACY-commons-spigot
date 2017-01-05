package com.ulfric.commons.spigot.permission;

import java.lang.reflect.Method;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;

import com.ulfric.commons.cdi.intercept.Context;
import com.ulfric.commons.cdi.intercept.Interceptor;

abstract class SkeletalPermissionInterceptor implements Interceptor<Void> {

	@Override
	public final Void intercept(Context<Void> context)
	{
		for (Object object : context.getArguments())
		{
			if (object instanceof PlayerEvent)
			{
				PlayerEvent playerEvent = (PlayerEvent) object;

				if (!this.hasPermissions(playerEvent.getPlayer(), context.getOrigin()))
				{
					return null;
				}
			}
		}
		return context.proceed();
	}

	abstract boolean hasPermissions(Player player, Method method);

}
