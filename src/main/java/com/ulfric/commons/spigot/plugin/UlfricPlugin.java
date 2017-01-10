package com.ulfric.commons.spigot.plugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.ulfric.commons.cdi.construct.BeanFactory;
import com.ulfric.commons.cdi.inject.Inject;
import com.ulfric.commons.cdi.inject.Injector;
import com.ulfric.commons.naming.Named;
import com.ulfric.commons.spigot.module.Module;
import com.ulfric.commons.spigot.service.ServiceUtils;

public abstract class UlfricPlugin extends JavaPlugin implements Named {

	@Inject
	private PluginModule container;

	public UlfricPlugin()
	{
		this.setupPlatform();
	}

	protected void setupPlatform()
	{
		this.getGlobalInjector().injectState(this);
		this.setupContainer();
	}

	private void setupContainer()
	{
		this.container.setName(this.getName());
	}

	private Injector getGlobalInjector()
	{
		return this.getGlobalBeanFactory().getInjector();
	}

	private BeanFactory getGlobalBeanFactory()
	{
		return ServiceUtils.getService(BeanFactory.class).orElseThrow(IllegalStateException::new);
	}

	public final Module getContainer()
	{
		return this.container;
	}

	@Override
	public final void onLoad()
	{
		this.init();
		this.container.load();
	}

	@Override
	public final void onEnable()
	{
		this.container.enable();
	}

	@Override
	public final void onDisable()
	{
		this.container.disable();
	}

	public void init() { }

}