package com.ulfric.commons.spigot.item;

import com.ulfric.commons.bean.Bean;
import com.ulfric.commons.spigot.text.ChatUtils;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Item extends Bean {
	
	public static Builder builder()
	{
		return new Builder();
	}
	
	private static final class Builder implements org.apache.commons.lang3.builder.Builder<Item>
	{
		
		private MaterialType type;
		private int amount;
		private String displayName;
		private List<String> lore;
		private boolean colorize;
		
		private final Map<Enchantment, Integer> enchantments = new HashMap<>();
		
		Builder()
		{
			
		}
		
		@Override
		public Item build()
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
			
			return new Item(itemStack);
		}
		
		public Builder setMaterialType(MaterialType type)
		{
			this.type = type;
			return this;
		}
		
		public Builder setAmount(int amount)
		{
			this.amount = amount;
			return this;
		}
		
		public Builder setDisplayName(String displayName)
		{
			this.displayName = displayName;
			return this;
		}
		
		public Builder setLore(List<String> lore)
		{
			this.lore = lore;
			return this;
		}
		
		public Builder colorize()
		{
			this.colorize = true;
			return this;
		}
		
		public Builder addEnchantment(Enchantment enchantment, int level)
		{
			this.enchantments.put(enchantment, level);
			return this;
		}
		
	}
	
	private final ItemStack itemStack;
	
	Item(ItemStack itemStack)
	{
		this.itemStack = itemStack;
	}
	
	public ItemStack getItemStack()
	{
		return this.itemStack;
	}
	
}
