package com.ulfric.commons.spigot.item;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

import org.bukkit.Material;

import com.ulfric.commons.bean.Bean;

import gnu.trove.map.TShortObjectMap;
import gnu.trove.map.hash.TShortObjectHashMap;

public final class MaterialType extends Bean {

	private static final Map<Material, TShortObjectMap<MaterialType>> CACHE = new EnumMap<>(Material.class);

	public static MaterialType getType(Material material)
	{
		return MaterialType.getType(material, (short) 0);
	}

	public static MaterialType getType(Material material, short data)
	{
		Objects.requireNonNull(material);

		TShortObjectMap<MaterialType> dataToType = 
				MaterialType.CACHE.computeIfAbsent(material, ignore -> new TShortObjectHashMap<>());

		MaterialType type = dataToType.get(data);
		if (type == null)
		{
			type = new MaterialType(material, data);
			dataToType.put(data, type);
		}
		return type;
	}

	private final Material type;
	private final short data;

	private MaterialType(Material type, short data)
	{
		this.type = type;
		this.data = data;
	}

	public Material getType()
	{
		return this.type;
	}

	public short getData()
	{
		return this.data;
	}

}