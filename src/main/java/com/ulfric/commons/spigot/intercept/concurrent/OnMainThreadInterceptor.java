package com.ulfric.commons.spigot.intercept.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import com.ulfric.commons.cdi.intercept.Context;
import com.ulfric.commons.cdi.intercept.Interceptor;
import com.ulfric.commons.spigot.plugin.PluginUtils;

public class OnMainThreadInterceptor implements Interceptor {

	@Override
	public Object intercept(Context context)
	{
		Plugin plugin = this.getPluginFromContext(context);
		return Bukkit.getScheduler().callSyncMethod(plugin, () ->
		{
			Object future = context.proceed();

			try
			{
				return future instanceof Future ? ((Future<?>) future).get() : null;
			}
			catch (InterruptedException | ExecutionException caught)
			{
				return ExceptionUtils.rethrow(caught);
			}
		});
	}

	private Plugin getPluginFromContext(Context context)
	{
		return PluginUtils.getProvidingPlugin(context.getOrigin().getDeclaringClass());
	}

}