package timeTraveler.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import timeTraveler.blocks.ParadoxExtractor;
import timeTraveler.core.TimeTraveler;
import timeTraveler.crafting.ExtractingRecipes;
import timeTraveler.gui.GuiExtractor;
import timeTraveler.ticker.Ticker;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityExtractor extends TileEntity implements ISidedInventory
{
    private static final int[] field_102010_d = new int[] {0};
    private static final int[] field_102011_e = new int[] {2, 1};
    private static final int[] field_102009_f = new int[] {1};

    /**
     * The ItemStacks that hold the items currently being used in the furnace
     */
    private ItemStack[] paradoxItemStacks = new ItemStack[3];

    /** The number of ticks that the furnace will keep burning */
    public int paradoxBurnTime = 0;

    /**
     * The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for
     */
    public int currentItemBurnTime = 0;

    /** The number of ticks that the current item has been cooking for */
    public int paradoxCookTime = 0;
    public int paradoxFormTime = 0;
    private String field_94130_e;

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory()
    {
        return this.paradoxItemStacks.length;
    }

    /**
     * Returns the stack in slot i
     */
    @Override
    public ItemStack getStackInSlot(int par1)
    {
        return this.paradoxItemStacks[par1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    @Override
    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.paradoxItemStacks[par1] != null)
        {
            ItemStack itemstack;

            if (this.paradoxItemStacks[par1].stackSize <= par2)
            {
                itemstack = this.paradoxItemStacks[par1];
                this.paradoxItemStacks[par1] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.paradoxItemStacks[par1].splitStack(par2);

                if (this.paradoxItemStacks[par1].stackSize == 0)
                {
                    this.paradoxItemStacks[par1] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.paradoxItemStacks[par1] != null)
        {
            ItemStack itemstack = this.paradoxItemStacks[par1];
            this.paradoxItemStacks[par1] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    @Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.paradoxItemStacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
     * Returns the name of the inventory.
     */
    @Override
    public String getInventoryName()
    {
        return this.hasCustomInventoryName() ? this.field_94130_e : "Paradox Extractor";
    }

    /**
     * If this returns false, the inventory name will be used as an unlocalized name, and translated into the player's
     * language. Otherwise it will be used directly.
     */
    @Override
    public boolean hasCustomInventoryName()
    {
        return this.field_94130_e != null && this.field_94130_e.length() > 0;
    }

    /**
     * Reads a tile entity from NBT.
     */
    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", 0);
        this.paradoxItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.paradoxItemStacks.length)
            {
                this.paradoxItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.paradoxBurnTime = par1NBTTagCompound.getShort("BurnTime");
        this.paradoxCookTime = par1NBTTagCompound.getShort("CookTime");
        this.paradoxFormTime = par1NBTTagCompound.getShort("FormTime");
        this.currentItemBurnTime = getItemBurnTime(this.paradoxItemStacks[1]);

        if (par1NBTTagCompound.hasKey("Tut Furnace"))
        {
            this.field_94130_e = par1NBTTagCompound.getString("Tut Furnace");
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("BurnTime", (short)this.paradoxBurnTime);
        par1NBTTagCompound.setShort("CookTime", (short)this.paradoxCookTime);
        par1NBTTagCompound.setShort("FormTime", (short)this.paradoxFormTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.paradoxItemStacks.length; ++i)
        {
            if (this.paradoxItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.paradoxItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);

        if (this.hasCustomInventoryName())
        {
            par1NBTTagCompound.setString("Paradox Extractor", this.field_94130_e);
        }
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns an integer between 0 and the passed value representing how close the current item is to being completely
     * cooked
     */
    public int getItemBurnTime(int par1)
    {
        return this.paradoxCookTime * par1 / 200;
    }
    public int getFormProgressScaled(int par1)
    {
    	return this.paradoxFormTime * par1 / 200;
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
     * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
     */
    public int getBurnTimeRemainingScaled(int par1)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = 200;
        }

        return this.paradoxBurnTime * par1 / this.currentItemBurnTime;
    }

    /**
     * Returns true if the furnace is currently burning
     */
    public boolean isBurning()
    {
        return this.paradoxBurnTime > 0;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    @Override
    public void updateEntity()
    {	
        boolean flag = this.paradoxBurnTime > 0;
        boolean flag1 = false;

        if (this.paradoxBurnTime > 0)
        {
            --this.paradoxBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.canSmelt())
            {
                    flag1 = true;

                    if (this.paradoxItemStacks[1] != null)
                    {
                        if (this.paradoxItemStacks[1].stackSize == 0)
                        {
                            this.paradoxItemStacks[1] = this.paradoxItemStacks[1].getItem().getContainerItem(paradoxItemStacks[1]);
                        }
                    }
            }
            if(this.paradoxItemStacks[1] != null && this.paradoxItemStacks[2] == null)
            {
            	ItemStack item = new ItemStack(TimeTraveler.bottledParadox);
            	
            	if(this.paradoxItemStacks[1].isItemEqual(item))
            	{
            		int paradoxCurrent = this.paradoxItemStacks[1].getTagCompound().getInteger("paradoxLevel");
            		this.formItem(paradoxCurrent);
            	}
            	else
            	{
            		this.formItem();
            	}
            }
            if(this.paradoxItemStacks[1] == null && this.paradoxItemStacks[2] != null)
            {
            	if(this.paradoxItemStacks[2].getTagCompound() != null)
            	{
                	int paradoxCurrent = this.paradoxItemStacks[2].getTagCompound().getInteger("paradoxLevel");
                	System.out.println(paradoxCurrent);
                	paradoxCurrent++;
                	System.out.println(paradoxCurrent);
                	int paradoxPlayerAmt = TimeTraveler.vars.getParadoxAmt();
                	System.out.println(Ticker.paradoxLevel + " Before");
                	if(paradoxPlayerAmt > 0)
                	{
       			     	GuiScreen curScreen = Minecraft.getMinecraft().currentScreen;
                		if(curScreen instanceof GuiExtractor)
                		{
                			paradoxPlayerAmt--;
                			TimeTraveler.vars.setParadoxAmt(paradoxPlayerAmt);
                			this.paradoxItemStacks[2].getTagCompound().setInteger("paradoxLevel", paradoxCurrent);
                    		System.out.println(Ticker.paradoxLevel + " After");
                		}
                	}
            	}
            	else
            	{
            		this.paradoxItemStacks[2].setTagCompound(new NBTTagCompound());
            		this.paradoxItemStacks[2].getTagCompound().setInteger("paradoxLevel", 0);
            	}
            }
            /*else
            {
                //this.paradoxCookTime = 0;
            }*/

            if (flag != this.paradoxBurnTime > 0)
            {
                flag1 = true;
                ParadoxExtractor.updateFurnaceBlockState(this.paradoxBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        /*if (flag1)
        {
            this.onInventoryChanged();
        }*/
    }

    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canSmelt()
    {
        if (this.paradoxItemStacks[1] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = ExtractingRecipes.extracting().getExtractingResult(this.paradoxItemStacks[1]);
            if (itemstack == null) return false;
            if (this.paradoxItemStacks[2] == null) return true;
            if (!this.paradoxItemStacks[2].isItemEqual(itemstack)) return false;
            int result = paradoxItemStacks[2].stackSize + itemstack.stackSize;
            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
    }
    /**
     * Forms bottled Paradox
     */
    public void formItem()
    {
        if (this.canSmelt())
        {
        	ItemStack itemstack = new ItemStack(TimeTraveler.bottledParadox);
            if (this.paradoxItemStacks[2] == null)
            {
                this.paradoxItemStacks[2] = itemstack.copy();
            }
            else if (this.paradoxItemStacks[2].isItemEqual(itemstack))
            {
                paradoxItemStacks[2].stackSize += itemstack.stackSize;
            }

            --this.paradoxItemStacks[1].stackSize;

            if (this.paradoxItemStacks[1].stackSize <= 0)
            {
                this.paradoxItemStacks[1] = null;
            }
        }
    }
    public void formItem(int paradoxLevel)
    {
        if (this.canSmelt())
        {
        	System.out.println("Can smelt");
        	ItemStack itemstack = this.paradoxItemStacks[1].copy();
            if (this.paradoxItemStacks[2] == null)
            {
            	System.out.println(itemstack);
            	//itemstack.getTagCompound().setInteger("paradoxLevel", paradoxLevel);
            	//System.out.println(itemstack.getTagCompound().getInteger("paradoxLevel"));
                paradoxItemStacks[2] = itemstack.copy();
            }
            else if (this.paradoxItemStacks[2].isItemEqual(itemstack))
            {
               // paradoxItemStacks[2].stackSize += itemstack.stackSize;
            }

            --this.paradoxItemStacks[1].stackSize;

            if (this.paradoxItemStacks[1].stackSize <= 0)
            {
                this.paradoxItemStacks[1] = null;
            }
        }
    }


    /**
     * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
     * fuel
     */
    public static int getItemBurnTime(ItemStack par0ItemStack)
    {
        if (par0ItemStack == null)
        {
            return 0;
        }
        else
        {
            Item item = par0ItemStack.getItem();
            if(item.equals(TimeTraveler.bottledParadox))
            {
            	if(par0ItemStack.getTagCompound() != null)
            	{
            		return par0ItemStack.getTagCompound().getInteger("paradoxLevel");
            	}
            	else
            	{
            		return 0;
            	}
            }
            /*if (par0ItemStack.getItem() instanceof ItemBlock && Block.blocksList[i] != null)
            {
                Block block = Block.blocksList[i];

                if (block == Block.woodSingleSlab)
                {
                    return 150;
                }

                if (block.blockMaterial == Material.wood)
                {
                    return 300;
                }
            }

            if (item instanceof ItemTool && ((ItemTool) item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemSword && ((ItemSword) item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemHoe && ((ItemHoe) item).getMaterialName().equals("WOOD")) return 200;
            //if (i == mod_MainClass.TutItem.itemID) return 100;
            if (i == Item.coal.itemID) return 1600;
            return GameRegistry.getFuelValue(par0ItemStack);*/
        }
		return 0;
    }

    /**
     * Return true if item is a fuel source (getItemBurnTime() > 0).
     */
    public static boolean isItemFuel(ItemStack par0ItemStack)
    {
        return getItemBurnTime(par0ItemStack) > 0;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    @Override
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    /***********************************************************************************
     * This function is here for compatibilities sake, Modders should Check for
     * Sided before ContainerWorldly, Vanilla Minecraft does not follow the sided standard
     * that Modding has for a while.
     *
     * In vanilla:
     *
     *   Top: Ores
     *   Sides: Fuel
     *   Bottom: Output
     *
     * Standard Modding:
     *   Top: Ores
     *   Sides: Output
     *   Bottom: Fuel
     *
     * The Modding one is designed after the GUI, the vanilla one is designed because its
     * intended use is for the hopper, which logically would take things in from the top.
     *
     * This will possibly be removed in future updates, and make vanilla the definitive
     * standard.
     */

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}
}
