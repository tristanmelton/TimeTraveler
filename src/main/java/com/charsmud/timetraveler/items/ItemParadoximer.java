package com.charsmud.timetraveler.items;

import com.charsmud.timetraveler.TimeTraveler;
import com.charsmud.timetraveler.TimeTraveler.PlayerTemporalLocation;
import com.charsmud.timetraveler.gui.GuiFutureReturn;
import com.charsmud.timetraveler.gui.GuiFutureTravel;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemParadoximer extends ItemBase {

	public ItemParadoximer(String name) 
	{
		super(name);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn)
	{
		if(!worldIn.isRemote)
		{
			System.out.println("Open GUI");
			if(TimeTraveler.TEMPORAL_LOCATION == PlayerTemporalLocation.PRESENT)
				Minecraft.getMinecraft().displayGuiScreen(new GuiFutureTravel(player));
			else if(TimeTraveler.TEMPORAL_LOCATION == PlayerTemporalLocation.FUTURE)
				Minecraft.getMinecraft().displayGuiScreen(new GuiFutureReturn(player));
			else
				System.out.println("Can't use in the past!");
			//player.openGui(TimeTraveler.instance, Reference.GUI_FUTURE_TRAVEL, worldIn, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ());
		}
		return super.onItemRightClick(worldIn, player, handIn);
	}

}
