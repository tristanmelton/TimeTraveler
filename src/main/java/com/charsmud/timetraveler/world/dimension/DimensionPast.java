package com.charsmud.timetraveler.world.dimension;

import com.charsmud.timetraveler.world.DimensionInit;

import net.minecraft.init.Biomes;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.IChunkGenerator;

public class DimensionPast extends WorldProvider
{
	public DimensionPast()
	{
		this.biomeProvider = new BiomeProviderSingle(Biomes.STONE_BEACH);
	}
	@Override
	public DimensionType getDimensionType()
	{
		return DimensionInit.PAST;
	}
	@Override 
	public IChunkGenerator createChunkGenerator()
	{
		return new PastChunkGenerator(world, super.createChunkGenerator());
	}
	@Override
	public boolean canRespawnHere()
	{
		return false;
	}
}
