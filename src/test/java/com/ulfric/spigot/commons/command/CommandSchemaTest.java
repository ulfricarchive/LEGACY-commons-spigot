package com.ulfric.spigot.commons.command;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
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
	void testBuildContextReturnsList()
	{
		Verify.that(this.mockNewInstance().buildContext(Matchers.any(), Matchers.any())).isInstanceOf(List.class);
	}

	private CommandSchema mockNewInstance()
	{
		return CommandSchema.newInstance(Mockito.mock(Command.class));
	}

}