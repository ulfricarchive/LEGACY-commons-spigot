package com.ulfric.commons.spigot.guard;

public interface RegionList extends Iterable<Region> {

	<T> T getDominantFlag(Flag<T> flag);

}