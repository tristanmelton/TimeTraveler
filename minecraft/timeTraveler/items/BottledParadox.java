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
	/**
	 * Opens the future travel GUI
	 */
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {    	
		containedParadox++;
		tag = par1ItemStack.getTagCompound();
		if (tag == null)
		{
			tag = new NBTTagCompound();
			par1ItemStack.setTagCompound(tag);
		}
		tag.setInteger("paradoxLevel", containedParadox);

		
    	return true;
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
