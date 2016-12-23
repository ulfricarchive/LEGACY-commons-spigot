package com.ulfric.spigot.commons.command;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.ulfric.verify.Verify;

@RunWith(JUnitPlatform.class)
class CommandInvokerTest {

	@Test
	void testNewInstanceDoesNotThrowExceptions()
	{
		Verify.that(CommandInvoker::new).runsWithoutExceptions();
	}

	@Test
	void testOnCommandReturnsTrue()
	{
		Verify.that(new CommandInvoker().onCommand(null, null, null, null)).isTrue();
	}

}