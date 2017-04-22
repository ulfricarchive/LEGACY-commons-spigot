package com.ulfric.commons.spigot.enchant;

import org.bukkit.inventory.ItemStack;

import com.ulfric.commons.naming.Named;

public interface Enchantment extends Named {

	void register();

	boolean isRegistered();

	void registerIfNotRegistered();

	String getFriendlyName();

	void applyTo(ItemStack item, int level);

}
