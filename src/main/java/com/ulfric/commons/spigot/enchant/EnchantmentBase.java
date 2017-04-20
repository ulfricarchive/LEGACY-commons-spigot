package com.ulfric.commons.spigot.enchant;

public abstract class EnchantmentBase extends org.bukkit.enchantments.Enchantment {

	public EnchantmentBase()
	{
		super(/* need an id */ 0);
	}

	private static int getEnchantmentId(Class<? extends EnchantmentBase> baseClass)
	{
		return 0;
	}

}
