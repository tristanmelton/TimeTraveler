package timeTraveler.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
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
	
	public ItemParadoximer () {
		super();
		setUnlocalizedName("ItemParadoximer");
		this.setCreativeTab(TimeTraveler.tabTT);
	}
	/**
	 * Opens the future travel GUI
	 * @return 
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
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
		return par1ItemStack;
    }
	@Override
    public void registerIcons(IIconRegister par1IconRegister)
 {
     this.itemIcon = par1IconRegister.registerIcon(TimeTraveler.modid + ":" + this.getUnlocalizedName());
 }

}
