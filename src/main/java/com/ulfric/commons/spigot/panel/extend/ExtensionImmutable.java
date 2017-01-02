package com.ulfric.commons.spigot.panel.extend;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import com.google.common.collect.Lists;
import com.ulfric.commons.spigot.panel.PanelExtension;

public class ExtensionImmutable extends PanelExtension {

	private final List<Integer> slots;

	private ExtensionImmutable(Integer... slots)
	{
		this.slots = Lists.newArrayList(slots);
	}

	public static ExtensionImmutable allSlotsImmutableExcept(Integer... slots)
	{
		List<Integer> immutableSlots = new ArrayList<>();

		immutableSlots.addAll(IntStream.range(0, 54).boxed().collect(Collectors.toList()));
		for (int slot : slots)
		{
			immutableSlots.remove(slot);
		}

		return new ExtensionImmutable(immutableSlots.toArray(new Integer[0]));
	}

	public static ExtensionImmutable allSlotsMutableExcept(Integer... slots)
	{
		return new ExtensionImmutable(slots);
	}

	public static ExtensionImmutable allSlotsImmutable()
	{
		return new ExtensionImmutable();
	}

	@Override
	protected void enable()
	{

	}

	@Override
	protected void open(InventoryOpenEvent event)
	{

	}

	@Override
	protected void update()
	{

	}

	@Override
	protected void click(InventoryClickEvent event)
	{
		if (slots.isEmpty() || slots.contains(event.getSlot()))
		{
			event.setCancelled(true);
		}
	}

	@Override
	protected void close(InventoryCloseEvent event)
	{

	}

}