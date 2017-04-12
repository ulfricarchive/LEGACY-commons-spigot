package com.ulfric.commons.spigot.scoreboard;

import java.util.function.Function;

import org.bukkit.entity.Player;

public interface ScoreboardElement extends Function<Player, ScoreboardEntry> {

}