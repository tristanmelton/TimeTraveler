package timeTraveler.entities;

import java.util.UUID;

import timeTraveler.core.TimeTraveler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class EntityHandler 
{
	@ForgeSubscribe
	public void onEntityJoin(EntityJoinWorldEvent event)
	{
		if(event.entity instanceof EntityLiving && !(event.entity instanceof EntityPlayer))
		{
			String[] entityData = new String[2];
			
			String uuid = event.entity.getPersistentID().toString();
			String entityName = event.entity.getEntityName();
			
			entityData[0] = uuid;
			entityData[1] = entityName;
			/*if(event.entity instanceof EntityPlayer)
			{
				String usr = ((EntityPlayer)event.entity).username;
				if(usr.equals("Link2006155"))
				{
					EntityWolf sprout = new EntityWolf(event.world);
					sprout.posX = event.entity.posX;
					sprout.posY = event.entity.posY;
					sprout.posZ = event.entity.posZ;
					sprout.setTamed(true);
				}
			}*/
			if(TimeTraveler.vars.pathData.doesEntityExist(entityData))
			{
				//Do stuff
			}
			else
			{
				event.entity.setDead();
			}
		}
	}

}
