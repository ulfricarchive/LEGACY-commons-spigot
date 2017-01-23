package com.ulfric.commons.spigot.plugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public enum PluginUtils {

	;

	private static final Class<?> PLUGIN_CLASS_LOADER_CLASS;
	private static final Field JAVA_PLUGIN_FIELD;

	static
	{
		try
		{
			PLUGIN_CLASS_LOADER_CLASS = Class.forName("org.bukkit.plugin.java.PluginClassLoader");
			JAVA_PLUGIN_FIELD = PluginUtils.PLUGIN_CLASS_LOADER_CLASS.getDeclaredField("pluginInit");

			PluginUtils.JAVA_PLUGIN_FIELD.setAccessible(true);
		}
		catch (ClassNotFoundException | NoSuchFieldException | SecurityException e)
		{
			throw new RuntimeException(e);
		}
	}

	public static Plugin getProvidingPlugin(Class<?> loadedClass)
	{
		Objects.requireNonNull(loadedClass);

		ClassLoader loader = loadedClass.getClassLoader();
		if (PluginUtils.PLUGIN_CLASS_LOADER_CLASS.isInstance(loader))
		{
			try
			{
				return (Plugin) PluginUtils.JAVA_PLUGIN_FIELD.get(loader);
			}
			catch (IllegalArgumentException | IllegalAccessException e)
			{
				throw new RuntimeException(e);
			}
		}

		throw new PluginMissingException(loadedClass);
	}

	public static List<Plugin> getAllPlugins()
	{
		Plugin[] pluginArray = Bukkit.getPluginManager().getPlugins();
		List<Plugin> plugins = new ArrayList<>(pluginArray.length);
		CollectionUtils.addAll(plugins, pluginArray);
		return plugins;
	}

}