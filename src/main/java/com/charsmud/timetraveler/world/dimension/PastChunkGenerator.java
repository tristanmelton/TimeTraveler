package com.charsmud.timetraveler.world.dimension;

import java.util.List;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PastChunkGenerator implements IChunkGenerator {
	
	private final World world;
	private final IChunkGenerator past;
	
	public PastChunkGenerator(World world, IChunkGenerator past) {
		this.world = world;
		this.past = past;
	}
	
	@Override
	public Chunk generateChunk(int x, int z) {
		return past.generateChunk(x, z);
	}
	
	@Override
	public void populate(int x, int z) {
		past.populate(x, z);
		// Hackery to simulate some mods another dimension id to generate their stuff in
		// mirrored overworld - credit to MC-U-Team for this!
		int dimIDBefore = world.provider.getDimension();
		world.provider.setDimension(0);
		GameRegistry.generateWorld(x, z, world, this, world.getChunkProvider());
		world.provider.setDimension(dimIDBefore);
		
	}
	
	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return past.generateStructures(chunkIn, x, z);
	}
	
	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		return past.getPossibleCreatures(creatureType, pos);
	}
	
	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
		return past.getNearestStructurePos(worldIn, structureName, position, findUnexplored);
	}
	
	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
		past.recreateStructures(chunkIn, x, z);
	}
	
	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return past.isInsideStructure(worldIn, structureName, pos);
	}
	
}