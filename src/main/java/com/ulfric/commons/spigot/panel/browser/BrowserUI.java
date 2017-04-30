package com.ulfric.commons.spigot.panel.browser;

import com.ulfric.commons.spigot.panel.Panel;

public interface BrowserUI<T extends Panel> {

	void insertInto(T panel);

}
