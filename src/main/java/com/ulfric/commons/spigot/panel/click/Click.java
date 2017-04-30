package com.ulfric.commons.spigot.panel.click;

@FunctionalInterface
public interface Click<T extends ClickData> {

	ClickResult handle(T click);

}
