package com.ulfric.commons.spigot.intercept.player;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityEvent;

import com.ulfric.commons.cdi.intercept.Context;
import com.ulfric.commons.cdi.intercept.Interceptor;

abstract class SkeletalLifeInterceptor implements Interceptor<Void> {

	@Override
	public Void intercept(Context<Void> context)
	{
		for (Object object : context.getArguments())
		{
			if (object instanceof EntityEvent)
			{
				EntityEvent event = (EntityEvent) object;

				if (event.getEntity() instanceof LivingEntity)
				{
					LivingEntity entity = (LivingEntity) event.getEntity();

					if (!this.correctLifeState(entity))
					{
						return null;
					}
				}
			}
		}

		return context.proceed();
	}

	abstract boolean correctLifeState(LivingEntity entity);

}
