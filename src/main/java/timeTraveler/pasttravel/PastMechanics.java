package timeTraveler.pasttravel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.StatList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.storage.WorldInfo;
import timeTraveler.core.TimeTraveler;
import timeTraveler.entities.EntityParadoxHunter;
import timeTraveler.entities.EntityPlayerPast;
import timeTraveler.gui.GuiTimeTravel;
import timeTraveler.mechanics.ClientMethods;
import timeTraveler.mechanics.CopyFile;
import timeTraveler.ticker.Ticker;
import cpw.mods.fml.client.FMLClientHandler;

/**
 * Contains information about the new Mechanics for the past
 * @author Charsmud
 *
 */
public class PastMechanics
{
	  ArrayList<PastPlayThread> playThreads = new ArrayList();

	
	/**
	 * Updates Paradox Bar and Renders
	 * @param minecraft
	 * @param amtOfParadox
	 */
	public void updateParadoxBar(Minecraft minecraft, int amtOfParadox)
	{
	    ScaledResolution var5 = new ScaledResolution(minecraft, minecraft.displayWidth, minecraft.displayHeight);
	    
	    int var6 = var5.getScaledWidth();
	    int var7 = var5.getScaledHeight();
	    int var8 = var7 / var7 + 25;
	    
	    GuiIngame gig = new GuiIngame(minecraft);
	    
    	ResourceLocation texture = new ResourceLocation("charsmud_timetraveler", "textures/hud/newGUIElements.png");

		minecraft.renderEngine.bindTexture(texture);
	    gig.drawTexturedModalRect(var6 / 2 - 200, var8, 0, 0, 128, 8);
		gig.drawTexturedModalRect(var6 / 2 - 200, var8, 0, 8, amtOfParadox, 8);
	}

	/**
	 * Saves the first timezone when world is created
	 * @param ms
	 */
	public void firstTime(MinecraftServer ms, Minecraft minecraft)
	{
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
		       /* try
		        {
		            cf.copyDirectory(fi, moveTo);
		        }
		        catch(IOException ex)
		        {
		        	ex.printStackTrace();
		        }*/
		    }

		}
	}
	
	/**
	 * Creates a Past Timezone
	 * @param ms
	 * @param minecraft
	 * @param cf
	 * 
	 */
	public void saveTime(MinecraftServer ms, Minecraft minecraft, CopyFile cf)
	{
		if(ClientMethods.isSinglePlayer())
		{
			WorldInfo we = minecraft.theWorld.getWorldInfo();
			   
			File fil = new File(minecraft.mcDataDir,  "mods/TimeMod/past/" + ms.getWorldName());
			   
			if(!fil.exists())
			{
				fil.mkdir();
			}
			int counter = new File(minecraft.mcDataDir , "mods/TimeMod/past/" + ms.getWorldName()).listFiles().length + 1;
		    //int counter = counterstart - 1;

		    try
			{
				WorldInfo worldinfo = minecraft.theWorld.getWorldInfo();
			  
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
	}
	
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
		if(ClientMethods.isSinglePlayer())
		{
			String worldName = ms.getWorldName();
			String folderName = ms.getFolderName();
			WorldClient worldClient = minecraft.theWorld;
			GuiTimeTravel gtt = new GuiTimeTravel();
			EntityPlayer ep = minecraft.thePlayer;
			ep.setDead();
			
			paradoxLevel = 0;
			
	        minecraft.theWorld.sendQuittingDisconnectingPacket();
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
		}
	}
	
	/**
	 * Draws the time left button
	 * @param minecraft
	 * @param text
	 */
	public void drawTimeTicker(Minecraft minecraft, String text)
	{
		GuiButton button = new GuiButton(0,0,5, text);
		button.drawButton(minecraft,0,0);
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
		if(ClientMethods.isSinglePlayer())
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
		}
	}
	
	/**
	 * Spawns a Paradox Hunter.  
	 * @param mcServer
	 * @param minecraft
	 */
	public void spawnParadoxHunter(MinecraftServer mcServer, Minecraft minecraft)
	{
		Random rand = new Random();
		
		int additionX = rand.nextInt(25);
		int additionY = rand.nextInt(3);
		int additionZ = rand.nextInt(25);
		World world  = mcServer.getEntityWorld();
		
		if(world.getBlock((int)minecraft.thePlayer.posX + additionX, (int)minecraft.thePlayer.posY + additionY,(int) minecraft.thePlayer.posZ + additionZ) == Blocks.air)
		{
			System.out.println("Hurrah!");
			EntityParadoxHunter hunter = new EntityParadoxHunter(world);
			hunter.setPosition(minecraft.thePlayer.posX + additionX, minecraft.thePlayer.posY + additionY, minecraft.thePlayer.posZ + additionZ);
			world.spawnEntityInWorld(hunter);
		}
	}
	
	/**
	 * Starts recording the actions of a player.
	 * @param player
	 * @param pastName
	 */
	public void beginPastRecording(EntityPlayer player, String pastName)
	{
		TimeTravelerPastRecorder aRecorder = (TimeTravelerPastRecorder) TimeTraveler.instance.recordThreads.get(player);
		if (aRecorder != null) 
		{
			aRecorder.recordThread.capture = Boolean.valueOf(false);
			System.out.println("Stopped recording " + player.getDisplayName() + " to file " + aRecorder.fileName + ".ppd");
			TimeTraveler.instance.recordThreads.remove(player);
			return;
		}
		synchronized (TimeTraveler.instance.recordThreads) 
		{
			for (TimeTravelerPastRecorder ar : TimeTraveler.instance.recordThreads.values()) 
			{
				if (ar.fileName.equals(pastName.toLowerCase())) 
				{
					System.out.println("'" + ar.fileName + ".ppd' is already being recorded to?");
					return;
				}
			}
		}
		if (aRecorder == null) 
		{
			System.out.println("Started recording " + player.getDisplayName() + " to file " + pastName + ".ppd");
			TimeTravelerPastRecorder mcr = new TimeTravelerPastRecorder();
			mcr.fileName = pastName.toLowerCase();
			TimeTraveler.instance.recordThreads.put(player, mcr);
			mcr.recordThread = new PastRecordThread(player, pastName);

			return;
		}
	}
	
	public void replayPast(EntityPlayer player) 
	{
		System.out.println(player.getDisplayName());
		File file = new File(FMLClientHandler.instance().getClient().mcDataDir + "/mods/TimeMod/past/EntityLocations/" + MinecraftServer.getServer().getWorldName() + "/" + TimeTraveler.vars.getLastPastTimeSavedForWorld() + "/" + player.getDisplayName() + ".ppd");
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
		World world = player.worldObj;

		EntityPlayerPast entity = new EntityPlayerPast(world);

		entity.setPosition(x, y, z);

		entity.setSkinSource(player.getDisplayName());
		entity.setCustomNameTag(player.getDisplayName());
		entity.setAlwaysRenderNameTag(true);

		world.spawnEntityInWorld(entity);
		System.out.println("Spawned entity in world at " + x + ", " + y + ", " + z);
		for (Iterator<PastPlayThread> iterator = this.playThreads.iterator(); iterator.hasNext();) {
			PastPlayThread item = (PastPlayThread) iterator.next();
			if (!item.t.isAlive())
			{
				iterator.remove();
			}
		}
		this.playThreads.add(new PastPlayThread(entity, file.toString()));
	}
}