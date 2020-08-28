package com.charsmud.timetraveler.util.mechanics.past;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;

import com.charsmud.timetraveler.TimeTraveler;
import com.charsmud.timetraveler.entities.EntityPlayerPast;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

/**
 * Contains information about the new Mechanics for the past
 * @author Charsmud
 *
 */
//TODO: Implement me

public class PastMechanics
{
	/**
	 * Saves the first timezone when world is created
	 * @param ms
	 */
	public void firstTime(MinecraftServer ms, Minecraft minecraft)
	{
		/*
		if(ClientMethods.isSinglePlayer())
		{
			CopyFile cf = new CopyFile();
		    File beginningOfWorld = new File(minecraft.mcDataDir + "/mods/TimeMod/present/" + ms.getWorldName());
		    
		    if(beginningOfWorld.length() == 0)
		    {
		    	File initWorldGen = new File(minecraft.mcDataDir + "/mods/TimeMod/past/" + ms.getWorldName());
		    	initWorldGen.mkdirs();
			    TimeTraveler.vars.setLastPastTimeSavedForWorld("Time 001");

		        //File fi = new File(minecraft.mcDataDir + "/saves/" + ms.getWorldName() + "/region");
		        //File moveTo = new File(minecraft.mcDataDir  + "/mods/TimeMod/past/" + ms.getWorldName() + "/Time 001");

		    }

		}*/
	}
	
	/**
	 * Creates a Past Timezone
	 * @param ms
	 * @param minecraft
	 * @param cf
	 * 
	 */
	/*public void saveTime(MinecraftServer ms, Minecraft minecraft, CopyFile cf)
	{
		if(ClientMethods.isSinglePlayer())
		{
			WorldInfo we = minecraft.world.getWorldInfo();
			   
			File fil = new File(minecraft.mcDataDir,  "mods/TimeMod/past/" + ms.getWorldName());
			   
			if(!fil.exists())
			{
				fil.mkdir();
			}
			int counter = new File(minecraft.mcDataDir , "mods/TimeMod/past/" + ms.getWorldName()).listFiles().length + 1;
		    //int counter = counterstart - 1;

		    try
			{
				WorldInfo worldinfo = minecraft.world.getWorldInfo();
			  
				File fi = new File(minecraft.mcDataDir  + "\\saves\\" + ms.getWorldName() + "\\region");
				File f2 = new File (minecraft.mcDataDir  + "\\mods\\TimeMod\\past\\" + ms.getWorldName());
				f2.mkdir();

				String fname = minecraft.mcDataDir + "\\mods\\TimeMod\\past\\" + ms.getWorldName() + "\\Time ";
				//counter = counter + 1;
				fname = fname.concat(String.format("%03d",counter));
			          
				File directoryToMoveTo = new File(fname);
			                		   
				cf.copyDirectory(fi, directoryToMoveTo);
				System.out.println("Created a time!");       
				//File destination = new File(Minecraft.getMinecraftDir(), "mods/TimeMod/past");
				//File zipped = new File(Minecraft.getMinecraftDir(), "mods/TimeMod/past/w1.zip");
			           
				// copyfiletime.unzip(zipped, destination);  
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}*/
	
	/**
	 * Return the player to the present from past.
	 * @param minecraft
	 * @param paradoxLevel
	 * @param ms
	 * @param minutes
	 * @param seconds
	 */
	public void returnToPresent(Minecraft minecraft, int paradoxLevel, MinecraftServer ms)
	{
		/*if(ClientMethods.isSinglePlayer())
		{
			String worldName = ms.getWorldName();
			String folderName = ms.getFolderName();
			WorldClient worldClient = minecraft.world;
			GuiTimeTravel gtt = new GuiTimeTravel();
			EntityPlayer ep = minecraft.thePlayer;
			ep.setDead();
			
			paradoxLevel = 0;
			
	        minecraft.world.sendQuittingDisconnectingPacket();
	        minecraft.loadWorld((WorldClient)null);

	        //minecraft.statFileWriter.readStat(StatList.leaveGameStat, 1);
	        //minecraft.displayGuiScreen(new GuiMainMenu());
	        
	        
	        File present = new File(minecraft.mcDataDir + "/mods/TimeMod/present/" + ms.getWorldName());
	        File worldFileDest = GuiTimeTravel.staticsource;
	        File worldFile = new File(minecraft.mcDataDir + "/saves/" + ms.getWorldName() + "/region");
	        
	        System.out.println(present);
	        System.out.println(worldFileDest);
	        System.out.println(worldFile);
	        try {
	            if (minecraft.getSaveLoader().canLoadWorld(worldName))
	            { 	
		        	Thread.sleep(3000);
		        	CopyFile.moveMultipleFiles(worldFile, worldFileDest);
		            Thread.sleep(2000);
		        	CopyFile.moveMultipleFiles(present, worldFile);
		            gtt.isInPast = false;
		            Ticker.minutes = 2;
		            Ticker.seconds = 1;
				    Ticker.paradoxLevel = 0;
			        minecraft.launchIntegratedServer(folderName, worldName, (WorldSettings)null);
	            }
	            else
	            {
	            	System.out.println("COULD NOT LOAD WORLD!  :(");
	            }

	        }
	        catch (Exception ex) {
	        	ex.printStackTrace();
	        }
		}*/
	}
	
	/**
	 * Player ran out of time in the past, sends player to present
	 * @param minecraft
	 * @param ms
	 * @param minutes
	 * @param seconds
	 * @param text
	 */
	public void outOfTime(Minecraft minecraft, MinecraftServer ms, String text)
	{
		/*if(ClientMethods.isSinglePlayer())
		{
			GuiTimeTravel gtt = new GuiTimeTravel();
			WorldClient worldClient = minecraft.theWorld;
			String worldName = ms.getWorldName();
			String folderName = ms.getFolderName();
			
			
		    minecraft.theWorld.sendQuittingDisconnectingPacket();
		    minecraft.loadWorld((WorldClient)null);

		    minecraft.displayGuiScreen(new GuiMainMenu());
		           
		    File present = new File(minecraft.mcDataDir + "/mods/TimeMod/present/" + ms.getWorldName());
		    File worldFileDest = GuiTimeTravel.staticsource;
		    File worldFile = new File(minecraft.mcDataDir + "/saves/" + ms.getWorldName() + "/region");
		    
		    try 
		    {
		        if (minecraft.getSaveLoader().canLoadWorld(worldName))
		        {
				    Thread.sleep(3000);
				    CopyFile.moveMultipleFiles(worldFile, worldFileDest);
				    Thread.sleep(1000);
				    CopyFile.moveMultipleFiles(present, worldFile);
				    gtt.isInPast = false;
				    Ticker.minutes = 1;
				    Ticker.seconds = 10;
				    minecraft.launchIntegratedServer(folderName, worldName, (WorldSettings)null);
				    
			        //minecraft.loadWorld(worldClient);
	            }
		    }  
		    catch (Exception ex)
		    {
		    	ex.printStackTrace();
			}	
		}*/
	}
	

	
	/**
	 * Starts recording the actions of a player.
	 * @param player
	 * @param pastName
	 */
	public void beginPastRecording(EntityPlayer player)
	{
		TimeTravelerPastRecorder aRecorder = (TimeTravelerPastRecorder) TimeTraveler.instance.recordThreads.get(player);
		synchronized (TimeTraveler.instance.recordThreads) 
		{
			if (aRecorder != null) 
			{
				System.out.println("'" + aRecorder.fileName + ".ppd' is already being recorded to?");
				return;
			}
			else
			{
				String name = player.getDisplayNameString();
				System.out.println("Started recording " + name + " to file " + name + ".ppd");
				TimeTravelerPastRecorder mcr = new TimeTravelerPastRecorder();
				mcr.fileName = name;
				mcr.recordThread = new PastRecordThread(player);
				TimeTraveler.instance.recordThreads.put(player, mcr);

				return;
			}

		}
	}
	public void stopPastRecording(EntityPlayer player)
	{
		TimeTravelerPastRecorder recorder = (TimeTravelerPastRecorder) TimeTraveler.instance.recordThreads.get(player);
		if(recorder != null)
		{
			recorder.recordThread.capture = Boolean.valueOf(false);
			System.out.println("Stopped recording " + player.getDisplayNameString() + " to file " + recorder.fileName + ".ppd");
			TimeTraveler.instance.recordThreads.remove(player);	
		}
		else
			System.out.println("Player is not being recorded!");
	}
	
	public void replayPast(EntityPlayer player) 
	{
		System.out.println(player.getDisplayName());
		File file = new File(FMLClientHandler.instance().getClient().mcDataDir + "/mods/TimeMod/past/EntityLocations/" + Minecraft.getMinecraft().getIntegratedServer().getWorldName() + "/" + player.getDisplayName() + ".ppd");
		if (!file.exists()) 
		{
			System.out.println("Can't find " + file + ".ppd past file!");
			return;
		}
		double x = 0.0D;
		double y = 0.0D;
		double z = 0.0D;
		try 
		{
			RandomAccessFile in = new RandomAccessFile(file, "r");
			short magic = in.readShort();
			if (magic != -4885)
			{
				System.out.println(file + " isn't a .ppd file.");
				in.close();
				return;
			}
			long timestamp = in.readLong();
			float yaw = in.readFloat();
			float pitch = in.readFloat();
			x = in.readDouble();
			y = in.readDouble();
			z = in.readDouble();

			in.close();
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		World world = player.world;

		EntityPlayerPast entity = new EntityPlayerPast(world);

		entity.setPosition(x, y, z);

		entity.setSkinSource(player.getDisplayNameString());
		entity.setCustomNameTag(player.getDisplayNameString());
		entity.setAlwaysRenderNameTag(true);

		world.spawnEntity(entity);
		System.out.println("Spawned entity in world at " + x + ", " + y + ", " + z);
		for (Iterator<PastPlayThread> iterator = TimeTraveler.instance.playThreads.iterator(); iterator.hasNext();) {
			PastPlayThread item = (PastPlayThread) iterator.next();
			if (!item.t.isAlive())
			{
				iterator.remove();
			}
		}
		TimeTraveler.instance.playThreads.add(new PastPlayThread(entity, file.toString()));
	}
}