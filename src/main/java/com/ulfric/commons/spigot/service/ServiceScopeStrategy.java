package com.ulfric.commons.spigot.service;

import com.ulfric.dragoon.scope.ScopeStrategy;
import com.ulfric.dragoon.scope.Scoped;
import com.ulfric.commons.service.Service;

public enum ServiceScopeStrategy implements ScopeStrategy {

	INSTANCE;

	@Override
	public <T> Scoped<T> getOrCreate(Class<T> request)
	{
		return this.getOrCreate(request);
	}

	@Override
	public <T> Scoped<T> getOrEmpty(Class<T> request)
	{
		if (!Service.class.isAssignableFrom(request))
		{
			throw new ClassNotServiceExcepton(request);
		}

		@SuppressWarnings("unchecked")
		Class<? extends Service> service = (Class<? extends Service>) request;

		Service registeredService = ServiceUtils.getService(service);
		if (registeredService == null)
		{
			return Scoped.createEmptyScope(request);
		}

		return this.wrapInScoped(registeredService);
	}

	@SuppressWarnings("unchecked")
	private <T> Scoped<T> wrapInScoped(Service service)
	{
		T casted = (T) service;
		Class<T> castedClass = (Class<T>) casted.getClass();
		Scoped<T> scoped = new Scoped<>(castedClass, casted);
		scoped.read();
		return scoped;
	}

}