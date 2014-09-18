package timeTraveler.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import timeTraveler.core.TimeTraveler;

/**
 * EXP Enhance item
 * @author Charsmud
 *
 */
public class ItemExpEnhance extends Item
{

	public ItemExpEnhance (int id)
	{
		super(id);
		this.setCreativeTab(CreativeTabs.tabMisc);
		
	}

    public void registerIcons(IconRegister par1IconRegister)
    {
    	this.itemIcon = par1IconRegister.registerIcon(TimeTraveler.modid + ":" + this.getUnlocalizedName());
    }
}
