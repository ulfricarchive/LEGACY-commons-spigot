package com.ulfric.commons.spigot.command;

public class CommandException extends RuntimeException {

	private final String detail;

	public CommandException(String message, String detail)
	{
		super(message);
		this.detail = detail;
	}

	public String getDetail()
	{
		return this.detail;
	}

}