package com.ulfric.commons.spigot.kit;

import java.util.List;

import org.bukkit.inventory.ItemStack;

public interface Kit {

	String kitName();

	List<ItemStack> contents();

	long cooldown();

}
