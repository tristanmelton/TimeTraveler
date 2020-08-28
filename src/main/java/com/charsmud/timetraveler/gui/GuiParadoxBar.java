package com.charsmud.timetraveler.gui;

import com.charsmud.timetraveler.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class GuiParadoxBar extends Gui
{
	private static final ResourceLocation TEXTURE_BASE = new ResourceLocation(Reference.MODID + ":textures/gui/paradox_bar.png");

	public GuiParadoxBar(Minecraft mc, int paradox)
	{
	    ScaledResolution scaled = new ScaledResolution(mc);
	    
	    int width = scaled.getScaledWidth();
	    int height = scaled.getScaledHeight();
	    int pos = height / height + 25;
	    	    

		mc.renderEngine.bindTexture(TEXTURE_BASE);
	    this.drawTexturedModalRect(width / 2 - 200, pos, 0, 0, 128, 8);
		this.drawTexturedModalRect(width / 2 - 200, pos, 0, 8, paradox, 8);

	}

}
