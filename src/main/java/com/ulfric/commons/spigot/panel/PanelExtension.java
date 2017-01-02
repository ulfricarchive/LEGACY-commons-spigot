package com.ulfric.commons.spigot.panel;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public abstract class PanelExtension {

	private Panel panel;

	protected PanelExtension()
	{

	}

	void ownedBy(Panel panel)
	{
		if (this.panel != null)
		{
			throw new PanelException("Owning panel is already defined!");
		}

		this.panel = panel;
	}

	protected abstract void enable();

	protected abstract void open(InventoryOpenEvent event);

	protected abstract void update();

	protected abstract void click(InventoryClickEvent event);

	protected abstract void close(InventoryCloseEvent event);

	public final Panel getOwningPanel()
	{
		return this.panel;
	}

}