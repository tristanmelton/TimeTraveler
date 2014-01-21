package timeTraveler.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import timeTraveler.core.TimeTraveler;

/**
 * BottledParadox item
 * @author Charsmud
 *
 */
public class CondensedParadox extends Item
{

	public CondensedParadox (int id)
	{
		super(id);
		this.setCreativeTab(CreativeTabs.tabMisc);
		
	}

    public void registerIcons(IconRegister par1IconRegister)
    {
     this.itemIcon = par1IconRegister.registerIcon(TimeTraveler.modid + ":" + this.getUnlocalizedName());
    }
}