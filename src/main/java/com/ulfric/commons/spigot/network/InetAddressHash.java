package com.ulfric.commons.spigot.network;

import java.net.InetAddress;
import java.util.stream.Stream;

import org.bukkit.entity.Player;

import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.service.ServiceUtils;

public interface InetAddressHash extends Service {

	static InetAddressHash getService()
	{
		return ServiceUtils.getService(InetAddressHash.class);
	}

	default String getHash(InetAddress address)
	{
		return this.getHash(address.getHostAddress());
	}

	String getHash(String address);

	String getInetAddress(String hash);

	Stream<? extends Player> onlinePlayersForHash(String hash);

}