package com.ulfric.commons.spigot.cdi.scope.service;

import com.ulfric.commons.cdi.construct.scope.ScopeStrategy;
import com.ulfric.commons.cdi.inject.Injector;
import com.ulfric.commons.spigot.service.ServiceUtils;

public class ServiceScopeStrategy implements ScopeStrategy<Service> {

	@Override
	public <T> T getInstance(Class<T> request, Service scope, Injector injector)
	{
		if (!com.ulfric.commons.service.Service.class.isAssignableFrom(request))
		{
			throw new NotAServiceException();
		}

		@SuppressWarnings("unchecked")
		Class<? extends com.ulfric.commons.service.Service> service =
			(Class<? extends com.ulfric.commons.service.Service>) request;

		@SuppressWarnings("unchecked")
		T registeredService = (T) ServiceUtils.getService(service);
		return registeredService;
	}

}