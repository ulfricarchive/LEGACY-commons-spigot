package com.ulfric.commons.spigot.scoreboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class MultiScoreboardEntry implements ScoreboardEntry {

	private final List<String> lines;

	public MultiScoreboardEntry(List<String> lines)
	{
		this.lines = Collections.unmodifiableList(new ArrayList<>(lines));
	}

	@Override
	public List<String> getLines()
	{
		return this.lines;
	}

}