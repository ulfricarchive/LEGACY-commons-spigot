package com.ulfric.commons.spigot.panel;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface Panel {

	Player holder();

	List<PanelExtension> extensions();

	void extend(PanelExtension extension);

	Inventory build();

}
