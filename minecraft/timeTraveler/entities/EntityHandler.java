package timeTraveler.entities;

import java.util.UUID;

import timeTraveler.core.StringArrayHolder;
import timeTraveler.core.TimeTraveler;
import timeTraveler.gui.GuiTimeTravel;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class EntityHandler 
{
	boolean inPast;

	@ForgeSubscribe
	public void onEntityJoin(EntityJoinWorldEvent event)
	{
		
		inPast = GuiTimeTravel.isInPast;

		System.out.println(":)");

		if(event.entity instanceof EntityLiving && !(event.entity instanceof EntityPlayer))
		{
			
			String[] entityData = new String[2];
			
			String uuid = event.entity.getPersistentID().toString();
			String entityName = event.entity.getEntityName();
			
			entityData[0] = uuid;
			entityData[1] = entityName;
			
			StringArrayHolder data = new StringArrayHolder(entityData);
			
			System.out.println(":) :)");
			
			if(inPast)
			{
				System.out.println(uuid);

				System.out.println(":) :) :)");

				if(!TimeTraveler.vars.pathData.doesEntityExist(data))
				{
					System.out.println("REMOVING ENTITY");

					event.entity.setDead();
				}
			}
		}
	}

}
