package com.ulfric.commons.spigot.item.parts;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.ulfric.commons.naming.Name;

@Name("skullowner")
enum ItemSkullOwnerPart implements ItemPart {

	INSTANCE;

	@Override
	public String serialize(ItemStack item, ItemMeta meta)
	{
		if (meta instanceof SkullMeta)
		{
			return ((SkullMeta) meta).getOwner();
		}

		return null;
	}

	@Override
	public ItemMeta deserialize(ItemStack item, ItemMeta meta, String value)
	{
		((SkullMeta) meta).setOwner(value);

		return meta;
	}

}
