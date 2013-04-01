package timeTraveler;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;

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
    	GuiTimeTravel gtt = new GuiTimeTravel();
    	GuiFutureTravel gft = new GuiFutureTravel(gtt, "");
    	ModLoader.openGUI(par2EntityPlayer, gft);
    	EntityPlayerPast p = new EntityPlayerPast(par3World);

    	p.setLocationAndAngles(par2EntityPlayer.posX, par2EntityPlayer.posY, par2EntityPlayer.posZ, 0, 0);

    	par3World.spawnEntityInWorld(p);

    	
    	return true;
    }
	public String getTextureFile()
	{
		return ClientProxy.TIME_TRAVELER_ITEM_TEX;
	}
}
