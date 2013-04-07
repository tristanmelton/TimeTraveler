package timeTraveler;

import net.minecraft.block.Block;
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
	
	public FutureTravelMechanics()
	{
		ep = FMLClientHandler.instance().getClient().thePlayer;
	}
	public void expandOres(WorldClient world)
	{
		System.out.println("CHECK");
		Chunk currentScanningChunk = world.getChunkFromChunkCoords((int)ep.posX / 16, (int) ep.posZ / 16);
		System.out.println("CHECK1");
		for(int x = 0; x <= 16; x++)
		{
			for(int y = 0; y <= 256; y++)
			{
				for(int z = 0; z <= 16; z++)
				{
					if(world.blockExists(x, y, z))
					{
						if(currentScanningChunk.getBlockID(x, y, z) == Block.bedrock.blockID)
						{
							FMLClientHandler.instance().getClient().thePlayer.sendChatToPlayer("BEDROCK");
						}
						if(currentScanningChunk.getBlockID(x, y, z) == Block.oreCoal.blockID)
						{
							FMLClientHandler.instance().getClient().thePlayer.sendChatToPlayer("COAL");
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