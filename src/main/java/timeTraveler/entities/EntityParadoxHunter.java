package timeTraveler.entities;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import timeTraveler.core.TimeTraveler;

public class EntityParadoxHunter extends EntityMob 
{
	public EntityParadoxHunter(World par1World)
	{
		super(par1World);
		//this.t = "/mods/Charsmud_TimeTraveler/textures/mobs/ParadoxHunter.png";
		this.setAIMoveSpeed(2.0F);
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));

	}
	public int getAttackStrength(Entity par1Entity) 
	{
		return 20;
	}

	protected boolean isAIEnabled()
	{
		return true;
	}
	public String getTexture()
    {
		return "/assets/charsmud_timetraveler/textures/mobs/ParadoxHunter.png";
    }
	public int getTotalArmorValue()
    {
        return 20;
    }
	protected String getLivingSound()
    {
        return "mob.zombie.say";
    }
 
    protected String getHurtSound()
    {
        return "mob.zombie.hurt";
    }
    
    protected String getDeathSound()
    {
        return "mob.zombie.death";
    }
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.worldObj.playSoundAtEntity(this, "mob.zombie.step", 0.15F, 1.0F);
    }
    protected void dropRareDrop(int par1)
    {
        switch (this.rand.nextInt(1))
        {
            case 0:
                this.dropItem(TimeTraveler.condensedParadox.itemID, 1);
                break;
        }
    }

	
	public void onLivingUpdate()
    {
		//BURNS THE MOB IF ITS DAYTIME AND IS IN THE SUN
        /*if (this.worldObj.isDaytime() && !this.worldObj.isRemote)
        {
            float var1 = this.getBrightness(1.0F);

            if (var1 > 0.5F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) && this.rand.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F)
            {
                this.setFire(8);
            }
        }*/

        super.onLivingUpdate();
    }


}