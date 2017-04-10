package com.ulfric.commons.spigot.metadata;

public interface MetadataIdentifier<T> {

	boolean equal(T first, T second);

}
