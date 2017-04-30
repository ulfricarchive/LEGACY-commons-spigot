package com.ulfric.commons.spigot.player;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.ulfric.commons.identity.UniqueIdUtils;

public enum PlayerUtils {

	;

	public static OfflinePlayer getOfflinePlayerWithHistory(String parse)
	{
		OfflinePlayer player = PlayerUtils.getOfflinePlayer(parse);
		if (player != null && PlayerUtils.hasPlayedBefore(player))
		{
			return player;
		}
		return null;
	}

	public static OfflinePlayer getOfflinePlayer(String parse)
	{
		UUID uniqueId = UniqueIdUtils.parseUniqueId(parse);
		if (uniqueId == null)
		{
			return PlayerUtils.getOfflinePlayerByName(parse);
		}
		return PlayerUtils.getOfflinePlayerByUniqueId(uniqueId);
	}

	@SuppressWarnings("deprecation")
	private static OfflinePlayer getOfflinePlayerByName(String name)
	{
		Player player = Bukkit.getPlayerExact(name);
		if (player != null)
		{
			return player;
		}
		return Bukkit.getOfflinePlayer(name);
	}

	private static OfflinePlayer getOfflinePlayerByUniqueId(UUID uniqueId)
	{
		Player player = Bukkit.getPlayer(uniqueId);
		if (player != null)
		{
			return player;
		}
		return Bukkit.getOfflinePlayer(uniqueId);
	}

	public static Player getPlayer(String parse)
	{
		UUID uniqueId = UniqueIdUtils.parseUniqueId(parse);
		if (uniqueId == null)
		{
			return Bukkit.getPlayer(parse);
		}
		return Bukkit.getPlayer(uniqueId);
	}

	public static boolean hasPlayedBefore(OfflinePlayer player)
	{
		return player.hasPlayedBefore() || player.isOnline();
	}

}