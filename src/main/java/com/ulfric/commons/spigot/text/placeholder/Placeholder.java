package com.ulfric.commons.spigot.text.placeholder;

import java.util.function.Function;

import org.bukkit.command.CommandSender;

import com.ulfric.commons.naming.Named;

public interface Placeholder extends Named, Function<CommandSender, String> {

}