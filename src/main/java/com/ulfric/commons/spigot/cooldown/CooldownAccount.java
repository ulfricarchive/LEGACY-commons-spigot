package com.ulfric.commons.spigot.cooldown;

import com.ulfric.commons.identity.Unique;
import com.ulfric.commons.naming.Named;

import java.util.stream.Stream;

public interface CooldownAccount extends Unique {
	
	boolean isCooldown(Class<? extends Named> owner);
	
	Cooldown getCooldown(Class<? extends Named> owner);
	
	Stream<Cooldown> getCooldowns();
	
	void setCooldown(Cooldown cooldown);
	
}
