package com.charsmud.timetraveler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.charsmud.timetraveler.proxy.CommonProxy;
import com.charsmud.timetraveler.util.Reference;
import com.charsmud.timetraveler.util.handlers.EventsHandler;
import com.charsmud.timetraveler.util.handlers.GuiHandler;
import com.charsmud.timetraveler.util.handlers.RegistryHandler;
import com.charsmud.timetraveler.util.mechanics.past.PastAction;
import com.charsmud.timetraveler.util.mechanics.past.PastMechanics;
import com.charsmud.timetraveler.util.mechanics.past.PastPlayThread;
import com.charsmud.timetraveler.util.mechanics.past.TimeTravelerPastRecorder;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class TimeTraveler 
{	
	public static enum PlayerTemporalLocation
	{
		PAST,
		PRESENT,
		FUTURE
	};
	
	public static PlayerTemporalLocation TEMPORAL_LOCATION;
	@Instance
	public static TimeTraveler instance;
	public static PastMechanics past_mechanics;
	public static final CreativeTabs TIMETRAVELER_TAB = new TimeTravelerTab("timetraveler");
	
	public Map<EntityPlayer, TimeTravelerPastRecorder> recordThreads = Collections.synchronizedMap(new HashMap());
	public ArrayList<PastPlayThread> playThreads = new ArrayList();

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	

    @EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
    	TEMPORAL_LOCATION = PlayerTemporalLocation.PRESENT;
    	RegistryHandler.preInitRegistries();
    	past_mechanics = new PastMechanics();
	}

    @EventHandler
    public void init(FMLInitializationEvent event)
	{
    	RegistryHandler.initRegistries();
    	MinecraftForge.EVENT_BUS.register(new EventsHandler());
	}
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	MinecraftForge.EVENT_BUS.register(new GuiHandler());
    }
	public List<PastAction> getActionListForPlayer(EntityPlayer ep)
	{
		TimeTravelerPastRecorder aRecorder = (TimeTravelerPastRecorder) this.recordThreads.get(ep);
		if (aRecorder == null) 
		{
			return null;
		}
		return aRecorder.eventsList;
	}

}
