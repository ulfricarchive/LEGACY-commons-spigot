package com.ulfric.spigot.commons.plugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.ulfric.commons.cdi.construct.BeanFactory;
import com.ulfric.commons.cdi.inject.Inject;
import com.ulfric.commons.logging.Logger;
import com.ulfric.commons.naming.Named;
import com.ulfric.spigot.commons.logging.ConsoleLogger;
import com.ulfric.spigot.commons.module.Disable;
import com.ulfric.spigot.commons.module.DisableInterceptor;
import com.ulfric.spigot.commons.module.Enable;
import com.ulfric.spigot.commons.module.EnableInterceptor;
import com.ulfric.spigot.commons.module.Load;
import com.ulfric.spigot.commons.module.LoadInterceptor;
import com.ulfric.spigot.commons.module.Module;
import com.ulfric.spigot.commons.service.ServiceUtils;

public abstract class UlfricPlugin extends JavaPlugin implements Named {

	public UlfricPlugin()
	{
		this.registerBeanFactory();
		this.injectState();
	}

	private void injectState()
	{
		ServiceUtils.getService(BeanFactory.class)
			.map(BeanFactory::getInjector)
			.ifPresent(i -> i.injectState(this));
		this.container.setName(this.getName());
	}

	@Inject
	private PluginModule container;

	public final Module getContainer()
	{
		return this.container;
	}

	private void registerBeanFactory()
	{
		ServiceUtils.registerIfAbsent(BeanFactory.class, this::createBeanFactory);
	}

	private BeanFactory createBeanFactory()
	{
		BeanFactory factory = BeanFactory.newInstance();

		factory.bind(Logger.class).to(ConsoleLogger.class);
		factory.bind(Load.class).toInterceptor(LoadInterceptor.class);
		factory.bind(Enable.class).toInterceptor(EnableInterceptor.class);
		factory.bind(Disable.class).toInterceptor(DisableInterceptor.class);

		return factory;
	}

	@Override
	public final void onLoad()
	{
		this.container.onLoad();
	}

	@Override
	public final void onEnable()
	{
		this.container.onEnable();
	}

	@Override
	public final void onDisable()
	{
		this.container.onDisable();
	}

	public void init() { }

}