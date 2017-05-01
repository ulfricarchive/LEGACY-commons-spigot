package com.ulfric.commons.spigot.text;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

	default void sendMessage(CommandSender target, String code, String... metadata)
	{
		if (target instanceof Player)
		{
			this.sendMessage((Player) target, code, metadata);
			return;
		}

		target.sendMessage(this.getPlainMessage(target, code, metadata));
	}

	default void sendMessage(CommandSender target, String code)
	{
		if (target instanceof Player)
		{
			this.sendMessage((Player) target, code);
			return;
		}

		target.sendMessage(this.getPlainMessage(target, code));
	}

	void sendMessage(Player target, String code, String... metadata);

	void sendMessage(Player target, String code);

	String getPlainMessage(CommandSender target, String code, String... metadata);

	String getPlainMessage(CommandSender target, String code);

	String getPlainMessage(String code, String... metadata);

	String getPlainMessage(String code);

	void registerPlaceholder(Placeholder placeholder);

	void unregisterPlaceholder(Placeholder placeholder);

}