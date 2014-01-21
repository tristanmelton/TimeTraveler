package timeTraveler.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import timeTraveler.core.TimeTraveler;
import timeTraveler.entities.EntityChair;
import timeTraveler.gui.GuiTimeTravel;
import cpw.mods.fml.client.FMLClientHandler;

public class TileEntityCollision extends TileEntity
{
        //The coordinates of our primary block will be stored in these variables.
        public int primary_x;
        public int primary_y;
        public int primary_z;
        
        public String operator;
        
        private int pLoc = 0;
        
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("px", primary_x);
        par1NBTTagCompound.setInteger("py", primary_y);
        par1NBTTagCompound.setInteger("pz", primary_z);
        par1NBTTagCompound.setString("op", operator);
    }
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        this.primary_x = par1NBTTagCompound.getInteger("px");
        this.primary_y = par1NBTTagCompound.getInteger("py");
        this.primary_z = par1NBTTagCompound.getInteger("pz");
        this.operator = par1NBTTagCompound.getString("op");
    }
    
    public void operatorFunctions(String operator)
    {
    	if(operator != null)
    	{
        	if(operator.equals("ext"))
        	{
        		FMLClientHandler.instance().getClient().thePlayer.openGui(TimeTraveler.instance, 0, FMLClientHandler.instance().getClient().theWorld, primary_x, primary_y, primary_z);
        	}
        	if(operator.equals("tt"))
        	{
        		EntityPlayer ep = FMLClientHandler.instance().getClient().thePlayer;
    			World world = FMLClientHandler.instance().getClient().theWorld;
        		EntityChair chair = new EntityChair(world);
        		chair.setPosition((double)primary_x + 0.5, (double)primary_y + 0.25, (double)primary_z + 0.5);
        		world.spawnEntityInWorld(chair);
        		if(ep.isRiding())
        		{
        			if(ep.isSneaking())
        			{
        				ep.mountEntity(null);
        			}
        			else
        			{
        				Minecraft.getMinecraft().displayGuiScreen(new GuiTimeTravel());
        				System.out.println("OPENGING GUI");
        			}
        		}
        		else if(!ep.isRiding())
        		{
        			if(ep.getHeldItem() != null && ep.getHeldItem().isItemEqual(new ItemStack(TimeTraveler.paradoximer)))
        			{
        				System.out.println("paradox");
        			}
        			else
        			{
            			ep.mountEntity(chair);
        			}
        		}
        	}
    	}
    }
}
