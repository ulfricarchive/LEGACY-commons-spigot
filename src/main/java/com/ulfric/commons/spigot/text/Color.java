package com.ulfric.commons.spigot.text;

import org.bukkit.command.CommandSender;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.service.ServiceUtils;
import com.ulfric.commons.version.Version;

@Name("Color")
@Version(1)
public interface Color extends Service {

	static Color getService()
	{
		return ServiceUtils.getService(Color.class);
	}

	String primary(CommandSender sender);

	String secondary(CommandSender sender);

	String tertiary(CommandSender sender);

	String warning(CommandSender sender);

}
