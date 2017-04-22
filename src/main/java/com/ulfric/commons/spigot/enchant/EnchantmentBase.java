package com.ulfric.commons.spigot.enchant;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.bukkit.inventory.ItemStack;

import com.ulfric.commons.exception.Try;

public abstract class EnchantmentBase extends org.bukkit.enchantments.Enchantment implements Enchantment {

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

	public EnchantmentBase()
	{
		super(-1);

		this.setId(EnchantmentBase.getEnchantmentId(this));
	}

	private void setId(int id)
	{
		Try.to(() ->
				FieldUtils.getDeclaredField(this.getClass(), "id").set(this, id));
	}

	@Override
	public final void register()
	{
		if (this.isRegistered())
		{
			throw new IllegalStateException("Enchantment already registered.");
		}

		org.bukkit.enchantments.Enchantment.registerEnchantment(this);

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
