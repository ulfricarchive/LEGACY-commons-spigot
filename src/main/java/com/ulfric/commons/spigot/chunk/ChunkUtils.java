package com.ulfric.commons.spigot.chunk;

import net.minecraft.server.v1_11_R1.BlockPosition;
import net.minecraft.server.v1_11_R1.Chunk;
import net.minecraft.server.v1_11_R1.ChunkSection;
import net.minecraft.server.v1_11_R1.IBlockData;
import org.bukkit.Material;

/**
 * Created by Connor on 4/24/2017.
 */
public class ChunkUtils {

  public IBlockData nmsBlock(Material material, byte data){
    int id = material.getId();
    int combined = id + (data << 12);
    return net.minecraft.server.v1_11_R1.Block.getByCombinedId(combined);
  }

  public void setBlockInChunk(Chunk chunk, int x, int y, int z, IBlockData iBlockData){
    chunk.a(new BlockPosition(x,y,z), iBlockData);
  }

  public void applyChanges(Chunk chunk, ChunkSection[] sections){
    System.arraycopy(sections,0,chunk.getSections(),0,sections.length);
    chunk.world.getWorld().refreshChunk(chunk.bukkitChunk.getX(),chunk.bukkitChunk.getZ());
  }

}
