package com.ulfric.commons.spigot.panel;

import org.bukkit.entity.Player;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.service.ServiceUtils;
import com.ulfric.commons.version.Version;


@Name("Panels")
@Version(1)
public interface Panels extends Service {

	static Panels getService()
	{
		return ServiceUtils.getService(Panels.class);
	}

	Browser getBrowser(Player player);

}
