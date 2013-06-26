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
public class BottledParadox extends Item
{
	private int containedParadox = 0;
	NBTTagCompound tag;

	public BottledParadox (int id)
	{
		super(id);
		this.setCreativeTab(CreativeTabs.tabMisc);
		
	}
    public void registerIcons(IconRegister par1IconRegister)
    {
     this.itemIcon = par1IconRegister.registerIcon(TimeTraveler.modid + ":" + this.getUnlocalizedName());
    }
    @Override
    public void addInformation(ItemStack is, EntityPlayer player, List l, boolean B)
    {
    	if(is.getTagCompound() != null)
    	{
        	l.add("Amount of Paradox Stored: " + is.getTagCompound().getInteger("paradoxLevel"));
    	}
    	else
    	{
    		l.add("Amount of Paradox Stored: 0");
    	}
    }

    public int getContainedParadox()
    {
    	return containedParadox;
    }
    
    
}
