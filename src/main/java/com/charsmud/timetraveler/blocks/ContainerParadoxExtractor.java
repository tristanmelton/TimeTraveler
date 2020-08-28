package com.charsmud.timetraveler.blocks;

import com.charsmud.timetraveler.blocks.slots.SlotParadoxExtractorOutput;
import com.charsmud.timetraveler.tileentities.TileEntityParadoxExtractor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerParadoxExtractor extends Container 
{
	private final TileEntityParadoxExtractor tile_entity;
	private int cookTime, totalCookTime, burnTime, currentBurnTime; 
	
	public ContainerParadoxExtractor(InventoryPlayer player, TileEntityParadoxExtractor tile_entity)
	{
		this.tile_entity = tile_entity;
		this.addSlotToContainer(new Slot(tile_entity, 0, 37, 14));
		this.addSlotToContainer(new SlotParadoxExtractorOutput(player.player, tile_entity, 1, 92, 33));
		
		for(int y = 0; y < 3; y++)
		{
			for(int x = 0; x < 9; x++)
			{
				this.addSlotToContainer(new Slot(player, x + y*9 + 9, 8 + x*18, 84 + y*18));
			}
		}
		for(int x = 0; x < 9; x++)
		{
			this.addSlotToContainer(new Slot(player, x, 8 + x*18, 142));
		}
	}
	@Override
	public void addListener(IContainerListener listener)
	{
		super.addListener(listener);
		listener.sendAllWindowProperties(this, tile_entity);
	}
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.listeners.size(); i++)
		{
			IContainerListener listener = (IContainerListener)this.listeners.get(i);
			
			if(this.cookTime != this.tile_entity.getField(2))
				listener.sendWindowProperty(this,  2, this.tile_entity.getField(2));
			if(this.burnTime != this.tile_entity.getField(0))
				listener.sendWindowProperty(this, 0, this.tile_entity.getField(0));
			if(this.currentBurnTime != this.tile_entity.getField(1))
				listener.sendWindowProperty(this, 1, this.tile_entity.getField(1));
			if(this.totalCookTime != this.tile_entity.getField(3))
				listener.sendWindowProperty(this, 3, this.tile_entity.getField(3));
		}
		this.cookTime = this.tile_entity.getField(2);
		this.burnTime = this.tile_entity.getField(0);
		this.currentBurnTime = this.tile_entity.getField(1);
		this.totalCookTime = this.tile_entity.getField(3);
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data)
	{
		this.tile_entity.setField(id, data);
	}
	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return this.tile_entity.isUsableByPlayer(playerIn);
	}
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	{
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = (Slot)this.inventorySlots.get(index);
		if(slot != null && slot.getHasStack())
		{
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			
			if(index == 2)
			{
				if(!this.mergeItemStack(stack1, 4, 40, true))
					return ItemStack.EMPTY;
				slot.onSlotChange(stack1, stack);
			}
			else if(index != 1 && index != 0)
			{
				if(!ParadoxExtractorRecipes.getInstance().getExtractingResult(stack1).isEmpty())
				{
					if(!this.mergeItemStack(stack1, 0, 1, false))
						return ItemStack.EMPTY;
					else if(TileEntityParadoxExtractor.isItemFuel(stack1))
					{
						if(!this.mergeItemStack(stack1, 1, 2, false))
							return ItemStack.EMPTY;
					}
					else if(index >= 4 && index < 31)
					{
						if(!this.mergeItemStack(stack1, 31, 40, false))
							return ItemStack.EMPTY;
					}
					else if(index >= 31 && index < 4 && !this.mergeItemStack(stack1, 4, 31, false))
						return ItemStack.EMPTY;
				}
			}
			else if(!this.mergeItemStack(stack1, 4, 40, false))
				return ItemStack.EMPTY;
			if(stack1.isEmpty())
				slot.putStack(ItemStack.EMPTY);
			else
				slot.onSlotChanged();
			if(stack1.getCount() == stack.getCount())
				return ItemStack.EMPTY;
			slot.onTake(playerIn, stack1);
		}
		return stack;
	}
}
