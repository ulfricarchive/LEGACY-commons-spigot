package com.ulfric.commons.spigot.panel;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import com.ulfric.commons.spigot.plugin.UlfricPlugin;

public abstract class Panel<T extends PanelType> {

	private final UlfricPlugin plugin;
	private final Player player;
	private final Set<PanelExtension> extensions = new HashSet<>();
	private final T type;

	protected Panel(UlfricPlugin plugin, Class<T> type, Player player)
	{
		this.plugin = plugin;
		this.player = player;
		this.type = this.loadType(type);
	}

	private T loadType(Class<T> type)
	{
		try
		{
			return ConstructorUtils.invokeConstructor(type, this);
		}
		catch (NoSuchMethodException | IllegalAccessException |
				InvocationTargetException | InstantiationException e)
		{
			throw new RuntimeException(e);
		}
	}

	protected final Panel<T> withExtension(PanelExtension extension)
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