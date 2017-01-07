package com.ulfric.commons.spigot.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import com.ulfric.commons.cdi.construct.BeanFactory;
import com.ulfric.commons.cdi.inject.Inject;
import com.ulfric.commons.naming.Named;
import com.ulfric.commons.spigot.plugin.PluginUtils;

public class Module implements Named {

	private final Map<String, Module> modules = new HashMap<>();
	private final List<Listener> listeners = new ArrayList<>();
	private final Plugin plugin = PluginUtils.getProvidingPlugin(this.getClass());

	private boolean loaded;
	private boolean enabled;

	@Inject
	private BeanFactory factory;

	public boolean isLoaded()
	{
		return this.loaded;
	}

	public boolean isEnabled()
	{
		return this.enabled;
	}

	public final void load()
	{
		this.verifyIsNotLoaded();

		this.onLoad();

		this.loaded = true;
	}

	public final void enable()
	{
		this.verifyIsNotEnabled();

		if (!this.isLoaded())
		{
			this.load();
		}

		this.enableListeners();
		this.onEnable();
		this.enabled = true;
	}

	public void disable()
	{
		this.verifyIsNotDisabled();

		this.disableListeners();
		this.onDisable();
		this.enabled = false;
	}

	private void verifyIsNotLoaded()
	{
		if (this.isLoaded())
		{
			throw new IllegalStateException("The module is already loaded!");
		}
	}

	private void verifyIsNotEnabled()
	{
		if (this.isEnabled())
		{
			throw new IllegalStateException("The module is already enabled!");
		}
	}

	private void verifyIsNotDisabled()
	{
		if (!this.isEnabled())
		{
			throw new IllegalStateException("The module is already disabled!");
		}
	}

	public void installModule(Class<? extends Module> module)
	{
		Objects.requireNonNull(module);

		Module createdModule = (Module) this.factory.request(module);
		if (this.modules.putIfAbsent(createdModule.getName(), createdModule) == null)
		{
			this.setupSubModule(createdModule);
		}
	}

	private void setupSubModule(Module module)
	{
		if (this.isLoaded() && !module.isLoaded())
		{
			module.load();
		}

		if (this.isEnabled() && !module.isEnabled())
		{
			module.enable();
		}
	}

	public void installListener(Class<? extends Listener> listener)
	{
		Objects.requireNonNull(listener);

		Listener createdListener = (Listener) this.factory.request(listener);
		this.listeners.add(createdListener);
		this.setupListener(createdListener);
	}

	private void setupListener(Listener listener)
	{
		if (this.isEnabled())
		{
			this.registerEvents(listener);
		}
	}

	private void enableListeners()
	{
		this.listeners.forEach(this::registerEvents);
	}

	private void registerEvents(Listener listener)
	{
		Bukkit.getPluginManager().registerEvents(listener, this.plugin);
	}

	private void disableListeners()
	{
		this.listeners.forEach(this::unregisterEvents);
	}

	private void unregisterEvents(Listener listener)
	{
		HandlerList.unregisterAll(listener);
	}

	@Load
	public void onLoad() { }

	@Enable
	public void onEnable() { }

	@Disable
	public void onDisable() { }

}