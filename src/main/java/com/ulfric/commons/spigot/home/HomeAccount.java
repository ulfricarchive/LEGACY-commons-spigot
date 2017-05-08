package com.ulfric.commons.spigot.home;

import com.ulfric.commons.identity.Unique;

import java.util.List;

public interface HomeAccount extends Unique {
	
	boolean isHome(String name);
	
	Home getHome(String name);
	
	List<Home> getHomes();
	
	void setHome(Home home);
	
	void deleteHome(String name);
	
}
