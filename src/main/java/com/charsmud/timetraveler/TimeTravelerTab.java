package com.charsmud.timetraveler;

import com.charsmud.timetraveler.blocks.BlockInit;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TimeTravelerTab extends CreativeTabs
{
	public TimeTravelerTab(String label)
	{
		super(label);
		//this.setBackgroundImageName("timetraveler.png"); //Call tab_timetraveler.png
	}
	
	@Override
	public ItemStack getTabIconItem()
	{
		return new ItemStack(Item.getItemFromBlock(BlockInit.TIME_TRAVELER));
	}
}
