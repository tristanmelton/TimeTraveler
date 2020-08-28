package com.charsmud.timetraveler.world.dimension;

import com.charsmud.timetraveler.world.DimensionInit;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.gen.IChunkGenerator;

public class DimensionFuture extends WorldProviderSurface
{
	
	@Override
	public DimensionType getDimensionType() {
		return DimensionInit.FUTURE;
	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		return new FutureChunkGenerator(world, super.createChunkGenerator());
	}
	@Override
	public boolean canRespawnHere()
	{
		return false;
	}
}
