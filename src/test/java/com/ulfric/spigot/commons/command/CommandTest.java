package com.ulfric.spigot.commons.command;

import java.lang.annotation.Annotation;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.ulfric.spigot.commons.permission.Permission;
import com.ulfric.verify.Verify;

@RunWith(JUnitPlatform.class)
class CommandTest {

	@Test
	void testCommandNameSingle()
	{
		Command command = this.get("testCommandNameSingle", Command.class);
		Verify.that(command.name()).isEqualTo("hello");
	}

	@Test
	void testCommandNameMultiple()
	{
		Commands Commands = this.get("testCommandNameMultiple", Commands.class);
		Command[] values = Commands.value();
		Verify.that(values.length).isNotZero();
		for (Command value : values)
		{
			Verify.that(value.name()).isEqualTo("hello");
		}
	}

	@Test
	void testCommandPermissionSingle()
	{
		Command command = this.get("testCommandPermissionSingle", Command.class);
		Verify.that(command.permissions()[0].value()).isEqualTo("hello");
	}

	private <T extends Annotation> T get(String methodName, Class<T> type)
	{
		try
		{
			return Cmds.class.getDeclaredMethod(methodName).getAnnotation(type);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			throw new RuntimeException(e);
		}
	}

	class Cmds
	{
		@Command(name = "hello")
		void testCommandNameSingle() { }

		@Command(name = "hello")
		@Command(name = "hello")
		void testCommandNameMultiple() { }

		@Command(name = "hello", permissions = @Permission("hello"))
		void testCommandPermissionSingle() { }
	}

}