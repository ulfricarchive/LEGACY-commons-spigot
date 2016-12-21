package com.ulfric.spigot.commons.command;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.ulfric.verify.Verify;

@RunWith(JUnitPlatform.class)
class CommandTest {

	@Test
	void testCommandName()
	{
		Command command = this.getCommand("testCommandName");
		Verify.that(command.name()).isEqualTo("hello");
	}

	private Command getCommand(String methodName)
	{
		try
		{
			return Commands.class.getDeclaredMethod(methodName).getAnnotation(Command.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			throw new RuntimeException(e);
		}
	}

	class Commands
	{
		@Command(name = "hello")
		void testCommandName() { }
	}

}