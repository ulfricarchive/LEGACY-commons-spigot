package com.ulfric.commons.spigot.server;

public enum TickUtils {

	;

	private static final long TICKS_PER_SECOND = 20L;

	public static long ticksFromSeconds(long seconds)
	{
		return TickUtils.TICKS_PER_SECOND * seconds;
	}

}