package com.ulfric.commons.spigot.command;

import com.ulfric.commons.spigot.text.LocaleDefaults;

public class RuleNotPassedException extends CommandException {

	public RuleNotPassedException(RuleEnforcement enforcement)
	{
		super(LocaleDefaults.RULE_NOT_PASSED, enforcement.getName());
	}

}