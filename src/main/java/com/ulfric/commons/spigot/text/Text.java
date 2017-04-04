package com.ulfric.commons.spigot.text;

import org.bukkit.command.CommandSender;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.service.Service;
import com.ulfric.commons.version.Version;

@Name("Text")
@Version(1)
public interface Text extends Service {

	String getMessage(CommandSender target, String code);

}