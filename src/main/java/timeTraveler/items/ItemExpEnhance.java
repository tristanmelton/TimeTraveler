package timeTraveler.items;

import net.minecraft.client.renderer.texture.IIconRegister;
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

	public ItemExpEnhance()
	{
		super();
		setUnlocalizedName("ExpEnhancer");
		this.setCreativeTab(TimeTraveler.tabTT);
		
	}

    public void registerIcons(IIconRegister par1IconRegister)
    {
    	this.itemIcon = par1IconRegister.registerIcon(TimeTraveler.modid + ":" + this.getUnlocalizedName());
    }
}
