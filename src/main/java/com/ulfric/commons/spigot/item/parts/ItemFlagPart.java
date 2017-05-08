package com.ulfric.commons.spigot.item.parts;

import java.util.stream.Collectors;

import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.ulfric.commons.naming.Name;

@Name("flag")
enum ItemFlagPart implements ItemPart {

	INSTANCE;

	@Override
	public String serialize(ItemStack item, ItemMeta meta)
	{
		return meta.getItemFlags()
				.stream()
				.map(ItemFlag::name)
				.collect(Collectors.joining(" " + this.getName() + "="));
	}

	@Override
	public ItemMeta deserialize(ItemStack item, ItemMeta meta, String value)
	{
		try
		{
			ItemFlag flag = ItemFlag.valueOf(value);

			meta.addItemFlags(flag);
		}
		catch (IllegalArgumentException ignored)
		{}

		return meta;
	}

}
