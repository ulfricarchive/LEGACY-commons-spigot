package com.ulfric.commons.spigot.panel;

import java.util.List;

public interface Browser {

	List<Panel> tabs();

	int currentIndex();

	default Panel currentTab()
	{
		return this.tabs().get(this.currentIndex());
	}

	void nextTab();

	void previousTab();

	void resetSession();

	<T extends Panel> T addTab(Class<T> panel);

	void display();

}
