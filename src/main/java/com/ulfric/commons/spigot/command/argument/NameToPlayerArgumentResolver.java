package com.ulfric.commons.spigot.command.argument;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.ulfric.commons.spigot.command.Context;

public class NameToPlayerArgumentResolver implements ArgumentResolver<Player> {

	@Override
	public Player apply(Context context, String argument)
	{
		// TODO visibility / permission checks
		return Bukkit.getPlayer(argument);
	}

}