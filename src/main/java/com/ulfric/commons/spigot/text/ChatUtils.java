package com.ulfric.commons.spigot.text;

import org.bukkit.ChatColor;

public enum ChatUtils {

	;

	public static String format(String text)
	{
		return ChatColor.translateAlternateColorCodes('&', text);
	}

}