package com.ulfric.commons.spigot.container;

import com.ulfric.commons.cdi.container.Component;
import com.ulfric.commons.cdi.container.ChildComponent;
import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.cdi.scope.service.NotAServiceException;
import com.ulfric.commons.spigot.service.ServiceUtils;

public final class ServiceComponent<S extends Service> extends ChildComponent {

	private final Class<S> service;
	private final S implementation;

	public ServiceComponent(Component parent, S implementation)
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

		throw new NotAServiceException();
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