package timeTraveler.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import timeTraveler.core.TimeTraveler;
import timeTraveler.gui.GuiFutureReturn;
import timeTraveler.gui.GuiFutureTravel;
import timeTraveler.gui.GuiTimeTravel;
import cpw.mods.fml.client.FMLClientHandler;

/**
 * Paradoximer item information
 * @author Charsmud
 *
 */
public class ItemParadoximer extends Item {
	//
	
	public ItemParadoximer (int id) {
		super(id);
		this.setCreativeTab(CreativeTabs.tabMisc);
	}
	/**
	 * Opens the future travel GUI
	 */
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
		//System.out.println(TimeTraveler.vars.pathData.allEntityData);
		if(!TimeTraveler.vars.getIsInFuture())
		{
	    	GuiTimeTravel gtt = new GuiTimeTravel();
	    	GuiFutureTravel gft = new GuiFutureTravel(gtt, "");
	    	Minecraft mc = FMLClientHandler.instance().getClient();
	    	mc.displayGuiScreen(gft);
	    	
		}
		else
		{
			GuiFutureReturn gfr = new GuiFutureReturn();
			Minecraft mc = Minecraft.getMinecraft();
			mc.displayGuiScreen(gfr);
			
		}

    	return true;
    }
    public void registerIcons(IconRegister par1IconRegister)
 {
     this.itemIcon = par1IconRegister.registerIcon(TimeTraveler.modid + ":" + this.getUnlocalizedName());
 }

}
