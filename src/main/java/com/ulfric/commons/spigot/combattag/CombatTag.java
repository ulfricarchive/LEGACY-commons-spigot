package com.ulfric.commons.spigot.combattag;

import java.util.UUID;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.service.ServiceUtils;
import com.ulfric.commons.version.Version;

@Name("combattag")
@Version(1)
public interface CombatTag extends Service {

	public static CombatTag getService()
	{
		return ServiceUtils.getService(CombatTag.class);
	}

	void mark(UUID uniqueId);

	void unmark(UUID uniqueId);

	boolean isMarked(UUID uniqueId);

}