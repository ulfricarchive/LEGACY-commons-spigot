package com.ulfric.commons.spigot.plugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.ulfric.commons.cdi.construct.BeanFactory;
import com.ulfric.commons.cdi.inject.Inject;
import com.ulfric.commons.logging.Logger;
import com.ulfric.commons.naming.Named;
import com.ulfric.commons.spigot.intercept.concurrent.OnMainThread;
import com.ulfric.commons.spigot.intercept.concurrent.OnMainThreadInterceptor;
import com.ulfric.commons.spigot.intercept.player.MustBeAlive;
import com.ulfric.commons.spigot.intercept.player.MustBeAliveInterceptor;
import com.ulfric.commons.spigot.intercept.player.MustBeDead;
import com.ulfric.commons.spigot.intercept.player.MustBeDeadInterceptor;
import com.ulfric.commons.spigot.intercept.server.Broadcast;
import com.ulfric.commons.spigot.intercept.server.BroadcastInterceptor;
import com.ulfric.commons.spigot.logging.ConsoleLogger;
import com.ulfric.commons.spigot.module.Disable;
import com.ulfric.commons.spigot.module.DisableInterceptor;
import com.ulfric.commons.spigot.module.Enable;
import com.ulfric.commons.spigot.module.EnableInterceptor;
import com.ulfric.commons.spigot.module.Load;
import com.ulfric.commons.spigot.module.LoadInterceptor;
import com.ulfric.commons.spigot.module.Module;
import com.ulfric.commons.spigot.permission.RequirePermission;
import com.ulfric.commons.spigot.permission.RequirePermissionInterceptor;
import com.ulfric.commons.spigot.permission.RequirePermissions;
import com.ulfric.commons.spigot.permission.RequirePermissionsInterceptor;
import com.ulfric.commons.spigot.service.ServiceUtils;

public abstract class UlfricPlugin extends JavaPlugin implements Named {

	public UlfricPlugin()
	{
		this.registerBeanFactory();
		this.injectState();
	}

	@Inject
	private PluginModule container;

	@Inject
	private BeanFactory beanFactory;

	public final Module getContainer()
	{
		return this.container;
	}

	public final BeanFactory getBeanFactory()
	{
		return this.beanFactory;
	}

	private void injectState()
	{
		ServiceUtils.getService(BeanFactory.class)
			.map(BeanFactory::getInjector)
			.ifPresent(i -> i.injectState(this));
		this.container.setName(this.getName());
	}

	private void registerBeanFactory()
	{
		ServiceUtils.registerIfAbsent(BeanFactory.class, this::createBeanFactory);
	}

	private BeanFactory createBeanFactory()
	{
		BeanFactory factory = BeanFactory.newInstance();

		this.registerBindings(factory);

		return factory;
	}

	private void registerBindings(BeanFactory factory)
	{
		factory.bind(Logger.class).to(ConsoleLogger.class);

		this.registerInterceptors(factory);
	}

	private void registerInterceptors(BeanFactory factory)
	{
		factory.bind(Load.class).toInterceptor(LoadInterceptor.class);
		factory.bind(Enable.class).toInterceptor(EnableInterceptor.class);
		factory.bind(Disable.class).toInterceptor(DisableInterceptor.class);

		factory.bind(OnMainThread.class).toInterceptor(OnMainThreadInterceptor.class);

		factory.bind(Broadcast.class).toInterceptor(BroadcastInterceptor.class);

		factory.bind(RequirePermission.class).toInterceptor(RequirePermissionInterceptor.class);
		factory.bind(RequirePermissions.class).toInterceptor(RequirePermissionsInterceptor.class);

		factory.bind(MustBeAlive.class).toInterceptor(MustBeAliveInterceptor.class);
		factory.bind(MustBeDead.class).toInterceptor(MustBeDeadInterceptor.class);
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