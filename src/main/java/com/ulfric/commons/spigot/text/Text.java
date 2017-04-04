package com.ulfric.commons.spigot.text;

import org.bukkit.command.CommandSender;

import com.ulfric.commons.service.Service;

public interface Text extends Service {

	String getMessage(CommandSender target, String code);

}