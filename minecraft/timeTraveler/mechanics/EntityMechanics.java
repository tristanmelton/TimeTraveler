package timeTraveler.mechanics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
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
	 * @param entityAnimal
	 * @param pathentity
	 * @param i
	 */
	public int setPathToEntity(EntityAnimal entityAnimal, int i)
	{
		PathEntity pathentity = entityAnimal.getNavigator().getPath();
		if(pathentity.isFinished())
		{
			i++;
		}
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
    			String line;
    			while((line = reader.readLine()) != null)
    			{
	    	    	String[] entityInformation = reader.readLine().split(",");
	    			
	    			String entityName = entityInformation[0];
	    			int entityX = Integer.parseInt(entityInformation[1]);
	    			int entityY = Integer.parseInt(entityInformation[2]);
	    			int entityZ = Integer.parseInt(entityInformation[3]);
	    			
	            	double distFromCoords = entityAnimal.getDistance(entityX, entityY, entityZ);
	            	if(distFromCoords >= rangeToPoint)
	            	{
	            		rangeToPoint = (float)distFromCoords + 3;
	            	}
	            	if(entityName == entityAnimal.getEntityName())
	            	{            	
	            		pathentity = worldClient.getEntityPathToXYZ(entityAnimal, entityX, entityY, entityZ, rangeToPoint, true, true, false, false);
	                	entityAnimal.setPathToEntity(pathentity);
	            	}
	            	reader.close();
    			}
            	//livingEntity.setPathToEntity(livingEntity, pathentity, i);
            	return i;

    		}	
    	}
    	catch(IOException ex)
    	{
    		ex.printStackTrace();
    	}
		return i;

	}

}
