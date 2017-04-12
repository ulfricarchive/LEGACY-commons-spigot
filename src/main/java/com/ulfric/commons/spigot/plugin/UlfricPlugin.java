package com.ulfric.commons.spigot.plugin;

import java.util.logging.Logger;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.ulfric.commons.spigot.container.ContainerLogger;
import com.ulfric.commons.spigot.intercept.RequirePermission;
import com.ulfric.commons.spigot.intercept.RequirePermissionInterceptor;
import com.ulfric.dragoon.ObjectFactory;
import com.ulfric.dragoon.scope.Supplied;
import com.ulfric.dragoon.scope.SuppliedScopeStrategy;

@Supplied
public abstract class UlfricPlugin extends JavaPlugin {

	private final ObjectFactory containerFactory;
	private PluginContainer container;

	public UlfricPlugin()
	{
		this.containerFactory = RootObjectFactory.getRootObjectFactory().requestExact(ObjectFactory.class);
		this.setupPlatform();
	}

	private void setupPlatform()
	{
		ObjectFactory factory = this.containerFactory;
		SuppliedScopeStrategy scope = (SuppliedScopeStrategy) factory.request(Supplied.class);
		scope.register(this.getThisClassAsObject(), () -> this);
		scope.register(ContainerLogger.class, () -> new ContainerLogger(this));
		factory.bind(Plugin.class).to(this.getClass());
		factory.bind(Logger.class).to(ContainerLogger.class);
		factory.bind(RequirePermission.class).to(RequirePermissionInterceptor.class);
	}

	private Class<Object> getThisClassAsObject()
	{
		Class<?> clazz = this.getClass();
		@SuppressWarnings("unchecked")
		Class<Object> casted = (Class<Object>) clazz;
		return casted;
	}

	public final void install(Class<?> feature)
	{
		this.container.install(feature);
	}

	@Override
	public final void onLoad()
	{
		this.container = this.containerFactory.requestExact(PluginContainer.class);
		this.init();
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