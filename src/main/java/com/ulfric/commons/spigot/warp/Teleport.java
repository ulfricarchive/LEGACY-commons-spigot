package com.ulfric.commons.spigot.warp;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.service.ServiceUtils;
import com.ulfric.commons.version.Version;

@Name("teleport")
@Version(1)
public interface Teleport extends Service {

	static Teleport getService()
	{
		return ServiceUtils.getService(Teleport.class);
	}

	void teleport(Entity entity, Location to);

	void cancelTeleport(Entity entity);

	boolean isTeleporting(Entity entity);

}