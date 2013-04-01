package timeTraveler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
	Minecraft minecraft = ModLoader.getMinecraftInstance();
	World w = minecraft.theWorld;
	
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
    	Minecraft m = ModLoader.getMinecraftInstance();
    	MinecraftServer ms = m.getIntegratedServer();
    	File locDirectory = new File(ModLoader.getMinecraftInstance().getMinecraftDir() + "/mods/TimeMod/past/" + ms.getWorldName() + "/playerLoc");
    	
    	File locs = new File(locDirectory + "/loc" + i + ".txt");
    	
    	try
    	{
    		if(locs.exists())
    		{
            	reader = new BufferedReader(new FileReader(locs));
            	
            	playerX = Integer.parseInt(reader.readLine());
            	playerY = Integer.parseInt(reader.readLine());
            	playerZ = Integer.parseInt(reader.readLine());
            	
            	distFromCoords = this.getDistance(playerX, playerY, playerZ);
            	if(distFromCoords >= rangeToPoint)
            	{
            		rangeToPoint = (float)distFromCoords + 3;
            	}
            	
            	pathentity = this.worldObj.getEntityPathToXYZ(this, playerX, playerY, playerZ, rangeToPoint, true, true, false, false);
            	super.setPathToEntity(pathentity);

            	String special = reader.readLine();
          		System.out.println(special);

            	if(special.equalsIgnoreCase("jump"))
            	{
            		m.thePlayer.sendChatMessage("JUMP");
            		System.out.println("JUMPING:  " + this.isJumping);
            		this.getJumpHelper().setJumping();
            		this.getJumpHelper().doJump();
            	}
            	if(special.equalsIgnoreCase(""))
            	{
            		System.out.println("SETSET");
                	super.setPathToEntity(pathentity);
            	}
    		}
    		
    	}
    	catch(IOException ex)
    	{
    		ex.printStackTrace();
    	}
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