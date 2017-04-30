package com.ulfric.commons.spigot.cooldown;

import com.ulfric.commons.identity.Unique;

import java.util.stream.Stream;

public interface CooldownAccount extends Unique {
	
	boolean isCooldown(String name);
	
	Cooldown getCooldown(String name);
	
	Stream<Cooldown> getCooldowns();
	
	void setCooldown(Cooldown cooldown);
	
}
