package com.ulfric.commons.spigot.network;

import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.service.ServiceUtils;

public interface InetAddressHash extends Service {

	static InetAddressHash getService()
	{
		return ServiceUtils.getService(InetAddressHash.class);
	}

	String getHash(String address);

	String getInetAddress(String hash);

}