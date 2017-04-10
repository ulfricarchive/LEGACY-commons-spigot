package com.ulfric.commons.spigot.service;

import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.ClassUtils;

import com.ulfric.commons.service.Service;
import com.ulfric.dragoon.container.ChildFeature;
import com.ulfric.dragoon.container.Feature;

public final class ServiceFeature<S extends Service> extends ChildFeature {

	private final Set<Class<S>> services = SetUtils.newIdentityHashSet();
	private final S implementation;

	public ServiceFeature(Feature parent, S implementation)
	{
		super(parent);
		this.implementation = implementation;
		this.addServices();
	}

	private void addServices()
	{
		Class<?> implementationClass = this.implementation.getClass();
		this.addService(implementationClass);
		this.addServices(ClassUtils.getAllInterfaces(implementationClass));
		this.addServices(ClassUtils.getAllSuperclasses(implementationClass));
	}

	private void addServices(List<Class<?>> potentialServices)
	{
		potentialServices.forEach(this::addService);
	}

	@SuppressWarnings("unchecked")
	private void addService(Class<?> potentialService)
	{
		if (this.isServiceDeclaration(potentialService))
		{
			this.services.add((Class<S>) potentialService);
		}
	}

	private boolean isServiceDeclaration(Class<?> potentialService)
	{
		return Service.class != potentialService && Service.class.isAssignableFrom(potentialService);
	}

	@Override
	public void onEnable()
	{
		this.services.forEach(service -> ServiceUtils.register(service, this.implementation));
	}

	@Override
	public void onDisable()
	{
		this.services.forEach(service -> ServiceUtils.unregister(service, this.implementation));
	}

}