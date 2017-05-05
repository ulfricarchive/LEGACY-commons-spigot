package com.ulfric.commons.spigot.block;

import com.ulfric.commons.spigot.event.Events;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.HashSet;
import java.util.Set;

public enum BlockUtils {
	
	;
	
	public static boolean callArtificialBreak(Player player, Block block)
	{
		return Events.fire(new BlockBreakEvent(block, player)).isCancelled();
	}
	
	public static Set<Block> getBlocks(Block origin, int radius)
	{
		Set<Block> blocks = new HashSet<>();
		blocks.add(origin);
		
		int originX = origin.getX();
		int originY = origin.getY();
		int originZ = origin.getZ();
		
		for (int x = originX - radius; x <= originX + radius; x++)
		{
			for (int y = originY - radius; y <= originY + radius; y++)
			{
				for (int z = originZ - radius; z <= originZ + radius; z++)
				{
					Block current = origin.getWorld().getBlockAt(x, y, z);
					
					blocks.add(current);
				}
			}
		}
		
		return blocks;
	}
	
	public static Set<Block> getBlocks(Location origin, int radius)
	{
		return BlockUtils.getBlocks(origin.getBlock(), radius);
	}
	
}
