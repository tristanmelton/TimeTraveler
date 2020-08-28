package com.charsmud.timetraveler.gui;

import com.charsmud.timetraveler.blocks.ContainerParadoxCondenser;
import com.charsmud.timetraveler.tileentities.TileEntityParadoxCondenser;
import com.charsmud.timetraveler.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiParadoxCondenser extends GuiContainer
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/paradox_condenser.png");
	private final InventoryPlayer player;
	private final TileEntityParadoxCondenser tile_entity;
	
	public GuiParadoxCondenser(InventoryPlayer player, TileEntityParadoxCondenser tile_entity)
	{
		super(new ContainerParadoxCondenser(player, tile_entity));
		this.player = player;
		this.tile_entity = tile_entity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{

		String tileName = this.tile_entity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize/2 - this.fontRenderer.getStringWidth(tileName)/2) + 3, 8, 4210752);
        this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
	}
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		if(TileEntityParadoxCondenser.isBurning(tile_entity))
		{
			int k = this.getBurnLeftScaled(13); //Height
			this.drawTexturedModalRect(this.guiLeft + 8, this.guiTop + 54 + 12 - k, 176, 12-k, 14, k+1);
		}
		int l = this.getCookProgressScaled(24);
		this.drawTexturedModalRect(this.guiLeft + 57, this.guiTop + 34, 176, 0, l+1, 16);
	}
	private int getBurnLeftScaled(int pixels)
	{
		int i = this.tile_entity.getField(1);
		if(i == 0)
			i = 200;
		return this.tile_entity.getField(0) * pixels / i;
	}
	private int getCookProgressScaled(int pixels)
	{
		int i = this.tile_entity.getField(2);
		int j = this.tile_entity.getField(3);
		return j != 0 && i != 0 ? i * pixels / j : 0;
	}
}
