package com.ulfric.commons.spigot.module;

import java.util.Objects;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import com.ulfric.commons.cdi.construct.BeanFactory;
import com.ulfric.commons.cdi.inject.Inject;
import com.ulfric.commons.naming.Named;
import com.ulfric.commons.service.Service;
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
	public final void disable()
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

	public final void install(Class<? extends State> state)
	{
		Objects.requireNonNull(state);

		State instance = this.request(state);
		this.installState(instance);
	}

	public final void installListener(Class<? extends Listener> listener)
	{
		Objects.requireNonNull(listener);

		Listener createdListener = this.request(listener);
		State state = new ListenerState(this, createdListener);
		this.installState(state);
	}

	public final <S extends Service> void installService(Class<S> service, Class<? extends S> implementation)
	{
		Objects.requireNonNull(service);
		Objects.requireNonNull(implementation);

		this.bind(service, implementation);
		S instance = this.request(service);
		State state = new ServiceState<>(this, service, instance);
		this.installState(state);
	}

	private <T> void bind(Class<T> service, Class<? extends T> implementation)
	{
		this.factory.bind(service).to(implementation);
	}

	private void installState(State state)
	{
		Objects.requireNonNull(state);

		this.substates.install(state);
	}

	private <T> T request(Class<T> request)
	{
		@SuppressWarnings("unchecked")
		T implementation = (T) this.factory.request(request);
		return implementation;
	}

}