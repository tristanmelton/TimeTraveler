package net.minecraft.src;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class GuiBACKUPSlot extends GuiSlot
{
    final GuiSelectBACKUP parentWorldGui;

    public GuiBACKUPSlot(GuiSelectBACKUP guiselectbackup)
    {
        super(guiselectbackup.mc, guiselectbackup.width, guiselectbackup.height, 32, guiselectbackup.height - 64, 36);
        parentWorldGui = guiselectbackup;
    }

    /**
     * Gets the size of the current slot list.
     */
    protected int getSize()
    {
        return GuiSelectBACKUP.getSize(parentWorldGui).size();
    }

    /**
     * the element in the slot that was clicked, boolean for wether it was double clicked or not
     */
    protected void elementClicked(int i, boolean flag)
    {
        GuiSelectBACKUP.onElementSelected(parentWorldGui, i);
        boolean flag1 = GuiSelectBACKUP.getSelectedWorld(parentWorldGui) >= 0 && GuiSelectBACKUP.getSelectedWorld(parentWorldGui) < getSize();
        GuiSelectBACKUP.getSelectButton(parentWorldGui).enabled = flag1;
        if (flag && flag1)
        {
            parentWorldGui.selectWorld(i);
        }
    }

    /**
     * returns true if the element passed in is currently selected
     */
    protected boolean isSelected(int i)
    {
        return i == GuiSelectBACKUP.getSelectedWorld(parentWorldGui);
    }

    /**
     * return the height of the content being scrolled
     */
    protected int getContentHeight()
    {
        return GuiSelectBACKUP.getSize(parentWorldGui).size() * 36;
    }

    protected void drawBackground()
    {
        parentWorldGui.drawDefaultBackground();
    }

    protected void drawSlot(int i, int j, int k, int l, Tessellator tessellator)
    {
        SaveFormatComparator saveformatcomparator = (SaveFormatComparator)GuiSelectBACKUP.getSize(parentWorldGui).get(i);
        String s = saveformatcomparator.getDisplayName();

        if (s == null || MathHelper.stringNullOrLengthZero(s))
        {
            s = (new StringBuilder()).append(GuiSelectBACKUP.getLocalizedWorldName(parentWorldGui)).append(" ").append(i + 1).toString();
        }

        String s1 = saveformatcomparator.getFileName();
        s1 = (new StringBuilder()).append(s1).append(" (").append(GuiSelectBACKUP.getDateFormatter(parentWorldGui).format(new Date(saveformatcomparator.getLastTimePlayed()))).toString();
        s1 = (new StringBuilder()).append(s1).append(")").toString();
        String s2 = "";

        if (saveformatcomparator.requiresConversion())
        {
            s2 = (new StringBuilder()).append(GuiSelectBACKUP.getLocalizedMustConvert(parentWorldGui)).append(" ").append(s2).toString();
        }
        else
        {
            s2 = GuiSelectBACKUP.getLocalizedGameMode(parentWorldGui)[saveformatcomparator.getGameType()];
        }

        if (saveformatcomparator.isHardcoreModeEnabled())
        {
            s2 = (new StringBuilder()).append("\2474").append(StatCollector.translateToLocal("gameMode.hardcore")).append("\2478").toString();
        }

        parentWorldGui.drawString(parentWorldGui.fontRenderer, s, j + 2, k + 1, 0xffffff);
        parentWorldGui.drawString(parentWorldGui.fontRenderer, s1, j + 2, k + 12, 0x808080);
        parentWorldGui.drawString(parentWorldGui.fontRenderer, s2, j + 2, k + 12 + 10, 0x808080);
    }
}
