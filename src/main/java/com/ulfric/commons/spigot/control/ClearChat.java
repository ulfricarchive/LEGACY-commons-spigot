package com.ulfric.commons.spigot.control;

import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.service.ServiceUtils;

public interface ClearChat extends Service {

	static ClearChat getService()
	{
		return ServiceUtils.getService(ClearChat.class);
	}

	void clear();

}