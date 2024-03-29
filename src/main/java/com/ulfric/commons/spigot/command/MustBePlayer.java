package com.ulfric.commons.spigot.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Rule(MustBePlayerEnforcement.class)
public @interface MustBePlayer {

}
