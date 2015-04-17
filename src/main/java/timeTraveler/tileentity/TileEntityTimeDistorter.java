package timeTraveler.tileentity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import timeTraveler.core.TimeTraveler;


public class TileEntityTimeDistorter extends TileEntity
{
	int xcoord = 0;
	int zcoord = 0;

	public TileEntityTimeDistorter()
	{
		
	}
	public void updateEntity()
	{
		World world = this.worldObj;
		int x = this.xCoord;
		int y = this.yCoord;
		int z = this.zCoord;
		
		updateWalls(world, x, y, z);
		System.out.println(xCoord + " to " + xcoord + " and " + zCoord + " to " + zcoord);
		
		if(world.isBlockIndirectlyGettingPowered(x, y, z))
		{
			List<Entity> entitiesToFreeze = world.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(this.xCoord,  0, this.zCoord, xcoord, 128, zcoord));
			
			if(entitiesToFreeze.size() > 0)
			{
				System.out.println(1);
				for(int i = 0; i < entitiesToFreeze.size(); i++)
				{
					Entity e = entitiesToFreeze.get(i);
					System.out.println(e);

					if(e instanceof EntityPlayer)
					{
						
					}
					else
					{	
						if(e.getEntityData().getBoolean("hasBeenTimeFreezed"))
						{
							System.out.println("Time Frozen!");

							e.setPosition(e.getEntityData().getDouble("timePosX"), e.getEntityData().getDouble("timePosY"), e.getEntityData().getDouble("timePosZ"));
							if(e instanceof EntityTNTPrimed)
							{
								((EntityTNTPrimed)e).fuse = 100000000;
							}
						}
						else
						{					
							e.getEntityData().setBoolean("hasBeenTimeFreezed", true);
							e.getEntityData().setDouble("timePosX", e.posX);
							e.getEntityData().setDouble("timePosY", e.posY);
							e.getEntityData().setDouble("timePosZ", e.posZ);
							if(e instanceof EntityTNTPrimed)
							{
								e.getEntityData().setBoolean("hasntSetFuse", true);
								e.getEntityData().setInteger("fuseLength", ((EntityTNTPrimed)e).fuse);
								System.out.println(e.getEntityData().getInteger("fuseLength"));
							}
						}
					}
				}
			}
		}
		else
		{
			List<Entity> entitiesToFreeze = world.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(this.xCoord, 0, this.zCoord, xcoord, 128, zcoord));
			for(Entity iteratedEntity : entitiesToFreeze)
			{
				if(iteratedEntity instanceof EntityTNTPrimed)
				{
					if(iteratedEntity.getEntityData().getBoolean("hasntSetFuse"))
					{
						iteratedEntity.getEntityData().setBoolean("hasntSetFuse", false);
						((EntityTNTPrimed)iteratedEntity).fuse = iteratedEntity.getEntityData().getInteger("fuseLength");
						System.out.println(iteratedEntity.getEntityData().getInteger("fuseLength"));

					}
				}
			}
		}
	}
	public void updateWalls(World world, int x, int y, int z)
	{
		boolean a = false, b = false;
		
		for(int xm = -128; xm < 128; xm++)
		{
			if(world.getTileEntity(x + xm, y, z) instanceof TileEntityMarker)
			{
				a = true;
				xcoord = x + xm;
			}
		}
		for(int zm = -128; zm < 128; zm++)
		{
			if(world.getTileEntity(x, y, z + zm) instanceof TileEntityMarker)
			{
				b = true;
				zcoord = z + zm;
			}
		}
		if((a == true && b == true) && (world.getTileEntity(xcoord, y, zcoord) instanceof TileEntityMarker) && world.isBlockIndirectlyGettingPowered(x, y, z))
		{
			
			for(int height = 0; height < 128; height++)
			{
				if(x > xcoord)
				{
					for(int xfill = x; xfill > xcoord; xfill--)
					{
						if(world.getBlock(xfill, height, z) == Blocks.air)
						{
							world.setBlock(xfill, height, z, TimeTraveler.timeField);
						}
						if(world.getBlock(xfill, height, zcoord) == Blocks.air)
						{
							world.setBlock(xfill, height, zcoord, TimeTraveler.timeField);
						}
					}
				}
				if(x < xcoord)
				{
					for(int xfill = x; xfill < xcoord; xfill++)
					{
						if(world.getBlock(xfill, height, z) == Blocks.air)
						{
							world.setBlock(xfill, height, z, TimeTraveler.timeField);
						}
						if(world.getBlock(xfill, height, zcoord) == Blocks.air)
						{
							world.setBlock(xfill, height, zcoord, TimeTraveler.timeField);
						}
					}
				}
				if(z > zcoord)
				{
					for(int zfill = z; zfill >zcoord; zfill--)
					{
						if(world.getBlock(x, height, zfill) == Blocks.air)
						{
							world.setBlock(x, height, zfill, TimeTraveler.timeField);
						}
						if(world.getBlock(xcoord, height, zfill) == Blocks.air)
						{
							world.setBlock(xcoord, height, zfill, TimeTraveler.timeField);
						}
					}
				}
				if(z < zcoord)
				{
					for(int zfill = z; zfill < zcoord; zfill++)
					{
						if(world.getBlock(x, height, zfill) == Blocks.air)
						{
							world.setBlock(x, height, zfill, TimeTraveler.timeField);
						}
						if(world.getBlock(xcoord, height, zfill) == Blocks.air)
						{
							world.setBlock(xcoord, height, zfill, TimeTraveler.timeField);
						}
					}
				}
				if(world.getBlock(xcoord, height, zcoord) == Blocks.air)
				{
					world.setBlock(xcoord, height, zcoord, TimeTraveler.timeField);
				}
			}
		}
		if(!world.isBlockIndirectlyGettingPowered(x, y, z))
		{
			for(int height = 0; height < 128; height++)
			{
				if(x > xcoord)
				{
					for(int xfill = x; xfill > xcoord; xfill--)
					{
						if(world.getBlock(xfill, height, z) == TimeTraveler.timeField)
						{
							world.setBlock(xfill, height, z, Blocks.air);
						}
						if(world.getBlock(xfill, height, zcoord) == TimeTraveler.timeField)
						{
							world.setBlock(xfill, height, zcoord, Blocks.air);
						}
					}
				}
				if(x < xcoord)
				{
					for(int xfill = x; xfill < xcoord; xfill++)
					{
						if(world.getBlock(xfill, height, z) == TimeTraveler.timeField)
						{
							world.setBlock(xfill, height, z, Blocks.air);
						}
						if(world.getBlock(xfill, height, zcoord) == TimeTraveler.timeField)
						{
							world.setBlock(xfill, height, zcoord, Blocks.air);
						}
					}
				}
				if(z > zcoord)
				{
					for(int zfill = z; zfill >zcoord; zfill--)
					{
						if(world.getBlock(x, height, zfill) == TimeTraveler.timeField)
						{
							world.setBlock(x, height, zfill, Blocks.air);
						}
						if(world.getBlock(xcoord, height, zfill) == TimeTraveler.timeField)
						{
							world.setBlock(xcoord, height, zfill, Blocks.air);
						}
					}
				}
				if(z < zcoord)
				{
					for(int zfill = z; zfill < zcoord; zfill++)
					{
						if(world.getBlock(x, height, zfill) == TimeTraveler.timeField)
						{
							world.setBlock(x, height, zfill, Blocks.air);
						}
						if(world.getBlock(xcoord, height, zfill) == TimeTraveler.timeField)
						{
							world.setBlock(xcoord, height, zfill, Blocks.air);
						}
					}
				}
				if(world.getBlock(xcoord, height, zcoord) == TimeTraveler.timeField)
				{
					world.setBlock(xcoord, height, zcoord, Blocks.air);
				}
			}
		}

	}
}
