package com.ulfric.spigot.commons.plugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.ulfric.commons.cdi.construct.BeanFactory;
import com.ulfric.commons.convert.ConversionService;
import com.ulfric.spigot.commons.service.ServiceUtils;

public abstract class UlfricPlugin extends JavaPlugin {

	public UlfricPlugin()
	{
		this.registerConversionService();
		this.registerBeanFactory();
	}

	public void registerConversionService()
	{
		ServiceUtils.registerIfAbsent(ConversionService.class, ConversionService::newInstance);
	}

	private void registerBeanFactory()
	{
		ServiceUtils.registerIfAbsent(BeanFactory.class, BeanFactory::newInstance)
			.getInjector()
			.injectState(this);
	}

}