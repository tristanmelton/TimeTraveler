package com.charsmud.timetraveler.blocks.slots;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotParadoxExtractorOutput extends Slot
{
    /** The player that is using the GUI where this slot resides. */
    private EntityPlayer player;
    private int removeCount;

    public SlotParadoxExtractorOutput(EntityPlayer player, IInventory inventory, int index, int x, int y)
    {
        super(inventory, index, x, y);
        this.player = player;
    }

    /**
     * Check if the stack is a valid item for this slot.
     */
    @Override
    public boolean isItemValid(ItemStack par1ItemStack)
    {
        return false;
    }

    /**
     * Decrease the size of the stack in slot
     */
    @Override
    public ItemStack decrStackSize(int amount)
    {
        if (this.getHasStack())
            this.removeCount += Math.min(amount, this.getStack().getCount());
        return super.decrStackSize(amount);
    }
    @Override
    public ItemStack onTake(EntityPlayer player, ItemStack stack)
    {
        this.onCrafting(stack);
        super.onTake(player, stack);
        return stack;
    }

    /*
    protected void onCrafting(ItemStack par1ItemStack, int par2)
    {
        this.removeCount += par2;
        this.onCrafting(par1ItemStack);
    }

    protected void onCrafting(ItemStack par1ItemStack)
    {
        par1ItemStack.onCrafting(this.player.worldObj, this.player, this.removeCount);

        if (!this.player.worldObj.isRemote)
        {
            int i = this.removeCount;
            float f = ParadoxRecipes.condensing().getExperience(par1ItemStack);
            int j;

            if (f == 0.0F)
            {
                i = 0;
            }
            else if (f < 1.0F)
            {
                j = MathHelper.floor_float((float)i * f);

                if (j < MathHelper.ceiling_float_int((float)i * f) && (float)Math.random() < (float)i * f - (float)j)
                {
                    ++j;
                }

                i = j;
            }

            while (i > 0)
            {
                //j = EntityXPOrb.getXPSplit(i);
                //i -= j;
               // this.thePlayer.worldObj.spawnEntityInWorld(new EntityXPOrb(this.thePlayer.worldObj, this.thePlayer.posX, this.thePlayer.posY + 0.5D, this.thePlayer.posZ + 0.5D, j));
            }
        }

        this.removeCount = 0;
    }*/
}
