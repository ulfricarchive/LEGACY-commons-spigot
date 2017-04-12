package com.ulfric.commons.spigot.scoreboard;

import java.util.Collections;
import java.util.List;

final class SingleScoreboardEntry implements ScoreboardEntry {

	private final List<String> line;

	public SingleScoreboardEntry(String line)
	{
		this.line = Collections.singletonList(line);
	}

	@Override
	public List<String> getLines()
	{
		return this.line;
	}

}