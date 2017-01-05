package com.ulfric.commons.spigot.permission;

import java.lang.reflect.Method;

import org.bukkit.entity.Player;

public final class RequirePermissionsInterceptor extends SkeletalPermissionInterceptor {

	@Override
	final boolean hasPermissions(Player player, Method method)
	{
		for (RequirePermission permission : method.getDeclaredAnnotation(RequirePermissions.class).value())
		{
			if (!player.hasPermission(permission.value()))
			{
				return false;
			}
		}
		return true;
	}

}
