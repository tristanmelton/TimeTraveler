package timeTraveler;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
/**
 * Past Temple information
 * @author Charsmud
 *
 */
public class WorldGenPastTemple extends WorldGenerator
{
	public WorldGenPastTemple() { }
/**
 * Contains information about the structure 
 */
	public boolean generate(World world, Random rand, int i, int j, int k) {
		if(world.getBlockId(i, j, k) != Block.grass.blockID || world.getBlockId(i + 10, j, k) != Block.grass.blockID || world.getBlockId(i + 10, j, k + 10) != Block.grass.blockID || world.getBlockId( i, j, k + 10) != Block.grass.blockID)
		{
			return false;
		}
		System.out.println("GENERATING STRUCUTRE");
        TileEntityChest tileentitychest = new TileEntityChest();
        tileentitychest.setInventorySlotContents(0, new ItemStack(mod_Time.paradoximer.itemID, 1, 1));
		world.setBlock(i + 0, j + 0, k + 0, Block.grass.blockID);
		world.setBlock(i + 0, j + 0, k + 1, Block.grass.blockID);
		world.setBlock(i + 0, j + 0, k + 2, Block.grass.blockID);
		world.setBlock(i + 0, j + 0, k + 3, Block.grass.blockID);
		world.setBlock(i + 0, j + 0, k + 4, Block.grass.blockID);
		world.setBlock(i + 0, j + 0, k + 5, Block.grass.blockID);
		world.setBlock(i + 0, j + 0, k + 6, Block.grass.blockID);
		world.setBlock(i + 0, j + 0, k + 7, Block.grass.blockID);
		world.setBlock(i + 0, j + 0, k + 8, Block.grass.blockID);
		world.setBlock(i + 1, j + 0, k + 0, Block.grass.blockID);
		world.setBlock(i + 1, j + 0, k + 1, Block.grass.blockID);
		world.setBlock(i + 1, j + 0, k + 2, Block.grass.blockID);
		world.setBlock(i + 1, j + 0, k + 3, Block.dirt.blockID);
		world.setBlock(i + 1, j + 0, k + 4, Block.dirt.blockID);
		world.setBlock(i + 1, j + 0, k + 5, Block.dirt.blockID);
		world.setBlock(i + 1, j + 0, k + 6, Block.grass.blockID);
		world.setBlock(i + 1, j + 0, k + 7, Block.grass.blockID);
		world.setBlock(i + 1, j + 0, k + 8, Block.grass.blockID);
		world.setBlock(i + 1, j + 1, k + 3, Block.cobblestoneMossy.blockID);
		world.setBlockAndMetadata(i + 1, j + 1, k + 4, Block.stoneBrick.blockID, 1);
		world.setBlock(i + 1, j + 1, k + 5, Block.cobblestoneMossy.blockID);
		world.setBlock(i + 1, j + 2, k + 4, Block.stoneBrick.blockID);
		world.setBlockAndMetadata(i + 1, j + 3, k + 4, Block.stoneBrick.blockID, 2);
		world.setBlockAndMetadata(i + 1, j + 4, k + 4, Block.stoneBrick.blockID, 1);
		world.setBlock(i + 1, j + 5, k + 4, Block.stoneBrick.blockID);
		world.setBlock(i + 1, j + 6, k + 4, Block.glowStone.blockID);
		world.setBlock(i + 2, j + 0, k + 0, Block.grass.blockID);
		world.setBlock(i + 2, j + 0, k + 1, Block.grass.blockID);
		world.setBlock(i + 2, j + 0, k + 2, Block.grass.blockID);
		world.setBlock(i + 2, j + 0, k + 3, Block.dirt.blockID);
		world.setBlock(i + 2, j + 0, k + 4, Block.dirt.blockID);
		world.setBlock(i + 2, j + 0, k + 5, Block.dirt.blockID);
		world.setBlock(i + 2, j + 0, k + 6, Block.grass.blockID);
		world.setBlock(i + 2, j + 0, k + 7, Block.grass.blockID);
		world.setBlock(i + 2, j + 0, k + 8, Block.grass.blockID);
		world.setBlockAndMetadata(i + 2, j + 1, k + 3, Block.stairsStoneBrickSmooth.blockID, 2);
		world.setBlockAndMetadata(i + 2, j + 1, k + 4, Block.stoneBrick.blockID, 1);
		world.setBlockAndMetadata(i + 2, j + 1, k + 5, Block.stairsStoneBrickSmooth.blockID, 3);
		world.setBlockAndMetadata(i + 2, j + 4, k + 4, Block.stairsStoneBrickSmooth.blockID, 5);
		world.setBlock(i + 2, j + 5, k + 4, Block.stoneBrick.blockID);
		world.setBlockAndMetadata(i + 2, j + 6, k + 4, Block.stairsStoneBrickSmooth.blockID, 1);
		world.setBlock(i + 3, j + 0, k + 0, Block.grass.blockID);
		world.setBlock(i + 3, j + 0, k + 1, Block.dirt.blockID);
		world.setBlock(i + 3, j + 0, k + 2, Block.grass.blockID);
		world.setBlock(i + 3, j + 0, k + 3, Block.dirt.blockID);
		world.setBlock(i + 3, j + 0, k + 4, Block.dirt.blockID);
		world.setBlock(i + 3, j + 0, k + 5, Block.dirt.blockID);
		world.setBlock(i + 3, j + 0, k + 6, Block.dirt.blockID);
		world.setBlock(i + 3, j + 0, k + 7, Block.dirt.blockID);
		world.setBlock(i + 3, j + 0, k + 8, Block.grass.blockID);
		world.setBlock(i + 3, j + 1, k + 1, Block.cobblestoneMossy.blockID);
		world.setBlock(i + 3, j + 1, k + 2, Block.stairsStoneBrickSmooth.blockID);
		world.setBlock(i + 3, j + 1, k + 3, Block.oreRedstoneGlowing.blockID);
		world.setBlockAndMetadata(i + 3, j + 1, k + 4, Block.stoneBrick.blockID, 2);
		world.setBlock(i + 3, j + 1, k + 5, Block.oreRedstone.blockID);
		world.setBlock(i + 3, j + 1, k + 6, Block.stairsStoneBrickSmooth.blockID);
		world.setBlock(i + 3, j + 1, k + 7, Block.cobblestoneMossy.blockID);
		world.setBlock(i + 3, j + 2, k + 4, Block.stairsStoneBrickSmooth.blockID);
		world.setBlockAndMetadata(i + 3, j + 4, k + 4, Block.stairsStoneBrickSmooth.blockID, 4);
		world.setBlock(i + 3, j + 5, k + 4, Block.blockLapis.blockID);
		world.setBlock(i + 3, j + 6, k + 4, Block.stairsStoneBrickSmooth.blockID);
		world.setBlock(i + 4, j + 0, k + 0, Block.grass.blockID);
		world.setBlock(i + 4, j + 0, k + 1, Block.dirt.blockID);
		world.setBlock(i + 4, j + 0, k + 2, Block.dirt.blockID);
		world.setBlock(i + 4, j + 0, k + 3, Block.dirt.blockID);
		world.setBlock(i + 4, j + 0, k + 4, Block.grass.blockID);
		world.setBlock(i + 4, j + 0, k + 5, Block.dirt.blockID);
		world.setBlock(i + 4, j + 0, k + 6, Block.dirt.blockID);
		world.setBlock(i + 4, j + 0, k + 7, Block.dirt.blockID);
		world.setBlock(i + 4, j + 0, k + 8, Block.grass.blockID);
		world.setBlockAndMetadata(i + 4, j + 1, k + 1, Block.stoneBrick.blockID, 1);
		world.setBlockAndMetadata(i + 4, j + 1, k + 2, Block.stoneBrick.blockID, 2);
		world.setBlockAndMetadata(i + 4, j + 1, k + 3, Block.silverfish.blockID, 2);
		world.setBlock(i + 4, j + 1, k + 4, Block.glowStone.blockID);
		world.setBlockAndMetadata(i + 4, j + 1, k + 5, Block.stoneBrick.blockID, 1);
		world.setBlockAndMetadata(i + 4, j + 1, k + 6, Block.silverfish.blockID, 2);
		world.setBlock(i + 4, j + 1, k + 7, Block.stoneBrick.blockID);
		world.setBlockAndMetadata(i + 4, j + 2, k + 1, Block.stoneBrick.blockID, 2);
		world.setBlockAndMetadata(i + 4, j + 2, k + 3, Block.stairsStoneBrickSmooth.blockID, 2);
		world.setBlock(i + 4, j + 2, k + 4, mod_Time.travelTime.blockID);
		world.setBlockAndMetadata(i + 4, j + 2, k + 5, Block.stairsStoneBrickSmooth.blockID, 3);
		world.setBlock(i + 4, j + 2, k + 7, Block.stoneBrick.blockID);
		world.setBlock(i + 4, j + 3, k + 1, Block.stoneBrick.blockID);
		world.setBlockAndMetadata(i + 4, j + 3, k + 7, Block.stoneBrick.blockID, 1);
		world.setBlockAndMetadata(i + 4, j + 4, k + 1, Block.stoneBrick.blockID, 1);
		world.setBlockAndMetadata(i + 4, j + 4, k + 2, Block.stairsStoneBrickSmooth.blockID, 7);
		world.setBlockAndMetadata(i + 4, j + 4, k + 3, Block.stairsStoneBrickSmooth.blockID, 6);
		world.setBlockAndMetadata(i + 4, j + 4, k + 5, Block.stairsStoneBrickSmooth.blockID, 7);
		world.setBlockAndMetadata(i + 4, j + 4, k + 6, Block.stairsStoneBrickSmooth.blockID, 6);
		world.setBlock(i + 4, j + 4, k + 7, Block.stoneBrick.blockID);
		world.setBlock(i + 4, j + 5, k + 1, Block.stoneBrick.blockID);
		world.setBlockAndMetadata(i + 4, j + 5, k + 2, Block.stoneBrick.blockID, 1);
		world.setBlock(i + 4, j + 5, k + 3, Block.blockLapis.blockID);
		world.setBlock(i + 4, j + 5, k + 4, Block.obsidian.blockID);
		world.setBlock(i + 4, j + 5, k + 5, Block.blockLapis.blockID);
		world.setBlockAndMetadata(i + 4, j + 5, k + 6, Block.stoneBrick.blockID, 2);
		world.setBlockAndMetadata(i + 4, j + 5, k + 7, Block.stoneBrick.blockID, 1);
		world.setBlock(i + 4, j + 6, k + 1, Block.glowStone.blockID);
		world.setBlockAndMetadata(i + 4, j + 6, k + 2, Block.stairsStoneBrickSmooth.blockID, 3);
		world.setBlockAndMetadata(i + 4, j + 6, k + 3, Block.stairsStoneBrickSmooth.blockID, 2);
		world.setBlockAndMetadata(i + 4, j + 6, k + 4, Block.chest.blockID, 4);
		world.setBlockTileEntity(i + 4, j + 6, k + 4, tileentitychest);
		world.setBlockAndMetadata(i + 4, j + 6, k + 5, Block.stairsStoneBrickSmooth.blockID, 3);
		world.setBlockAndMetadata(i + 4, j + 6, k + 6, Block.stairsStoneBrickSmooth.blockID, 2);
		world.setBlock(i + 4, j + 6, k + 7, Block.glowStone.blockID);
		world.setBlock(i + 5, j + 0, k + 0, Block.grass.blockID);
		world.setBlock(i + 5, j + 0, k + 1, Block.dirt.blockID);
		world.setBlock(i + 5, j + 0, k + 2, Block.dirt.blockID);
		world.setBlock(i + 5, j + 0, k + 3, Block.dirt.blockID);
		world.setBlock(i + 5, j + 0, k + 4, Block.dirt.blockID);
		world.setBlock(i + 5, j + 0, k + 5, Block.dirt.blockID);
		world.setBlock(i + 5, j + 0, k + 6, Block.dirt.blockID);
		world.setBlock(i + 5, j + 0, k + 7, Block.dirt.blockID);
		world.setBlock(i + 5, j + 0, k + 8, Block.grass.blockID);
		world.setBlock(i + 5, j + 1, k + 1, Block.cobblestoneMossy.blockID);
		world.setBlockAndMetadata(i + 5, j + 1, k + 2, Block.stairsStoneBrickSmooth.blockID, 1);
		world.setBlock(i + 5, j + 1, k + 3, Block.oreRedstoneGlowing.blockID);
		world.setBlockAndMetadata(i + 5, j + 1, k + 4, Block.stoneBrick.blockID, 1);
		world.setBlock(i + 5, j + 1, k + 5, Block.oreRedstoneGlowing.blockID);
		world.setBlockAndMetadata(i + 5, j + 1, k + 6, Block.stairsStoneBrickSmooth.blockID, 1);
		world.setBlock(i + 5, j + 1, k + 7, Block.cobblestoneMossy.blockID);
		world.setBlockAndMetadata(i + 5, j + 2, k + 4, Block.stairsStoneBrickSmooth.blockID, 1);
		world.setBlockAndMetadata(i + 5, j + 4, k + 4, Block.stairsStoneBrickSmooth.blockID, 5);
		world.setBlock(i + 5, j + 5, k + 4, Block.blockLapis.blockID);
		world.setBlockAndMetadata(i + 5, j + 6, k + 4, Block.stairsStoneBrickSmooth.blockID, 1);
		world.setBlock(i + 6, j + 0, k + 0, Block.grass.blockID);
		world.setBlock(i + 6, j + 0, k + 1, Block.grass.blockID);
		world.setBlock(i + 6, j + 0, k + 2, Block.grass.blockID);
		world.setBlock(i + 6, j + 0, k + 3, Block.dirt.blockID);
		world.setBlock(i + 6, j + 0, k + 4, Block.dirt.blockID);
		world.setBlock(i + 6, j + 0, k + 5, Block.dirt.blockID);
		world.setBlock(i + 6, j + 0, k + 6, Block.grass.blockID);
		world.setBlock(i + 6, j + 0, k + 7, Block.grass.blockID);
		world.setBlock(i + 6, j + 0, k + 8, Block.grass.blockID);
		world.setBlockAndMetadata(i + 6, j + 1, k + 3, Block.stairsStoneBrickSmooth.blockID, 2);
		world.setBlockAndMetadata(i + 6, j + 1, k + 4, Block.silverfish.blockID, 2);
		world.setBlockAndMetadata(i + 6, j + 1, k + 5, Block.stairsStoneBrickSmooth.blockID, 3);
		world.setBlockAndMetadata(i + 6, j + 4, k + 4, Block.stairsStoneBrickSmooth.blockID, 4);
		world.setBlockAndMetadata(i + 6, j + 5, k + 4, Block.stoneBrick.blockID, 2);
		world.setBlock(i + 6, j + 6, k + 4, Block.stairsStoneBrickSmooth.blockID);
		world.setBlock(i + 7, j + 0, k + 0, Block.grass.blockID);
		world.setBlock(i + 7, j + 0, k + 1, Block.grass.blockID);
		world.setBlock(i + 7, j + 0, k + 2, Block.grass.blockID);
		world.setBlock(i + 7, j + 0, k + 3, Block.dirt.blockID);
		world.setBlock(i + 7, j + 0, k + 4, Block.dirt.blockID);
		world.setBlock(i + 7, j + 0, k + 5, Block.dirt.blockID);
		world.setBlock(i + 7, j + 0, k + 6, Block.grass.blockID);
		world.setBlock(i + 7, j + 0, k + 7, Block.grass.blockID);
		world.setBlock(i + 7, j + 0, k + 8, Block.grass.blockID);
		world.setBlock(i + 7, j + 1, k + 3, Block.cobblestoneMossy.blockID);
		world.setBlockAndMetadata(i + 7, j + 1, k + 4, Block.stoneBrick.blockID, 2);
		world.setBlock(i + 7, j + 1, k + 5, Block.cobblestoneMossy.blockID);
		world.setBlockAndMetadata(i + 7, j + 2, k + 4, Block.stoneBrick.blockID, 1);
		world.setBlockAndMetadata(i + 7, j + 3, k + 4, Block.stoneBrick.blockID, 2);
		world.setBlock(i + 7, j + 4, k + 4, Block.stoneBrick.blockID);
		world.setBlockAndMetadata(i + 7, j + 5, k + 4, Block.stoneBrick.blockID, 1);
		world.setBlock(i + 7, j + 6, k + 4, Block.glowStone.blockID);
		world.setBlock(i + 8, j + 0, k + 0, Block.grass.blockID);
		world.setBlock(i + 8, j + 0, k + 1, Block.grass.blockID);
		world.setBlock(i + 8, j + 0, k + 2, Block.grass.blockID);
		world.setBlock(i + 8, j + 0, k + 3, Block.dirt.blockID);
		world.setBlock(i + 8, j + 0, k + 4, Block.dirt.blockID);
		world.setBlock(i + 8, j + 0, k + 5, Block.grass.blockID);
		world.setBlock(i + 8, j + 0, k + 6, Block.grass.blockID);
		world.setBlock(i + 8, j + 0, k + 7, Block.grass.blockID);
		world.setBlock(i + 8, j + 0, k + 8, Block.grass.blockID);

		return true;
	}
}