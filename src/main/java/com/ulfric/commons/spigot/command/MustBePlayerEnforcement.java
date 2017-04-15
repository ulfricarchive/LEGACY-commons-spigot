package com.ulfric.commons.spigot.command;

import org.bukkit.entity.Player;

import com.ulfric.commons.naming.Name;

@Name("must-be-player")
public class MustBePlayerEnforcement implements RuleEnforcement {

	@Override
	public void accept(Context context)
	{
		if (!(context.getSender() instanceof Player))
		{
			throw new RuleNotPassedException(this);
		}
	}

}
