package com.charsmud.timetraveler.util.mechanics.future;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.ImmutableMap;

import java.util.Random;
import java.util.Set;
import java.util.WeakHashMap;

import net.minecraft.block.Block;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.village.Village;
import net.minecraft.village.VillageCollection;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.BlockStateContainer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenBirchTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ChunkCoordComparator;
import net.minecraftforge.fml.client.FMLClientHandler;

/**
 * Contains information about the future mechanics
 * @author Charsmud
 *
 */
public class FutureTravelMechanics
{
	public FutureTravelMechanics()
	{
	}
	Random random = new Random();
	/**
	 * Main expanding forests method
	 * @param world
	 * @param size
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	public void expandForests(World world, int chunkX, int chunkZ, int chunkRuns)
	{
		if(!world.isRemote)
		{
			try
			{	
    			//Collection<Chunk> hashChunk = world.getChunkProvider().getLoadedChunks();
    			//Iterator itr = hashChunk.iterator();
				Chunk currentScanningChunk = world.getChunkFromChunkCoords(chunkX, chunkZ);
		        //Iterator<ChunkCoordIntPair> iterator = world.activeChunkSet.iterator();
				ArrayList<BlockPos> list = new ArrayList<BlockPos>();
				ArrayList<IBlockState> treeTypes = new ArrayList<IBlockState>();
				// while(itr.hasNext())
				if (currentScanningChunk != null) 
				{
					int h = currentScanningChunk.getTopFilledSegment();
					for (int i = 0; i < chunkRuns; ++i) 
					{
						int x = random.nextInt(16);
						int z = random.nextInt(16);
						int y = currentScanningChunk.getHeightValue(x, z) - 1;
						if (y >= 0 && currentScanningChunk.getBlockState(new BlockPos(x, y, z))
								.getBlock() == Blocks.LEAVES)
						{
							double dx = random.nextDouble();
							double dz = random.nextDouble();
							double del = (double) (dx * dx + dz * dz);
							del = 8.0D / MathHelper.sqrt(del) + 8.0 * random.nextDouble() * random.nextDouble();
							int x_ = (int) (dx * del) + (x += currentScanningChunk.x << 4);
							int z_ = (int) (dz * del) + (z += currentScanningChunk.z << 4);
							int y_ = world.getHeight(x_, z_) - 1;
							if (y_ == -1 || world.getBlockState(new BlockPos(x_, y_, z_)).getBlock() != Blocks.GRASS
									|| world.getBlockState(new BlockPos(x_, y_ + 1, z_)).getBlock() != Blocks.AIR
									|| (y - y_) > 32 || (y - y_) < 3)
								continue;
							list.add(new BlockPos(x_, y_ + 1, z_));
							treeTypes.add(world.getBlockState(new BlockPos(x, y, z)));
						}
					}
				}
				// System.out.println(">> " + list.size());
				Iterator<IBlockState> typeIterator = treeTypes.iterator();
				for (BlockPos coord : list) 
				{
					if (typeIterator.hasNext())
					{
						growTree(typeIterator.next(), world, coord.getX(), coord.getY(), coord.getZ(), random);
					}
				}
			}
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
		}
	}
	/**
	 * Grows the trees
	 * @param meta
	 * @param worldIn
	 * @param x
	 * @param y
	 * @param z
	 * @param rand
	 */
	private void growTree(IBlockState meta, World worldIn, int x, int y, int z, Random rand) {
		int l = Biome.getIdForBiome(worldIn.getBiome(new BlockPos(x,y,z)));
		Object object = null;
		int i1 = 0;
		int j1 = 0;
		if (l == Biome.getIdForBiome(Biomes.TAIGA) || l ==  Biome.getIdForBiome(Biomes.TAIGA_HILLS)) 
		{
			object = new WorldGenTaiga2(true);
		} 
		else if (l == Biome.getIdForBiome(Biomes.BIRCH_FOREST) || l == Biome.getIdForBiome(Biomes.BIRCH_FOREST_HILLS)) 
		{
			object = new WorldGenBirchTree(true, true);
		} 
		else if (l == Biome.getIdForBiome(Biomes.FOREST) || l == Biome.getIdForBiome(Biomes.FOREST_HILLS)) 
		{
			if (rand.nextInt(10) == 0)
			{
				object = new WorldGenBigTree(true);
			}
			else 
			{
				object = new WorldGenTrees(true);// WorldGenTrees(true, 4 + rand.nextInt(7), 3, 3, false);
			}
		} 
		else 
		{
			object = new WorldGenTrees(true);

			if (rand.nextInt(10) == 0)
			{
				object = new WorldGenBigTree(true);
			}
		}
		((WorldGenerator) object).generate(worldIn, rand, new BlockPos(x + i1, y, z + j1));
	}
	/**
	 * Ore Mappings and Max Ore Range
	 */
    private static final Map<Block, Tuple> oreExpansion = new HashMap();
    { // value range for maximum ore in a chunk
            oreExpansion.put(Blocks.REDSTONE_ORE, new Tuple(150, 200)); //24.8  25,50
            oreExpansion.put(Blocks.IRON_ORE, new Tuple(150, 300)); //77  77,154
            oreExpansion.put(Blocks.COAL_ORE, new Tuple(140, 200)); // 142.6  143, 284
            oreExpansion.put(Blocks.GOLD_ORE, new Tuple(70, 140)); //8.2  8, 16
            oreExpansion.put(Blocks.LAPIS_ORE, new Tuple(50, 100)); //3.43  3, 7
            oreExpansion.put(Blocks.DIAMOND_ORE, new Tuple(50, 100)); //3.097  3, 6
            oreExpansion.put(Blocks.EMERALD_ORE, new Tuple(50, 100));
    }

    /**
     * Contains Chunk Stuff
     */
    private static WeakHashMap<Chunk, Long> basis = new WeakHashMap();
    
    //Map<ChunkCoordinates, Integer> placements = new LinkedHashMap<ChunkCoordinates, Integer>();
    //Set<Entry<ChunkCoordinates, Integer>> p2 = new HashSet<Entry<ChunkCoordinates, Integer>>();
    
    /**
     * Expands ores
     * @param world
     */
    public void expandOres(World world, int chunkX, int chunkZ, int numRuns)
    {
    	if(!world.isRemote)
    	{
    		try
    		{
    			//Collection<Chunk> chunks = world.getChunkProvider().getLoadedChunks();
                Chunk currentScanningChunk = world.getChunkFromChunkCoords(chunkX, chunkZ);
    			//Iterator<Chunk> iterator = chunks.iterator();
                
                Map<Vec3i, Integer> placements = new HashMap();
                
                Set<Integer> oreIDs = new HashSet();
                for (Block block : oreExpansion.keySet())
                        oreIDs.add(Block.getIdFromBlock(block));
                
                //while(iterator.hasNext())
                if(currentScanningChunk != null)
                {
                    //Chunk chunk = iterator.next();
                    //Chunk currentScanningChunk = world.getChunkFromChunkCoords(chunk.x, chunk.z);
                    long base;
                    if (!basis.containsKey(currentScanningChunk))
                        basis.put(currentScanningChunk, base = currentScanningChunk.getRandomWithSeed(253L).nextLong());
                    else
                    	base = basis.get(currentScanningChunk);
                    Map<Integer, List<Vec3i>> possible = new HashMap();
                    for (int i : oreIDs)
                    	possible.put(i, new ArrayList());
                    ExtendedBlockStorage[] blockStorage = currentScanningChunk.getBlockStorageArray();
                    for (int i = 0; i < blockStorage.length && blockStorage[i] != null; ++i)
					{
						ExtendedBlockStorage storage = blockStorage[i];
						BlockStateContainer state = storage.getData();
						for (int x = 0; x < 16; x++)
						{
							for (int z = 0; z < 16; z++) 
							{
								for (int y = 0; y < 16; y++) 
								{
									IBlockState blockState = state.get(x, y, z);
									if(blockState == null)
										continue;
									int id = Block.getIdFromBlock(state.get(x, y, z).getBlock());
									if (oreIDs.contains(id)) 
									{
										possible.get(id).add(new Vec3i(currentScanningChunk.x * 16 + x, i * 16 + y, currentScanningChunk.z * 16 + z));
									}
								}
							}
						}
						/*
						 * byte[] blocksLSB = storage.getData().get(x, y, z); for (int j = 0; j <
						 * blocksLSB.length; ++j) { if (blocksLSB[j] != 0 &&
						 * oreIDs.contains(blocksLSB[j]&255)) { System.out.println("POSSIBLE");
						 * possible.get(blocksLSB[j]&255).add(new ChunkCoordinates((j & 15) +
						 * (chunk.chunkXPos << 4), (j >> 8) + storage.getYLocation(), (j >> 4 & 15) +
						 * (chunk.chunkZPos << 4))); } }
						 */
					}
					for (Map.Entry<Block, Tuple> oreProbability : oreExpansion.entrySet()) 
					{
						int blockID = Block.getIdFromBlock(oreProbability.getKey());
						List<Vec3i> genCoords = possible.get(blockID);
						if (genCoords.size() == 0)
							continue;
						Tuple t = oreProbability.getValue();
						int min = (Integer) t.getFirst();
						int max = (Integer) t.getSecond();
						int chunkCount = (int) ((base ^ blockID * 6364136223846793005L + 1442695040888963407L)
								% (max - min + 1) + min);
						int attempts = chunkCount - genCoords.size();
						if (attempts > 0) 
						{
							attempts = random.nextInt(attempts + 1);
							attempts = random.nextInt(attempts + 1);
							for (int i = 0; i < attempts && genCoords.size() > 0; i++)
							{
								placements.put(genCoords.remove(random.nextInt(genCoords.size())), blockID);
							}
						}
					}
				}
                for (Iterator<Map.Entry<Vec3i, Integer>> iter = placements.entrySet().iterator(); iter.hasNext();)
                {
                    Map.Entry<Vec3i, Integer> entry = iter.next();
                    Vec3i location = entry.getKey();
                    int x = location.getX(), y = location.getY(), z = location.getZ();
                    switch (random.nextInt(6))
                    {
                    	case 0:
                    		--y;
                            break;
                        case 1:
                            ++y;
                            break;
                        case 2:
                            --z;
                            break;
                        case 3:
                            ++z;
                            break;
                        case 4:
                            --x;
                            break;
                        case 5:
                            ++x;
                    }
                    BlockPos blockPos = new BlockPos(x,y,z); 
                    if (world.getBlockState(blockPos).getBlock() == Blocks.STONE
                    	|| world.getBlockState(blockPos).getBlock() == Blocks.GRAVEL)
                    {
                        System.out.println("SETBLOCK " + Block.getBlockById(entry.getValue()).getUnlocalizedName() + " " + blockPos);
                        world.setBlockState(blockPos, Block.getBlockById(entry.getValue()).getDefaultState());
                    }
                }
    		}
    		catch(Exception ex)
    		{
    			ex.printStackTrace();
    		}
    	}
    }


    
    public void expandVillages(World world)
    {
    	VillageCollection worldVillages = world.villageCollection;
    	List<Village> villageList = worldVillages.getVillageList();
    
        Iterator iterator = villageList.iterator();
        //StructureVillagePieces villagePieces = new StructureVillagePieces();
        while(iterator.hasNext())
        {
        	Village village = (Village)iterator.next();
        	//village.
        	//ArrayList listOfPieces = villagePieces.getStructureVillageWeightedPieceList(new Random(), 9);
               
        }
    }
    

    private final class OreEntry<K, V> implements Map.Entry<K, V> 
    {
        private final K key;
        private V value;

        public OreEntry(K key, V value)
        {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey()
        {
            return key;
        }

        @Override
        public V getValue() 
        {
            return value;
        }

        @Override
        public V setValue(V value)
        {
            V old = this.value;
            this.value = value;
            return old;
        }
    }

}
	



