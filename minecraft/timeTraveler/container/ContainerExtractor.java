package timeTraveler.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import timeTraveler.crafting.ExtractingRecipes;
import timeTraveler.gui.SlotParadox;
import timeTraveler.tileentity.TileEntityExtractor;
import timeTraveler.tileentity.TileEntityParadoxCondenser;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerExtractor extends Container
{
    private TileEntityExtractor paradox;
    private int lastCookTime = 0;
    private int lastFormTime = 0;
    private int lastBurnTime = 0;
    private int lastItemBurnTime = 0;

    public ContainerExtractor(InventoryPlayer par1InventoryPlayer, TileEntityExtractor par2TileEntityExtractor)
    {
        this.paradox = par2TileEntityExtractor;
        this.addSlotToContainer(new Slot(par2TileEntityExtractor, 1, 34, 33));
        this.addSlotToContainer(new SlotParadox(par1InventoryPlayer.player, par2TileEntityExtractor, 2, 91, 33));
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.paradox.paradoxCookTime);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.paradox.paradoxBurnTime);
        par1ICrafting.sendProgressBarUpdate(this, 2, this.paradox.currentItemBurnTime);
        par1ICrafting.sendProgressBarUpdate(this, 3, this.paradox.paradoxFormTime);

    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCookTime != this.paradox.paradoxCookTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.paradox.paradoxCookTime);
            }

            if (this.lastBurnTime != this.paradox.paradoxBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.paradox.paradoxBurnTime);
            }

            if (this.lastItemBurnTime != this.paradox.currentItemBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.paradox.currentItemBurnTime);
            }
            if (this.lastFormTime != this.paradox.paradoxFormTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.paradox.paradoxFormTime);
            }

        }

        this.lastCookTime = this.paradox.paradoxCookTime;
        this.lastBurnTime = this.paradox.paradoxBurnTime;
        this.lastFormTime = this.paradox.paradoxFormTime;
        this.lastItemBurnTime = this.paradox.currentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.paradox.paradoxCookTime = par2;
        }

        if (par1 == 1)
        {
            this.paradox.paradoxBurnTime = par2;
        }

        if (par1 == 2)
        {
            this.paradox.currentItemBurnTime = par2;
        }
        if(par1 == 3)
        {
        	this.paradox.paradoxFormTime = par2;
        }
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.paradox.isUseableByPlayer(par1EntityPlayer);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (par2 != 1 && par2 != 0)
            {
                if (ExtractingRecipes.extracting().getExtractingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityExtractor.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 3 && par2 < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }
}
