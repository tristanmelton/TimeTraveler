package timeTraveler.ticker;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import timeTraveler.core.TimeTraveler;
import timeTraveler.entities.EntityPlayerPast;
import timeTraveler.gui.GuiTimeTravel;
import timeTraveler.mechanics.CopyFile;
import timeTraveler.mechanics.EntityMechanics;
import timeTraveler.pasttravel.PastMechanics;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

/**
 * Ticker
 * @author Charsmud
 *
 */
public class TickerClient implements ITickHandler 
{

	public int ctr;
	public int ct;
	public int count;
	
	public static int paradoxLevel;
	
	
	public static int seconds = 1;
	public static int minutes = 5;
	
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
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
	}
	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		paradoxLevel = TimeTraveler.vars.getParadoxAmt();
		
		if(type.equals(EnumSet.of(TickType.RENDER)))
		{
			onRenderTick();
		}
		else
			if(type.equals(EnumSet.of(TickType.CLIENT)))
			{
			     GuiScreen curScreen = Minecraft.getMinecraft().currentScreen;
			     if(curScreen != null)
			     {
			          onTickInGui(FMLClientHandler.instance().getClient(), curScreen);
			     }
			     else 
			     {
			    	 onTickInGame(FMLClientHandler.instance().getClient());
			     }

			}
	}
	private void onTickInGame(Minecraft mc)
	{
		ctr++;
		ct++;
		
		hasInitRun = (new File(mc.mcDataDir + "/mods/TimeMod/past/" + FMLClientHandler.instance().getServer().getWorldName())).exists();


		PastMechanics mechanics = new PastMechanics();
		
	    text  = "Time Remaining: " + minutes + " Minute, " + seconds + " Seconds";

		isInPast = GuiTimeTravel.isInPast;		
		if(!isInPast && !TimeTraveler.vars.getIsInFuture())
		{
			TimeTraveler.vars.setIsSpawnedPastPlayer(false);
			if(mc.thePlayer.isSneaking())
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
		
		if(ctr == 5140)
		{
			if(!isInPast && !TimeTraveler.vars.getIsInFuture())
			{
				mechanics.saveTime(mc.getIntegratedServer(), mc, copyFile);
				mechanics.beginPastRecording(FMLClientHandler.instance().getClient().thePlayer, FMLClientHandler.instance().getClient().thePlayer.getDisplayName());
				mechanics.beginPastRecording(FMLClientHandler.instance().getClient().thePlayer, FMLClientHandler.instance().getClient().thePlayer.getDisplayName());

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
			mechanics.beginPastRecording(FMLClientHandler.instance().getClient().thePlayer, FMLClientHandler.instance().getClient().thePlayer.getDisplayName());

		}
		if(isInPast)
		{	
			if(!TimeTraveler.vars.getIsSpawnedPastPlayer())
			{
				mechanics.replayPast(mc.thePlayer);
				TimeTraveler.vars.setIsSpawnedPastPlayer(true);
			}
			if(TimeTraveler.vars.getNextSet())
			{
				/*if(mobsInitSpawned)
				{
					File allEntityData = new File(mc.mcDataDir + "/mods/TimeMod/past/EntityLocations/" + FMLClientHandler.instance().getServer().getWorldName() + "/" + TimeTraveler.vars.getPastTime() + ".epd");
					
					try 
					{
						BufferedReader reader = new BufferedReader(new FileReader(allEntityData));
						String line;
						for(int i = 0; i < pathFileLine + 1; i++)
						{
							line = reader.readLine();
						}
						while (((line = reader.readLine()) != null) && TimeTraveler.vars.getNextSet()) 
						{
							pathFileLine++;
			                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
			                DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);
		
								
			                String[] entityData = line.split(",");
											
							String entityName = entityData[0];
							int entityX = Integer.parseInt(entityData[1]);
							int entityY = Integer.parseInt(entityData[2]);
							int entityZ = Integer.parseInt(entityData[3]);
							String uuidEntityUniqueId = entityData[4];	
							
							dataoutputstream.writeUTF(entityName);
							dataoutputstream.writeInt(entityX);
							dataoutputstream.writeInt(entityY);
							dataoutputstream.writeInt(entityZ);
							dataoutputstream.writeUTF(uuidEntityUniqueId);	
							
		                    PacketDispatcher.sendPacketToServer(new Packet250CustomPayload("entitypathupdate", bytearrayoutputstream.toByteArray()));
						}
						reader.close();	
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
					TimeTraveler.vars.setNextSet(true);

				}
				/*if(!mobsInitSpawned)
				{
					File allEntityData = new File(FMLClientHandler.instance().getClient().getMinecraftDir() + "/mods/TimeMod/past/EntityLocations/" + FMLClientHandler.instance().getServer().getWorldName() + "/" + TimeTraveler.vars.getPastTime() + ".epd");
					
					try 
					{
						BufferedReader reader = new BufferedReader(new FileReader(allEntityData));
						String line;
						while (((line = reader.readLine()) != null) && TimeTraveler.vars.getNextSet()) 
						{
							pathFileLine++;
							if(line.equals("===================="))
							{
								TimeTraveler.vars.setNextSet(false);
							}
							else 
							{
			                    ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
			                    DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);
		
								
								String[] entityData = line.split(",");
											
								String entityName = entityData[0];
								int entityX = Integer.parseInt(entityData[1]);
								int entityY = Integer.parseInt(entityData[2]);
								int entityZ = Integer.parseInt(entityData[3]);
								String uuidEntityUniqueId = entityData[4];		
								
								dataoutputstream.writeUTF(entityName);
								dataoutputstream.writeInt(entityX);
								dataoutputstream.writeInt(entityY);
								dataoutputstream.writeInt(entityZ);
								dataoutputstream.writeUTF(uuidEntityUniqueId);
								
		                        PacketDispatcher.sendPacketToServer(new Packet250CustomPayload("entityspawn", bytearrayoutputstream.toByteArray()));
							}
						}
						reader.close();	
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
					mobsInitSpawned = true;
					TimeTraveler.vars.setNextSet(true);
				}*/
				
			}
			count++;
			if(paradoxLevel < 0)
			{
				paradoxLevel = 0;
			}
			if(paradoxLevel <= 128)
			{
				EntityPlayerSP eps = mc.thePlayer;
				if(eps.isPotionActive(Potion.invisibility))
				{
					invisPotTime++;
					if(invisPotTime == 10)
					{
						paradoxLevel = paradoxLevel - 5;
						invisPotTime = 0;
					}
				}
				if(eps.isSneaking())
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
	private void onTickInGui(Minecraft mc, GuiScreen gui)
	{
	}
	private void onRenderTick()
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
	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.CLIENT, TickType.RENDER);
	}
	@Override
	public String getLabel()
	{
		return "TickHandler.CLIENT";
	}
}
