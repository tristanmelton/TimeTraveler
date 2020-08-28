package com.charsmud.timetraveler.tileentities;

import com.charsmud.timetraveler.blocks.BlockParadoxCondenser;
import com.charsmud.timetraveler.blocks.ParadoxCondenserRecipes;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class TileEntityParadoxCondenser extends TileEntity implements IInventory, ITickable
{
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
	private String customName;
	
	private int burnTime;
	private int currentBurnTime;
	private int cookTime;
	private int totalCookTime;
	
	@Override
	public String getName()
	{
		return this.hasCustomName() ? this.customName : "container.paradox_condenser";
	}
	@Override
	public boolean hasCustomName()
	{
		return this.customName != null && !this.customName.isEmpty();
	}
	@Override
	public ITextComponent getDisplayName()
	{
		return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
	}
	@Override
	public int getSizeInventory()
	{
		return this.inventory.size();
	}
	@Override 
	public boolean isEmpty()
	{
		for(ItemStack stack : this.inventory)
		{
			if(!stack.isEmpty())
				return false;
		}
		return true;
	}
	@Override
	public ItemStack getStackInSlot(int index)
	{
		return (ItemStack)this.inventory.get(index);
	}
	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		return ItemStackHelper.getAndSplit(this.inventory, index, count);
	}
	@Override
	public ItemStack removeStackFromSlot(int index)
	{
		return ItemStackHelper.getAndRemove(this.inventory, index);
	}
	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		ItemStack itemstack = (ItemStack)this.inventory.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.inventory.set(index, stack);
		
		if(stack.getCount() > this.getInventoryStackLimit())
			stack.setCount(this.getInventoryStackLimit());
		if(index == 0 && !flag)
		{
			this.totalCookTime = this.getCookTime(stack);
			this.cookTime = 0;
			this.markDirty();
		}
	}
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound,  this.inventory);
		this.burnTime = compound.getInteger("BurnTime");
		this.cookTime = compound.getInteger("CookTime");
		this.totalCookTime = compound.getInteger("CookTimeTotal");
		this.currentBurnTime = getItemBurnTime((ItemStack)this.inventory.get(2));
		
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setInteger("BurnTime", (short)this.burnTime);
		compound.setInteger("CookTime", (short)this.cookTime);
		compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		
		if(this.hasCustomName())
			compound.setString("CustomName", this.customName);
		return compound;
	}
	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}
	
	public boolean isBurning()
	{
		return this.burnTime > 0;
	}
	@SideOnly(Side.CLIENT)
	public static boolean isBurning(IInventory inventory)
	{
		return inventory.getField(0) > 0;
	}
	
	public void update()
	{
		boolean flag = this.isBurning();
		boolean flag1 = false;
		
		if(this.isBurning())
			--this.burnTime;
		if(!this.world.isRemote)
		{
			ItemStack stack = (ItemStack)this.inventory.get(1);
			if(this.isBurning() || !stack.isEmpty() && !((((ItemStack)this.inventory.get(0)).isEmpty())))
			{
				if(!this.isBurning() && this.canSmelt())
				{
					this.burnTime = getItemBurnTime(stack);
					this.currentBurnTime = this.burnTime;
					
					if(this.isBurning())
					{
						flag1 = true;
						if(!stack.isEmpty())
						{
							Item item = stack.getItem();
							stack.shrink(1);
							if(stack.isEmpty())
							{
								ItemStack item1 = item.getContainerItem(stack);
								this.inventory.set(1, item1);
							}
						}
					}
				}
				if(this.isBurning() && this.canSmelt())
				{
					++this.cookTime;
					if(this.cookTime == this.totalCookTime)
					{
						this.cookTime = 0;
						this.totalCookTime = this.getCookTime((ItemStack)this.inventory.get(0));
						this.smeltItem();
						flag1 = true;
					}
				}
				else
					this.cookTime = 0;
			}
			else if(!this.isBurning() && this.cookTime > 0)
			{
				this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
				//Resets cook back to zero if out of fuel
			}
			if(flag != this.isBurning())
			{
				flag1 = true;
				BlockParadoxCondenser.setState(this.isBurning(), this.world, this.pos);
			}
		}
		if(flag1)
			this.markDirty();
	}
	public int getCookTime(ItemStack input1)
	{
		return 200;
	}
	private boolean canSmelt()
	{
		if(((ItemStack)this.inventory.get(0)).isEmpty() || ((ItemStack)this.inventory.get(1)).isEmpty())
			return false;
		else
		{
			ItemStack result = ParadoxCondenserRecipes.getInstance().getCondensingResult((ItemStack)this.inventory.get(0));
			if(result.isEmpty())
				return false;
			else
			{
				ItemStack output = (ItemStack)this.inventory.get(2);
				if(output.isEmpty())
					return true;
				if(!output.isItemEqual(result))
					return false;
				int res = output.getCount() + result.getCount();
				return res <= getInventoryStackLimit() && res <= output.getMaxStackSize();
			}
		}
	}
	public void smeltItem()
	{
		if(this.canSmelt())
		{
			ItemStack input1 = (ItemStack)this.inventory.get(0);
			ItemStack result = ParadoxCondenserRecipes.getInstance().getCondensingResult(input1);
			ItemStack output = (ItemStack)this.inventory.get(2);
			
			if(output.isEmpty())
				this.inventory.set(2, result.copy());
			else if(output.getItem() == result.getItem())
				output.grow(result.getCount());
			input1.shrink(1);
		}
	}
	public static int getItemBurnTime(ItemStack fuel)
	{
		if(fuel.isEmpty()) return 0;
		else
		{
			Item item = fuel.getItem();
			if(item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR)
			{
				Block block = Block.getBlockFromItem(item);
				if(block == Blocks.WOODEN_SLAB)
					return 150;
				if(block.getDefaultState().getMaterial() == Material.WOOD)
					return 300;
				if(block == Blocks.COAL_BLOCK)
					return 16000;
			}
			if(item instanceof ItemTool && "WOOD".equals(((ItemTool)item).getToolMaterialName()))
				return 200;
			if(item instanceof ItemSword && "WOOD".equals(((ItemSword)item).getToolMaterialName()))
				return 200;
			if(item instanceof ItemHoe && "WOOD".equals(((ItemHoe)item).getMaterialName()))
				return 200;
			if(item == Items.STICK)
				return 100;
			if(item == Items.COAL)
				return 1600;
			if(item == Items.LAVA_BUCKET)
				return 20000;
			if(item == Item.getItemFromBlock(Blocks.SAPLING))
				return 100;
			if(item == Items.BLAZE_ROD)
				return 2400;
			return GameRegistry.getFuelValue(fuel);
		}
	}
	public static boolean isItemFuel(ItemStack fuel)
	{
		return getItemBurnTime(fuel) > 0;
	}
	@Override
	public boolean isUsableByPlayer(EntityPlayer player)
	{
		return this.world.getTileEntity(this.pos)!= this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D; 
	}
	@Override
	public void openInventory(EntityPlayer player) {}
	@Override
	public void closeInventory(EntityPlayer player) {}
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		if(index == 2)
			return false;
		else if(index == 0)
			return true;
		else
			return isItemFuel(stack);
	}
	public String getGuiID()
	{
		return "timetraveler:paradox_condenser";
	}
	@Override
	public int getField(int id)
	{
		switch(id)
		{
		case 0:
			return this.burnTime;
		case 1:
			return this.currentBurnTime;
		case 2:
			return this.cookTime;
		case 3:
			return this.totalCookTime;
		default:
			return 0;
		}
	}
	@Override
	public void setField(int id, int value)
	{
		switch(id)
		{
		case 0:
			this.burnTime = value;
			break;
		case 1:
			this.currentBurnTime = value;
			break;
		case 2:
			this.cookTime = value;
			break;
		case 3:
			this.totalCookTime = value;
			break;
		}
	}
	@Override
	public int getFieldCount()
	{
		return 4;
	}
	@Override
	public void clear()
	{
		this.inventory.clear();
	}
	public void setCustomName(String customName)
	{
		this.customName = customName;
	}
    /*private static final int[] field_102010_d = new int[] {0};
    private static final int[] field_102011_e = new int[] {2, 1};
    private static final int[] field_102009_f = new int[] {1};


    private ItemStack[] paradoxItemStacks = new ItemStack[3];


    public int paradoxBurnTime = 0;


    public int currentItemBurnTime = 0;


    public int paradoxCookTime = 0;
    public int paradoxFormTime = 0;
    private String field_94130_e;

    @Override
    public int getSizeInventory()
    {
        return this.paradoxItemStacks.length;
    }


    @Override
    public ItemStack getStackInSlot(int par1)
    {
        return this.paradoxItemStacks[par1];
    }


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


    @Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.paradoxItemStacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName()
    {
        return this.hasCustomInventoryName() ? this.field_94130_e : "Paradox Condenser";
    }


    @Override
    public boolean hasCustomInventoryName()
    {
        return this.field_94130_e != null && this.field_94130_e.length() > 0;
    }

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
            par1NBTTagCompound.setString("Paradox Condenser", this.field_94130_e);
        }
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @SideOnly(Side.CLIENT)


    public int getCookProgressScaled(int par1)
    {
        return this.paradoxCookTime * par1 / 200;
    }
    
    public int getFormProgressScaled(int par1)
    {
    	return this.paradoxFormTime * par1 / 200;
    }
    
    @SideOnly(Side.CLIENT)

    public int getBurnTimeRemainingScaled(int par1)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = 200;
        }

        return this.paradoxBurnTime * par1 / this.currentItemBurnTime;
    }


    public boolean isBurning()
    {
        return this.paradoxBurnTime > 0;
    }


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
            if (this.paradoxBurnTime == 0 && this.canSmelt())
            {
                this.currentItemBurnTime = this.paradoxBurnTime = getItemBurnTime(this.paradoxItemStacks[1]);

                if (this.paradoxBurnTime > 0)
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
            }
            if(this.paradoxItemStacks[0] == null && this.paradoxItemStacks[1] != null)
            {
            	if(this.paradoxItemStacks[1].getItem() == TimeTraveler.bottledParadox)
            	{
                	int paradoxAmount = this.paradoxItemStacks[1].getTagCompound().getInteger("paradoxLevel") - 1;
                	if(paradoxAmount > 0)
                	{
                    	++this.paradoxFormTime;
                    	this.paradoxItemStacks[1].getTagCompound().setInteger("paradoxLevel", paradoxAmount);
                    	//Time it takes to form
                    	if(this.paradoxFormTime == 200)
                    	{
                    		this.paradoxFormTime = 0;
                    		this.formItem();
                    		flag1 = true;
                    	}
                	}
            	}
            }
            if(this.paradoxItemStacks[0] != null && this.paradoxItemStacks[1] != null)
            {
                if (this.isBurning() && this.canSmelt())
                {                
                    if(this.paradoxItemStacks[1] != null)
                    {
                        ++this.paradoxCookTime;
                        int paradoxAmount = this.paradoxItemStacks[1].getTagCompound().getInteger("paradoxLevel") - 1;
                        
                        this.paradoxItemStacks[1].getTagCompound().setInteger("paradoxLevel", paradoxAmount);
                        //Time it takes to Condense an item
                        if (this.paradoxCookTime == 200)
                        {
                            this.paradoxCookTime = 0;
                            this.smeltItem();
                            flag1 = true;
                        }

                    }
                }

            }


            if (flag != this.paradoxBurnTime > 0)
            {
                flag1 = true;
                //BlockParadoxCondenser.updateFurnaceBlockState(this.paradoxBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (flag1)
        {
            //this.onInventoryChanged();
        }
    }

    private boolean canSmelt()
    {
        if (this.paradoxItemStacks[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = ParadoxRecipes.condensing().getCondensingResult(this.paradoxItemStacks[0]);
            if (itemstack == null) return false;
            if (this.paradoxItemStacks[2] == null) return true;
            if (!this.paradoxItemStacks[2].isItemEqual(itemstack)) return false;
            int result = paradoxItemStacks[2].stackSize + itemstack.stackSize;
            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
    }


    
    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemstack = ParadoxRecipes.condensing().getCondensingResult(this.paradoxItemStacks[0]);

            if (this.paradoxItemStacks[2] == null)
            {
                this.paradoxItemStacks[2] = itemstack.copy();
            }
            else if (this.paradoxItemStacks[2].isItemEqual(itemstack))
            {
                paradoxItemStacks[2].stackSize += itemstack.stackSize;
            }

            //--this.paradoxItemStacks[0].stackSize;

            if (this.paradoxItemStacks[0].stackSize <= 0)
            {
                this.paradoxItemStacks[0] = null;
            }
        }
    }


    public void formItem()
    {
    	ItemStack itemstack = new ItemStack(TimeTraveler.condensedParadox);
    	if (this.paradoxItemStacks[2] == null)
    	{
    		this.paradoxItemStacks[2] = itemstack.copy();
    	}
    	else if (this.paradoxItemStacks[2].isItemEqual(itemstack))
    	{
    		paradoxItemStacks[2].stackSize += itemstack.stackSize;
    	}        
    }


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
            if (par0ItemStack.getItem() instanceof ItemBlock && Block.blocksList[i] != null)
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
            return GameRegistry.getFuelValue(par0ItemStack);
        }
		return 0;
    }


    public static boolean isItemFuel(ItemStack par0ItemStack)
    {
        return getItemBurnTime(par0ItemStack) > 0;
    }

    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }




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
		
	}*/




}
