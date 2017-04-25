package com.ulfric.commons.spigot.punishment;

import com.ulfric.commons.spigot.service.ServiceUtils;

public interface Kick extends PunishmentService {

	static Kick getService()
	{
		return ServiceUtils.getService(Kick.class);
	}

}