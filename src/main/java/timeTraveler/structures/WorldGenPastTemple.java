package timeTraveler.structures;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import timeTraveler.core.TimeTraveler;
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
		if(world.getBlock(i, j, k) != Blocks.grass || world.getBlock(i + 10, j, k) != Blocks.grass || world.getBlock(i + 10, j, k + 10) != Blocks.grass || world.getBlock( i, j, k + 10) != Blocks.grass)
		{
			return false;
		}
        TileEntityChest tileentitychest = new TileEntityChest();
        tileentitychest.setInventorySlotContents(0, new ItemStack(TimeTraveler.paradoximer, 1, 1));
		world.setBlock(i + 0, j + 0, k + 0, Blocks.grass);
		world.setBlock(i + 0, j + 0, k + 1, Blocks.grass);
		world.setBlock(i + 0, j + 0, k + 2, Blocks.grass);
		world.setBlock(i + 0, j + 0, k + 3, Blocks.grass);
		world.setBlock(i + 0, j + 0, k + 4, Blocks.grass);
		world.setBlock(i + 0, j + 0, k + 5, Blocks.grass);
		world.setBlock(i + 0, j + 0, k + 6, Blocks.grass);
		world.setBlock(i + 0, j + 0, k + 7, Blocks.grass);
		world.setBlock(i + 0, j + 0, k + 8, Blocks.grass);
		world.setBlock(i + 1, j + 0, k + 0, Blocks.grass);
		world.setBlock(i + 1, j + 0, k + 1, Blocks.grass);
		world.setBlock(i + 1, j + 0, k + 2, Blocks.grass);
		world.setBlock(i + 1, j + 0, k + 3, Blocks.dirt);
		world.setBlock(i + 1, j + 0, k + 4, Blocks.dirt);
		world.setBlock(i + 1, j + 0, k + 5, Blocks.dirt);
		world.setBlock(i + 1, j + 0, k + 6, Blocks.grass);
		world.setBlock(i + 1, j + 0, k + 7, Blocks.grass);
		world.setBlock(i + 1, j + 0, k + 8, Blocks.grass);
		world.setBlock(i + 1, j + 1, k + 3, Blocks.mossy_cobblestone);
		world.setBlock(i + 1, j + 1, k + 4, Blocks.stonebrick, 1, 1);
		world.setBlock(i + 1, j + 1, k + 5, Blocks.mossy_cobblestone);
		world.setBlock(i + 1, j + 2, k + 4, Blocks.stonebrick);
		world.setBlock(i + 1, j + 3, k + 4, Blocks.stonebrick, 2, 1);
		world.setBlock(i + 1, j + 4, k + 4, Blocks.stonebrick, 1, 1);
		world.setBlock(i + 1, j + 5, k + 4, Blocks.stonebrick);
		world.setBlock(i + 1, j + 6, k + 4, Blocks.glowstone);
		world.setBlock(i + 2, j + 0, k + 0, Blocks.grass);
		world.setBlock(i + 2, j + 0, k + 1, Blocks.grass);
		world.setBlock(i + 2, j + 0, k + 2, Blocks.grass);
		world.setBlock(i + 2, j + 0, k + 3, Blocks.dirt);
		world.setBlock(i + 2, j + 0, k + 4, Blocks.dirt);
		world.setBlock(i + 2, j + 0, k + 5, Blocks.dirt);
		world.setBlock(i + 2, j + 0, k + 6, Blocks.grass);
		world.setBlock(i + 2, j + 0, k + 7, Blocks.grass);
		world.setBlock(i + 2, j + 0, k + 8, Blocks.grass);
		world.setBlock(i + 2, j + 1, k + 3, Blocks.stone_brick_stairs, 2, 1);
		world.setBlock(i + 2, j + 1, k + 4, Blocks.stonebrick, 1, 1);
		world.setBlock(i + 2, j + 1, k + 5, Blocks.stone_brick_stairs, 3, 1);
		world.setBlock(i + 2, j + 4, k + 4, Blocks.stone_brick_stairs, 5, 1);
		world.setBlock(i + 2, j + 5, k + 4, Blocks.stonebrick);
		world.setBlock(i + 2, j + 6, k + 4, Blocks.stone_brick_stairs, 1, 1);
		world.setBlock(i + 3, j + 0, k + 0, Blocks.grass);
		world.setBlock(i + 3, j + 0, k + 1, Blocks.dirt);
		world.setBlock(i + 3, j + 0, k + 2, Blocks.grass);
		world.setBlock(i + 3, j + 0, k + 3, Blocks.dirt);
		world.setBlock(i + 3, j + 0, k + 4, Blocks.dirt);
		world.setBlock(i + 3, j + 0, k + 5, Blocks.dirt);
		world.setBlock(i + 3, j + 0, k + 6, Blocks.dirt);
		world.setBlock(i + 3, j + 0, k + 7, Blocks.dirt);
		world.setBlock(i + 3, j + 0, k + 8, Blocks.grass);
		world.setBlock(i + 3, j + 1, k + 1, Blocks.mossy_cobblestone);
		world.setBlock(i + 3, j + 1, k + 2, Blocks.stone_brick_stairs);
		world.setBlock(i + 3, j + 1, k + 3, Blocks.redstone_ore);
		world.setBlock(i + 3, j + 1, k + 4, Blocks.stonebrick, 2, 1);
		world.setBlock(i + 3, j + 1, k + 5, Blocks.redstone_ore);
		world.setBlock(i + 3, j + 1, k + 6, Blocks.stone_brick_stairs);
		world.setBlock(i + 3, j + 1, k + 7, Blocks.mossy_cobblestone);
		world.setBlock(i + 3, j + 2, k + 4, Blocks.stone_brick_stairs);
		world.setBlock(i + 3, j + 4, k + 4, Blocks.stone_brick_stairs, 4, 1);
		world.setBlock(i + 3, j + 5, k + 4, Blocks.lapis_block);
		world.setBlock(i + 3, j + 6, k + 4, Blocks.stone_brick_stairs);
		world.setBlock(i + 4, j + 0, k + 0, Blocks.grass);
		world.setBlock(i + 4, j + 0, k + 1, Blocks.dirt);
		world.setBlock(i + 4, j + 0, k + 2, Blocks.dirt);
		world.setBlock(i + 4, j + 0, k + 3, Blocks.dirt);
		world.setBlock(i + 4, j + 0, k + 4, Blocks.grass);
		world.setBlock(i + 4, j + 0, k + 5, Blocks.dirt);
		world.setBlock(i + 4, j + 0, k + 6, Blocks.dirt);
		world.setBlock(i + 4, j + 0, k + 7, Blocks.dirt);
		world.setBlock(i + 4, j + 0, k + 8, Blocks.grass);
		world.setBlock(i + 4, j + 1, k + 1, Blocks.stonebrick, 1, 1);
		world.setBlock(i + 4, j + 1, k + 2, Blocks.stonebrick, 2, 1);
		world.setBlock(i + 4, j + 1, k + 3, Blocks.monster_egg, 2, 1);
		world.setBlock(i + 4, j + 1, k + 4, Blocks.glowstone);
		world.setBlock(i + 4, j + 1, k + 5, Blocks.stonebrick, 1, 1);
		world.setBlock(i + 4, j + 1, k + 6, Blocks.monster_egg, 2, 1);
		world.setBlock(i + 4, j + 1, k + 7, Blocks.stonebrick);
		world.setBlock(i + 4, j + 2, k + 1, Blocks.stonebrick, 2, 1);
		world.setBlock(i + 4, j + 2, k + 3, Blocks.stone_brick_stairs, 2, 1);
		world.setBlock(i + 4, j + 2, k + 4, TimeTraveler.travelTime);
		world.setBlock(i + 4, j + 2, k + 5, Blocks.stone_brick_stairs, 3, 1);
		world.setBlock(i + 4, j + 2, k + 7, Blocks.stonebrick);
		world.setBlock(i + 4, j + 3, k + 1, Blocks.stonebrick);
		world.setBlock(i + 4, j + 3, k + 7, Blocks.stonebrick, 1, 1);
		world.setBlock(i + 4, j + 4, k + 1, Blocks.stonebrick, 1, 1);
		world.setBlock(i + 4, j + 4, k + 2, Blocks.stone_brick_stairs, 7, 1);
		world.setBlock(i + 4, j + 4, k + 3, Blocks.stone_brick_stairs, 6, 1);
		world.setBlock(i + 4, j + 4, k + 5, Blocks.stone_brick_stairs, 7, 1);
		world.setBlock(i + 4, j + 4, k + 6, Blocks.stone_brick_stairs, 6, 1);
		world.setBlock(i + 4, j + 4, k + 7, Blocks.stonebrick);
		world.setBlock(i + 4, j + 5, k + 1, Blocks.stonebrick);
		world.setBlock(i + 4, j + 5, k + 2, Blocks.stonebrick, 1, 1);
		world.setBlock(i + 4, j + 5, k + 3, Blocks.lapis_block);
		world.setBlock(i + 4, j + 5, k + 4, Blocks.obsidian);
		world.setBlock(i + 4, j + 5, k + 5, Blocks.lapis_block);
		world.setBlock(i + 4, j + 5, k + 6, Blocks.stonebrick, 2, 1);
		world.setBlock(i + 4, j + 5, k + 7, Blocks.stonebrick, 1, 1);
		world.setBlock(i + 4, j + 6, k + 1, Blocks.glowstone);
		world.setBlock(i + 4, j + 6, k + 2, Blocks.stone_brick_stairs, 3, 1);
		world.setBlock(i + 4, j + 6, k + 3, Blocks.stone_brick_stairs, 2, 1);
		world.setBlock(i + 4, j + 6, k + 4, Blocks.chest, 4, 1);
		world.setTileEntity(i + 4, j + 6, k + 4, tileentitychest);
		world.setBlock(i + 4, j + 6, k + 5, Blocks.stone_brick_stairs, 3, 1);
		world.setBlock(i + 4, j + 6, k + 6, Blocks.stone_brick_stairs, 2, 1);
		world.setBlock(i + 4, j + 6, k + 7, Blocks.glowstone);
		world.setBlock(i + 5, j + 0, k + 0, Blocks.grass);
		world.setBlock(i + 5, j + 0, k + 1, Blocks.dirt);
		world.setBlock(i + 5, j + 0, k + 2, Blocks.dirt);
		world.setBlock(i + 5, j + 0, k + 3, Blocks.dirt);
		world.setBlock(i + 5, j + 0, k + 4, Blocks.dirt);
		world.setBlock(i + 5, j + 0, k + 5, Blocks.dirt);
		world.setBlock(i + 5, j + 0, k + 6, Blocks.dirt);
		world.setBlock(i + 5, j + 0, k + 7, Blocks.dirt);
		world.setBlock(i + 5, j + 0, k + 8, Blocks.grass);
		world.setBlock(i + 5, j + 1, k + 1, Blocks.mossy_cobblestone);
		world.setBlock(i + 5, j + 1, k + 2, Blocks.stone_brick_stairs, 1, 1);
		world.setBlock(i + 5, j + 1, k + 3, Blocks.redstone_ore);
		world.setBlock(i + 5, j + 1, k + 4, Blocks.stonebrick, 1, 1);
		world.setBlock(i + 5, j + 1, k + 5, Blocks.redstone_ore);
		world.setBlock(i + 5, j + 1, k + 6, Blocks.stone_brick_stairs, 1, 1);
		world.setBlock(i + 5, j + 1, k + 7, Blocks.mossy_cobblestone);
		world.setBlock(i + 5, j + 2, k + 4, Blocks.stone_brick_stairs, 1, 1);
		world.setBlock(i + 5, j + 4, k + 4, Blocks.stone_brick_stairs, 5, 1);
		world.setBlock(i + 5, j + 5, k + 4, Blocks.lapis_block);
		world.setBlock(i + 5, j + 6, k + 4, Blocks.stone_brick_stairs, 1, 1);
		world.setBlock(i + 6, j + 0, k + 0, Blocks.grass);
		world.setBlock(i + 6, j + 0, k + 1, Blocks.grass);
		world.setBlock(i + 6, j + 0, k + 2, Blocks.grass);
		world.setBlock(i + 6, j + 0, k + 3, Blocks.dirt);
		world.setBlock(i + 6, j + 0, k + 4, Blocks.dirt);
		world.setBlock(i + 6, j + 0, k + 5, Blocks.dirt);
		world.setBlock(i + 6, j + 0, k + 6, Blocks.grass);
		world.setBlock(i + 6, j + 0, k + 7, Blocks.grass);
		world.setBlock(i + 6, j + 0, k + 8, Blocks.grass);
		world.setBlock(i + 6, j + 1, k + 3, Blocks.stone_brick_stairs, 2, 1);
		world.setBlock(i + 6, j + 1, k + 4, Blocks.monster_egg, 2, 1);
		world.setBlock(i + 6, j + 1, k + 5, Blocks.stone_brick_stairs, 3, 1);
		world.setBlock(i + 6, j + 4, k + 4, Blocks.stone_brick_stairs, 4, 1);
		world.setBlock(i + 6, j + 5, k + 4, Blocks.stonebrick, 2, 1);
		world.setBlock(i + 6, j + 6, k + 4, Blocks.stone_brick_stairs);
		world.setBlock(i + 7, j + 0, k + 0, Blocks.grass);
		world.setBlock(i + 7, j + 0, k + 1, Blocks.grass);
		world.setBlock(i + 7, j + 0, k + 2, Blocks.grass);
		world.setBlock(i + 7, j + 0, k + 3, Blocks.dirt);
		world.setBlock(i + 7, j + 0, k + 4, Blocks.dirt);
		world.setBlock(i + 7, j + 0, k + 5, Blocks.dirt);
		world.setBlock(i + 7, j + 0, k + 6, Blocks.grass);
		world.setBlock(i + 7, j + 0, k + 7, Blocks.grass);
		world.setBlock(i + 7, j + 0, k + 8, Blocks.grass);
		world.setBlock(i + 7, j + 1, k + 3, Blocks.mossy_cobblestone);
		world.setBlock(i + 7, j + 1, k + 4, Blocks.stonebrick, 2, 1);
		world.setBlock(i + 7, j + 1, k + 5, Blocks.mossy_cobblestone);
		world.setBlock(i + 7, j + 2, k + 4, Blocks.stonebrick, 1, 1);
		world.setBlock(i + 7, j + 3, k + 4, Blocks.stonebrick, 2, 1);
		world.setBlock(i + 7, j + 4, k + 4, Blocks.stonebrick);
		world.setBlock(i + 7, j + 5, k + 4, Blocks.stonebrick, 1, 1);
		world.setBlock(i + 7, j + 6, k + 4, Blocks.glowstone);
		world.setBlock(i + 8, j + 0, k + 0, Blocks.grass);
		world.setBlock(i + 8, j + 0, k + 1, Blocks.grass);
		world.setBlock(i + 8, j + 0, k + 2, Blocks.grass);
		world.setBlock(i + 8, j + 0, k + 3, Blocks.dirt);
		world.setBlock(i + 8, j + 0, k + 4, Blocks.dirt);
		world.setBlock(i + 8, j + 0, k + 5, Blocks.grass);
		world.setBlock(i + 8, j + 0, k + 6, Blocks.grass);
		world.setBlock(i + 8, j + 0, k + 7, Blocks.grass);
		world.setBlock(i + 8, j + 0, k + 8, Blocks.grass);

		return true;
	}
}