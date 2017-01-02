package com.ulfric.spigot.commons.module;

import com.ulfric.commons.text.FormatUtils;

public class DisableInterceptor extends StateInterceptor {

	@Override
	protected void before(String name)
	{
		this.logger.info("Disabling " + name);
	}

	@Override
	protected void after(String name, long timeToProcessInMillis)
	{
		this.logger.info("Disabled " + name + " in " + FormatUtils.formatMilliseconds(timeToProcessInMillis));
	}

}