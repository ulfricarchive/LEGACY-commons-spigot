package com.ulfric.commons.spigot.service;

import java.util.Objects;
import java.util.function.Supplier;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;

import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.plugin.PluginUtils;

public enum ServiceUtils {

	;

	public static final ServicePriority DEFAULT_SERVICE_PRIORITY = ServicePriority.Normal;

	public static <S extends Service> S registerIfAbsent(Class<S> service, Supplier<S> provider)
	{
		Objects.requireNonNull(service);
		Objects.requireNonNull(provider);

		S currentService = ServiceUtils.getService(service);
		if (currentService != null)
		{
			return currentService;
		}

		S implementation = provider.get();
		ServiceUtils.register(service, implementation);

		return implementation;
	}

	public static <S extends Service> void register(Class<S> service, S implementation)
	{
		Objects.requireNonNull(service);
		Objects.requireNonNull(implementation);

		ServicesManager services = Bukkit.getServicesManager();
		Plugin owner = PluginUtils.getProvidingPlugin(implementation.getClass());
		services.register(service, implementation, owner, ServiceUtils.DEFAULT_SERVICE_PRIORITY);
	}

	public static <S extends Service> void unregister(Class<S> service, S implementation)
	{
		Objects.requireNonNull(service);
		Objects.requireNonNull(implementation);

		ServicesManager services = Bukkit.getServicesManager();
		services.unregister(service, implementation);
	}

	public static <S extends Service> S getService(Class<S> service)
	{
		Objects.requireNonNull(service);

		ServicesManager services = Bukkit.getServicesManager();
		RegisteredServiceProvider<S> provider = services.getRegistration(service);
		if (provider == null)
		{
			return null;
		}
		return provider.getProvider();
	}

}