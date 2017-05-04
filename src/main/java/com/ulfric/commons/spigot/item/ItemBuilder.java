package com.ulfric.commons.spigot.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.Builder;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.ulfric.commons.spigot.text.ChatUtils;

public class ItemBuilder implements Builder<ItemStack> {

	private MaterialType type;
	private int amount;
	private String displayName;
	private List<String> lore;
	private boolean colorize;
	private Map<Enchantment, Integer> enchantments;

	@Override
	public ItemStack build()
	{
		Objects.requireNonNull(type, "type");
		
		MaterialType type = this.type;
		
		ItemStack itemStack = new ItemStack(type.getType(), this.amount == 0 ? 1 : this.amount, type.getData());
		
		ItemMeta meta = itemStack.getItemMeta();
		
		if (this.displayName != null)
		{
			meta.setDisplayName(this.colorize ? ChatUtils.format(this.displayName) : this.displayName);
		}
		
		if (this.lore != null && !this.lore.isEmpty())
		{
			if (this.colorize)
			{
				meta.setLore(new ArrayList<>(this.lore).stream().map(ChatUtils::format).collect(Collectors.toList()));
			}
			else
			{
				meta.setLore(this.lore);
			}
		}
		
		itemStack.addEnchantments(this.enchantments);
		
		return itemStack;
	}

	public ItemBuilder setMaterialType(MaterialType type)
	{
		this.type = type;
		return this;
	}

	public ItemBuilder setAmount(int amount)
	{
		this.amount = amount;
		return this;
	}

	public ItemBuilder setDisplayName(String displayName)
	{
		this.displayName = displayName;
		return this;
	}

	public ItemBuilder setLore(List<String> lore)
	{
		this.lore = lore;
		return this;
	}

	public ItemBuilder setLore(String... lore)
	{
		return this.setLore(Arrays.asList(lore));
	}

	public ItemBuilder colorize()
	{
		this.colorize = true;
		return this;
	}

	public ItemBuilder addEnchantment(Enchantment enchantment, int level)
	{
		this.enchantments.put(enchantment, level);
		return this;
	}

}