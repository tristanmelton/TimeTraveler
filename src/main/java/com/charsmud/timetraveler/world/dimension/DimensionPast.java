package com.charsmud.timetraveler.world.dimension;

import com.charsmud.timetraveler.world.DimensionInit;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.gen.IChunkGenerator;

public class DimensionPast extends WorldProviderSurface
{
	
	@Override
	public DimensionType getDimensionType() {
		return DimensionInit.PAST;
	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		return new PastChunkGenerator(world, super.createChunkGenerator());
	}
	@Override
	public boolean canRespawnHere()
	{
		return false;
	}
}
