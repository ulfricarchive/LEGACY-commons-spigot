package com.ulfric.commons.spigot.entity;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.projectiles.ProjectileSource;

public enum PlayerUtils {
	
	;
	
	public static Player getOwningPlayer(LivingEntity entity)
	{
		if (entity instanceof Player)
		{
			return (Player) entity;
		}
		
		return null;
	}
	
	public static Player getOwningPlayer(ProjectileSource source)
	{
		if (source instanceof Player)
		{
			return (Player) source;
		}
		
		return null;
	}
	
	public static Player getOwningPlayer(CommandSender sender)
	{
		if (sender instanceof Player)
		{
			return (Player) sender;
		}
		
		return null;
	}
	
	public static Player getOwningPlayer(AnimalTamer tamer)
	{
		return Bukkit.getPlayer(tamer.getUniqueId());
	}
	
}
