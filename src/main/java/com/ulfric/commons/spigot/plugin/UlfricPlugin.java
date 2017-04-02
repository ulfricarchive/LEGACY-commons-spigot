package com.ulfric.commons.spigot.plugin;

import java.util.logging.Logger;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.ulfric.commons.spigot.container.ContainerLogger;
import com.ulfric.commons.spigot.service.ServiceUtils;
import com.ulfric.dragoon.ObjectFactory;
import com.ulfric.dragoon.container.Container;
import com.ulfric.dragoon.scope.Supplied;
import com.ulfric.dragoon.scope.SuppliedScopeStrategy;

@Supplied
public abstract class UlfricPlugin extends JavaPlugin {

	private ObjectFactory containerFactory;
	private PluginContainer container;

	public UlfricPlugin()
	{
		this.setupPlatform();
	}

	protected void setupPlatform()
	{
		this.createObjectFactoryParentingPluginContainer();
		this.bindPluginToThis();

		this.createPluginContainer();
	}

	private void createObjectFactoryParentingPluginContainer()
	{
		ObjectFactory factory = this.getParentFactory();
		this.containerFactory = factory.requestExact(ObjectFactory.class);
	}

	private ObjectFactory getParentFactory()
	{
		return ServiceUtils.getService(ObjectFactory.class).orElseThrow(IllegalStateException::new);
	}

	private void bindPluginToThis()
	{
		ObjectFactory factory = this.containerFactory;
		SuppliedScopeStrategy scope = (SuppliedScopeStrategy) factory.request(Supplied.class);
		scope.register(this.getThisClassAsObject(), () -> this);
		scope.register(ContainerLogger.class, () -> new ContainerLogger(this));
		factory.bind(Plugin.class).to(this.getClass());
		factory.bind(Logger.class).to(ContainerLogger.class);
	}

	private Class<Object> getThisClassAsObject()
	{
		Class<?> clazz = this.getClass();
		@SuppressWarnings("unchecked")
		Class<Object> casted = (Class<Object>) clazz;
		return casted;
	}

	private void createPluginContainer()
	{
		this.container = this.containerFactory.requestExact(PluginContainer.class);
	}

	public final Container getContainer()
	{
		return this.container;
	}

	@Override
	public final void onLoad()
	{
		this.container.load();
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