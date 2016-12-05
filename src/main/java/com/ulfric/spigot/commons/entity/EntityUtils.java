package com.ulfric.spigot.commons.entity;

import org.bukkit.entity.LivingEntity;

import com.ulfric.commons.api.Util;

public class EntityUtils implements Util {

	public static void kill(LivingEntity entity)
	{
		if (entity == null)
		{
			return;
		}

		entity.setHealth(0);
	}

	private EntityUtils()
	{
		Util.onConstruct();
	}

}