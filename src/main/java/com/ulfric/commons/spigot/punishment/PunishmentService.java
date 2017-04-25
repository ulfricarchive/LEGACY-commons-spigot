package com.ulfric.commons.spigot.punishment;

import java.util.List;

import com.ulfric.commons.service.Service;

public interface PunishmentService extends Service {

	String getDefaultReason();

	Punishment getActive(PunishmentHolder holder);

	void apply(PunishmentHolder holder, Punishment punishment);

	List<Punishment> lift(PunishmentHolder holder);

}