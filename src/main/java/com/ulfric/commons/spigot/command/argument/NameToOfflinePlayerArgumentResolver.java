package com.ulfric.commons.spigot.command.argument;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import com.ulfric.commons.identity.UniqueIdUtils;
import com.ulfric.commons.spigot.command.Context;

public class NameToOfflinePlayerArgumentResolver implements ArgumentResolver<OfflinePlayer> {

	@SuppressWarnings("deprecation")
	@Override
	public OfflinePlayer apply(Context context, String argument)
	{
		OfflinePlayer player;
		UUID uniqueId = UniqueIdUtils.parseUniqueId(argument);
		if (uniqueId != null)
		{
			player = Bukkit.getOfflinePlayer(uniqueId);
		}
		else
		{
			player = Bukkit.getOfflinePlayer(argument);
		}

		if (player == null || !(player.hasPlayedBefore() || player.isOnline()))
		{
			return null;
		}

		return player;
	}

}