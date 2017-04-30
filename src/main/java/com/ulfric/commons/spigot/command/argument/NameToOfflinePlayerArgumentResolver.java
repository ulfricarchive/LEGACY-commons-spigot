package com.ulfric.commons.spigot.command.argument;

import org.bukkit.OfflinePlayer;

import com.ulfric.commons.spigot.command.Context;
import com.ulfric.commons.spigot.player.PlayerUtils;

public class NameToOfflinePlayerArgumentResolver implements ArgumentResolver<OfflinePlayer> {

	@Override
	public OfflinePlayer apply(Context context, String argument)
	{
		return PlayerUtils.getOfflinePlayerWithHistory(argument);
	}

}