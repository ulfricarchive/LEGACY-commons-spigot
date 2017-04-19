package com.ulfric.commons.spigot.guard;

import org.bukkit.Location;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.service.ServiceUtils;
import com.ulfric.commons.version.Version;

@Name("Guard")
@Version(1)
public interface Guard extends Service, RegionColl {

	public static Guard getService()
	{
		return ServiceUtils.getService(Guard.class);
	}

	Region getRegion(String name);

	boolean isAllowed(Location location, Flag<Boolean> flag);

	Flag<?> getFlag(String name);

	void registerFlag(Flag<?> flag);

	void unregisterFlag(Flag<?> flag);

}