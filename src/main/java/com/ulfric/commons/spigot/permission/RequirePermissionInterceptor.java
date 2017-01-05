package com.ulfric.commons.spigot.permission;

import org.bukkit.event.player.PlayerEvent;

import com.ulfric.commons.cdi.intercept.Context;
import com.ulfric.commons.cdi.intercept.Interceptor;

public class RequirePermissionInterceptor extends SkeletalPermissionInterceptor implements Interceptor<Void> {

	@Override
	public Void intercept(Context<Void> context)
	{
		RequirePermission permission = context.getOrigin().getDeclaredAnnotation(RequirePermission.class);

		for (Object object : context.getArguments())
		{
			if (object instanceof PlayerEvent)
			{
				if (!this.hasPermissions((PlayerEvent) object, permission))
				{
					return null;
				}
			}
		}
		return context.proceed();
	}

}
