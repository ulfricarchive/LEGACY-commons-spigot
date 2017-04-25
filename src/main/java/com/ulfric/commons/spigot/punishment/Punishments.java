package com.ulfric.commons.spigot.punishment;

import java.net.InetAddress;
import java.util.UUID;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.service.ServiceUtils;

public interface Punishments extends Service {

	static Punishments getService()
	{
		return ServiceUtils.getService(Punishments.class);
	}

	default Punisher getPunisher(CommandSender sender)
	{
		if (sender instanceof ConsoleCommandSender)
		{
			return this.getConsolePunisher();
		}

		if (sender instanceof Player)
		{
			return this.getPunisher(((Entity) sender).getUniqueId());
		}

		throw new IllegalArgumentException("Could not get punisher from " + sender);
	}

	Punisher getConsolePunisher();

	Punisher getPluginPunisher();

	Punisher getPunisher(UUID uniqueId);

	PunishmentHolder getPunishmentHolder(UUID uniqueId);

	PunishmentHolder getPunishmentHolder(InetAddress address);

}