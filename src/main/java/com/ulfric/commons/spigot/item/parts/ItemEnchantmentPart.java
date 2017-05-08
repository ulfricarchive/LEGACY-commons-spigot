package com.ulfric.commons.spigot.item.parts;

import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.ulfric.commons.naming.Name;

@Name("enchant")
enum ItemEnchantmentPart implements ItemPart {

	INSTANCE;

	private static final Pattern DOT = Pattern.compile("\\.");

	@Override
	public String serialize(ItemStack item, ItemMeta meta)
	{
		Map<Enchantment, Integer> enchants = meta.getEnchants();

		return enchants.keySet()
				.stream()
				.map(enchant -> enchant.getName() + "." + enchants.get(enchant))
				.collect(Collectors.joining(" " + this.getName() + "="));
	}

	@Override
	public ItemMeta deserialize(ItemStack item, ItemMeta meta, String value)
	{
		String[] parts = ItemEnchantmentPart.DOT.split(value);

		Enchantment enchant = Enchantment.getByName(parts[0]);

		if (enchant == null)
		{
			return meta;
		}

		try
		{
			int level = Integer.parseInt(parts[1]);

			meta.addEnchant(enchant, level, true);
		}
		catch (NumberFormatException ignored)
		{}

		return meta;
	}

}
