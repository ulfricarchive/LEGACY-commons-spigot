package com.ulfric.commons.spigot.warp;

import java.util.List;

public interface WarpAccount {

	boolean isWarp(String name);

	Warp getWarp(String name);

	List<Warp> getWarps();

	void setWarp(Warp warp);

	void deleteWarp(String name);

}