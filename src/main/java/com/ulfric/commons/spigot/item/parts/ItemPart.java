package com.ulfric.commons.spigot.item.parts;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.ulfric.commons.naming.Named;

public interface ItemPart extends Named {

	String serialize(ItemStack item, ItemMeta meta);

	ItemMeta deserialize(ItemStack item, ItemMeta meta, String value);

	default String lowerName()
	{
		return this.getName().toLowerCase();
	}

}
