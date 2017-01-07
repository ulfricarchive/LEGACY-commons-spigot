package com.ulfric.commons.spigot.module;

import java.util.Objects;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import com.ulfric.commons.cdi.construct.BeanFactory;
import com.ulfric.commons.cdi.inject.Inject;
import com.ulfric.commons.naming.Named;
import com.ulfric.commons.spigot.plugin.PluginUtils;

public class Module implements Named, State {

	private final StateContainer states = new StateContainer(this);
	private final Plugin plugin = PluginUtils.getProvidingPlugin(this.getClass());

	private boolean loaded;
	private boolean enabled;

	@Inject
	private BeanFactory factory;

	@Load
	public void onLoad() { }

	@Enable
	public void onEnable() { }

	@Disable
	public void onDisable() { }

	@Override
	public final boolean isLoaded()
	{
		return this.loaded;
	}

	@Override
	public final boolean isEnabled()
	{
		return this.enabled;
	}

	public final Plugin getOwningPlugin()
	{
		return this.plugin;
	}

	@Override
	public final void load()
	{
		this.verifyIsNotLoaded();

		this.onLoad();

		this.loaded = true;
	}

	@Override
	public final void enable()
	{
		this.verifyIsNotEnabled();

		this.loadIfNotLoaded();

		this.onEnable();
		this.enabled = true;
		this.states.refresh();
	}

	@Override
	public void disable()
	{
		this.verifyIsNotDisabled();

		this.onDisable();
		this.enabled = false;
		this.states.refresh();
	}

	private void loadIfNotLoaded()
	{
		if (!this.isLoaded())
		{
			this.load();
		}
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

	public final void installModule(Class<? extends Module> module)
	{
		Objects.requireNonNull(module);

		Module createdModule = (Module) this.factory.request(module);
		this.states.install(createdModule);
	}

	public final void installListener(Class<? extends Listener> listener)
	{
		Objects.requireNonNull(listener);

		Listener createdListener = (Listener) this.factory.request(listener);
		State state = new ListenerState(this, createdListener);
		this.states.install(state);
	}

}