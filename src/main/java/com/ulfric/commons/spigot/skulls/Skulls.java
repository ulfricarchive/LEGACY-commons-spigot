package com.ulfric.commons.spigot.skulls;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.ClassUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.collect.ForwardingMultimap;
import com.ulfric.commons.exception.Try;

public enum Skulls {

	;

	private static final Base64.Encoder ENCODER = Base64.getEncoder();

	private static final Class<?> GAME_PROFILE =
			Try.to(() -> ClassUtils.getClass("com.mojang.authlib.GameProfile"));

	private static final Constructor<?> GAME_PROFILE_CONSTRUCTOR =
			Try.to(() -> Skulls.GAME_PROFILE.getDeclaredConstructor(UUID.class, String.class));

	private static final Method GET_PROPERTIES =
			Try.to(() -> Skulls.GAME_PROFILE.getDeclaredMethod("getProperties"));

	private static final Class<?> PROPERTY =
			Try.to(() -> ClassUtils.getClass("com.mojang.authlib.properties.Property"));

	private static final Constructor<?> PROPERTY_CONSTRUCTOR =
			Try.to(() -> Skulls.PROPERTY.getDeclaredConstructor(String.class, String.class));

	private static final Method MAP_PUT =
			Try.to(() -> ForwardingMultimap.class.getDeclaredMethod("put", Object.class, Object.class));

	private static final Field PROFILE_FIELD =
			Try.to(() -> ItemMeta.class.getDeclaredField("profile"));

	private static final Map<String, ItemStack> SKULL_CACHE = new ConcurrentHashMap<>();

	public static ItemStack of(String url)
	{
		return Skulls.SKULL_CACHE.computeIfAbsent(url.toLowerCase(), lowerUrl ->
		{
			ItemStack item = Skulls.createNewSkull();
			ItemMeta meta = item.getItemMeta();

			Object profile = Skulls.generateNewProfile();

			byte[] encodedData = Skulls.encodeUrl(url);

			Object propertyMap = Skulls.getPropertyMap(profile);

			Object newProperties = Skulls.createNewProperties(encodedData);

			Skulls.injectNewProperties(propertyMap, newProperties);
			Skulls.setProfile(meta, profile);

			item.setItemMeta(meta);

			return item;
		});
	}

	private static ItemStack createNewSkull()
	{
		return new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
	}

	private static Object generateNewProfile()
	{
		return Try.to(() -> Skulls.GAME_PROFILE_CONSTRUCTOR.newInstance(UUID.randomUUID(), null));
	}

	private static byte[] encodeUrl(String url)
	{
		return Skulls.ENCODER.encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
	}

	private static Object getPropertyMap(Object profile)
	{
		return Try.to(() -> Skulls.GET_PROPERTIES.invoke(profile));
	}

	private static Object createNewProperties(byte[] encodedData)
	{
		return Try.to(() -> Skulls.PROPERTY_CONSTRUCTOR.newInstance("textures", new String(encodedData)));
	}

	private static void injectNewProperties(Object propertyMap, Object newProperties)
	{
		Try.to(() -> Skulls.MAP_PUT.invoke(propertyMap, "textures", newProperties));
	}

	private static void setProfile(ItemMeta meta, Object profile)
	{
		Try.to(() -> Skulls.PROFILE_FIELD.set(meta, profile));
	}

}
