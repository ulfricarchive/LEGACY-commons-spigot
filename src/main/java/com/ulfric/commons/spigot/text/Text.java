package com.ulfric.commons.spigot.text;

import org.bukkit.command.CommandSender;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.service.ServiceUtils;
import com.ulfric.commons.spigot.text.placeholder.Placeholder;
import com.ulfric.commons.version.Version;

@Name("Text")
@Version(1)
public interface Text extends Service {

	static Text getService()
	{
		return ServiceUtils.getService(Text.class);
	}

	default void sendMessage(CommandSender target, String code)
	{
		target.sendMessage(this.getMessage(target, code));
	}

	String getMessage(CommandSender target, String code);

	void registerPlaceholder(Placeholder placeholder);

	void unregisterPlaceholder(Placeholder placeholder);

}