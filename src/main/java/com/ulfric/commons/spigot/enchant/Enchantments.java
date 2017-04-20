package com.ulfric.commons.spigot.enchant;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.service.Service;
import com.ulfric.commons.spigot.service.ServiceUtils;
import com.ulfric.commons.version.Version;

@Name("Enchantments")
@Version(1)
public interface Enchantments extends Service {

	static Enchantments getService()
	{
		return ServiceUtils.getService(Enchantments.class);
	}

	void register(Enchantment enchantment);

	Enchantment of(org.bukkit.enchantments.Enchantment enchantment);

}
