package com.ulfric.commons.spigot.enchant;

import java.lang.reflect.Field;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.bukkit.inventory.ItemStack;

import com.ulfric.commons.exception.Try;

public abstract class EnchantmentBase extends org.bukkit.enchantments.Enchantment implements Enchantment {

	private static final Field ACCEPTING_NEW_FIELD =
			FieldUtils.getDeclaredField(org.bukkit.enchantments.Enchantment.class, "acceptingNew", true);

	private boolean registered = false;

	private static int getEnchantmentId(EnchantmentBase base)
	{
		CustomEnchant enchant = base.getClass().getAnnotation(CustomEnchant.class);

		if (enchant == null)
		{
			throw new IllegalStateException("Enchantment " + base.getClass() + " does not have CustomEnchant annotation");
		}

		return enchant.value().getId();
	}

	private static void unlockRegistrations()
	{
		EnchantmentBase.setRegistrations(true);
	}

	private static void lockRegistrations()
	{
		EnchantmentBase.setRegistrations(false);
	}

	private static void setRegistrations(boolean value)
	{
		Try.to(() -> EnchantmentBase.ACCEPTING_NEW_FIELD.set(null, value));
	}

	public EnchantmentBase()
	{
		super(-1);

		this.setId(EnchantmentBase.getEnchantmentId(this));
	}

	private void setId(int id)
	{
		Try.to(() ->
				FieldUtils.getDeclaredField(this.getClass(), "id", true).set(this, id));
	}

	@Override
	public final void register()
	{
		if (this.isRegistered())
		{
			throw new IllegalStateException("Enchantment already registered.");
		}

		EnchantmentBase.unlockRegistrations();

		org.bukkit.enchantments.Enchantment.registerEnchantment(this);

		EnchantmentBase.lockRegistrations();

		this.registered = true;
	}

	@Override
	public final boolean isRegistered()
	{
		return this.registered;
	}

	@Override
	public final void registerIfNotRegistered()
	{
		if (!this.isRegistered())
		{
			this.register();
		}
	}

	@Override
	public void applyTo(ItemStack item, int level)
	{
		item.addEnchantment(this, level);
	}

	@Override
	public int getStartLevel()
	{
		return 0;
	}

	@Override
	public boolean isTreasure()
	{
		return false;
	}

	@Override
	public boolean isCursed()
	{
		return false;
	}

	@Override
	public boolean conflictsWith(org.bukkit.enchantments.Enchantment enchantment)
	{
		return false;
	}

	@Override
	public boolean canEnchantItem(ItemStack item)
	{
		return true;
	}

}
