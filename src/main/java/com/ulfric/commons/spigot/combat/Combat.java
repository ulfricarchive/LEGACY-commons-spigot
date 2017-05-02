package com.ulfric.commons.spigot.combat;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.service.ServiceUtils;
import com.ulfric.commons.version.Version;

import java.util.UUID;
import java.util.stream.Stream;

@Name("combat")
@Version(1)
public interface Combat extends Service {
	
	public static Combat getService()
	{
		return ServiceUtils.getService(Combat.class);
	}
	
	void setCombat(UUID uniqueId, Encounter encounter);
	
	boolean inCombat(UUID uniqueId);
	
	Encounter getEncounter(UUID uniqueId);
	
	void removeCombat(UUID uniqueId);
	
	Stream<Encounter> getEncounters();
	
}
