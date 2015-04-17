package timeTraveler.futuretravel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterFuture extends Teleporter
{
    private final WorldServer worldServerInstance;

    /** A private Random() function in Teleporter */
    private final Random random;
    private final LongHashMap field_85191_c = new LongHashMap();
    private final List field_85190_d = new ArrayList();

    public TeleporterFuture(WorldServer par1WorldServer)
    {
    	super(par1WorldServer);
        this.worldServerInstance = par1WorldServer;
        this.random = new Random(par1WorldServer.getSeed());
    }

    /**
     * Place an entity in a nearby portal, creating one if necessary.
     */
    @Override
	public void placeInPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
	{
		int var9 = MathHelper.floor_double(par1Entity.posX);
		int var10 = MathHelper.floor_double(par1Entity.posY) - 1;
		int var11 = MathHelper.floor_double(par1Entity.posZ);
		byte var12 = 1;
		byte var13 = 0;

		for (int var14 = -2; var14 <= 2; ++var14) 
		{
			for (int var15 = -2; var15 <= 2; ++var15) 
			{
				for (int var16 = -1; var16 < 3; ++var16) 
				{
					int var17 = var9 + var15 * var12 + var14 * var13;
					int var18 = var10 + var16;
					int var19 = var11 + var15 * var13 - var14 * var12;
					boolean var20 = var16 < 0;
					//this.worldServerInstance.setBlock(var17, var18, var19, var20 ? Block.obsidian.blockID : 0);
				}
			}
		}

		par1Entity.setLocationAndAngles((double) var9, (double) var10,	(double) var11, par1Entity.rotationYaw, 0.0F);
		par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;

	}

    /**
     * Place an entity in a nearby portal which already exists.
     */
    @Override
    public boolean placeInExistingPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
    {
    	return true;
    }
    @Override
    public boolean makePortal(Entity par1Entity)
    {
        return true;
    }

    @Override
    public void removeStalePortalLocations(long par1)
    {
        if (par1 % 100L == 0L)
        {
            Iterator var3 = this.field_85190_d.iterator();
            long var4 = par1 - 600L;

            while (var3.hasNext())
            {
                Long var6 = (Long)var3.next();
                PortalPosition var7 = (PortalPosition)this.field_85191_c.getValueByKey(var6.longValue());

                if (var7 == null || var7.lastUpdateTime < var4)
                {
                    var3.remove();
                    this.field_85191_c.remove(var6.longValue());
                }
            }
        }
    }
}
