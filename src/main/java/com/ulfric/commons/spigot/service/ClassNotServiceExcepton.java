package com.ulfric.commons.spigot.service;

public class ClassNotServiceExcepton extends IllegalArgumentException {

	public ClassNotServiceExcepton(Class<?> notService)
	{
		super(String.valueOf(notService));
	}

}