package com.ulfric.commons.spigot.guard;

import org.bukkit.Location;

public interface RegionColl {

	RegionList getRegions(Location location);

	void addRegion(Region region);

	void removeRegion(Region region);

}