package com.ulfric.commons.spigot.permission;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;

abstract class SkeletalPermissionInterceptor {

	boolean hasPermissions(PlayerEvent playerEvent, RequirePermission... permissions)
	{
		Player player = playerEvent.getPlayer();
		for (RequirePermission permission : permissions)
		{
			if (!player.hasPermission(permission.value()))
			{
				return false;
			}
		}
		return true;
	}

}
