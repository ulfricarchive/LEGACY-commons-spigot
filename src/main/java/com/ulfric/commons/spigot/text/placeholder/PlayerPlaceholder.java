package com.ulfric.commons.spigot.text.placeholder;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface PlayerPlaceholder extends Placeholder {

	@Override
	default String apply(CommandSender target)
	{
		if (target instanceof Player)
		{
			return this.apply((Player) target);
		}

		return null;
	}

	String apply(Player player);

}