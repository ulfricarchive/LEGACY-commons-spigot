package com.ulfric.commons.spigot.scoreboard;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public final class UlfricScoreboard {

	private static final String OBJECTIVE_CRITERIA = "dummy";
	private static final String ENTRY_PADDING = ChatColor.RESET + " ";
	private static final Map<UUID, UlfricScoreboard> SCOREBOARDS = new HashMap<>();

	private static int getDefaultPriority(Object object)
	{
		DefaultPriority priority = object.getClass().getAnnotation(DefaultPriority.class);
		if (priority == null)
		{
			return 0;
		}
		return priority.value();
	}

	public static UlfricScoreboard getScoreboard(Player player)
	{
		Objects.requireNonNull(player);

		return UlfricScoreboard.SCOREBOARDS.computeIfAbsent(player.getUniqueId(), ignore -> new UlfricScoreboard(player));
	}

	private final UUID player;
	private final Scoreboard scoreboard;
	private final Map<Class<?>, ScoreboardValue> entries = new TreeMap<>((o1, o2) ->
		Integer.compare(UlfricScoreboard.getDefaultPriority(o1), UlfricScoreboard.getDefaultPriority(o2)));

	private Objective sidebar;
	private String sidebarTitle;

	private UlfricScoreboard(Player player)
	{
		this.player = player.getUniqueId();
		this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		player.setScoreboard(this.scoreboard);
	}

	public void addElement(ScoreboardElement element)
	{
		Objects.requireNonNull(element);

		Player player = Bukkit.getPlayer(this.player);
		this.entries.computeIfAbsent(element.getClass(), type ->
			new ScoreboardValue(element, element.apply(player)));
	}

	public void refreshElement(Class<?> element)
	{
		if (this.entries.computeIfPresent(element, (ignore, entry) -> entry.refresh(Bukkit.getPlayer(this.player))) != null)
		{
			this.sendSidebar();
		}
	}

	public void setSidebarTitle(String sidebarTitle)
	{
		this.sidebarTitle = sidebarTitle;
		this.modifyObjective(objective -> objective.setDisplayName(sidebarTitle));
	}

	private void sendSidebar()
	{
		Objective staleSidebar = this.sidebar;
		Objective sidebar = this.createObjective();
		this.sidebar = sidebar;
		this.populateSidebar();
		this.showSidebar();

		this.modifyObjective(Objective::unregister, staleSidebar);
	}

	private void populateSidebar()
	{
		if (this.entries.isEmpty())
		{
			return;
		}

		this.sidebar.setDisplayName(this.sidebarTitle);

		List<List<String>> entries = this.entries.values()
			.stream()
			.map(ScoreboardValue::getEntry)
			.map(ScoreboardEntry::getLines)
			.collect(Collectors.toList());

		int score = entries.stream().mapToInt(List::size).sum() + entries.size();

		Iterator<List<String>> entriesIterator = entries.iterator();
		while (entriesIterator.hasNext())
		{
			List<String> element = entriesIterator.next();

			for (String entry : element)
			{
				Score entryScore = this.createScore(entry);
				entryScore.setScore(score--);
			}

			if (entriesIterator.hasNext())
			{
				Score blankLine = this.createBlankLineScore();
				blankLine.setScore(score--);
			}
		}
	}

	private Score createBlankLineScore()
	{
		return this.createScore("");
	}

	private Score createScore(String name)
	{
		Score score = this.sidebar.getScore(name);
		if (score.isScoreSet())
		{
			return this.createScore(name + UlfricScoreboard.ENTRY_PADDING);
		}
		return score;
	}

	private void showSidebar()
	{
		this.modifyObjective(objective -> objective.setDisplaySlot(DisplaySlot.SIDEBAR));
	}

	private Objective createObjective()
	{
		String name = this.generateObjectiveName();
		return this.scoreboard.registerNewObjective(name, UlfricScoreboard.OBJECTIVE_CRITERIA);
	}

	private String generateObjectiveName()
	{
		return RandomStringUtils.random(16);
	}

	private void modifyObjective(Consumer<Objective> modifier)
	{
		this.modifyObjective(modifier, this.sidebar);
	}

	private void modifyObjective(Consumer<Objective> modifier, Objective objective)
	{
		if (objective == null || !objective.isModifiable())
		{
			return;
		}

		modifier.accept(objective);
	}

}