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
	private boolean loaded;

	public void install(State state)
	{
		Objects.requireNonNull(state);

		if (this.states.add(state))
		{
			this.setupState(state);
		}
	}

	private void setupState(State state)
	{
		if (this.owner.isLoaded())
		{
			if (!state.isLoaded())
			{
				state.load();
			}
		}

		if (this.owner.isEnabled())
		{
			if (state.isDisabled())
			{
				state.enable();
			}

			return;
		}
	}

	public void refresh()
	{
		if (!this.loaded && this.owner.isLoaded())
		{
			this.states.forEach(State::load);
		}

		if (this.owner.isEnabled())
		{
			this.states.forEach(State::enable);
		}
		else if (this.owner.isDisabled())
		{
			this.states.forEach(State::disable);
		}
	}

}