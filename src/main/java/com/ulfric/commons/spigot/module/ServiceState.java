package com.ulfric.commons.spigot.module;

import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.service.ServiceUtils;

final class ServiceState<S extends Service> extends SkeletalState {

	public ServiceState(Module parent, Class<S> service, S implementation)
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