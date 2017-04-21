package com.ulfric.commons.spigot.location;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;

import com.ulfric.commons.identity.UniqueIdUtils;

public enum WorldUtils {

	;

	public static World getWorld(String nameOrUniqueId)
	{
		UUID uniqueId = UniqueIdUtils.parseUniqueId(nameOrUniqueId);
		if (uniqueId != null)
		{
			World world = Bukkit.getWorld(uniqueId);

			if (world != null)
			{
				return world;
			}
		}

		return Bukkit.getWorld(nameOrUniqueId);
	}

	public static World getDefaultWorld()
	{
		return Bukkit.getWorlds().stream().findFirst().orElse(null);
	}

}