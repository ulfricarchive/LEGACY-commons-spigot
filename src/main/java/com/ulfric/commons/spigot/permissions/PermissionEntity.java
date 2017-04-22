package com.ulfric.commons.spigot.permissions;

import java.util.function.Predicate;
import java.util.stream.Stream;

import com.ulfric.commons.identity.Identifiable;

public interface PermissionEntity extends Identifiable, Predicate<String> {

	void add(String node);

	void add(PermissionEntity parent);
	
	void remove(String node);
	
	void remove(PermissionEntity parent);
	
	Stream<PermissionEntity> getParents();

}