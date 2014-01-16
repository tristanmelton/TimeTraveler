package timeTraveler.entities;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import timeTraveler.core.TimeTraveler;
import timeTraveler.gui.GuiTimeTravel;
import timeTraveler.items.ItemExpEnhance;
import timeTraveler.mechanics.PastMechanics;
import cpw.mods.fml.client.FMLClientHandler;

public class EntityHandler 
{
	boolean inPast;
	
	@ForgeSubscribe
	public void onEntityCollide(LivingUpdateEvent event)
	{
		EntityPlayer ep = FMLClientHandler.instance().getClient().thePlayer;
		short xpMult = 0;
		
		
		if(event.entity instanceof EntityXPOrb)
		{
			System.out.println(":)");
			ItemStack[] itemsInInventory = ep.inventory.mainInventory;
			
			for(int i = 0; i < 36; i++)
			{
				if(itemsInInventory[i].getItem() instanceof ItemExpEnhance)
				{
					xpMult++;
				}
			}
			
			NBTTagCompound nbt = new NBTTagCompound();
			((EntityXPOrb)event.entity).writeEntityToNBT(nbt);
			if(xpMult != 0)
			{
				short newXP = (short)((xpMult * 2) * nbt.getShort("Value"));
				System.out.println(newXP);
				nbt.setShort("Value", newXP);
				((EntityXPOrb)event.entity).readEntityFromNBT(nbt);
			}
		}
	}
	
	@ForgeSubscribe
	public void onEntityJoin(EntityJoinWorldEvent event) 
	{		
		inPast = GuiTimeTravel.isInPast;
		PastMechanics mechanics = new PastMechanics();
		if(event.entity instanceof EntityLiving)
		{
			ExtendedEntity props = ExtendedEntity.get((EntityLiving) event.entity);
			if (inPast) 
			{					
				if(TimeTraveler.vars.pathData.data.containsKey(props.getEntityUID()))
				{
					//System.out.println("NOT A PAST ENTITY");
					//event.entity.setDead();
				}
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
