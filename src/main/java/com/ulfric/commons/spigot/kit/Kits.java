package com.ulfric.commons.spigot.kit;

import org.bukkit.entity.Player;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.service.ServiceUtils;
import com.ulfric.commons.version.Version;

@Name("Kits")
@Version(1)
public interface Kits extends Service {

	static Kits getService()
	{
		return ServiceUtils.getService(Kits.class);
	}

	Kit getKit(String name);

	long cooldownFor(Kit kit, Player player);

	void giveKit(Kit kit, Player player);

}
