package com.ulfric.commons.spigot.entity;

import java.util.EnumSet;
import java.util.Set;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public enum LivingEntityUtils {

	;

	private static final Set<Material> TRANSPARENTS;

	static
	{
		Set<Material> transparents = EnumSet.noneOf(Material.class);
		for (Material material : Material.values())
		{
			if (!material.isBlock()
					|| material.isSolid()
					|| material.isOccluding())
			{
				continue;
			}

			transparents.add(material);
		}
		TRANSPARENTS = transparents;
	}

	public static Block getSelectedBlock(LivingEntity entity)
	{
		int range = LivingEntityUtils.getBlockBreakRange(entity);
		return entity.getTargetBlock(LivingEntityUtils.TRANSPARENTS, range);
	}

	private static int getBlockBreakRange(LivingEntity entity)
	{
		return entity instanceof Player
				&& ((Player) entity).getGameMode() == GameMode.CREATIVE
				? 5 : 4;
	}

}