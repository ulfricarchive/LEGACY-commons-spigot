package com.ulfric.commons.spigot.command;

import java.util.function.Predicate;

import com.ulfric.commons.naming.Named;

public interface RuleEnforcement extends Named, Predicate<Context> {

}
