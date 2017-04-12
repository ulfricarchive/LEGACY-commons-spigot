package com.ulfric.commons.spigot.permissions;

import com.ulfric.commons.identity.Identity;
import com.ulfric.commons.naming.Name;
import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.service.ServiceUtils;
import com.ulfric.commons.version.Version;

@Name("Text")
@Version(1)
public interface Permissions extends Service {

	public static Permissions getService()
	{
		return ServiceUtils.getService(Permissions.class);
	}

	PermissionEntity getPermissionEntity(Identity identity);

}