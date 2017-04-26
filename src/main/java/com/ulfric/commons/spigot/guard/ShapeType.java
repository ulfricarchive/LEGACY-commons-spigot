package com.ulfric.commons.spigot.guard;

import com.ulfric.commons.naming.Named;

public enum ShapeType {

	OMNIPRESENT(Omnipresent.class),
	CUBOID(Cuboid.class);

	public static ShapeType getShapeType(String name)
	{
		return ShapeType.valueOf(name.toUpperCase());
	}

	private final Class<? extends Shape> type;

	private ShapeType(Class<? extends Shape> type)
	{
		this.type = type;
	}

	public Class<? extends Shape> getShapeType()
	{
		return this.type;
	}

	public String getShapeName()
	{
		return Named.getNameFromAnnotation(this.type);
	}

}