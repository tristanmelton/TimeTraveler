package timeTraveler.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import timeTraveler.core.TimeTraveler;

public class TimeFluidBucket extends ItemBucket
{

	public Block contains;

	public TimeFluidBucket( Block contains)
	{
		super(contains);
		setUnlocalizedName("timeFluidBucket");
		this.contains = contains;
		MinecraftForge.EVENT_BUS.register(this);
		this.setCreativeTab(TimeTraveler.tabTT);
	}

	public ItemStack fillCustomBucket(World world, MovingObjectPosition pos)
	{
		Block blockID = world.getBlock(pos.blockX, pos.blockY, pos.blockZ);

		if ((blockID == contains)
				&& world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ) == 0) {
			world.setBlockToAir(pos.blockX, pos.blockY, pos.blockZ);
			return new ItemStack(this);
		} else
			return null;
	}
   public void registerIcons(IIconRegister par1IconRegister)
 {
     this.itemIcon = par1IconRegister.registerIcon(TimeTraveler.modid + ":" + this.getUnlocalizedName());
 }

}