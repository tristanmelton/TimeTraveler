package timeTraveler;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.block.Block;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.chunk.Chunk;

/**
 * Contains information about the future mechanics
 * @author Charsmud
 *
 */
public class FutureTravelMechanics
{
	public void expandOres(WorldClient world)
	{
		System.out.println("CHECK");
		Chunk currentScanningChunk = world.getChunkFromChunkCoords(0, 0);
		currentScanningChunk = world.getChunkFromBlockCoords(0, 0);
		System.out.println("CHECK1");
		for(int x = 0; x <= 16; x++)
		{
			for(int y = 0; y <= 128; y++)
			{
				for(int z = 0; z <= 16; z++)
				{
					System.out.println(currentScanningChunk.getBlockID(x, y, z));
				}
			}
		}
		if(currentScanningChunk.getBlockID(1, 1, 1) == Block.bedrock.blockID)
		{
			System.out.println("CHECK3");
			FMLClientHandler.instance().getClient().thePlayer.sendChatToPlayer("BEDROCK");
		}
	}
}