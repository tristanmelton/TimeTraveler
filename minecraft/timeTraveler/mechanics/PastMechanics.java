package timeTraveler.mechanics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.lwjgl.opengl.GL11;

import timeTraveler.gui.GuiTimeTravel;

import cpw.mods.fml.client.FMLClientHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraft.stats.StatList;
import net.minecraft.world.storage.WorldInfo;

/**
 * Contains information about the new Mechanics for the past
 * @author Charsmud
 *
 */
public class PastMechanics {
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
	    
	    GL11.glBindTexture(GL11.GL_TEXTURE_2D, minecraft.renderEngine.getTexture("/timeTraveler/textureMap/newGUIElements.png"));
		gig.drawTexturedModalRect(var6 / 2 - 200, var8, 0, 0, 128, 8);
		gig.drawTexturedModalRect(var6 / 2 - 200, var8, 0, 8, amtOfParadox, 8);
	}
	/**
	 * Adds a Player Location
	 * @param ms
	 * @param minecraft
	 */
	public void addPlayerLoc(MinecraftServer ms, Minecraft minecraft, String special)
	{
		File playerLoc = new File(minecraft.getMinecraftDir() + "/mods/TimeMod/past/" + ms.getWorldName() + "/playerLoc");
	    
	    playerLoc(playerLoc, minecraft, special);

	}
	/**
	 * Actual player loc saving method, get's player's x, y and z and writes to a file. (Helper Method)
	 * @param destToSave
	 * @param minecraft
	 */
	public void playerLoc(File destToSave, Minecraft minecraft, String special)
	{
		EntityPlayer ep = minecraft.thePlayer;
		
		int playerX = (int)ep.posX;
		int playerY = (int)ep.posY;
		int playerZ = (int)ep.posZ;
		
		System.out.println(playerX + " " + playerY + " " + playerZ);
		
		if(!destToSave.exists())
		{
			destToSave.mkdirs();
			
		}
		File nextLoc = new File(destToSave + "/loc" + ((destToSave.listFiles().length) + 1) + ".txt");
		try
		{			
			BufferedWriter out = new BufferedWriter(new FileWriter(nextLoc));
			out.write(Integer.toString(playerX));
			out.newLine();
			out.write(Integer.toString(playerY));
			out.newLine();
			out.write(Integer.toString(playerZ));
			out.newLine();
			out.write(special);
			out.flush();
			out.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	/**
	 * Saves the first timezone when world is created
	 * @param ms
	 * @param minecraft
	 */
	public void firstTime(MinecraftServer ms, Minecraft minecraft)
	{
		CopyFile cf = new CopyFile();
	    File beginningOfWorld = new File(minecraft.getMinecraftDir() + "/mods/TimeMod/present/" + ms.getWorldName());
	    
	    if(beginningOfWorld.length() == 0)
	    {
	    	File initWorldGen = new File(minecraft.getMinecraftDir() + "/mods/TimeMod/past/" + ms.getWorldName());
	    	initWorldGen.mkdirs();
	        File fi = new File(minecraft.getMinecraftDir() + "/saves/" + ms.getWorldName() + "/region");
	        File moveTo = new File(minecraft.getMinecraftDir() + "/mods/TimeMod/past/" + ms.getWorldName() + "/Time 001");
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
	/**
	 * Creates a Past Timezone
	 * @param ms
	 * @param minecraft
	 * @param cf
	 */
	public void saveTime(MinecraftServer ms, Minecraft minecraft, CopyFile cf)
	{
		WorldInfo we = minecraft.theWorld.getWorldInfo();
		   
		File fil = new File(minecraft.getMinecraftDir(), "mods/TimeMod/past/" + ms.getWorldName());
		   
		if(!fil.exists())
		{
			fil.mkdir();
		}
		int counterstart = new File(minecraft.getMinecraftDir(), "mods/TimeMod/past/" + ms.getWorldName()).listFiles().length;
	    int counter = counterstart;
		          
		try
		{
			WorldInfo worldinfo = minecraft.theWorld.getWorldInfo();
		  
			File fi = new File(minecraft.getMinecraftDir() + "\\saves\\" + ms.getWorldName() + "\\region");
			File f2 = new File (minecraft.getMinecraftDir() + "\\mods\\TimeMod\\past\\" + ms.getWorldName());
			f2.mkdir();

			String fname = minecraft.getMinecraftDir() + "\\mods\\TimeMod\\past\\" + ms.getWorldName() + "\\Time ";
			counter = counter + 1;
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
	/**
	 * Return the player to the present from past.
	 * @param minecraft
	 * @param paradoxLevel
	 * @param ms
	 * @param minutes
	 * @param seconds
	 */
	public void returnToPresent(Minecraft minecraft, int paradoxLevel, MinecraftServer ms, int minutes, int seconds)
	{
		GuiTimeTravel gtt = new GuiTimeTravel();
		EntityPlayer ep = minecraft.thePlayer;
		ep.setDead();
		
		paradoxLevel = 0;
		
		
        minecraft.statFileWriter.readStat(StatList.leaveGameStat, 1);
        minecraft.theWorld.sendQuittingDisconnectingPacket();
        minecraft.loadWorld((WorldClient)null);
        //minecraft.displayGuiScreen(new GuiMainMenu());
        
        //minecraft.loadWorld(w);
        
        File present = new File(minecraft.getMinecraftDir() + "/mods/TimeMod/present/" + ms.getWorldName());
        File worldFileDest = GuiTimeTravel.staticsource;
        File worldFile = new File(minecraft.getMinecraftDir() + "/saves/" + ms.getWorldName() + "/region");
        
        System.out.println(present);
        System.out.println(worldFileDest);
        System.out.println(worldFile);
        try {
        	Thread.sleep(3000);
        	CopyFile.moveMultipleFiles(worldFile, worldFileDest);
            Thread.sleep(2000);
        	CopyFile.moveMultipleFiles(present, worldFile);
            gtt.isInPast = false;
        }
        catch (Exception ex) {
        	ex.printStackTrace();
        }
        minutes = 1;
        seconds = 30;   
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
	public void outOfTime(Minecraft minecraft, MinecraftServer ms, int minutes, int seconds, String text)
	{
		System.out.println(1);
		GuiTimeTravel gtt = new GuiTimeTravel();
		System.out.println(2);
		text = "Time Remaining: " + seconds + " Seconds";	
		System.out.println(3);
	    minecraft.statFileWriter.readStat(StatList.leaveGameStat, 1);
	    minecraft.theWorld.sendQuittingDisconnectingPacket();
	    minecraft.loadWorld((WorldClient)null);
	    minecraft.displayGuiScreen(new GuiMainMenu());
	           
	    //minecraft.loadWorld(w);
	    File present = new File(minecraft.getMinecraftDir() + "/mods/TimeMod/present/" + ms.getWorldName());
	    File worldFileDest = GuiTimeTravel.staticsource;
	    File worldFile = new File(minecraft.getMinecraftDir() + "/saves/" + ms.getWorldName() + "/region");
	           
	    System.out.println(worldFileDest + " " + gtt.getSaveNumber());
	    System.out.println(present);
	    System.out.println(worldFileDest);
	    System.out.println(worldFile);
	    
	    try 
	    {
	    	Thread.sleep(3000);
	    	CopyFile.moveMultipleFiles(worldFile, worldFileDest);
	    	Thread.sleep(1000);
	    	CopyFile.moveMultipleFiles(present, worldFile);
	    	gtt.isInPast = false;
	    }  
	    catch (Exception ex)
	    {
	    	ex.printStackTrace();
		}	
    	minutes = 1;
    	seconds = 10;

	}
}
