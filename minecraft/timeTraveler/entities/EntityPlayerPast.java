package timeTraveler.entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import timeTraveler.mechanics.EntityMechanics;

import cpw.mods.fml.client.FMLClientHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;


/**
 * Entity for the Past Player
 * @author Charsmud
 *
 */
public class EntityPlayerPast extends EntityAnimal
{
	private PathEntity pathToEntity;
	
	int i;
	
	int playerX;
	int playerY;
	int playerZ;
	float rangeToPoint;
	
	double distFromCoords;
	private BufferedReader reader;
	
    public EntityPlayerPast(World par1World) {
		super(par1World);
		
		i = 1;
		rangeToPoint = 30F;
		
        this.entityType = "humanoid";
        this.texture = "/mob/pigzombie.png";

        //this.skinUrl = Minecraft.getMinecraft().thePlayer.skinUrl;
        this.fireResistance = 20;
        
	}
    /**
     * Gets amount of health (max)
     */
    public int getMaxHealth()
    {
        return 20;
    }

public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
    }

    protected String getLivingSound()
    {
        return "null";
    }

    protected String getHurtSound()
    {
        return "damage.hit1";
    }

    protected String getDeathSound()
    {
        return "null";
    }

    protected float getSoundVolume()
    {
        return 1.0F;
    }

    protected int getDropItemId()
    {
        return 0;
    }
    
    
    /**
     * Sets pathing to a specific point.  
     */
    @Override
    public void setPathToEntity(PathEntity pathentity)
    {
    	EntityMechanics mechanics = new EntityMechanics();
    	mechanics.setPathToEntity(this, pathentity, i);
    	/*Minecraft m = FMLClientHandler.instance().getClient();
    	MinecraftServer ms = m.getIntegratedServer();
    	File locDirectory = new File(m.getMinecraftDir() + "/mods/TimeMod/past/EntityLocations/" + ms.getWorldName() + "/Time " + i + ".epd");
    	
    	
    	try
    	{
    		if(locDirectory.exists())
    		{
    			String[] entityInformation = reader.readLine().split(",");
    			String entityName = entityInformation[0];
    			String entityX = entityInformation[1];
    			String entityY = entityInformation[2];
    			String entityZ = entityInformation[3];
    			
    			
            	 reader = new BufferedReader(new FileReader(locDirectory));
            	
            	playerX = Integer.parseInt(reader.readLine());
            	playerY = Integer.parseInt(reader.readLine());
            	playerZ = Integer.parseInt(reader.readLine());
            	
            	distFromCoords = this.getDistance(playerX, playerY, playerZ);
            	if(distFromCoords >= rangeToPoint)
            	{
            		rangeToPoint = (float)distFromCoords + 3;
            	}
            	
            	pathentity = this.worldObj.getEntityPathToXYZ(this, playerX, playerY, playerZ, rangeToPoint, true, true, false, false);

            	String special = reader.readLine();

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

    		}    		
    	}
    	catch(IOException ex)
    	{
    		ex.printStackTrace();
    	}*/
    }
    /**
     * Checks if path is complete (or near to)
     * @param pathentity
     * @return
     */
    public boolean checkPathComplete(PathEntity pathentity)
    {    	
    	if(this.getDistance(playerX, playerY, playerZ) <= 2.0F)
    	{
        	i++;
        	return true;
    	}
        else
        {
        	return false;
        }
    }
    /**
     * Runs once a tick, updates the entity and it's AI
     */
    public void onUpdate()
    {
    	super.onUpdate();
    	setPathToEntity(pathToEntity);
    	checkPathComplete(pathToEntity);
    }
	@Override
	/**
	 * UNUSED
	 */
	public EntityAgeable createChild(EntityAgeable var1) {
		return null;
	}
}