package com.ulfric.commons.spigot.permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@Repeatable(Permissions.class)
public @interface Permission {

	String value();

	String message() default "permission-missing";

}