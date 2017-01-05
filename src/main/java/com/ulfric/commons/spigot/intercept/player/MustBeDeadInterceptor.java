package com.ulfric.commons.spigot.intercept.player;

import org.bukkit.entity.Player;

public class MustBeDeadInterceptor extends SkeletalLifeInterceptor {

	@Override
	boolean correctLifeState(Player player)
	{
		return player.isDead();
	}

}
