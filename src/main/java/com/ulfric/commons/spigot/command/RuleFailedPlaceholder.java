package com.ulfric.commons.spigot.command;

import org.bukkit.command.CommandSender;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.spigot.metadata.Metadata;
import com.ulfric.commons.spigot.metadata.MetadataDefaults;
import com.ulfric.commons.spigot.text.placeholder.Placeholder;

@Name("RULE_FAILED")
public class RuleFailedPlaceholder implements Placeholder {

	@Override
	public String apply(CommandSender to)
	{
		return Metadata.readString(to, MetadataDefaults.RULE_FAILED);
	}

}