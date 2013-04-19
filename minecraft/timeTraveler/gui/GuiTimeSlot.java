package timeTraveler.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.src.ModLoader;
import net.minecraft.util.MathHelper;

class GuiTimeSlot extends GuiSlot
{
    final GuiTimeTravel parentWorldGui;
    Minecraft minecraft = ModLoader.getMinecraftInstance();
    
    public GuiTimeSlot(GuiTimeTravel par1)
    {
        super(par1.mc, par1.width, par1.height, 32, par1.height - 64, 36);
        this.parentWorldGui = par1;
    }

    /**
     * Gets the size of the current slot list.
     */
    protected int getSize()
    {
        return GuiTimeTravel.getSize(this.parentWorldGui).size();
    }

    /**
     * the element in the slot that was clicked, boolean for wether it was double clicked or not
     */
    protected void elementClicked(int par1, boolean par2)
    {
        GuiTimeTravel.onElementSelected(this.parentWorldGui, par1);
        boolean var3 = GuiTimeTravel.getSelectedWorld(this.parentWorldGui) >= 0 && GuiTimeTravel.getSelectedWorld(this.parentWorldGui) < this.getSize();
        GuiTimeTravel.getSelectButton(this.parentWorldGui).enabled = var3;
        System.out.println(par2 + "  " + var3);
        if (par2 && var3)
        {
        	//Put what I want to do when double click item selected.  TODO: Add in what I want to do when timezone is double clicked
        }
    }

    /**
     * returns true if the element passed in is currently selected
     */
    protected boolean isSelected(int par1)
    {
        return par1 == GuiTimeTravel.getSelectedWorld(this.parentWorldGui);
    }

    /**
     * return the height of the content being scrolled
     */
    protected int getContentHeight()
    {
        return GuiTimeTravel.getSize(this.parentWorldGui).size() * 36;
    }

    protected void drawBackground()
    {
        this.parentWorldGui.drawDefaultBackground();
    }

    protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator)
    {
        String var6 = GuiTimeTravel.getSize(this.parentWorldGui).get(par1).toString();
        String var7 = var6;

        if (var7 == null || MathHelper.stringNullOrLengthZero(var7))
        {
            var7 = GuiTimeTravel.getLocalizedWorldName(this.parentWorldGui) + " " + (par1 + 1);
        }

        String var8 = var6;
       // var8 = var8 + " (" + GuiTimeTravel.getDateFormatter(this.parentWorldGui).format(new Date(var6.getLastTimePlayed()));
       // var8 = var8 + ")";
        String var9 = "";

       /* if (var6.requiresConversion())
        {
            var9 = GuiTimeTravel.getLocalizedMustConvert(this.parentWorldGui) + " " + var9;
        }
        else
        {
            var9 = GuiTimeTravel.getLocalizedGameMode(this.parentWorldGui)[var6.func_75790_f().getID()];

            if (var6.isHardcoreModeEnabled())
            {
                var9 = "\u00a74" + StatCollector.translateToLocal("gameMode.hardcore") + "\u00a7r";
            }

            if (var6.func_75783_h())
            {
                var9 = var9 + ", " + StatCollector.translateToLocal("selectWorld.cheats");
            }
        }*/
        this.parentWorldGui.drawString(minecraft.fontRenderer, var7, par2 + 2, par3 + 1, 16777215);
        this.parentWorldGui.drawString(minecraft.fontRenderer, var8, par2 + 2, par3 + 12, 8421504);
        this.parentWorldGui.drawString(minecraft.fontRenderer, var9, par2 + 2, par3 + 12 + 10, 8421504);
    }
}
