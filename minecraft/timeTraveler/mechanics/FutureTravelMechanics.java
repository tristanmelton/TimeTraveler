package timeTraveler.mechanics;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.chunk.Chunk;
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
		Chunk currentScanningChunk = world.getChunkFromBlockCoords((int)ep.posX, (int) ep.posZ);
		expandRedstone(world, currentScanningChunk, redstone);
		expandDiamond(world, currentScanningChunk, diamond);
		expandCoal(world, currentScanningChunk, coal);
		expandEmerald(world, currentScanningChunk, emerald);
		expandGold(world, currentScanningChunk, gold);
		expandIron(world, currentScanningChunk, iron);
		expandLapis(world, currentScanningChunk, lapis);
	}
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
								int expandX = rand.nextInt(2);
								int expandY = rand.nextInt(2);
								int expandZ = rand.nextInt(2);
								System.out.println(x*16 + " " + y + " " + z*16);
								currentScanningChunk.setBlockIDWithMetadata(x + expandX, y + expandY, z + expandZ, Block.oreCoal.blockID, 0);
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
								int expandX = rand.nextInt(2);
								int expandY = rand.nextInt(2);
								int expandZ = rand.nextInt(2);
								System.out.println(x*16 + " " + y + " " + z*16);
								currentScanningChunk.setBlockIDWithMetadata(x + expandX, y + expandY, z + expandZ, Block.oreDiamond.blockID, 0);
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
								int expandX = rand.nextInt(2);
								int expandY = rand.nextInt(2);
								int expandZ = rand.nextInt(2);
								System.out.println(x*16 + " " + y + " " + z*16);
								currentScanningChunk.setBlockIDWithMetadata(x + expandX, y + expandY, z + expandZ, Block.oreEmerald.blockID, 0);
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
								int expandX = rand.nextInt(2);
								int expandY = rand.nextInt(2);
								int expandZ = rand.nextInt(2);
								System.out.println(x*16 + " " + y + " " + z*16);
								currentScanningChunk.setBlockIDWithMetadata(x + expandX, y + expandY, z + expandZ, Block.oreGold.blockID, 0);
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
								int expandX = rand.nextInt(2);
								int expandY = rand.nextInt(2);
								int expandZ = rand.nextInt(2);
								System.out.println(x*16 + " " + y + " " + z*16);
								currentScanningChunk.setBlockIDWithMetadata(x + expandX, y + expandY, z + expandZ, Block.oreIron.blockID, 0);
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
								int expandX = rand.nextInt(2);
								int expandY = rand.nextInt(2);
								int expandZ = rand.nextInt(2);
								System.out.println(x*16 + " " + y + " " + z*16);
								currentScanningChunk.setBlockIDWithMetadata(x + expandX, y + expandY, z + expandZ, Block.oreLapis.blockID, 0);
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
								int expandX = rand.nextInt(2);
								int expandY = rand.nextInt(2);
								int expandZ = rand.nextInt(2);
								System.out.println(x*16 + " " + y + " " + z*16);
								currentScanningChunk.setBlockIDWithMetadata(x + expandX, y + expandY, z + expandZ, Block.oreRedstone.blockID, 0);
							}
							if(currentScanningChunk.getBlockID(x, y, z) == Block.oreRedstoneGlowing.blockID)
							{
								Random rand = new Random();
								int expandX = rand.nextInt(2);
								int expandY = rand.nextInt(2);
								int expandZ = rand.nextInt(2);
								System.out.println(x*16 + " " + y + " " + z*16);
								currentScanningChunk.setBlockIDWithMetadata(x + expandX, y + expandY, z + expandZ, Block.oreRedstone.blockID, 0);
							}

						}
					}
				}
			}
		}
	}

}