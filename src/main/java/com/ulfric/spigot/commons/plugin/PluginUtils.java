package com.ulfric.spigot.commons.plugin;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import com.ulfric.commons.api.UtilInstantiationException;

public class PluginUtils {

	public static List<Plugin> getAllPlugins()
	{
		return Arrays.stream(Bukkit.getPluginManager().getPlugins()).collect(Collectors.toList());
	}

	public static Optional<Plugin> getOwningPlugin(Class<?> loadedClass)
	{
		Objects.requireNonNull(loadedClass);

		ClassLoader loader = loadedClass.getClassLoader();
		for (Plugin plugin : PluginUtils.getAllPlugins())
		{
			Class<? extends Plugin> pluginClass = plugin.getClass();
			if (pluginClass == loadedClass ||
					pluginClass.getClassLoader() == loader)
			{
				return Optional.of(plugin);
			}
		}

		return Optional.empty();
	}

	private PluginUtils()
	{
		throw new UtilInstantiationException();
	}

}