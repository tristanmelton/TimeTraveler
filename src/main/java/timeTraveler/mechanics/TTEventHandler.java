package timeTraveler.mechanics;

import java.io.IOException;
import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import timeTraveler.core.TimeTraveler;
import timeTraveler.entities.ExtendedEntity;
import timeTraveler.gui.GuiTimeTravel;
import timeTraveler.pasttravel.PastAction;
import timeTraveler.pasttravel.PastMechanics;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class TTEventHandler
{
	@ForgeSubscribe
	public void onArrowLooseEvent(ArrowLooseEvent ev) throws IOException
	{
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if (side == Side.SERVER)
		{
			List<PastAction> aList = TimeTraveler.instance.getActionListForPlayer(ev.entityPlayer);
			if (aList != null)
			{
				PastAction pa = new PastAction((byte)5);
				pa.arrowCharge = ev.charge;
				aList.add(pa);
			}
		}
	}

	@ForgeSubscribe
	public void onItemTossEvent(ItemTossEvent ev) throws IOException 
	{
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if (side == Side.SERVER)
		{
			List<PastAction> aList = TimeTraveler.instance.getActionListForPlayer(ev.player);
			if (aList != null) 
			{
				PastAction ma = new PastAction((byte) 3);

				ev.entityItem.getEntityItem().writeToNBT(ma.itemData);

				aList.add(ma);
			}
		}
	}

	@ForgeSubscribe
	public void onServerChatEvent(ServerChatEvent ev) 
	{
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if (side == Side.SERVER)
		{
			List<PastAction> aList = TimeTraveler.instance
					.getActionListForPlayer(ev.player);
			if (aList != null)
			{
				PastAction ma = new PastAction((byte) 1);
				ma.message = ev.message;
				aList.add(ma);
			}
		}
	}

	boolean inPast;

	@ForgeSubscribe
	public void onEntityJoin(EntityJoinWorldEvent event)
	{
		inPast = GuiTimeTravel.isInPast;
		PastMechanics mechanics = new PastMechanics();
		if (event.entity instanceof EntityLiving) 
		{
			ExtendedEntity props = ExtendedEntity
					.get((EntityLiving) event.entity);
			if (inPast) 
			{
				if (TimeTraveler.vars.pathData.data.containsKey(props
						.getEntityUID()))
				{
					// System.out.println("NOT A PAST ENTITY");
					// event.entity.setDead();
				}
			}
		}
	}

	@ForgeSubscribe
	public void onEntityConstructing(EntityConstructing event)
	{
		/*
		 * Be sure to check if the entity being constructed is the correct type
		 * for the extended properties about to add! The null check may not be
		 * necessary - I only use it to make sure properties are only registered
		 * once per entity.
		 */
		if (event.entity instanceof EntityLiving
				&& ExtendedEntity.get((EntityLiving) event.entity) == null) 
		{
			ExtendedEntity.register((EntityLiving) event.entity);
		}
		// That will call the constructor as well as cause the init() method to
		// be called automatically
		if (event.entity instanceof EntityLiving
				&& event.entity
						.getExtendedProperties(ExtendedEntity.EXT_PROP_NAME) == null)
		{
			event.entity.registerExtendedProperties(
					ExtendedEntity.EXT_PROP_NAME, new ExtendedEntity(
							(EntityLiving) event.entity));
		}
	}
}
