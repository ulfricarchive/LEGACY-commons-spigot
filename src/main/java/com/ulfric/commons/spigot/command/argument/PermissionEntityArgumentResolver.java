package com.ulfric.commons.spigot.command.argument;

import java.util.UUID;

import com.ulfric.commons.identity.Identity;
import com.ulfric.commons.identity.UniqueIdUtils;
import com.ulfric.commons.spigot.command.Context;
import com.ulfric.commons.spigot.permissions.PermissionEntity;
import com.ulfric.commons.spigot.permissions.Permissions;

public class PermissionEntityArgumentResolver implements ArgumentResolver<PermissionEntity> {

	@Override
	public PermissionEntity apply(Context context, String argument)
	{
		UUID uniqueId = UniqueIdUtils.parseUniqueId(argument);
		Identity identity = Identity.of(uniqueId == null ? argument : uniqueId);
		return Permissions.getService().getPermissionEntity(identity);
	}

}