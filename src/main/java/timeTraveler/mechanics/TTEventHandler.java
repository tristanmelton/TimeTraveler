package timeTraveler.mechanics;

import java.io.IOException;
import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import timeTraveler.core.TimeTraveler;
import timeTraveler.entities.EntityXPOrbTT;
import timeTraveler.entities.ExtendedEntity;
import timeTraveler.gui.GuiTimeTravel;
import timeTraveler.items.ItemExpEnhance;
import timeTraveler.pasttravel.PastAction;
import timeTraveler.pasttravel.PastActionTypes;
import timeTraveler.pasttravel.PastMechanics;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class TTEventHandler
{
	
	@ForgeSubscribe
	public void onBlockBreakEvent(BreakEvent event)
	{
		if(!GuiTimeTravel.isInPast)
		{
			List<PastAction> aList = TimeTraveler.instance.getActionListForPlayer(event.getPlayer());

			if (aList != null)
			{
				PastAction ma = new PastAction(PastActionTypes.BREAKBLOCK);
				ma.xCoord = event.x;
				ma.yCoord = event.y;
				ma.zCoord = event.z;
				aList.add(ma);
				System.out.println("BREAKING BLOCK");
			}
		}
		else
		{
			if(ParadoxBlockLevels.paradoxAmountsForBlocks().getParadoxBlockLevelList().containsKey(event.block))
			{
				System.out.println(event.block);
				int paradox = TimeTraveler.vars.getParadoxAmt() + (Integer)(ParadoxBlockLevels.paradoxAmountsForBlocks().getParadoxBlockLevelList().get(event.block));
				System.out.println(paradox);
				TimeTraveler.vars.setParadoxAmt(paradox);
			}
		}
		
	}
	@ForgeSubscribe
	public void onLivingPlaceBlockEvent(LivingPlaceBlockEvent event) {
		Side side = FMLCommonHandler.instance().getEffectiveSide();

		if(!GuiTimeTravel.isInPast)
		{
			if (side == Side.SERVER) {
				if (event.entityLiving instanceof EntityPlayerMP) {
					EntityPlayerMP thePlayer = (EntityPlayerMP) event.entityLiving;
					List<PastAction> aList = TimeTraveler.instance
							.getActionListForPlayer(thePlayer);

					if (aList != null) {
						PastAction ma = new PastAction(
								PastActionTypes.PLACEBLOCK);
						event.theItem.writeToNBT(ma.itemData);
						ma.xCoord = event.xCoord;
						ma.yCoord = event.yCoord;
						ma.zCoord = event.zCoord;
						aList.add(ma);
					}
				}
			}
		}
	}
	@ForgeSubscribe
	public void onArrowLooseEvent(ArrowLooseEvent ev) throws IOException
	{
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if (side == Side.SERVER)
		{
			List<PastAction> aList = TimeTraveler.instance.getActionListForPlayer(ev.entityPlayer);
			if (aList != null)
			{
				PastAction pa = new PastAction(PastActionTypes.SHOOTARROW);
				pa.arrowCharge = ev.charge;
				aList.add(pa);
			}
		}
	}

	@ForgeSubscribe
	public void onItemTossEvent(ItemTossEvent ev) throws IOException 
	{
		
		List<PastAction> aList = TimeTraveler.instance.getActionListForPlayer(ev.player);
		if (aList != null) 
		{
			PastAction ma = new PastAction(PastActionTypes.DROP);

			ev.entityItem.getEntityItem().writeToNBT(ma.itemData);

			aList.add(ma);
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
		if(event.entity instanceof EntityXPOrb)
		{
			EntityXPOrb xp = (EntityXPOrb)event.entity;
			InventoryPlayer inventory = FMLClientHandler.instance().getClient().thePlayer.inventory;
			int enhancers = 0;
			for(int i = 0; i < inventory.getSizeInventory(); i++)
			{
				if(inventory.getStackInSlot(i) != null)
				{
					if(inventory.getStackInSlot(i).getItem() instanceof ItemExpEnhance)
					{
						int size = inventory.getStackInSlot(i).stackSize;
						enhancers = enhancers + size;
					}
				}
			}
			if(enhancers > 0)
			{
				event.entity.setDead();
				int xpVal = xp.getXpValue() * enhancers;
				System.out.println(xpVal);
				EntityXPOrbTT orb = new EntityXPOrbTT(event.world, event.entity.posX, event.entity.posY, event.entity.posZ, xpVal);
				orb.worldObj.spawnEntityInWorld(orb);
			}
		}
		
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
