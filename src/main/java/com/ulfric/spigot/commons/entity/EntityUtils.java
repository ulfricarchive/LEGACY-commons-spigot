package com.ulfric.spigot.commons.entity;

import java.util.Collections;
import java.util.Objects;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent;

import com.ulfric.commons.api.UtilInstantiationException;

public class EntityUtils {

	public static void kill(LivingEntity entity)
	{
		if (entity == null)
		{
			return;
		}

		double damageAmount = entity.getHealth();
		EntityDamageEvent cause = EntityUtils.newDamageEvent(entity, damageAmount);

		entity.setLastDamageCause(cause);
		entity.setHealth(0);
	}

	private static EntityDamageEvent newDamageEvent(Entity entity, double amount)
	{
		Objects.requireNonNull(entity);

		return EntityUtils.newDamageEvent(entity, amount, EntityDamageEvent.DamageCause.CUSTOM);
	}

	private static EntityDamageEvent newDamageEvent(Entity entity, double amount,
			EntityDamageEvent.DamageCause cause)
	{
		Objects.requireNonNull(entity);
		Objects.requireNonNull(cause);

		return new EntityDamageEvent(entity, cause,
				Collections.singletonMap(EntityDamageEvent.DamageModifier.BASE, amount),
				Collections.emptyMap());
	}

	private EntityUtils()
	{
		throw new UtilInstantiationException();
	}

}