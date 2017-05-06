package com.ulfric.commons.spigot.item.parts;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.ulfric.commons.naming.Name;

@Name("lore")
enum ItemLorePart implements ItemPart {

	INSTANCE;

	private static final Pattern SPACE = Pattern.compile("\\s");
	private static final Pattern EQUALS = Pattern.compile("=");

	private static final Pattern SPACE_SERIALIZED = Pattern.compile("<s>");
	private static final Pattern EQUALS_SERIALIZED = Pattern.compile("<e>");
	private static final Pattern NEW_LINE_SERIALIZED = Pattern.compile("<n>");

	@Override
	public String serialize(ItemStack item, ItemMeta meta)
	{
		return meta.hasLore() ? this.serializeLore(meta.getLore()) : null;
	}

	private String serializeLore(List<String> lore)
	{
		return lore.stream()
				.map(ItemLorePart::serializeSpaces)
				.map(ItemLorePart::serializeEquals)
				.collect(Collectors.joining(ItemLorePart.NEW_LINE_SERIALIZED.pattern()));
	}

	private static String serializeSpaces(String line)
	{
		return ItemLorePart.SPACE.matcher(line).replaceAll(ItemLorePart.SPACE_SERIALIZED.pattern());
	}

	private static String serializeEquals(String line)
	{
		return ItemLorePart.EQUALS.matcher(line).replaceAll(ItemLorePart.EQUALS_SERIALIZED.pattern());
	}

	@Override
	public ItemMeta deserialize(ItemStack item, ItemMeta meta, String value)
	{
		meta.setLore(this.deserializeLore(value));

		return meta;
	}

	private List<String> deserializeLore(String value)
	{
		String[] lines = ItemLorePart.NEW_LINE_SERIALIZED.split(value);

		return Stream.of(lines)
				.map(ItemLorePart::deserializeSpaces)
				.map(ItemLorePart::deserializeEquals)
				.collect(Collectors.toList());
	}

	private static String deserializeSpaces(String line)
	{
		return ItemLorePart.SPACE_SERIALIZED.matcher(line).replaceAll(" ");
	}

	private static String deserializeEquals(String line)
	{
		return ItemLorePart.EQUALS_SERIALIZED.matcher(line).replaceAll(ItemLorePart.EQUALS.pattern());
	}

}
