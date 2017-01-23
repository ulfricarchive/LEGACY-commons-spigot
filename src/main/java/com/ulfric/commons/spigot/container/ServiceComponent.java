package com.ulfric.commons.spigot.container;

import com.ulfric.commons.cdi.container.Component;
import com.ulfric.commons.cdi.container.SkeletalComponent;
import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.service.ServiceUtils;

public final class ServiceComponent<S extends Service> extends SkeletalComponent {

	public ServiceComponent(Component parent, Class<S> service, S implementation)
	{
		super(parent);
		this.service = service;
		this.implementation = implementation;
	}

	private final Class<S> service;
	private final S implementation;

	@Override
	public void enable()
	{
		ServiceUtils.register(this.service, this.implementation);
	}

	@Override
	public void disable()
	{
		ServiceUtils.unregister(this.service, this.implementation);
	}

}