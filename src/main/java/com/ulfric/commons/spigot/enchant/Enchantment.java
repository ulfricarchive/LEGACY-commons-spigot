package com.ulfric.commons.spigot.enchant;

import org.bukkit.inventory.ItemStack;

import com.ulfric.commons.naming.Named;

public interface Enchantment extends Named {

	int getMaxLevel();

	String getFriendlyName();

	boolean isApplicableTo(ItemStack item);

	void applyTo(ItemStack item, int level);

}
