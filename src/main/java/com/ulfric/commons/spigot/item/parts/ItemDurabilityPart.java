package com.ulfric.commons.spigot.item.parts;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import com.ulfric.commons.naming.Name;

@Name("durability")
enum ItemDurabilityPart implements ItemPart {

	INSTANCE;

	@Override
	public String serialize(ItemStack item, ItemMeta meta)
	{
		return String.valueOf(item.getData());
	}

	@Override
	public ItemMeta deserialize(ItemStack item, ItemMeta meta, String value)
	{
		item.setData(new MaterialData(item.getType(), Byte.parseByte(value)));

		return meta;
	}

}
