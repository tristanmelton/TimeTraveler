package com.charsmud.timetraveler.blocks.slots;

import com.charsmud.timetraveler.tileentities.TileEntityParadoxCondenser;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotParadoxCondenserFuel extends Slot 
{
	public SlotParadoxCondenserFuel(IInventory inventory, int index, int x, int y)
	{
		super(inventory,  index, x, y);
	}
	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return TileEntityParadoxCondenser.isItemFuel(stack);
	}
	@Override
	public int getItemStackLimit(ItemStack stack)
	{
		return super.getItemStackLimit(stack);
	}
}
