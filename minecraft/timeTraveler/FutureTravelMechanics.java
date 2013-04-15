package timeTraveler;

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
	public FutureTravelMechanics()
	{
		ep = FMLClientHandler.instance().getClient().thePlayer;
		world = FMLClientHandler.instance().getClient().theWorld;
	}
	public void expandOres(WorldClient world)
	{
		System.out.println("CHECK");
		Chunk currentScanningChunk = world.getChunkFromChunkCoords((int)ep.posX / 16, (int) ep.posZ / 16);
		System.out.println("CHECK1");
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
							FMLClientHandler.instance().getClient().thePlayer.sendChatToPlayer(Integer.toString(currentScanningChunk.getBlockID(x + 1, y, z)));
							
							currentScanningChunk.setBlockIDWithMetadata(x + 1, y + 1, z, Block.oreCoal.blockID, 0);
						}
						if(currentScanningChunk.getBlockID(x, y, z) == Block.oreDiamond.blockID)
						{
							FMLClientHandler.instance().getClient().thePlayer.sendChatToPlayer("DIAMOND");
						}
						if(currentScanningChunk.getBlockID(x, y, z) == Block.oreEmerald.blockID)
						{
							FMLClientHandler.instance().getClient().thePlayer.sendChatToPlayer("EMERALD");
						}
						if(currentScanningChunk.getBlockID(x, y, z) == Block.oreGold.blockID)
						{
							FMLClientHandler.instance().getClient().thePlayer.sendChatToPlayer("GOLD");
						}
						if(currentScanningChunk.getBlockID(x, y, z) == Block.oreIron.blockID)
						{
							FMLClientHandler.instance().getClient().thePlayer.sendChatToPlayer("IRON");
						}
						if(currentScanningChunk.getBlockID(x, y, z) == Block.oreLapis.blockID)
						{
							FMLClientHandler.instance().getClient().thePlayer.sendChatToPlayer("LAPIS");
						}
						if(currentScanningChunk.getBlockID(x, y, z) == Block.oreRedstone.blockID)
						{
							FMLClientHandler.instance().getClient().thePlayer.sendChatToPlayer("REDSTONE");
						}




					}
				}
			}
		}
	}
}