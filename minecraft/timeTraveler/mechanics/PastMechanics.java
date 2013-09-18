package timeTraveler.mechanics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.StatList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.storage.WorldInfo;

import org.lwjgl.opengl.GL11;

import timeTraveler.gui.GuiTimeTravel;
import timeTraveler.ticker.TickerClient;
import cpw.mods.fml.client.FMLClientHandler;

/**
 * Contains information about the new Mechanics for the past
 * @author Charsmud
 *
 */
public class PastMechanics
{
	/**
	 * Updates Paradox Bar and Renders
	 * @param minecraft
	 * @param amtOfParadox
	 */
	public void updateParadoxBar(Minecraft minecraft, int amtOfParadox)
	{
	    ScaledResolution var5 = new ScaledResolution(minecraft.gameSettings, minecraft.displayWidth, minecraft.displayHeight);
	    
	    int var6 = var5.getScaledWidth();
	    int var7 = var5.getScaledHeight();
	    int var8 = var7 / var7 + 25;
	    
	    GuiIngame gig = new GuiIngame(minecraft);
	    
    	ResourceLocation texture = new ResourceLocation("charsmud_timetraveler", "textures/hud/newGUIElements.png");

	   // GL11.glBindTexture(GL11.GL_TEXTURE_2D, minecraft.renderEngine.func_110577_a(new ResourceLocation("/timeTraveler/textureMap/newGUIElements.png")));
		minecraft.renderEngine.bindTexture(texture);
	    gig.drawTexturedModalRect(var6 / 2 - 200, var8, 0, 0, 128, 8);
		gig.drawTexturedModalRect(var6 / 2 - 200, var8, 0, 8, amtOfParadox, 8);
	}
	/**
	 * adds an EntityLiving's name and coordinates to a List.
	 * @param par1EntityLiving
	 * @param par2List
	 */
	public void addEntityData(List<String> par2List)
	{
		
		List<EntityLiving> allEntities = FMLClientHandler.instance().getClient().theWorld.loadedEntityList;
		
		for(int i = 0; i < allEntities.size(); i++)
		{
			if(allEntities.get(i) instanceof EntityLiving)
			{
				String entityName = allEntities.get(i).getEntityName();
				int xCoord = (int) allEntities.get(i).posX;
				int yCoord = (int) allEntities.get(i).posY;
				int zCoord = (int) allEntities.get(i).posZ;
				UUID entityUniqueId = allEntities.get(i).getPersistentID();

				par2List.add(entityName + "," + xCoord + "," + yCoord + "," + zCoord + "," + entityUniqueId);
			}
		}
		//par2List.add("====================");
	}
	/**
	 * Saves the list input as a .epd file (entity position data).
	 * Also clears the list once it's done writing to the file.
	 * @param par1List
	 */
	public void saveEntityData(List<String> par1List, MinecraftServer par2MinecraftServer)
	{
		  FileWriter fstream;
		try
		{
			File init = new File(FMLClientHandler.instance().getClient().mcDataDir + "\\mods\\TimeMod\\past\\EntityLocations\\" + par2MinecraftServer.getWorldName());
			if(!init.exists())
			{
				init.mkdirs();
			}
			int fNumbers = new File(FMLClientHandler.instance().getClient().mcDataDir + "\\mods\\TimeMod\\past\\EntityLocations\\" + par2MinecraftServer.getWorldName()).listFiles().length + 1;
			
			String time = "Time ";
			time = time.concat(String.format("%03d",fNumbers));
			fstream = new FileWriter(FMLClientHandler.instance().getClient().mcDataDir + "\\mods\\TimeMod\\past\\EntityLocations\\" + par2MinecraftServer.getWorldName() + "\\" + time + ".epd");
			
			BufferedWriter out = new BufferedWriter(fstream);
			for(int i = 0; i < par1List.size(); i++)
			{
				out.write(par1List.get(i));
				out.newLine();

			}
			out.flush();
			out.close();
			par1List.clear();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

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
		        File fi = new File(minecraft.mcDataDir + "/saves/" + ms.getWorldName() + "/region");
		        File moveTo = new File(minecraft.mcDataDir  + "/mods/TimeMod/past/" + ms.getWorldName() + "/Time 001");
		        try
		        {
		            cf.copyDirectory(fi, moveTo);
		        }
		        catch(IOException ex)
		        {
		        	ex.printStackTrace();
		        }
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
			
			
	        minecraft.statFileWriter.readStat(StatList.leaveGameStat, 1);
	        minecraft.theWorld.sendQuittingDisconnectingPacket();
	        minecraft.loadWorld((WorldClient)null);
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
		            TickerClient.minutes = 1;
		            TickerClient.seconds = 10;
				    TickerClient.paradoxLevel = 0;
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
				    TickerClient.minutes = 1;
				    TickerClient.seconds = 10;
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
}
