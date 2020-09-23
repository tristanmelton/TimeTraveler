package com.charsmud.timetraveler.gui;

import java.io.File;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.fml.client.GuiScrollingList;

class GuiTimeSlot extends GuiScrollingList
{
	private GuiPastTravel parentWorldGui;
	private ArrayList<String> timeList;
	
	public GuiTimeSlot(GuiPastTravel par1, ArrayList<String> timeList)
	{
		super(par1.mc, 200, par1.height, 32, par1.height - 64, 36, 36);
		this.timeList = timeList;
		//super(par1.mc, par1.width, par1.height, 32, par1.height - 64, 36);
		this.parentWorldGui = par1;
	}

	/**
	 * Gets the size of the current slot list.
	 */
	@Override
	protected int getSize()
	{
		return timeList.size();
	}

	/**
	 * the element in the slot that was clicked, boolean for whether it was double
	 * clicked or not
	 */
	@Override
	protected void elementClicked(int var1, boolean var2) 
	{
		parentWorldGui.selectTime(var1);
		parentWorldGui.setSelectedEnabled(true);
		if (var2)
		{
			// OPTIONAL_TODO: Add in what I want to do when timezone is double clicked
		}
	}

	/**
	 * returns true if the element passed in is currently selected
	 */
	@Override
	protected boolean isSelected(int par1)
	{
		return par1 == parentWorldGui.getSelectedWorld();
	}

	/**
	 * return the height of the content being scrolled
	 */
	@Override
	protected int getContentHeight() 
	{
		return this.getSize() * 36;
	}

	@Override
	protected void drawBackground()
	{
		this.parentWorldGui.drawDefaultBackground();
	}

	@Override
	protected void drawSlot(int slotIdx, int entryRight, int slotTop, int slotBuffer, Tessellator tess/*int slotIndex, int xPos, int yPos, int heightIn, int mouseXIn, int mouseYIn, float partialTicks*/) 
	{
		
		String var6 = timeList.get(slotIdx).toString();
		String var7 = var6;

		if (var7 == null || var7.length() == 0) 
		{
			var7 = parentWorldGui.getLocalizedWorldName(this.parentWorldGui) + " " + (slotIdx + 1);
		}

		String var8 = var6;
		// var8 = var8 + " (" +
		// GuiTimeTravel.getDateFormatter(this.parentWorldGui).format(new
		// Date(var6.getLastTimePlayed()));
		// var8 = var8 + ")";

		this.parentWorldGui.drawString(this.parentWorldGui.mc.fontRenderer, var7, this.left + 3, slotTop + 2, 0xFF2222);
		this.parentWorldGui.drawString(this.parentWorldGui.mc.fontRenderer, var8, this.left + 3, slotTop + 12, 0xFF2222);
	}
}
