package com.ulfric.commons.spigot.text;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.metadata.Metadata;
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
		int length = (metadata.length / 2) * 2;
		for (int x = 0; x < length; x += 2)
		{
			Metadata.write(target, metadata[x], metadata[x+1]);
		}

		this.sendMessage(target, code);

		for (int x = 0; x < length; x += 2)
		{
			Metadata.delete(target, metadata[x]);
		}
	}

	default void sendMessage(CommandSender target, String code)
	{
		if (target instanceof Player)
		{
			this.sendMessage((Player) target, code);
			return;
		}

		target.sendMessage(this.getLegacyMessage(target, code));
	}

	default void sendMessage(Player target, String code, String... metadata)
	{
		int length = (metadata.length / 2) * 2;
		for (int x = 0; x < length; x += 2)
		{
			Metadata.write(target, metadata[x], metadata[x+1]);
		}

		this.sendMessage(target, code);

		for (int x = 0; x < length; x += 2)
		{
			Metadata.delete(target, metadata[x]);
		}
	}

	default void sendMessage(Player target, String code)
	{
		target.sendRawMessage(this.getRawMessage(target, code));
	}

	String getRawMessage(CommandSender target, String code);

	String getLegacyMessage(CommandSender target, String code);

	void registerPlaceholder(Placeholder placeholder);

	void unregisterPlaceholder(Placeholder placeholder);

}