package com.ulfric.commons.spigot.permission;

import org.bukkit.event.player.PlayerEvent;

import com.ulfric.commons.cdi.intercept.Context;
import com.ulfric.commons.cdi.intercept.Interceptor;

public class PermissionInterceptor implements Interceptor<Void> {

	@Override
	public Void intercept(Context<Void> context)
	{
		for (Object object : context.getArguments())
		{
			if (object instanceof PlayerEvent)
			{
				PlayerEvent event = (PlayerEvent) object;

				if (!event.getPlayer().hasPermission(context.getOrigin().getDeclaredAnnotation(Permission.class).value()))
				{
					return null;
				}
			}
		}
		return context.proceed();
	}

}
