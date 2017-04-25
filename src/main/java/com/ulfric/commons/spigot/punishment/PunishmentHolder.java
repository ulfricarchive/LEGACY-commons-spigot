package com.ulfric.commons.spigot.punishment;

import java.util.List;

import org.bukkit.entity.Player;

public interface PunishmentHolder extends Punisher {

	List<Player> getOnlinePlayers();

}