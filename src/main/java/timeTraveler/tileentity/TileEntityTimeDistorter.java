package timeTraveler.tileentity;

import java.util.List;

import timeTraveler.core.TimeTraveler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;


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
		
		if(world.isBlockIndirectlyGettingPowered(x, y, z))
		{
			List<Entity> entitiesToFreeze = world.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(this.xCoord,  0, this.zCoord, xcoord, 128, zcoord));
			if(entitiesToFreeze.size() > 0)
			{
				for(int i = 0; i < entitiesToFreeze.size(); i++)
				{
					Entity e = entitiesToFreeze.get(i);
					System.out.println(e);
					if(e instanceof EntityPlayer)
					{
						
					}
					else
					{
						e.setVelocity(0.0D, 0.0D, 0.0D);
						e.motionX *= 0.1D;
						e.motionY *= 0.1D;
						e.motionZ *= 0.1D;
						e.setPosition(e.posX, e.posY, e.posZ);
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
			if(world.getBlockTileEntity(x + xm, y, z) instanceof TileEntityMarker)
			{
				a = true;
				xcoord = x + xm;
			}
		}
		for(int zm = -128; zm < 128; zm++)
		{
			if(world.getBlockTileEntity(x, y, z + zm) instanceof TileEntityMarker)
			{
				b = true;
				zcoord = z + zm;
			}
		}
		if((a == true && b == true) && (world.getBlockTileEntity(xcoord, y, zcoord) instanceof TileEntityMarker) && world.isBlockIndirectlyGettingPowered(x, y, z))
		{
			
			for(int height = 0; height < 128; height++)
			{
				if(x > xcoord)
				{
					for(int xfill = x; xfill > xcoord; xfill--)
					{
						if(world.getBlockId(xfill, height, z) == 0)
						{
							world.setBlock(xfill, height, z, TimeTraveler.timeField.blockID);
						}
						if(world.getBlockId(xfill, height, zcoord) == 0)
						{
							world.setBlock(xfill, height, zcoord, TimeTraveler.timeField.blockID);
						}
					}
				}
				if(x < xcoord)
				{
					for(int xfill = x; xfill < xcoord; xfill++)
					{
						if(world.getBlockId(xfill, height, z) == 0)
						{
							world.setBlock(xfill, height, z, TimeTraveler.timeField.blockID);
						}
						if(world.getBlockId(xfill, height, zcoord) == 0)
						{
							world.setBlock(xfill, height, zcoord, TimeTraveler.timeField.blockID);
						}
					}
				}
				if(z > zcoord)
				{
					for(int zfill = z; zfill >zcoord; zfill--)
					{
						if(world.getBlockId(x, height, zfill) == 0)
						{
							world.setBlock(x, height, zfill, TimeTraveler.timeField.blockID);
						}
						if(world.getBlockId(xcoord, height, zfill) == 0)
						{
							world.setBlock(xcoord, height, zfill, TimeTraveler.timeField.blockID);
						}
					}
				}
				if(z < zcoord)
				{
					for(int zfill = z; zfill < zcoord; zfill++)
					{
						if(world.getBlockId(x, height, zfill) == 0)
						{
							world.setBlock(x, height, zfill, TimeTraveler.timeField.blockID);
						}
						if(world.getBlockId(xcoord, height, zfill) == 0)
						{
							world.setBlock(xcoord, height, zfill, TimeTraveler.timeField.blockID);
						}
					}
				}
				if(world.getBlockId(xcoord, height, zcoord) == 0)
				{
					world.setBlock(xcoord, height, zcoord, TimeTraveler.timeField.blockID);
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
						if(world.getBlockId(xfill, height, z) == TimeTraveler.timeField.blockID)
						{
							world.setBlock(xfill, height, z, 0);
						}
						if(world.getBlockId(xfill, height, zcoord) == TimeTraveler.timeField.blockID)
						{
							world.setBlock(xfill, height, zcoord, 0);
						}
					}
				}
				if(x < xcoord)
				{
					for(int xfill = x; xfill < xcoord; xfill++)
					{
						if(world.getBlockId(xfill, height, z) == TimeTraveler.timeField.blockID)
						{
							world.setBlock(xfill, height, z, 0);
						}
						if(world.getBlockId(xfill, height, zcoord) == TimeTraveler.timeField.blockID)
						{
							world.setBlock(xfill, height, zcoord, 0);
						}
					}
				}
				if(z > zcoord)
				{
					for(int zfill = z; zfill >zcoord; zfill--)
					{
						if(world.getBlockId(x, height, zfill) == TimeTraveler.timeField.blockID)
						{
							world.setBlock(x, height, zfill, 0);
						}
						if(world.getBlockId(xcoord, height, zfill) == TimeTraveler.timeField.blockID)
						{
							world.setBlock(xcoord, height, zfill, 0);
						}
					}
				}
				if(z < zcoord)
				{
					for(int zfill = z; zfill < zcoord; zfill++)
					{
						if(world.getBlockId(x, height, zfill) == TimeTraveler.timeField.blockID)
						{
							world.setBlock(x, height, zfill, 0);
						}
						if(world.getBlockId(xcoord, height, zfill) == TimeTraveler.timeField.blockID)
						{
							world.setBlock(xcoord, height, zfill, 0);
						}
					}
				}
				if(world.getBlockId(xcoord, height, zcoord) == TimeTraveler.timeField.blockID)
				{
					world.setBlock(xcoord, height, zcoord, 0);
				}
			}
		}

	}
}
