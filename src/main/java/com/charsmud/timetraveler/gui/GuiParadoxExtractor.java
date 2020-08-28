package com.charsmud.timetraveler.gui;

import com.charsmud.timetraveler.blocks.ContainerParadoxExtractor;
import com.charsmud.timetraveler.tileentities.TileEntityParadoxExtractor;
import com.charsmud.timetraveler.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;



@SideOnly(Side.CLIENT)
public class GuiParadoxExtractor extends GuiContainer
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/paradox_extractor.png");
	private final InventoryPlayer player;
    private TileEntityParadoxExtractor tile_entity;

    public GuiParadoxExtractor(InventoryPlayer inventory, TileEntityParadoxExtractor tile_entity)
    {
        super(new ContainerParadoxExtractor(inventory, tile_entity));
        this.tile_entity = tile_entity;
        this.player = inventory;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
		String tileName = this.tile_entity.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(tileName, this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURES);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;
        if (this.tile_entity.isBurning())
        {
            i1 = this.getBurnLeftScaled(12);
            //this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }

        i1 = this.getFormProgressScaled(24);
        this.drawTexturedModalRect(k + 59, l + 32, 176, 0, i1, 17);
    }
	private int getBurnLeftScaled(int pixels)
	{
		int i = this.tile_entity.getField(1);
		if(i == 0)
			i = 200;
		return this.tile_entity.getField(0) * pixels / i;
	}
	private int getFormProgressScaled(int pixels)
	{
		int i = this.tile_entity.getField(2);
		int j = this.tile_entity.getField(3);
		return j != 0 && i != 0 ? i * pixels / j : 0;
	}
}
