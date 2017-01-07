package com.ulfric.commons.spigot.module;

import java.util.Objects;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import com.ulfric.commons.cdi.construct.BeanFactory;
import com.ulfric.commons.cdi.inject.Inject;
import com.ulfric.commons.naming.Named;
import com.ulfric.commons.spigot.plugin.PluginUtils;

public class Module implements Named, State {

	private final StateContainer substates = new StateContainer(this);
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

	@Override
	public final boolean isDisabled()
	{
		return State.super.isDisabled();
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
		this.refreshSubstates();
	}

	private void verifyIsNotLoaded()
	{
		if (this.isLoaded())
		{
			throw new IllegalStateException("The module is already loaded!");
		}
	}

	@Override
	public final void enable()
	{
		this.verifyIsNotEnabled();

		this.loadIfNotLoaded();

		this.onEnable();
		this.enabled = true;
		this.refreshSubstates();
	}

	private void verifyIsNotEnabled()
	{
		if (this.isEnabled())
		{
			throw new IllegalStateException("The module is already enabled!");
		}
	}

	private void loadIfNotLoaded()
	{
		if (!this.isLoaded())
		{
			this.load();
		}
	}

	@Override
	public void disable()
	{
		this.verifyIsNotDisabled();

		this.onDisable();
		this.enabled = false;
		this.refreshSubstates();
	}

	private void verifyIsNotDisabled()
	{
		if (this.isDisabled())
		{
			throw new IllegalStateException("The module is already disabled!");
		}
	}

	private void refreshSubstates()
	{
		this.substates.refresh();
	}

	public final void installModule(Class<? extends Module> module)
	{
		Objects.requireNonNull(module);

		Module createdModule = (Module) this.factory.request(module);
		this.substates.install(createdModule);
	}

	public final void installListener(Class<? extends Listener> listener)
	{
		Objects.requireNonNull(listener);

		Listener createdListener = (Listener) this.factory.request(listener);
		State state = new ListenerState(this, createdListener);
		this.substates.install(state);
	}

}