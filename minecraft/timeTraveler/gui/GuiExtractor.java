package timeTraveler.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import timeTraveler.container.ContainerExtractor;
import timeTraveler.container.ContainerParadox;
import timeTraveler.tileentity.TileEntityExtractor;
import timeTraveler.tileentity.TileEntityParadoxCondenser;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiExtractor extends GuiContainer
{
    private TileEntityExtractor paradoxInventory;

    public GuiExtractor(InventoryPlayer par1InventoryPlayer, TileEntityExtractor par2TileEntityExtractor)
    {
        super(new ContainerExtractor(par1InventoryPlayer, par2TileEntityExtractor));
        this.paradoxInventory = par2TileEntityExtractor;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String s = this.paradoxInventory.isInvNameLocalized() ? this.paradoxInventory.getInvName() : StatCollector.translateToLocal(this.paradoxInventory.getInvName());
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
    	ResourceLocation texture = new ResourceLocation("charsmud_timetraveler", "textures/gui/paradoxextractor.png");

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(texture);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;
        if (this.paradoxInventory.isBurning())
        {
            i1 = this.paradoxInventory.getBurnTimeRemainingScaled(12);
            //this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }

        i1 = this.paradoxInventory.getCookProgressScaled(24);
        this.drawTexturedModalRect(k + 59, l + 32, 176, 0, i1, 17);
    }	
}
