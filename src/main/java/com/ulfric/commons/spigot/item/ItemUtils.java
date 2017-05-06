package com.ulfric.commons.spigot.item;

import java.util.Collection;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.ulfric.commons.spigot.item.parts.ItemParts;

public enum ItemUtils {

	;

	public static void giveItems(Player player, Collection<ItemStack> items)
	{
		ItemUtils.giveItems(player, items.toArray(new ItemStack[0]));
	}

	public static void giveItems(Player player, ItemStack... items)
	{
		Inventory inventory = player.getInventory();
		Location location = player.getLocation();

		Map<Integer, ItemStack> leftovers = inventory.addItem(items);

		leftovers.values().forEach(leftover ->
		{
			World world = location.getWorld();

			world.dropItem(location, leftover);
		});
	}

	public static String serialize(ItemStack item)
	{
		return ItemParts.serialize(item);
	}

	public static ItemStack deserializeItem(String string)
	{
		return ItemParts.deserialize(string);
	}

}
