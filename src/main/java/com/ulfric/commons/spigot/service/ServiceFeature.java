package com.ulfric.commons.spigot.service;

import com.ulfric.commons.service.Service;
import com.ulfric.dragoon.container.ChildFeature;
import com.ulfric.dragoon.container.Feature;

public final class ServiceFeature<S extends Service> extends ChildFeature {

	private final Class<S> service;
	private final S implementation;

	public ServiceFeature(Feature parent, S implementation)
	{
		super(parent);
		this.implementation = implementation;
		this.service = this.getServiceFromInterfaces();
	}

	private Class<S> getServiceFromInterfaces()
	{
		for (Class<?> iface : this.implementation.getClass().getInterfaces())
		{
			if (Service.class.isAssignableFrom(iface))
			{
				@SuppressWarnings("unchecked")
				Class<S> service = (Class<S>) iface;
				return service;
			}
		}

		throw new IllegalArgumentException();
	}

	@Override
	public void onEnable()
	{
		ServiceUtils.register(this.service, this.implementation);
	}

	@Override
	public void onDisable()
	{
		ServiceUtils.unregister(this.service, this.implementation);
	}

}