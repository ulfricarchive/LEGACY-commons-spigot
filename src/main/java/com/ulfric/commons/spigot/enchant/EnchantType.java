package com.ulfric.commons.spigot.enchant;

public enum EnchantType {

	;

	private static final int MIN_ID = 150;

	private final int id;

	EnchantType()
	{
		this.id = EnchantType.MIN_ID + this.ordinal();
	}

	public int getId()
	{
		return this.id;
	}

}
