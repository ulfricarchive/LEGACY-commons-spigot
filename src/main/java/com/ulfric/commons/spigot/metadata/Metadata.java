package com.ulfric.commons.spigot.metadata;

import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.Plugin;

import com.ulfric.commons.spigot.plugin.PluginUtils;
import com.ulfric.commons.spigot.plugin.UlfricPlugin;

public enum Metadata {

	;

	public static void write(Metadatable holder, String key, Object value)
	{
		Plugin plugin = PluginUtils.getProvidingPlugin(value.getClass())
				.orElseGet(() -> PluginUtils.getProvidingPluginOrFail(UlfricPlugin.class));
		holder.setMetadata(key, new FixedMetadataValue(plugin, value));
	}

	public static String readString(Metadatable holder, String key)
	{
		Object value = Metadata.read(holder, key);

		if (value instanceof String)
		{
			return (String) value;
		}

		return null;
	}

	public static Object read(Metadatable holder, String key)
	{
		MetadataValue metadata = Metadata.getMetadata(holder, key);

		if (metadata == null)
		{
			return null;
		}

		return metadata.value();
	}

	public static MetadataValue getMetadata(Metadatable holder, String key)
	{
		for (MetadataValue value : holder.getMetadata(key))
		{
			if (value.getOwningPlugin() instanceof UlfricPlugin)
			{
				return value;
			}
		}

		return null;
	}

}