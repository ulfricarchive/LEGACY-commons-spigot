package com.ulfric.commons.spigot.item.parts;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.base.Joiner;

public enum ItemParts {

	TYPE(ItemTypePart.INSTANCE),
	DURABILITY(ItemDurabilityPart.INSTANCE),
	AMOUNT(ItemDurabilityPart.INSTANCE),
	NAME(ItemNamePart.INSTANCE),
	LORE(ItemLorePart.INSTANCE);

	private static final Pattern WHITESPACE = Pattern.compile("\\s+");
	public static final Pattern EQUALS = Pattern.compile("=");

	private static final Map<String, ItemParts> BY_NAME = new HashMap<>();

	static
	{
		for (ItemParts part : ItemParts.values())
		{
			ItemParts.BY_NAME.put(part.part.lowerName(), part);
		}
	}

	public static String serialize(ItemStack item)
	{
		ItemMeta meta = item.getItemMeta();

		Joiner joiner = Joiner.on(" ").skipNulls();

		return joiner.join(
				Stream.of(ItemParts.values())
						.map(parts -> parts.part.lowerName() + "=" + parts.part.serialize(item, meta))
						.collect(Collectors.toList())
		);
	}

	public static ItemStack deserialize(String string)
	{
		String[] sections = ItemParts.WHITESPACE.split(string);

		ItemStack item = new ItemStack(Material.AIR);
		ItemMeta meta = null;

		for (String section : sections)
		{
			String[] split = ItemParts.EQUALS.split(section);

			String partName = split[0];
			String value = split[1];

			if (partName == null)
			{
				continue;
			}

			ItemParts part = ItemParts.BY_NAME.get(partName.toLowerCase());

			if (part == null)
			{
				continue;
			}

			meta = part.part.deserialize(item, meta, value);
		}

		if (meta != null)
		{
			item.setItemMeta(meta);
		}

		return item;
	}

	private final ItemPart part;

	ItemParts(ItemPart part)
	{
		this.part = part;
	}

	public String lowerName()
	{
		return this.name().toLowerCase();
	}

}
