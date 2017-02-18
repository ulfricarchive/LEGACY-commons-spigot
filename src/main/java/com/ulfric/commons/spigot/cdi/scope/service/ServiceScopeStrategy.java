package com.ulfric.commons.spigot.cdi.scope.service;

import com.ulfric.commons.cdi.scope.ScopeStrategy;
import com.ulfric.commons.cdi.scope.Scoped;
import com.ulfric.commons.spigot.service.ServiceUtils;

public enum ServiceScopeStrategy implements ScopeStrategy {

	INSTANCE;

	@Override
	public <T> Scoped<T> getOrCreate(Class<T> request)
	{
		return this.getOrEmpty(request);
	}

	@Override
	public <T> Scoped<T> getOrEmpty(Class<T> request)
	{
		this.verifyClassIsService(request);

		@SuppressWarnings("unchecked")
		Class<? extends com.ulfric.commons.service.Service> service =
			(Class<? extends com.ulfric.commons.service.Service>) request;

		@SuppressWarnings("unchecked")
		T registeredService = (T) ServiceUtils.getService(service);
		Scoped<T> scoped = new Scoped<>(request, registeredService);
		scoped.read();
		return scoped;
	}

	private void verifyClassIsService(Class<?> request) {
		if (!com.ulfric.commons.service.Service.class.isAssignableFrom(request))
		{
			throw new NotAServiceException();
		}
	}

}