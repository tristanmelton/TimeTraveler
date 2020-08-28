package com.charsmud.timetraveler.items;


import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBottledParadox extends ItemBase
{
	public ItemBottledParadox(String name)
	{
		super(name);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);

		if(stack.hasTagCompound())
		{
			tooltip.add("Amount of Paradox Stored: " + stack.getTagCompound().getInteger("paradoxAmount"));
		}
	}
}
