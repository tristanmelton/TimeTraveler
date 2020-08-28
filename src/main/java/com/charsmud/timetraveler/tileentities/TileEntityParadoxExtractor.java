package com.charsmud.timetraveler.tileentities;

import com.charsmud.timetraveler.blocks.BlockParadoxExtractor;
import com.charsmud.timetraveler.blocks.ParadoxExtractorRecipes;
import com.charsmud.timetraveler.gui.GuiParadoxExtractor;
import com.charsmud.timetraveler.items.ItemInit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityParadoxExtractor extends TileEntity implements IInventory, ITickable
{
	//TODO: Implement Me
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);
	private String customName;
	
	private int burnTime;
	private int currentBurnTime;
	private int cookTime;
	private int totalCookTime;
	
	@Override
	public String getName()
	{
		return this.hasCustomName() ? this.customName : "container.paradox_extractor";
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
		this.currentBurnTime = getItemBurnTime((ItemStack)this.inventory.get(1));
		
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
	//TODO: Update over here
	public void update()
	{
        boolean flag = this.isBurning();
        boolean flag1 = false;

        if (this.isBurning())
        {
            --this.burnTime;
        }

        if (!this.world.isRemote)
        {
            if(this.inventory.get(0) != ItemStack.EMPTY && this.inventory.get(1) == ItemStack.EMPTY)
            {
            	ItemStack item = new ItemStack(ItemInit.BOTTLED_PARADOX);
            	
            	if(this.canSmelt() && this.inventory.get(0).isItemEqual(item))
            	{
            		if(this.inventory.get(0).getTagCompound() == null)
            			this.inventory.get(0).getTagCompound().setInteger("paradoxAmount", 0);
            		int paradoxCurrent = this.inventory.get(0).getTagCompound().getInteger("paradoxLevel");
            		this.smeltItem(paradoxCurrent);
            	}
            	else
            	{
            		this.smeltItem();
            	}
            }
            if(this.inventory.get(0) == ItemStack.EMPTY && this.inventory.get(1) != ItemStack.EMPTY)
            {
            	if(this.inventory.get(1).getTagCompound() != null)
            	{
                	int paradoxCurrent = this.inventory.get(1).getTagCompound().getInteger("paradoxLevel");
                	System.out.println(paradoxCurrent);
                	paradoxCurrent++;
                	System.out.println(paradoxCurrent);
                	int paradoxPlayerAmt = 1; //TimeTraveler.vars.getParadoxAmt(); //TODO: This is a test for debug, change later
                	if(paradoxPlayerAmt > 0)
                	{
       			     	GuiScreen curScreen = Minecraft.getMinecraft().currentScreen;
                		if(curScreen instanceof GuiParadoxExtractor)
                		{
                			paradoxPlayerAmt--;
                			//TimeTraveler.vars.setParadoxAmt(paradoxPlayerAmt); //TODO: This is a test for debug, change later
                			this.inventory.get(1).getTagCompound().setInteger("paradoxLevel", paradoxCurrent);
                		}
                	}
            	}
            	else
            	{
            		this.inventory.get(1).setTagCompound(new NBTTagCompound());
            		this.inventory.get(1).getTagCompound().setInteger("paradoxLevel", 0);
            	}
            }

            if (flag != this.isBurning())
            {
                flag1 = true;
                BlockParadoxExtractor.setState(this.isBurning(), this.world, this.pos);
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
		if(((ItemStack)this.inventory.get(0)).isEmpty())
			return false;
		else
		{
			ItemStack result = ParadoxExtractorRecipes.getInstance().getExtractingResult((ItemStack)this.inventory.get(0));
			if(result.isEmpty())
				return false;
			else
			{
				ItemStack output = (ItemStack)this.inventory.get(1);
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
        if (this.canSmelt())
        {
        	ItemStack itemstack = new ItemStack(ItemInit.BOTTLED_PARADOX);
            if (this.inventory.get(1) == ItemStack.EMPTY)
            {
                this.inventory.set(1, itemstack.copy());
            }
            /*else if (this.inventory.get(2).isItemEqual(itemstack))
            {
                paradoxItemStacks[2].stackSize += itemstack.stackSize;
            }*/

            this.inventory.get(0).shrink(1);

            if (this.inventory.get(0).getCount() <= 0)
            {
                this.inventory.set(0, ItemStack.EMPTY);
            }
        }
    }
    public void smeltItem(int paradoxLevel)
    {
        if (this.canSmelt())
        {
        	System.out.println("Can smelt");
        	ItemStack itemstack = this.inventory.get(0).copy();
            if (this.inventory.get(1) == ItemStack.EMPTY)
            {
            	//itemstack.getTagCompound().setInteger("paradoxLevel", paradoxLevel);
            	//System.out.println(itemstack.getTagCompound().getInteger("paradoxLevel"));
                this.inventory.set(1, itemstack.copy());
            }
            else if (this.inventory.get(1).isItemEqual(itemstack))
            {
               // paradoxItemStacks[2].stackSize += itemstack.stackSize;
            }

            this.inventory.get(0).shrink(1);

            if (this.inventory.get(0).getCount() <= 0)
            {
                this.inventory.set(0, ItemStack.EMPTY);;
            }
        }
    }
	public static int getItemBurnTime(ItemStack fuel)
	{
        if (fuel == ItemStack.EMPTY)
        {
            return 0;
        }
        else
        {
            Item item = fuel.getItem();
            if(item.equals(ItemInit.BOTTLED_PARADOX))
            {
            	if(fuel.getTagCompound() != null)
            	{
            		return fuel.getTagCompound().getInteger("paradoxLevel");
            	}
            	else
            	{
            		return 0;
            	}
            }
        }
		return 0;
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
		if(index == 1)
			return false;
		else if(index == 0)
			return true;
		else
			return isItemFuel(stack);
	}
	public String getGuiID()
	{
		return "timetraveler:paradox_extractor";
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

}
