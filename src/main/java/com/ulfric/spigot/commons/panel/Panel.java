package com.ulfric.spigot.commons.panel;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import com.ulfric.commons.reflect.ConstructorUtils;
import com.ulfric.spigot.commons.plugin.UlfricPlugin;

public abstract class Panel<T extends PanelType> {

	private final UlfricPlugin plugin;
	private final Player player;
	private final Set<PanelExtension> extensions = new HashSet<>();

	private T type;

	protected Panel(UlfricPlugin plugin, Class<T> type, Player player)
	{
		this.plugin = plugin;
		this.player = player;
		this.loadType(type);
	}

	private void loadType(Class<T> type)
	{
		this.type = ConstructorUtils.create(ConstructorUtils.getDeclaredConstructor(type, Panel.class), this);
	}

	protected final Panel withExtension(PanelExtension extension)
	{
		extension.ownedBy(this);
		extension.enable();

		this.extensions.add(extension);

		return this;
	}

	public void open()
	{
		this.plugin.getServer().getPluginManager().registerEvents(new Listener() {

			@EventHandler
			public void on(InventoryClickEvent event)
			{

			}

			@EventHandler
			public void on(InventoryCloseEvent event)
			{

			}

		}, this.plugin);
	}

	public final UlfricPlugin getOwningPlugin()
	{
		return this.plugin;
	}

	public final Player getPlayer()
	{
		return this.player;
	}

	public final PanelType getType()
	{
		return this.type;
	}

}