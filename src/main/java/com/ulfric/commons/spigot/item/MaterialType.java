package com.ulfric.commons.spigot.item;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

import org.bukkit.Material;

import com.ulfric.commons.bean.Bean;

import gnu.trove.map.TByteObjectMap;
import gnu.trove.map.hash.TByteObjectHashMap;

public final class MaterialType extends Bean {

	private static final Map<Material, TByteObjectMap<MaterialType>> CACHE = new EnumMap<>(Material.class);

	public static MaterialType getType(Material material)
	{
		return MaterialType.getType(material, (byte) 0);
	}

	public static MaterialType getType(Material material, byte data)
	{
		Objects.requireNonNull(material);

		TByteObjectMap<MaterialType> dataToType = 
				MaterialType.CACHE.computeIfAbsent(material, ignore -> new TByteObjectHashMap<>());

		MaterialType type = dataToType.get(data);
		if (type == null)
		{
			type = new MaterialType(material, data);
			dataToType.put(data, type);
		}
		return type;
	}

	private final Material type;
	private final byte data;

	private MaterialType(Material type, byte data)
	{
		this.type = type;
		this.data = data;
	}

	public Material getType()
	{
		return this.type;
	}

	public byte getData()
	{
		return this.data;
	}

}