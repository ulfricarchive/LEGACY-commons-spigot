package com.ulfric.commons.spigot.warp;

import com.ulfric.commons.spigot.warp.WarpException;

public class SpawnException extends WarpException {
	
	public SpawnException(String message, Exception exception)
	{
		super(message, exception);
	}
	
	public SpawnException(String message)
	{
		super(message);
	}
	
	public SpawnException()
	{
		super();
	}
	
}
