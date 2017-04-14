package com.ulfric.commons.spigot.command;

import org.bukkit.entity.Player;

import com.ulfric.commons.naming.Name;

@Name("must-be-player")
public class MustBePlayerEnforcement implements RuleEnforcement {

	@Override
	public boolean test(Context context)
	{
		return context.getSender() instanceof Player;
	}

}
