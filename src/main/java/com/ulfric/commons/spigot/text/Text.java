package com.ulfric.commons.spigot.text;

import org.bukkit.command.CommandSender;

public interface Text {

	String getMessage(CommandSender target, String code);

}