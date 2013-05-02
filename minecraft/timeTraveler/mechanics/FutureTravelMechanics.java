package timeTraveler.mechanics;

import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.storage.ISaveHandler;
import cpw.mods.fml.client.FMLClientHandler;
/**
 * Contains information about the future mechanics
 * @author Charsmud
 *
 */
public class FutureTravelMechanics
{
	EntityPlayer ep;
	WorldClient world;
	
	/**
	 * Constructor
	 */
	public FutureTravelMechanics()
	{
		ep = FMLClientHandler.instance().getClient().thePlayer;
		world = FMLClientHandler.instance().getClient().theWorld;
	}
	/**
	 * Main expanding ores method
	 * @param world
	 * @param coal
	 * @param diamond
	 * @param emerald
	 * @param gold
	 * @param iron
	 * @param lapis
	 * @param redstone
	 */
	public void expandOres(WorldClient world, int coal, int diamond, int emerald, int gold, int iron, int lapis, int redstone)
	{
		Iterator<ChunkCoordIntPair> iterator = world.activeChunkSet.iterator();
		
		while(iterator.hasNext())
		{
			ChunkCoordIntPair coords = iterator.next();
			//Chunk currentScanningChunk = world.getChunkFromBlockCoords((int)ep.posX, (int) ep.posZ);
			Chunk currentScanningChunk = world.getChunkFromChunkCoords(coords.chunkXPos, coords.chunkZPos);
			expandRedstone(world, currentScanningChunk, redstone);
			expandDiamond(world, currentScanningChunk, diamond);
			expandCoal(world, currentScanningChunk, coal);
			expandEmerald(world, currentScanningChunk, emerald);
			expandGold(world, currentScanningChunk, gold);
			expandIron(world, currentScanningChunk, iron);
			expandLapis(world, currentScanningChunk, lapis);

			ISaveHandler save = world.getSaveHandler();
			IChunkLoader saver = save.getChunkLoader(world.provider);
			try
			{
				System.out.println(world);
				System.out.println(currentScanningChunk);
				saver.saveChunk(world, currentScanningChunk);
			}
			catch(MinecraftException ex)
			{
				ex.printStackTrace();
				System.out.println("FAILED TO SAVE MINE");
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
				System.out.println("FAILED TO SAVE IO");
			}

		}
	}
	/**
	 * Main expanding forests method
	 * @param world
	 * @param size
	 */
	public void expandForests(WorldClient world, int size)
	{
		Iterator<ChunkCoordIntPair> iterator = world.activeChunkSet.iterator();
		
		while(iterator.hasNext())
		{
			ChunkCoordIntPair coords = iterator.next();
			
			Chunk currentScanningChunk = world.getChunkFromChunkCoords(coords.chunkXPos, coords.chunkZPos);
			expandForest(world, currentScanningChunk, size);
			
			ISaveHandler save = world.getSaveHandler();
			IChunkLoader saver = save.getChunkLoader(world.provider);
			try
			{
				saver.saveChunk(world, currentScanningChunk);
			}
			catch(MinecraftException ex)
			{
				ex.printStackTrace();
				System.out.println("FAILED TO SAVE MINE");
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
				System.out.println("FAILED TO SAVE IO");
			}
		}
	}
	//BELOW ARE HELPER METHODS
	
	/**
	 * Coal ore expansion helper method
	 * @param world
	 * @param currentScanningChunk
	 * @param size
	 */
	public void expandCoal(WorldClient world, Chunk currentScanningChunk, int size)
	{
		for(int i = 0; i < size; i++)
		{
			for(int x = 0; x < 15; x++)
			{
				for(int y = 0; y < 255; y++)
				{
					for(int z = 0; z < 15; z++)
					{
						if(world.blockExists(x, y, z))
						{
							if(currentScanningChunk.getBlockID(x, y, z) == Block.oreCoal.blockID)
							{
								Random rand = new Random();
								int expandX = rand.nextInt(3)-1;
								int expandY = rand.nextInt(3)-1;
								int expandZ = rand.nextInt(3)-1;
								System.out.println(x*16 + " " + y + " " + z*16);
								if(currentScanningChunk.getBlockID(Math.abs(x + expandX), Math.abs(y + expandY), Math.abs(z + expandZ)) != 0)
								{
									currentScanningChunk.setBlockIDWithMetadata(Math.abs(x + expandX), Math.abs(y + expandY), Math.abs(z + expandZ), Block.oreCoal.blockID, 0);
								}
							}
						}
					}
				}
			}
		}
	}
	/**
	 * Diamond ore expansion helper method
	 * @param world
	 * @param currentScanningChunk
	 * @param size
	 */
	public void expandDiamond(WorldClient world, Chunk currentScanningChunk, int size)
	{
		for(int i = 0; i < size; i++)
		{
			for(int x = 0; x < 15; x++)
			{
				for(int y = 0; y < 255; y++)
				{
					for(int z = 0; z < 15; z++)
					{
						if(world.blockExists(x, y, z))
						{
							if(currentScanningChunk.getBlockID(x, y, z) == Block.oreDiamond.blockID)
							{
								Random rand = new Random();
								int expandX = rand.nextInt(3)-1;
								int expandY = rand.nextInt(3)-1;
								int expandZ = rand.nextInt(3)-1;
								System.out.println(x*16 + " " + y + " " + z*16);
								if(currentScanningChunk.getBlockID(Math.abs(x + expandX), Math.abs(y + expandY), Math.abs(z + expandZ)) != 0)
								{
									currentScanningChunk.setBlockIDWithMetadata(Math.abs(x + expandX), Math.abs(y + expandY), Math.abs(z + expandZ), Block.oreDiamond.blockID, 0);
								}
							}
						}
					}
				}
			}
		}
	}
	/**
	 * Emerald ore expansion helper method
	 * @param world
	 * @param currentScanningChunk
	 * @param size
	 */
	public void expandEmerald(WorldClient world, Chunk currentScanningChunk, int size)
	{
		for(int i = 0; i < size; i++)
		{
			for(int x = 0; x < 15; x++)
			{
				for(int y = 0; y < 255; y++)
				{
					for(int z = 0; z < 15; z++)
					{
						if(world.blockExists(x, y, z))
						{
							if(currentScanningChunk.getBlockID(x, y, z) == Block.oreEmerald.blockID)
							{
								Random rand = new Random();
								int expandX = rand.nextInt(3) - 1;
								int expandY = rand.nextInt(3)-1;
								int expandZ = rand.nextInt(3)-1;
								System.out.println(x*16 + " " + y + " " + z*16);
								if(currentScanningChunk.getBlockID(Math.abs(x + expandX), Math.abs(y + expandY), Math.abs(z + expandZ)) != 0)
								{
									currentScanningChunk.setBlockIDWithMetadata(Math.abs(x + expandX), Math.abs(y + expandY), Math.abs(z + expandZ), Block.oreEmerald.blockID, 0);
								}
							}
						}
					}
				}
			}
		}
	}
	/**
	 * Gold ore expansion helper method
	 * @param world
	 * @param currentScanningChunk
	 * @param size
	 */
	public void expandGold(WorldClient world, Chunk currentScanningChunk, int size)
	{
		for(int i = 0; i < size; i++)
		{
			for(int x = 0; x < 15; x++)
			{
				for(int y = 0; y < 255; y++)
				{
					for(int z = 0; z < 15; z++)
					{
						if(world.blockExists(x, y, z))
						{
							if(currentScanningChunk.getBlockID(x, y, z) == Block.oreGold.blockID)
							{
								Random rand = new Random();
								int expandX = rand.nextInt(3)-1;
								int expandY = rand.nextInt(3)-1;
								int expandZ = rand.nextInt(3)-1;
								System.out.println(x*16 + " " + y + " " + z*16);
								if(currentScanningChunk.getBlockID(Math.abs(x + expandX), Math.abs(y + expandY), Math.abs(z + expandZ)) != 0)
								{
									currentScanningChunk.setBlockIDWithMetadata(Math.abs(x + expandX), Math.abs(y + expandY), Math.abs(z + expandZ), Block.oreGold.blockID, 0);
								}
							}
						}
					}
				}
			}
		}
	}
	/**
	 * Iron ore expansion helper method
	 * @param world
	 * @param currentScanningChunk
	 * @param size
	 */
	public void expandIron(WorldClient world, Chunk currentScanningChunk, int size)
	{
		for(int i = 0; i < size; i++)
		{
			for(int x = 0; x < 15; x++)
			{
				for(int y = 0; y < 255; y++)
				{
					for(int z = 0; z < 15; z++)
					{
						if(world.blockExists(x, y, z))
						{
							if(currentScanningChunk.getBlockID(x, y, z) == Block.oreIron.blockID)
							{
								Random rand = new Random();
								int expandX = rand.nextInt(3)-1;
								int expandY = rand.nextInt(3)-1;
								int expandZ = rand.nextInt(3)-1;
								System.out.println(x*16 + " " + y + " " + z*16);
								if(currentScanningChunk.getBlockID(Math.abs(x + expandX), Math.abs(y + expandY), Math.abs(z + expandZ)) != 0)
								{
									currentScanningChunk.setBlockIDWithMetadata(Math.abs(x + expandX), Math.abs(y + expandY), Math.abs(z + expandZ), Block.oreIron.blockID, 0);
								}
							}

						}
					}
				}
			}
		}
	}
	/**
	 * Lapis ore expansion helper method
	 * @param world
	 * @param currentScanningChunk
	 * @param size
	 */
	public void expandLapis(WorldClient world, Chunk currentScanningChunk, int size)
	{
		for(int i = 0; i < size; i++)
		{
			for(int x = 0; x < 15; x++)
			{
				for(int y = 0; y < 255; y++)
				{
					for(int z = 0; z < 15; z++)
					{
						if(world.blockExists(x, y, z))
						{
							if(currentScanningChunk.getBlockID(x, y, z) == Block.oreLapis.blockID)
							{
								Random rand = new Random();
								int expandX = rand.nextInt(3)-1;
								int expandY = rand.nextInt(3)-1;
								int expandZ = rand.nextInt(3)-1;
								System.out.println(x*16 + " " + y + " " + z*16);
								if(currentScanningChunk.getBlockID(Math.abs(x + expandX), Math.abs(y + expandY), Math.abs(z + expandZ)) != 0)
								{
									currentScanningChunk.setBlockIDWithMetadata(Math.abs(x + expandX), Math.abs(y + expandY), Math.abs(z + expandZ), Block.oreLapis.blockID, 0);
								}
							}

						}
					}
				}
			}
		}
	}
	/**
	 * Redstone ore expansion helper method
	 * @param world
	 * @param currentScanningChunk
	 * @param size
	 */
	public void expandRedstone(WorldClient world, Chunk currentScanningChunk, int size)
	{
		for(int i = 0; i < size; i++)
		{
			for(int x = 0; x < 15; x++)
			{
				for(int y = 0; y < 255; y++)
				{
					for(int z = 0; z < 15; z++)
					{
						if(world.blockExists(x, y, z))
						{
							if(currentScanningChunk.getBlockID(x, y, z) == Block.oreRedstone.blockID)
							{
								Random rand = new Random();
								int expandX = rand.nextInt(3)-1;
								int expandY = rand.nextInt(3)-1;
								int expandZ = rand.nextInt(3)-1;
								System.out.println(x*16 + " " + y + " " + z*16);
								if(currentScanningChunk.getBlockID(Math.abs(x + expandX), Math.abs(y + expandY), Math.abs(z + expandZ)) != 0)
								{
									currentScanningChunk.setBlockIDWithMetadata(Math.abs(x + expandX), Math.abs(y + expandY), Math.abs(z + expandZ), Block.oreRedstone.blockID, 0);
								}
							}
							if(currentScanningChunk.getBlockID(x, y, z) == Block.oreRedstoneGlowing.blockID)
							{
								Random rand = new Random();
								int expandX = rand.nextInt(3)-1;
								int expandY = rand.nextInt(3)-1;
								int expandZ = rand.nextInt(3)-1;
								System.out.println(x*16 + " " + y + " " + z*16);
								if(currentScanningChunk.getBlockID(Math.abs(x + expandX), Math.abs(y + expandY), Math.abs(z + expandZ)) != 0)
								{
									currentScanningChunk.setBlockIDWithMetadata(Math.abs(x + expandX), Math.abs(y + expandY), Math.abs(z + expandZ), Block.oreRedstone.blockID, 0);
								}
							}
						}
					}
				}
			}
		}
	}
	/**
	 * Forest expansion helper method
	 * @param world
	 * @param currentScanningChunk
	 * @param size
	 */
	public void expandForest(WorldClient world, Chunk currentScanningChunk, int size)
	{
		for(int i = 0; i < size; i++)
		{
			for(int x = 0; x < 15; x++)
			{
				for(int y = 0; y < 250; y++)
				{
					for(int z = 0; z < 15; z++)
					{
						if(world.blockExists(x, y, z))
						{
							if(currentScanningChunk.getBlockID(x, y, z) == Block.leaves.blockID)
							{
								Random rand = new Random();
								int expandX = rand.nextInt(5) - 5;
								int expandY = rand.nextInt(5) - 5;
								int expandZ = rand.nextInt(5) - 5;
								System.out.println(expandY);
								if(currentScanningChunk.getBlockID(Math.abs(x + expandX), Math.abs(y + expandY), Math.abs(z + expandZ)) == Block.grass.blockID)
								{
									//if(currentScanningChunk.canBlockSeeTheSky(Math.abs(x + expandX), Math.abs(y + expandY), Math.abs(z + expandZ)))
									//{
										currentScanningChunk.setBlockIDWithMetadata(Math.abs(x + expandX), Math.abs(y + expandY + 1), Math.abs(z + expandZ), Block.sapling.blockID, 0);
										//BlockSapling bs = (BlockSapling) Block.sapling;
										//bs.growTree(world, Math.abs(x + expandX), Math.abs(y + expandY + 1), Math.abs(z + expandZ), rand);
										//}
								}

							}
						}
					}
				}
			}
		}
	}
}