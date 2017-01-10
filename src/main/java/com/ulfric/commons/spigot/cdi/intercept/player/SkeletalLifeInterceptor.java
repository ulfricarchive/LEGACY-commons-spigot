package com.ulfric.commons.spigot.cdi.intercept.player;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.player.PlayerEvent;

import com.ulfric.commons.cdi.intercept.Context;
import com.ulfric.commons.cdi.intercept.Interceptor;

abstract class SkeletalLifeInterceptor implements Interceptor {

	@Override
	public Object intercept(Context context)
	{
		for (Object object : context.getArguments())
		{
			LivingEntity entity = null;

			if (object instanceof PlayerEvent)
			{
				PlayerEvent event = (PlayerEvent) object;

				entity = event.getPlayer();
			}

			if (object instanceof EntityEvent)
			{
				EntityEvent event = (EntityEvent) object;

				if (event.getEntity() instanceof LivingEntity)
				{
					entity = (LivingEntity) event.getEntity();
				}
			}

			if (entity != null)
			{
				if (!this.correctLifeState(entity))
				{
					return null;
				}
			}
		}

		return context.proceed();
	}

	abstract boolean correctLifeState(LivingEntity entity);

}
