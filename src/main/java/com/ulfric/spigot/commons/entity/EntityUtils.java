package com.ulfric.spigot.commons.entity;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageModifier;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.ulfric.commons.api.UtilInstantiationException;

public class EntityUtils {

	public static final double HEALTH_OF_DEAD_ENTITY = 0;

	public static void kill(LivingEntity entity)
	{
		if (entity == null)
		{
			return;
		}

		EntityDamageEvent cause = EntityUtils.newKillingDamageEvent(entity);
		entity.setLastDamageCause(cause);
		entity.setHealth(EntityUtils.HEALTH_OF_DEAD_ENTITY);
	}

	private static EntityDamageEvent newKillingDamageEvent(LivingEntity entity)
	{
		double damageAmount = entity.getHealth();
		EntityDamageEvent event = EntityUtils.newDamageEvent(entity, damageAmount);
		return event;
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

		EntityDamageEvent.DamageModifier modifier = EntityDamageEvent.DamageModifier.BASE;
		Map<DamageModifier, Double> damageModifiers = Collections.singletonMap(modifier, amount);
		Map<DamageModifier, Function<Double, Double>> modifierFunctions =
				Collections.singletonMap(modifier, Functions.identity());

		return new EntityDamageEvent(entity, cause, damageModifiers, modifierFunctions);
	}

	private EntityUtils()
	{
		throw new UtilInstantiationException();
	}

}