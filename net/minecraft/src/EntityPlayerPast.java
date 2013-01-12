package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class EntityPlayerPast extends EntityAnimal
{
	private PathEntity pathToEntity;
	Minecraft minecraft = ModLoader.getMinecraftInstance();
	World w = minecraft.theWorld;
    public EntityPlayerPast(World par1World) {
		super(par1World);
        this.entityType = "humanoid";
        this.texture = "/mob/pigzombie.png";

        //this.skinUrl = Minecraft.getMinecraft().thePlayer.skinUrl;
        this.fireResistance = 20;

	}
    public int getMaxHealth()
    {
        return 20;
    }

public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
    }

    protected String getLivingSound()
    {
        return "null";
    }

    protected String getHurtSound()
    {
        return "null";
    }

    protected String getDeathSound()
    {
        return "null";
    }

    protected float getSoundVolume()
    {
        return 1.0F;
    }

    protected int getDropItemId()
    {
        return 0;
    }
    
    @Override
    public void setPathToEntity(PathEntity pathentity)
    {
     pathToEntity = this.worldObj.getEntityPathToXYZ(this, -6, 68, 117, 30F, true, true, false, false);

     super.setPathToEntity(pathToEntity);
    }

    
    public void onUpdate()
    {
    	super.onUpdate();
    	setPathToEntity(pathToEntity);
    }
	@Override
	public EntityAgeable func_90011_a(EntityAgeable var1) {
		// TODO Auto-generated method stub
		return null;
	}

}