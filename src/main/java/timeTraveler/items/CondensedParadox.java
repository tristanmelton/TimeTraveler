package timeTraveler.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import timeTraveler.core.TimeTraveler;

/**
 * BottledParadox item
 * @author Charsmud
 *
 */
public class CondensedParadox extends Item
{

	public CondensedParadox ()
	{
		super();
		setUnlocalizedName("CondensedParadox");
		this.setCreativeTab(TimeTraveler.tabTT);
		
	}

    public void registerIcons(IIconRegister par1IconRegister)
    {
     this.itemIcon = par1IconRegister.registerIcon(TimeTraveler.modid + ":" + this.getUnlocalizedName());
    }
}
