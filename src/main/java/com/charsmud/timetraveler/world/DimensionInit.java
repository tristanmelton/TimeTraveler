package com.charsmud.timetraveler.world;

import com.charsmud.timetraveler.world.dimension.DimensionFuture;
import com.charsmud.timetraveler.world.dimension.DimensionPast;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class DimensionInit
{
	public static final int DIMENSION_FUTURE_ID = 2;
	public static final int DIMENSION_PAST_ID = 3;
	public static final DimensionType FUTURE = DimensionType.register("Future", "_future", DIMENSION_FUTURE_ID, DimensionFuture.class, false);
	public static final DimensionType PAST = DimensionType.register("Past", "_past", DIMENSION_PAST_ID, DimensionPast.class, false);
	public static void registerDimensions()
	{
		DimensionManager.registerDimension(DIMENSION_FUTURE_ID, FUTURE);
		DimensionManager.registerDimension(DIMENSION_PAST_ID, PAST);
	}
}
