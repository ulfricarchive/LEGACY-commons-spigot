package com.ulfric.commons.spigot.panel.click;

@FunctionalInterface
public interface CancelledClick<T extends ClickData> extends Click<T> {

	@Override
	default ClickResult handle(T click)
	{
		this.handleCancelled(click);

		return ClickResult.CANCEL;
	}

	void handleCancelled(T click);

}
