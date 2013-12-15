package timeTraveler.entities;

import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import timeTraveler.core.EntityData;
import timeTraveler.core.StringArrayHolder;
import timeTraveler.core.TimeTraveler;
import timeTraveler.gui.GuiTimeTravel;

public class EntityHandler 
{
	boolean inPast;
	
	@ForgeSubscribe
	public void onEntityJoin(EntityJoinWorldEvent event) 
	{
		inPast = GuiTimeTravel.isInPast;

		if(event.entity instanceof EntityLiving)
		{
			ExtendedEntity props = ExtendedEntity.get((EntityLiving) event.entity);

			if (inPast) 
			{

			}
		}
	}

	@ForgeSubscribe
	public void onEntityConstructing(EntityConstructing event) 
	{
		/*
		 * Be sure to check if the entity being constructed is the correct type
		 * for the extended properties about to add! The null check may
		 * not be necessary - I only use it to make sure properties are only
		 * registered once per entity.
		 */
		if(event.entity instanceof EntityLiving && ExtendedEntity.get((EntityLiving) event.entity) == null)
		{
			ExtendedEntity.register((EntityLiving) event.entity);
		}
		// That will call the constructor as well as cause the init() method to be called automatically
		if (event.entity instanceof EntityLiving && event.entity.getExtendedProperties(ExtendedEntity.EXT_PROP_NAME) == null)
		{
			event.entity.registerExtendedProperties(ExtendedEntity.EXT_PROP_NAME, new ExtendedEntity((EntityLiving) event.entity));
		}
	}

}
