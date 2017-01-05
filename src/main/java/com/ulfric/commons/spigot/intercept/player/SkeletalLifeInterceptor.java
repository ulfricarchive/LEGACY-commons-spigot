package com.ulfric.commons.spigot.intercept.player;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;

import com.ulfric.commons.cdi.intercept.Context;
import com.ulfric.commons.cdi.intercept.Interceptor;

abstract class SkeletalLifeInterceptor implements Interceptor<Void> {

	@Override
	public Void intercept(Context<Void> context)
	{
		for (Object object : context.getArguments())
		{
			if (object instanceof PlayerEvent)
			{
				PlayerEvent event = (PlayerEvent) object;

				if (!this.correctLifeState(event.getPlayer()))
				{
					return null;
				}
			}
		}

		return context.proceed();
	}

	abstract boolean correctLifeState(Player player);

}
