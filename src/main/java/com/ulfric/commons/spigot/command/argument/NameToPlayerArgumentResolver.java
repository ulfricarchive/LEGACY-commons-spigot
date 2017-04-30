package com.ulfric.commons.spigot.command.argument;

import org.bukkit.entity.Player;

import com.ulfric.commons.spigot.command.Context;
import com.ulfric.commons.spigot.player.PlayerUtils;

public class NameToPlayerArgumentResolver implements ArgumentResolver<Player> {

	@Override
	public Player apply(Context context, String argument)
	{
		// TODO visibility / permission checks
		return PlayerUtils.getPlayer(argument);
	}

}