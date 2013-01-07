package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class EntityPlayerPast extends EntityCreature
{
    public EntityPlayerPast(World par1World) {
		super(par1World);
        this.entityType = "humanoid";
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
}