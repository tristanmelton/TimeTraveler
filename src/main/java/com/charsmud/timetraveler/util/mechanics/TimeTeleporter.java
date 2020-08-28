package com.charsmud.timetraveler.util.mechanics;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TimeTeleporter extends Teleporter
{
	private final WorldServer world;
	private double x,y,z;
	
	public TimeTeleporter(WorldServer world, double x, double y, double z)
	{
		super(world);
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public void placeInPortal(Entity entityIn, float rotationYaw)
	{
		this.world.getBlockState(new BlockPos((int)this.x, (int)this.y, (int)this.z));
		entityIn.setPosition(this.x, this.y, this.z);
		
		int i = MathHelper.floor(entityIn.posX);
		int j = MathHelper.floor(entityIn.posY);
		int k = MathHelper.floor(entityIn.posZ);
		int l = 1;
		int i1 = 0;
		//Generate obsidian platform below
		/*
		for(int j1 = -2; j1 <=2; ++j1)
		{
			for(int k1 = -2; k1 < 2; ++k1)
			{
				for(int l1 = -1; l1 < 3; ++l1)
				{
					int i2 = i + k1 * 1 + j1 * 0;
					int j2 = j + l1;
					int k2 = k + k1 * 0 - j1 * 1;
					boolean flag = l1 < 0;
					this.world.setBlockState(new BlockPos(i2, j2, k2),  flag ? Blocks.OBSIDIAN.getDefaultState() : Blocks.AIR.getDefaultState());
				}
			}
		}*/
		entityIn.setLocationAndAngles((double)i, (double)j, (double)k, entityIn.rotationYaw, 0.0F);
		entityIn.motionX = 0.0D;
		entityIn.motionY = 0.0D;
		entityIn.motionZ = 0.0D;
	}
	public static void teleportToDimension(EntityPlayer player, int dimension, double x, double y, double z)
	{
		EntityPlayerMP entityPlayerMP = (EntityPlayerMP)player;
		MinecraftServer server = player.getEntityWorld().getMinecraftServer();
		WorldServer worldServer = server.getWorld(dimension);
		player.addExperienceLevel(0);
		
		if(worldServer == null || worldServer.getMinecraftServer() == null)
			throw new IllegalArgumentException("Dimension: " + dimension + "doesn't exist");
		worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension(entityPlayerMP, dimension, new TimeTeleporter(worldServer, x, y, z));
	}
}
