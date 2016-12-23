package com.ulfric.spigot.commons.command;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.ulfric.verify.Verify;

@RunWith(JUnitPlatform.class)
class CommandSchemaTest {

	@Test
	void testNewInstanceNull()
	{
		Verify.that(() -> CommandSchema.newInstance(null)).doesThrow(NullPointerException.class);
	}

	@Test
	void testNewInstanceMockIsNotNull()
	{
		Verify.that(this.mockNewInstance()).isNotNull();
	}

	@Test
	void testNewInstanceMockIsUnique()
	{
		Verify.that(this::mockNewInstance).suppliesUniqueValues();
	}

	@Test
	void testBuildContextThrowsNullPointerExceptionIfPassedNullSender()
	{
		String[] args = new String[0];
		Verify.that(() -> this.mockNewInstance().buildContext(null, args)).doesThrow(NullPointerException.class);
	}

	@Test
	void testBuildContextThrowsNullPointerExceptionIfPassedNullArgs()
	{
		CommandSender sender = Mockito.mock(CommandSender.class);
		Verify.that(() -> this.mockNewInstance().buildContext(sender, null)).doesThrow(NullPointerException.class);
	}

	@Test
	void testBuildContextReturnsList()
	{
		CommandSender sender = Mockito.mock(CommandSender.class);
		String[] args = new String[0];
		Verify.that(this.mockNewInstance().buildContext(sender, args)).isInstanceOf(List.class);
	}

	private CommandSchema mockNewInstance()
	{
		return CommandSchema.newInstance(Mockito.mock(Command.class));
	}

}