package com.ulfric.commons.spigot.logging;

import java.lang.reflect.Field;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.ulfric.verify.Verify;

@RunWith(JUnitPlatform.class)
class LoggerUtilsTest {

	@BeforeEach
	void init() throws Exception
	{
		Field field = Bukkit.class.getDeclaredField("server");
		field.setAccessible(true);
		field.set(null, null);
	}

	@Test
	void testGetLoggerWhenBukkitServerIsNull()
	{
		Verify.that(Bukkit.getServer()).isNull();
		Verify.that(LoggerUtils.getGlobalLogger()).isNotNull();
	}

	@Test
	void testGetLoggerWhenBukkitServerIsNotNull()
	{
		Logger logger = Logger.getLogger("test");
		Server mockServer = Mockito.mock(Server.class);
		Mockito.when(mockServer.getLogger()).thenReturn(logger);
		Bukkit.setServer(mockServer);
		Verify.that(LoggerUtils.getGlobalLogger()).isSameAs(logger);
	}

}