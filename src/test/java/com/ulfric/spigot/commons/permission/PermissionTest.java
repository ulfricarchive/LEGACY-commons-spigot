package com.ulfric.spigot.commons.permission;

import java.lang.annotation.Annotation;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.ulfric.verify.Verify;

@RunWith(JUnitPlatform.class)
class PermissionTest {

	@Test
	void testSinglePermission()
	{
		Permission permission = this.get("testPermission", Permission.class);
		Verify.that(permission.value()).isEqualTo("hello");
	}

	@Test
	void testMultiplePermissions()
	{
		Permissions permissions = this.get("testPermissionMulti", Permissions.class);
		Permission[] values = permissions.value();
		Verify.that(values.length).isNotZero();
		for (Permission value : values)
		{
			Verify.that(value.value()).isEqualTo("hello");
		}
	}

	private <T extends Annotation> T get(String methodName, Class<T> annotationType)
	{
		try
		{
			return Perms.class.getDeclaredMethod(methodName).getAnnotation(annotationType);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			throw new RuntimeException(e);
		}
	}

	class Perms
	{
		@Permission("hello")
		void testPermission() { }

		@Permission("hello")
		@Permission("hello")
		void testPermissionMulti() { }
	}

}