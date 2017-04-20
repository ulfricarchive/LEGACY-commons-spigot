package com.ulfric.commons.spigot.warp;

public class WarpException extends RuntimeException {
	
	public WarpException(String message, Exception exception)
	{
		super(message, exception);
	}
	
	public WarpException(String message)
	{
		super(message);
	}
	
	public WarpException()
	{
		super();
	}
	
}
