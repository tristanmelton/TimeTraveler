package timeTraveler.mechanics;

import net.minecraft.block.Block;
import net.minecraftforge.event.Event;

public class BlockPlaceEvent extends Event
{
	public Block block;
	public int x, y, z;
	
	public BlockPlaceEvent(Block par0Block, int par1, int par2, int par3)
	{
		this.block = par0Block;
		this.x = par1;
		this.y = par2;
		this.z = par3;
	}
	
	
	
}
