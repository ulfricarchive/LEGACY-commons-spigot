package com.ulfric.commons.spigot.chunk;

import org.bukkit.Material;

import net.minecraft.server.v1_11_R1.Block;
import net.minecraft.server.v1_11_R1.BlockPosition;
import net.minecraft.server.v1_11_R1.Chunk;
import net.minecraft.server.v1_11_R1.ChunkSection;
import net.minecraft.server.v1_11_R1.IBlockData;

public enum ChunkUtils {

	;

	public static IBlockData nmsBlock(Material material, byte data)
	{
		int id = material.getId();
		int combined = id + (data << 12);

		return Block.getByCombinedId(combined);
	}

	public static void setBlockInChunk(Chunk chunk, int x, int y, int z, IBlockData iBlockData)
	{
		chunk.a(new BlockPosition(x, y, z), iBlockData);
	}

	public static void applyChanges(Chunk chunk, ChunkSection[] sections)
	{
		System.arraycopy(sections, 0, chunk.getSections(), 0, sections.length);
		chunk.world.getWorld().refreshChunk(chunk.bukkitChunk.getX(), chunk.bukkitChunk.getZ());
	}

}
