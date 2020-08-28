package com.charsmud.timetraveler.util.handlers;

import com.charsmud.timetraveler.TimeTraveler;
import com.charsmud.timetraveler.util.mechanics.past.PastMechanics;
import com.charsmud.timetraveler.util.mechanics.past.TimeTravelerPastRecorder;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;

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
    			recorder = new TimeTravelerPastRecorder();
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

}
