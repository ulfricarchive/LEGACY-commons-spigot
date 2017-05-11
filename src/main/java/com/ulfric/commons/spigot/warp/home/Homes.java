package com.ulfric.commons.spigot.warp.home;

import org.bukkit.entity.Player;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.service.ServiceUtils;
import com.ulfric.commons.version.Version;

@Name("Homes")
@Version(1)
public interface Homes extends Service {
	
	public static Homes getService()
	{
		return ServiceUtils.getService(Homes.class);
	}
	
	int getTotalHomes(Player player);
	
	void openHomesPanel(Player player);
	
}
