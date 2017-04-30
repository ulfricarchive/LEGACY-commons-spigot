package com.ulfric.commons.spigot.panel.browser;

import java.util.List;

import org.bukkit.entity.Player;

import com.ulfric.commons.spigot.panel.Panel;

public interface Browser {

	Player owner();

	boolean isOpen();

	List<Panel> tabs();

	int currentIndex();

	default Panel currentTab()
	{
		if (this.tabs().size() == 0)
		{
			return null;
		}

		return this.tabs().get(this.currentIndex());
	}

	void nextTab();

	void previousTab();

	void resetSession();

	default void openTab(Panel panel)
	{
		this.addTab(panel);
		this.display();
	}

	void addTab(Panel panel);

	void display();

}
