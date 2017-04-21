package com.ulfric.commons.spigot.warp;

import java.util.List;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.service.ServiceUtils;
import com.ulfric.commons.version.Version;

@Name("warps")
@Version(1)
public interface Warps extends Service {

	static Warps getService()
	{
		return ServiceUtils.getService(Warps.class);
	}

	void setWarp(Warp warp);

	void deleteWarp(String name);

	Warp getWarp(String name);

	List<Warp> getWarps();

}