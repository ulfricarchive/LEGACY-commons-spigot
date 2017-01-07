package com.ulfric.commons.spigot.intercept.server;

import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;

import com.ulfric.commons.cdi.intercept.Context;
import com.ulfric.commons.cdi.intercept.Interceptor;
import com.ulfric.commons.spigot.intercept.concurrent.OnMainThread;

public class BroadcastInterceptor implements Interceptor {

	@Override
	public Object intercept(Context context)
	{
		Object value = context.proceed();

		if (value instanceof CompletableFuture)
		{
			CompletableFuture<?> future = (CompletableFuture<?>) value;

			future.whenComplete((message, exception) ->
			{
				if (exception != null)
				{
					return;
				}

				this.broadcast(message, this.getPermission(context));
			});

			return value;
		}

		this.broadcast(value, this.getPermission(context));

		return value;
	}

	private String getPermission(Context context)
	{
		Broadcast broadcast = context.getOrigin().getAnnotation(Broadcast.class);
		return broadcast == null ? null : broadcast.requiredPermission();
	}

	@OnMainThread
	public void broadcast(Object message, String permission)
	{
		if (!this.canSend(message))
		{
			return;
		}

		String messageString = message.toString();
		if (StringUtils.isEmpty(permission))
		{
			Bukkit.broadcastMessage(messageString);
			return;
		}
		Bukkit.broadcast(messageString, permission);
	}

	public boolean canSend(Object message)
	{
		return message != null;
	}

}