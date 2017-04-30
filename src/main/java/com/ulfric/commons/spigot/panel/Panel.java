package com.ulfric.commons.spigot.panel;

import com.ulfric.commons.spigot.panel.browser.Browser;
import com.ulfric.commons.spigot.panel.click.ButtonBuilder;

public interface Panel {

	Browser browser();

	ButtonBuilder<?> buildButton();

	void open();

}
