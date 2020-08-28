package com.charsmud.timetraveler.world.dimension;

import java.util.List;

import com.charsmud.timetraveler.util.mechanics.future.FutureTravelMechanics;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FutureChunkGenerator implements IChunkGenerator {
	
	private final World world;
	private final IChunkGenerator future;
	private final FutureTravelMechanics future_mechanics;
	public FutureChunkGenerator(World world, IChunkGenerator future) {
		this.future_mechanics = new FutureTravelMechanics();
		this.world = world;
		this.future = future;
	}
	
	@Override
	public Chunk generateChunk(int x, int z) {
		return future.generateChunk(x, z);
	}
	
	@Override
	public void populate(int x, int z) {
		future.populate(x, z);
		// Hackery to simulate some mods another dimension id to generate their stuff in
		// mirrored overworld - credit to MC-U-Team for this!
		int dimIDBefore = world.provider.getDimension();
		world.provider.setDimension(0);
		GameRegistry.generateWorld(x, z, world, this, world.getChunkProvider());
		future_mechanics.expandOres(world, x, z, 20);
		future_mechanics.expandForests(world, x, z, 20);
		world.provider.setDimension(dimIDBefore);
		
	}
	
	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return future.generateStructures(chunkIn, x, z);
	}
	
	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		return future.getPossibleCreatures(creatureType, pos);
	}
	
	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
		return future.getNearestStructurePos(worldIn, structureName, position, findUnexplored);
	}
	
	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
		future.recreateStructures(chunkIn, x, z);
	}
	
	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return future.isInsideStructure(worldIn, structureName, pos);
	}
	
}