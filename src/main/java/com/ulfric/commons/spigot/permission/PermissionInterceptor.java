package com.ulfric.commons.spigot.permission;

import org.bukkit.event.player.PlayerEvent;

import com.ulfric.commons.cdi.intercept.Context;
import com.ulfric.commons.cdi.intercept.Interceptor;

public class PermissionInterceptor implements Interceptor<Void> {

	@Override
	public Void intercept(Context<Void> context)
	{
		RequirePermission[] permissions = context.getOrigin().getDeclaredAnnotation(RequirePermissions.class).value();

		for (Object object : context.getArguments())
		{
			if (object instanceof PlayerEvent)
			{
				PlayerEvent event = (PlayerEvent) object;

				for (RequirePermission permission : permissions)
				{
					if (!event.getPlayer().hasPermission(permission.value()))
					{
						return null;
					}
				}
			}
		}
		return context.proceed();
	}

}
