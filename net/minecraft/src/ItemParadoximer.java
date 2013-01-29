package net.minecraft.src;

import java.io.File;

import net.minecraft.client.Minecraft;

public class ItemParadoximer extends Item {
	//
	
	public ItemParadoximer (int i) {
		super(i);
		maxStackSize = 1;
		this.iconIndex = ModLoader.addOverride("/gui/items.png", "/TimeMod/Items/paradoximer.png");
	}
	/**
	 * Opens the future travel GUI
	 */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	GuiTimeTravel gtt = new GuiTimeTravel();
    	GuiFutureTravel gft = new GuiFutureTravel(gtt, "");
    	ModLoader.openGUI(par3EntityPlayer, gft);
    	
    	EntityPlayerPast p = new EntityPlayerPast(par2World);

    	p.setLocationAndAngles(par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, 0, 0);

    	par2World.spawnEntityInWorld(p);

    	
    	return par1ItemStack;
    }
}
