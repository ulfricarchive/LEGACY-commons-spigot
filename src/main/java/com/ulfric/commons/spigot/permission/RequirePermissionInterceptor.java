package com.ulfric.commons.spigot.permission;

import java.lang.reflect.Method;

import org.bukkit.entity.Player;

public final class RequirePermissionInterceptor extends SkeletalPermissionInterceptor {

	@Override
	final boolean hasPermissions(Player player, Method method)
	{
		return player.hasPermission(method.getDeclaredAnnotation(RequirePermission.class).value());
	}

}
