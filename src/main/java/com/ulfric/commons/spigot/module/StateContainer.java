package com.ulfric.commons.spigot.module;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

final class StateContainer {

	public StateContainer(State owner)
	{
		this.owner = owner;
	}

	private final State owner;
	private final Set<State> states = new LinkedHashSet<>();

	public void install(State state)
	{
		Objects.requireNonNull(state);

		if (this.states.add(state))
		{
			this.refreshState(state);
		}
	}

	public void refresh()
	{
		this.states.forEach(this::refreshState);
	}

	private void refreshState(State state)
	{
		if (this.owner.isLoaded() && !state.isLoaded())
		{
			state.load();
		}

		if (this.owner.isEnabled() && !state.isEnabled())
		{
			state.enable();
		}
		else if (this.owner.isDisabled() && !state.isDisabled())
		{
			state.disable();
		}
	}

}