package com.ulfric.commons.spigot.item.parts;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.ulfric.commons.naming.Name;

@Name("type")
enum ItemTypePart implements ItemPart {

	INSTANCE;

	@Override
	public String serialize(ItemStack item, ItemMeta meta)
	{
		return item.getType().name();
	}

	@Override
	public ItemMeta deserialize(ItemStack item, ItemMeta meta, String value)
	{
		item.setType(Material.getMaterial(value));

		return item.getItemMeta();
	}

}
