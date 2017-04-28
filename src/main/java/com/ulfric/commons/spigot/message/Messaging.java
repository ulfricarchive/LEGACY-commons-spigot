package com.ulfric.commons.spigot.message;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.service.ServiceUtils;
import com.ulfric.commons.version.Version;

import java.util.UUID;

@Name("Messaging")
@Version(1)
public interface Messaging extends Service {
	
	public static Messaging getService()
	{
		return ServiceUtils.getService(Messaging.class);
	}
	
	void setLastRecipients(UUID uniqueId, UUID recipient);
	
	UUID getLastRecipient(UUID uniqueId);
	
	boolean hasLastRecipient(UUID uniqueId);
	
	void clear(UUID uniqueId);
	
}
