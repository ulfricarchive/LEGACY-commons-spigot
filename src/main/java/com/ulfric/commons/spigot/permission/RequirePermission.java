package com.ulfric.commons.spigot.permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ulfric.commons.cdi.intercept.Intercept;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(RequirePermissions.class)
@Intercept
public @interface RequirePermission {

	String value();

}
