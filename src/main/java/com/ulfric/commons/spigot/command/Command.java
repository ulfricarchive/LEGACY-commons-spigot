package com.ulfric.commons.spigot.command;

import com.ulfric.commons.naming.Named;

public interface Command extends Named {

	void run(Context context);

}