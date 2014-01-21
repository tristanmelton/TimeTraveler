package timeTraveler.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityChair extends Entity
{

	public EntityChair(World par1World)
	{
		super(par1World);
		this.width = 0;
		this.height = 0;
	}
	

	@Override
	protected void entityInit()
	{
		this.setInvisible(true);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) 
	{
		// TODO Auto-generated method stub
		
	}

	/**
	  * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	  */
	public boolean interact(EntityPlayer player)
	{
		if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != player)
		{
			return true;
		} 
		else 
		{
			if(!this.worldObj.isRemote)
			{
				player.mountEntity(this);
				return true;
			} 
		}
		return false;
	}

}
