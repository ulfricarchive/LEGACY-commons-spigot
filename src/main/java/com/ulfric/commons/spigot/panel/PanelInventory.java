package com.ulfric.commons.spigot.panel;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface PanelInventory {

	void setTitle(String title);

	void setItem(int slot, ItemStack item);

	Inventory finalInventory();

}
