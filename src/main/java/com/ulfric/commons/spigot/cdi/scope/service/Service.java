package com.ulfric.commons.spigot.cdi.scope.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ulfric.commons.cdi.construct.scope.Scope;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Scope
public @interface Service {

}
