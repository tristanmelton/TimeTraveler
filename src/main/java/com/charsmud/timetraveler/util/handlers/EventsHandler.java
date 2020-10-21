package com.charsmud.timetraveler.util.handlers;

import java.io.IOException;
import java.util.List;

import com.charsmud.timetraveler.TimeTraveler;
import com.charsmud.timetraveler.TimeTraveler.PlayerTemporalLocation;
import com.charsmud.timetraveler.util.mechanics.TimeTeleporter;
import com.charsmud.timetraveler.util.mechanics.past.PastAction;
import com.charsmud.timetraveler.util.mechanics.past.PastActionTypes;
import com.charsmud.timetraveler.util.mechanics.past.TimeTravelerPastRecorder;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber
public class EventsHandler 
{
    @SubscribeEvent
    public void onPlayerLogIn(PlayerLoggedInEvent event)
    {
        if (event.player != null && !event.player.world.isRemote)
        {
    		TimeTravelerPastRecorder recorder = (TimeTravelerPastRecorder) TimeTraveler.instance.recordThreads.get(event.player);
    		if(recorder != null)
    			System.out.println("Something is wrong! Player already joins and is being recorded");
    		else
    		{
    			TimeTraveler.past_mechanics.beginPastRecording(event.player);
    		}
        }
    }
    @SubscribeEvent
    public void onEntityLeaveWorld(PlayerLoggedOutEvent event)
    {
        if (event.player != null && !event.player.world.isRemote)
        {
    		TimeTravelerPastRecorder recorder = (TimeTravelerPastRecorder) TimeTraveler.instance.recordThreads.get(event.player);
    		if(recorder != null)
    			TimeTraveler.past_mechanics.stopPastRecording(event.player);
    		else
    			System.out.println("Not recording the player who logged out!");
        }    	
    }
    @SubscribeEvent
    public void onBlockPlaceEvent(PlaceEvent event)
    {
		Side side = FMLCommonHandler.instance().getEffectiveSide();

		if(TimeTraveler.instance.TEMPORAL_LOCATION == PlayerTemporalLocation.PRESENT)
		{
			if (side == Side.SERVER) 
			{
				System.out.println("PLACE EVENT");
				EntityPlayerMP thePlayer = (EntityPlayerMP) event.getPlayer();
				List<PastAction> aList = TimeTraveler.instance.getActionListForPlayer(thePlayer);
				if (aList != null)
				{
					PastAction ma = new PastAction(PastActionTypes.PLACEBLOCK);
					ma.blockType = event.getPlacedBlock().getBlock();
					ma.xCoord = event.getPos().getX();
					ma.yCoord = event.getPos().getY();
					ma.zCoord = event.getPos().getZ();
					TimeTraveler.instance.addActionToPlayer(event.getPlayer(), ma);
				}
			}
		}

    }
    @SubscribeEvent
    public void onBlockBreakEvent(BreakEvent event)
    {
		if(TimeTraveler.instance.TEMPORAL_LOCATION == PlayerTemporalLocation.PRESENT)
		{
			System.out.println("BREAK EVENT");

			List<PastAction> aList = TimeTraveler.instance.getActionListForPlayer(event.getPlayer());

			if (aList != null)
			{
				PastAction ma = new PastAction(PastActionTypes.BREAKBLOCK);
				ma.xCoord = event.getPos().getX();
				ma.yCoord = event.getPos().getY();
				ma.zCoord = event.getPos().getZ();
				TimeTraveler.instance.addActionToPlayer(event.getPlayer(), ma);
			}
		}
		//TODO: Paradox on block break
		/*else
		{
			if(ParadoxBlockLevels.paradoxAmountsForBlocks().getParadoxBlockLevelList().containsKey(event.block))
			{
				System.out.println(event.block);
				int paradox = TimeTraveler.vars.getParadoxAmt() + (Integer)(ParadoxBlockLevels.paradoxAmountsForBlocks().getParadoxBlockLevelList().get(event.block));
				System.out.println(paradox);
				TimeTraveler.vars.setParadoxAmt(paradox);
			}
		}*/
    }
    
	@SubscribeEvent
	public void onArrowLooseEvent(ArrowLooseEvent ev) throws IOException
	{
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if (side == Side.SERVER)
		{
			List<PastAction> aList = TimeTraveler.instance.getActionListForPlayer(ev.getEntityPlayer());
			if (aList != null)
			{
				PastAction pa = new PastAction(PastActionTypes.SHOOTARROW);
				pa.arrowCharge = ev.getCharge();
				TimeTraveler.instance.addActionToPlayer(ev.getEntityPlayer(), pa);
			}
		}
	}

	@SubscribeEvent
	public void onItemTossEvent(ItemTossEvent ev) throws IOException 
	{
		System.out.println("TOSS EVENT");
	
		List<PastAction> aList = TimeTraveler.instance.getActionListForPlayer(ev.getPlayer());
		if (aList != null) 
		{
			PastAction ma = new PastAction(PastActionTypes.DROP);
			ev.getEntityItem().writeToNBT(ma.itemData);
			TimeTraveler.instance.addActionToPlayer(ev.getPlayer(), ma);
		}
	}
	//TODO: Add chat
	/*@SubscribeEvent
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
	}*/
    //TODO: This is a bad workaround!  Do a better way of sending back to present
	@SubscribeEvent
	public void onPlayerTickEvent(PlayerTickEvent event)
	{
		if(event.side == Side.SERVER)
		{
			if(TimeTraveler.TEMPORAL_LOCATION == PlayerTemporalLocation.PAST && TimeTraveler.instance.playThreads.isEmpty())
			{
				//TODO: Reset the past dimension??????
	        	TimeTraveler.TEMPORAL_LOCATION = PlayerTemporalLocation.PRESENT;
	        	TimeTeleporter.teleportToDimension(event.player, 0, event.player.posX, event.player.posY, event.player.posZ);
	        	TimeTraveler.past_mechanics.beginPastRecording(event.player);

	        	// get the height value so you don't get stuck in solid blocks or worse, in the void
	        	double dy = event.player.world.getHeight(MathHelper.floor(event.player.posX), MathHelper.floor(event.player.posZ));
	        	// still seem to need to set the position, +1 so you don't get in the void
	        	event.player.setPositionAndUpdate(event.player.posX, dy + 1, event.player.posZ);
			}		
		}
	}
}
