package com.ulfric.spigot.commons.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.ulfric.commons.cdi.Injector;

public class UlfricPlugin extends JavaPlugin {

	public UlfricPlugin()
	{
		ServicesManager services = Bukkit.getServicesManager();

		if (!services.isProvidedFor(Injector.class))
		{
			this.registerInjector();
		}

		Injector injector = services.getRegistration(Injector.class).getProvider();
		injector.injectValues(this);
	}

	private void registerInjector()
	{
		ServicesManager services = Bukkit.getServicesManager();

		Class<Injector> service = Injector.class;
		Injector serviceImplementation = Injector.newInstance();
		Plugin serviceProvider = this;
		ServicePriority servicePriority = ServicePriority.Normal;
		services.register(service, serviceImplementation, serviceProvider, servicePriority);
	}

}