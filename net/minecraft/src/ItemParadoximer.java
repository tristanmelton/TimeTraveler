package net.minecraft.src;

import java.io.File;

import net.minecraft.client.Minecraft;

public class ItemParadoximer extends Item {
	
	
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
    	return par1ItemStack;
    }
}
