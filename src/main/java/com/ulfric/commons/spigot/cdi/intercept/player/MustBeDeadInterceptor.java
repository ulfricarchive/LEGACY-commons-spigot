package com.ulfric.commons.spigot.cdi.intercept.player;

import org.bukkit.entity.LivingEntity;

public class MustBeDeadInterceptor extends SkeletalLifeInterceptor {

	@Override
	boolean correctLifeState(LivingEntity entity)
	{
		return entity.isDead();
	}

}
