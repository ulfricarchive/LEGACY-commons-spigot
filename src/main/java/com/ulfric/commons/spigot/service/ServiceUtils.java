package com.ulfric.commons.spigot.service;

import java.util.Optional;
import java.util.function.Supplier;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;

import com.ulfric.commons.spigot.plugin.PluginUtils;

public enum ServiceUtils {

	;

	public static final ServicePriority DEFAULT_SERVICE_PRIORITY = ServicePriority.Normal;

	public static <S> S registerIfAbsent(Class<S> service, Supplier<S> provider)
	{
		Optional<S> currentService = ServiceUtils.getService(service);
		if (currentService.isPresent())
		{
			return currentService.get();
		}

		ServicesManager services = Bukkit.getServicesManager();

		S implementation = provider.get();
		Plugin owner = PluginUtils.getProvidingPlugin(service);

		services.register(service, implementation, owner, ServiceUtils.DEFAULT_SERVICE_PRIORITY);
		return implementation;
	}

	public static <S> Optional<S> getService(Class<S> service)
	{
		ServicesManager services = Bukkit.getServicesManager();
		RegisteredServiceProvider<S> provider = services.getRegistration(service);
		if (provider == null)
		{
			return Optional.empty();
		}
		return Optional.ofNullable(provider.getProvider());
	}

}