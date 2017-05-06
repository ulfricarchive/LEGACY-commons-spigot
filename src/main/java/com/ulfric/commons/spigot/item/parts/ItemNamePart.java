package com.ulfric.commons.spigot.item.parts;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.ulfric.commons.naming.Name;

@Name("name")
enum ItemNamePart implements ItemPart {

	INSTANCE;

	@Override
	public String serialize(ItemStack item, ItemMeta meta)
	{
		return meta.hasDisplayName() ? meta.getDisplayName() : null;
	}

	@Override
	public ItemMeta deserialize(ItemStack item, ItemMeta meta, String value)
	{
		meta.setDisplayName(value);

		return meta;
	}

}
