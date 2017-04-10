package com.ulfric.commons.spigot.plugin;

import java.util.logging.Logger;

import org.bukkit.event.Listener;

import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.command.Command;
import com.ulfric.commons.spigot.command.CommandFeature;
import com.ulfric.commons.spigot.container.ContainerLogger;
import com.ulfric.commons.spigot.listener.ListenerFeature;
import com.ulfric.commons.spigot.service.ServiceFeature;
import com.ulfric.commons.spigot.service.ServiceUtils;
import com.ulfric.commons.spigot.text.placeholder.Placeholder;
import com.ulfric.commons.spigot.text.placeholder.PlaceholderFeature;
import com.ulfric.dragoon.ObjectFactory;
import com.ulfric.dragoon.container.Container;

public class RootObjectFactory {

	static
	{
		RootObjectFactory.setupFeatureWrappers();
	}

	@SuppressWarnings("unchecked")
	private static void setupFeatureWrappers()
	{
		Container.registerFeatureWrapper(Service.class, ServiceFeature::new);
		Container.registerFeatureWrapper(Listener.class, ListenerFeature::new);
		Container.registerFeatureWrapper(Command.class, CommandFeature::new);
		Container.registerFeatureWrapper(Placeholder.class, PlaceholderFeature::new);
	}

	public static ObjectFactory getRootObjectFactory()
	{
		ObjectFactory rootFactory = ServiceUtils.getService(ObjectFactory.class);
		if (rootFactory == null)
		{
			rootFactory = ObjectFactory.newInstance();
			RootObjectFactory.setupDefaultBindings(rootFactory);

			// TODO install instead?
			ServiceUtils.register(ObjectFactory.class, rootFactory);
		}
		return rootFactory;
	}

	private static void setupDefaultBindings(ObjectFactory factory)
	{
		factory.bind(Logger.class).to(ContainerLogger.class);
	}

	private RootObjectFactory()
	{
		throw new IllegalStateException();
	}

}