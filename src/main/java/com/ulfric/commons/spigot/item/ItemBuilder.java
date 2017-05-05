package com.ulfric.commons.spigot.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.Builder;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.ulfric.commons.spigot.text.Text;

public class ItemBuilder implements Builder<ItemStack> {

	private MaterialType type;
	private int amount = 1;
	private String displayName;
	private List<String> lore;
	private CommandSender formatFor;
	private Map<Enchantment, Integer> enchantments;

	@Override
	public ItemStack build()
	{
		Objects.requireNonNull(this.type, "type");
		
		MaterialType type = this.type;
		
		ItemStack itemStack = new ItemStack(type.getType(), this.amount, type.getData());
		
		ItemMeta meta = itemStack.getItemMeta();

		Function<String, String> plainMessage = this.plainMessageProvider();
		
		if (this.displayName != null)
		{
			meta.setDisplayName(plainMessage.apply(this.displayName));
		}
		
		if (this.lore != null)
		{
			meta.setLore(
					new ArrayList<>(this.lore)
							.stream()
							.map(plainMessage)
							.collect(Collectors.toList())
			);
		}
		
		itemStack.addEnchantments(this.enchantments);
		
		return itemStack;
	}

	private Function<String, String> plainMessageProvider()
	{
		if (this.formatFor != null)
		{
			return code -> Text.getService().getPlainMessage(this.formatFor, code);
		}

		return code -> Text.getService().getPlainMessage(code);
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

	public ItemBuilder addEnchantment(Enchantment enchantment, int level)
	{
		this.enchantments.put(enchantment, level);

		return this;
	}

	public ItemBuilder formatFor(CommandSender sender)
	{
		this.formatFor = sender;

		return this;
	}

}