package com.charsmud.timetraveler.world;

import java.util.Random;

import com.charsmud.timetraveler.blocks.BlockInit;
import com.charsmud.timetraveler.items.ItemInit;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenCustomStructures implements IWorldGenerator 
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		int i = (chunkX * 16) + random.nextInt(15);
		int k = (chunkZ * 16) + random.nextInt(15);
		int j = calculateGenerationHeight(world, i, k, Blocks.GRASS);
		int chance = random.nextInt(5);
		if(chance == 0 && world.getBlockState(new BlockPos(i, j, k)).getBlock()  != Blocks.GRASS || world.getBlockState(new BlockPos(i + 10, j, k)).getBlock() != Blocks.GRASS || world.getBlockState(new BlockPos(i + 10, j, k + 10)).getBlock() != Blocks.GRASS || world.getBlockState(new BlockPos(i, j, k + 10)).getBlock() != Blocks.GRASS)
		{
			return;
		}
		System.out.println("Generating");
        TileEntityChest tileentitychest = new TileEntityChest();
        tileentitychest.setInventorySlotContents(0, new ItemStack(ItemInit.PARADOXIMER, 1));
		world.setBlockState(new BlockPos(i + 0, j + 0, k + 0), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 0, j + 0, k + 1), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 0, j + 0, k + 2), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 0, j + 0, k + 3), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 0, j + 0, k + 4), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 0, j + 0, k + 5), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 0, j + 0, k + 6), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 0, j + 0, k + 7), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 0, j + 0, k + 8), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 0, k + 0), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 0, k + 1), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 0, k + 2), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 0, k + 3), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 0, k + 4), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 0, k + 5), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 0, k + 6), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 0, k + 7), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 0, k + 8), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 1, k + 3), Blocks.MOSSY_COBBLESTONE.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 1, k + 4), Blocks.STONEBRICK.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 1, k + 5), Blocks.MOSSY_COBBLESTONE.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 2, k + 4), Blocks.STONEBRICK.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 3, k + 4), Blocks.STONEBRICK.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 4, k + 4), Blocks.STONEBRICK.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 5, k + 4), Blocks.STONEBRICK.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 6, k + 4), Blocks.GLOWSTONE.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 0), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 1), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 2), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 3), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 4), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 5), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 6), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 7), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 8), Blocks.GRASS.getDefaultState());
		//world.setBlockState(new BlockPos(i + 2, j + 1, k + 3), Blocks.STONE_BRICK_STAIRS, 2, 1);
		world.setBlockState(new BlockPos(i + 2, j + 1, k + 4), Blocks.STONEBRICK.getDefaultState());
		//world.setBlockState(new BlockPos(i + 2, j + 1, k + 5), Blocks.STONE_BRICK_STAIRS, 3, 1);
		//world.setBlockState(new BlockPos(i + 2, j + 4, k + 4), Blocks.STONE_BRICK_STAIRS, 5, 1);
		world.setBlockState(new BlockPos(i + 2, j + 5, k + 4), Blocks.STONEBRICK.getDefaultState());
		//world.setBlockState(new BlockPos(i + 2, j + 6, k + 4), Blocks.STONE_BRICK_STAIRS, 1, 1);
		world.setBlockState(new BlockPos(i + 3, j + 0, k + 0), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 0, k + 1), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 0, k + 2), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 0, k + 3), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 0, k + 4), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 0, k + 5), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 0, k + 6), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 0, k + 7), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 0, k + 8), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 1, k + 1), Blocks.MOSSY_COBBLESTONE.getDefaultState());
		//world.setBlockState(new BlockPos(i + 3, j + 1, k + 2), Blocks.STONE_BRICK_STAIRS);
		world.setBlockState(new BlockPos(i + 3, j + 1, k + 3), Blocks.REDSTONE_ORE.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 1, k + 4), Blocks.STONEBRICK.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 1, k + 5), Blocks.REDSTONE_ORE.getDefaultState());
		//world.setBlockState(new BlockPos(i + 3, j + 1, k + 6), Blocks.STONE_BRICK_STAIRS);
		world.setBlockState(new BlockPos(i + 3, j + 1, k + 7), Blocks.MOSSY_COBBLESTONE.getDefaultState());
		//world.setBlockState(new BlockPos(i + 3, j + 2, k + 4), Blocks.STONE_BRICK_STAIRS);
		//world.setBlockState(new BlockPos(i + 3, j + 4, k + 4), Blocks.STONE_BRICK_STAIRS, 4, 1);
		world.setBlockState(new BlockPos(i + 3, j + 5, k + 4), Blocks.LAPIS_BLOCK.getDefaultState());
		//world.setBlockState(new BlockPos(i + 3, j + 6, k + 4), Blocks.STONE_BRICK_STAIRS);
		world.setBlockState(new BlockPos(i + 4, j + 0, k + 0), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 0, k + 1), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 0, k + 2), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 0, k + 3), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 0, k + 4), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 0, k + 5), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 0, k + 6), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 0, k + 7), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 0, k + 8), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 1, k + 1), Blocks.STONEBRICK.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 1, k + 2), Blocks.STONEBRICK.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 1, k + 3), Blocks.MONSTER_EGG.getStateFromMeta(2));
		world.setBlockState(new BlockPos(i + 4, j + 1, k + 4), Blocks.GLOWSTONE.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 1, k + 5), Blocks.STONEBRICK.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 1, k + 6), Blocks.MONSTER_EGG.getStateFromMeta(2));
		world.setBlockState(new BlockPos(i + 4, j + 1, k + 7), Blocks.STONEBRICK.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 2, k + 1), Blocks.STONEBRICK.getDefaultState());
		//world.setBlockState(new BlockPos(i + 4, j + 2, k + 3), Blocks.STONE_BRICK_STAIRS, 2, 1);
		world.setBlockState(new BlockPos(i + 4, j + 2, k + 4), BlockInit.TIME_TRAVELER.getDefaultState());
		//world.setBlockState(new BlockPos(i + 4, j + 2, k + 5), Blocks.STONE_BRICK_STAIRS, 3, 1);
		world.setBlockState(new BlockPos(i + 4, j + 2, k + 7), Blocks.STONEBRICK.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 3, k + 1), Blocks.STONEBRICK.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 3, k + 7), Blocks.STONEBRICK.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 4, k + 1), Blocks.STONEBRICK.getDefaultState());
		//world.setBlockState(new BlockPos(i + 4, j + 4, k + 2), Blocks.STONE_BRICK_STAIRS, 7, 1);
		//world.setBlockState(new BlockPos(i + 4, j + 4, k + 3), Blocks.STONE_BRICK_STAIRS, 6, 1);
		//world.setBlockState(new BlockPos(i + 4, j + 4, k + 5), Blocks.STONE_BRICK_STAIRS, 7, 1);
		//world.setBlockState(new BlockPos(i + 4, j + 4, k + 6), Blocks.STONE_BRICK_STAIRS, 6, 1);
		world.setBlockState(new BlockPos(i + 4, j + 4, k + 7), Blocks.STONEBRICK.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 5, k + 1), Blocks.STONEBRICK.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 5, k + 2), Blocks.STONEBRICK.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 5, k + 3), Blocks.LAPIS_BLOCK.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 5, k + 4), Blocks.OBSIDIAN.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 5, k + 5), Blocks.LAPIS_BLOCK.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 5, k + 6), Blocks.STONEBRICK.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 5, k + 7), Blocks.STONEBRICK.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 6, k + 1), Blocks.GLOWSTONE.getDefaultState());
		//world.setBlockState(new BlockPos(i + 4, j + 6, k + 2), Blocks.STONE_BRICK_STAIRS, 3, 1);
		//world.setBlockState(new BlockPos(i + 4, j + 6, k + 3), Blocks.STONE_BRICK_STAIRS, 2, 1);
		world.setBlockState(new BlockPos(i + 4, j + 6, k + 4), Blocks.CHEST.getDefaultState());
		world.setTileEntity(new BlockPos(i + 4, j + 6, k + 4), tileentitychest);
		//world.setBlockState(new BlockPos(i + 4, j + 6, k + 5), Blocks.STONE_BRICK_STAIRS, 3, 1);
		//world.setBlockState(new BlockPos(i + 4, j + 6, k + 6), Blocks.STONE_BRICK_STAIRS, 2, 1);
		world.setBlockState(new BlockPos(i + 4, j + 6, k + 7), Blocks.GLOWSTONE.getDefaultState());
		world.setBlockState(new BlockPos(i + 5, j + 0, k + 0), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 5, j + 0, k + 1), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 5, j + 0, k + 2), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 5, j + 0, k + 3), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 5, j + 0, k + 4), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 5, j + 0, k + 5), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 5, j + 0, k + 6), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 5, j + 0, k + 7), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 5, j + 0, k + 8), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 5, j + 1, k + 1), Blocks.MOSSY_COBBLESTONE.getDefaultState());
		//world.setBlockState(new BlockPos(i + 5, j + 1, k + 2), Blocks.STONE_BRICK_STAIRS, 1, 1);
		world.setBlockState(new BlockPos(i + 5, j + 1, k + 3), Blocks.REDSTONE_ORE.getDefaultState());
		world.setBlockState(new BlockPos(i + 5, j + 1, k + 4), Blocks.STONEBRICK.getDefaultState());
		world.setBlockState(new BlockPos(i + 5, j + 1, k + 5), Blocks.REDSTONE_ORE.getDefaultState());
		//world.setBlockState(new BlockPos(i + 5, j + 1, k + 6), Blocks.STONE_BRICK_STAIRS, 1, 1);
		world.setBlockState(new BlockPos(i + 5, j + 1, k + 7), Blocks.MOSSY_COBBLESTONE.getDefaultState());
		//world.setBlockState(new BlockPos(i + 5, j + 2, k + 4), Blocks.STONE_BRICK_STAIRS, 1, 1);
		//world.setBlockState(new BlockPos(i + 5, j + 4, k + 4), Blocks.STONE_BRICK_STAIRS, 5, 1);
		world.setBlockState(new BlockPos(i + 5, j + 5, k + 4), Blocks.LAPIS_BLOCK.getDefaultState());
		//world.setBlockState(new BlockPos(i + 5, j + 6, k + 4), Blocks.STONE_BRICK_STAIRS, 1, 1);
		world.setBlockState(new BlockPos(i + 6, j + 0, k + 0), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 6, j + 0, k + 1), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 6, j + 0, k + 2), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 6, j + 0, k + 3), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 6, j + 0, k + 4), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 6, j + 0, k + 5), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 6, j + 0, k + 6), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 6, j + 0, k + 7), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 6, j + 0, k + 8), Blocks.GRASS.getDefaultState());
		//world.setBlockState(new BlockPos(i + 6, j + 1, k + 3), Blocks.STONE_BRICK_STAIRS, 2, 1);
		world.setBlockState(new BlockPos(i + 6, j + 1, k + 4), Blocks.MONSTER_EGG.getStateFromMeta(2));
		//world.setBlockState(new BlockPos(i + 6, j + 1, k + 5), Blocks.STONE_BRICK_STAIRS, 3, 1);
		//world.setBlockState(new BlockPos(i + 6, j + 4, k + 4), Blocks.STONE_BRICK_STAIRS, 4, 1);
		world.setBlockState(new BlockPos(i + 6, j + 5, k + 4), Blocks.STONEBRICK.getDefaultState());
		//world.setBlockState(new BlockPos(i + 6, j + 6, k + 4), Blocks.STONE_BRICK_STAIRS.GetDefaultState());
		world.setBlockState(new BlockPos(i + 7, j + 0, k + 0), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 7, j + 0, k + 1), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 7, j + 0, k + 2), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 7, j + 0, k + 3), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 7, j + 0, k + 4), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 7, j + 0, k + 5), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 7, j + 0, k + 6), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 7, j + 0, k + 7), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 7, j + 0, k + 8), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 7, j + 1, k + 3), Blocks.MOSSY_COBBLESTONE.getDefaultState());
		world.setBlockState(new BlockPos(i + 7, j + 1, k + 4), Blocks.STONEBRICK.getDefaultState());
		world.setBlockState(new BlockPos(i + 7, j + 1, k + 5), Blocks.MOSSY_COBBLESTONE.getDefaultState());
		world.setBlockState(new BlockPos(i + 7, j + 2, k + 4), Blocks.STONEBRICK.getDefaultState());
		world.setBlockState(new BlockPos(i + 7, j + 3, k + 4), Blocks.STONEBRICK.getDefaultState());
		world.setBlockState(new BlockPos(i + 7, j + 4, k + 4), Blocks.STONEBRICK.getDefaultState());
		world.setBlockState(new BlockPos(i + 7, j + 5, k + 4), Blocks.STONEBRICK.getDefaultState());
		world.setBlockState(new BlockPos(i + 7, j + 6, k + 4), Blocks.GLOWSTONE.getDefaultState());
		world.setBlockState(new BlockPos(i + 8, j + 0, k + 0), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 8, j + 0, k + 1), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 8, j + 0, k + 2), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 8, j + 0, k + 3), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 8, j + 0, k + 4), Blocks.DIRT.getDefaultState());
		world.setBlockState(new BlockPos(i + 8, j + 0, k + 5), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 8, j + 0, k + 6), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 8, j + 0, k + 7), Blocks.GRASS.getDefaultState());
		world.setBlockState(new BlockPos(i + 8, j + 0, k + 8), Blocks.GRASS.getDefaultState());

	}
	private static int calculateGenerationHeight(World world, int x, int z, Block topBlock)
	{
		int y = world.getHeight();
		boolean foundGround = false;
		
		while(!foundGround && y-- >= 0)
		{
			Block block = world.getBlockState(new BlockPos(x,y,z)).getBlock();
			foundGround = block == topBlock;
		}
		return y;
	}
}
