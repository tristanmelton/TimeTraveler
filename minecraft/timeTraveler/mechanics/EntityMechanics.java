package timeTraveler.mechanics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.client.FMLClientHandler;
 /**
  * Entity Mechanics
  * @author Charsmud
  *
  */
public class EntityMechanics
{
	/**
	 * Sets a path to coordinates
	 * @param livingEntity
	 * @param pathentity
	 * @param i
	 */
	public void setPathToEntity(EntityLiving livingEntity, PathEntity pathentity, int i)
	{
		float rangeToPoint = 30F;
    	Minecraft m = FMLClientHandler.instance().getClient();
    	MinecraftServer ms = m.getIntegratedServer();
    	
    	WorldClient worldClient = m.theWorld;
    	
    	File locDirectory = new File(m.getMinecraftDir() + "/mods/TimeMod/past/EntityLocations/" + ms.getWorldName() + "/Time " + i + ".epd");

    	
    	try
    	{
    		if(locDirectory.exists())
    		{
    	    	BufferedReader reader = new BufferedReader(new FileReader(locDirectory));
    			
    			String[] entityInformation = reader.readLine().split(",");
    			String entityName = entityInformation[0];
    			int entityX = Integer.parseInt(entityInformation[1]);
    			int entityY = Integer.parseInt(entityInformation[2]);
    			int entityZ = Integer.parseInt(entityInformation[3]);
    		            	
            	//playerX = Integer.parseInt(reader.readLine());
            	//playerY = Integer.parseInt(reader.readLine());
            	//playerZ = Integer.parseInt(reader.readLine());
            	
            	double distFromCoords = livingEntity.getDistance(entityX, entityY, entityZ);
            	if(distFromCoords >= rangeToPoint)
            	{
            		rangeToPoint = (float)distFromCoords + 3;
            	}
            	
            	pathentity = worldClient.getEntityPathToXYZ(livingEntity, entityX, entityY, entityZ, rangeToPoint, true, true, false, false);
            	reader.close();
            	/*String special = reader.readLine();

            	if(special != null)
            	{
                	System.out.println(special);

                	if(special.equalsIgnoreCase("jump"))
                	{
                		m.thePlayer.sendChatMessage("JUMP");
                		this.getJumpHelper().setJumping();
                		this.getJumpHelper().doJump();
                	}
                	if(special.equalsIgnoreCase("sneak"))
                	{
                		this.setSneaking(true);
                	}
                	if(special.equalsIgnoreCase(""))
                	{
                		System.out.println("SETSET");
                    	super.setPathToEntity(pathentity);
                	}
            	}
            	super.setPathToEntity(pathentity);
	*/
    		}	
    	}
    	catch(IOException ex)
    	{
    		ex.printStackTrace();
    	}

	}
}
