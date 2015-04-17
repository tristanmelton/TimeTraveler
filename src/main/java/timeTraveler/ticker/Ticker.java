package timeTraveler.ticker;

import java.io.File;
import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.potion.Potion;
import timeTraveler.core.TimeTraveler;
import timeTraveler.gui.GuiTimeTravel;
import timeTraveler.mechanics.CopyFile;
import timeTraveler.pasttravel.PastMechanics;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;

/**
 * Ticker
 * @author Charsmud
 *
 */
public class Ticker 
{
	public int ctr;
	public int ct;
	public int count;
	
	public static int paradoxLevel;
	
	public static int minutes = 2;
	public static int seconds = 1;
	
	public int invisPotTime = 0;
	public int sneakTime = 0;
	
	private int timeNumber = 1;
	private int pathFileLine = 0;
	private int hunterSpawn = 0;

	String text;

	CopyFile copyFile = new CopyFile();

	public boolean hasInitRun = false;
	public boolean hasInitMobs = false;
	
	private boolean mobsInitSpawned = false; 
	private boolean isInPast;
	private boolean hasRun = false;

	
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event)
	{
		if(event.side.isClient())
		{
			paradoxLevel = TimeTraveler.vars.getParadoxAmt();

			Minecraft mc = Minecraft.getMinecraft();
			ctr++;
			ct++;
			//System.out.println(ctr);
			hasInitRun = (new File(mc.mcDataDir + "/mods/TimeMod/past/" + FMLClientHandler.instance().getServer().getWorldName())).exists();


			PastMechanics mechanics = new PastMechanics();
			
		    text  = "Time Remaining: " + minutes + " Minute, " + seconds + " Seconds";

			isInPast = GuiTimeTravel.isInPast;		
			if(!isInPast && !TimeTraveler.vars.getIsInFuture())
			{
				TimeTraveler.vars.setIsSpawnedPastPlayer(false);
				if(event.player.isSneaking())
				{
				}
				if(paradoxLevel >= 30)
				{
					hunterSpawn++;
					if(hunterSpawn == 150)
					{
						mechanics.spawnParadoxHunter(mc.getIntegratedServer(), mc);
						System.out.println("Spawned Paradox Hunter!");
						hunterSpawn = 0;
					}
				}
			}
			
			if(ct == 20)
			{
				if(!isInPast && !TimeTraveler.vars.getIsInFuture())
				{				
					ct = 0;
				}
			}
			
			if(ctr == 2400)
			{
				if(!isInPast && !TimeTraveler.vars.getIsInFuture())
				{
					mechanics.saveTime(mc.getIntegratedServer(), mc, copyFile);
					mechanics.beginPastRecording(event.player, event.player.getDisplayName());
					mechanics.beginPastRecording(event.player, event.player.getDisplayName());

				}
				ctr = 0;
			}
			if(!hasInitRun)
			{
				hasInitRun = true;
				mechanics.firstTime(mc.getIntegratedServer(), mc);
				mechanics.saveTime(mc.getIntegratedServer(), mc, copyFile);
			}
			if(!hasRun)
			{
				hasRun = true;
				mechanics.beginPastRecording(event.player, event.player.getDisplayName());

			}
			if(isInPast)
			{	
				if(!TimeTraveler.vars.getIsSpawnedPastPlayer())
				{
					mechanics.replayPast(event.player);
					TimeTraveler.vars.setIsSpawnedPastPlayer(true);
				}
				if(TimeTraveler.vars.getNextSet())
				{

				}
				count++;
				if(paradoxLevel < 0)
				{
					paradoxLevel = 0;
				}
				if(paradoxLevel <= 128)
				{
					if(event.player.isPotionActive(Potion.invisibility))
					{
						invisPotTime++;
						if(invisPotTime == 10)
						{
							paradoxLevel = paradoxLevel - 5;
							invisPotTime = 0;
						}
					}
					if(event.player.isSneaking())
					{
						sneakTime++;
						if(sneakTime == 30)
						{
							paradoxLevel = paradoxLevel - 2;
							sneakTime = 0;
						}
					}
				}
				else
				{

					mechanics.returnToPresent(mc, paradoxLevel, mc.getIntegratedServer());
					mobsInitSpawned = false;
					isInPast = false;
					TimeTraveler.vars.setNextSet(true);
				}
			}
			if(isInPast)
			{
				if(count == 20)
				{
					seconds--;
					count = 0;
				}
				if(seconds == 0)
				{
					minutes--;
					seconds = 60;
				}
				if(minutes == 0)
				{
					text = "Time Remaining: " + seconds + " Seconds";
				}
				if(minutes <= 0 && seconds <= 1)
				{
					mechanics.outOfTime(mc, mc.getIntegratedServer(), text);
					mobsInitSpawned = false;
					isInPast = false;
					TimeTraveler.vars.setNextSet(true);

				}
			}	
			TimeTraveler.vars.setParadoxAmt(paradoxLevel);

		}
	}
	@SubscribeEvent
	public void onRenderTick(TickEvent.RenderTickEvent event)
	{
		Minecraft mc = FMLClientHandler.instance().getClient();
		PastMechanics mechanics = new PastMechanics();

        if(mc.currentScreen == null)
        {
        	if(isInPast)
        	{
				mechanics.drawTimeTicker(mc, text);
        	}
    		mechanics.updateParadoxBar(mc, paradoxLevel);
        }

	}
}
