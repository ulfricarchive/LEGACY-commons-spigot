package com.ulfric.commons.spigot.scoreboard;

import org.bukkit.entity.Player;

final class ScoreboardValue {

	private final ScoreboardElement element;
	private final ScoreboardEntry entry;

	public ScoreboardValue(ScoreboardElement element, ScoreboardEntry entry)
	{
		this.element = element;
		this.entry = entry;
	}

	public ScoreboardEntry getEntry()
	{
		return this.entry;
	}

	public ScoreboardValue refresh(Player player)
	{
		return new ScoreboardValue(this.element, this.element.apply(player));
	}

}