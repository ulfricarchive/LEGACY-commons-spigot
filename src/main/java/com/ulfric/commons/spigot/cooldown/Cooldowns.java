package com.ulfric.commons.spigot.cooldown;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.service.ServiceUtils;
import com.ulfric.commons.version.Version;

import java.util.UUID;

@Name("Cooldown")
@Version(1)
public interface Cooldowns extends Service {
	
	public static Cooldowns getService()
	{
		return ServiceUtils.getService(Cooldowns.class);
	}
	
	CooldownAccount getAccount(UUID uniqueId);
	
}
