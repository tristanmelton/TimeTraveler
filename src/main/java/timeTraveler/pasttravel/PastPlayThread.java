package timeTraveler.pasttravel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.List;

import cpw.mods.fml.client.FMLClientHandler;

import timeTraveler.core.TimeTraveler;
import timeTraveler.entities.EntityPlayerPast;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.DimensionManager;

class PastPlayThread implements Runnable
{
	Thread t;
	EntityPlayerPast replayEntity;
	RandomAccessFile in;

	public PastPlayThread(EntityPlayerPast _player, String recfile)
	{
		try 
		{
			File file = new File(FMLClientHandler.instance().getClient().mcDataDir + "/mods/TimeMod/past/EntityLocations/" + MinecraftServer.getServer().getWorldName() + "/" + TimeTraveler.vars.getLastPastTimeSavedForWorld());

			this.in = new RandomAccessFile(new File(recfile).getAbsoluteFile(), "r");
			//this.in = new RandomAccessFile(file.getAbsolutePath() + "/" + recfile, "r");
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		this.replayEntity = _player;
		this.t = new Thread(this, "TimeTraveler Past Player Replay Thread");
		this.t.start();
	}

	public void run() 
	{
		try
		{
			Thread.sleep(500L);
		} 
		catch (InterruptedException e1)
		{
			e1.printStackTrace();
		}
		try
		{
			short magic = this.in.readShort();
			if (magic != -4885)
			{
				throw new Exception("Not a Past file!  Someone's trying to hack the past!");
			}
			while (this.in.getFilePointer() != this.in.length()) 
			{
				float yaw = this.in.readFloat();
				float pitch = this.in.readFloat();
				double x = this.in.readDouble();
				double y = this.in.readDouble();
				double z = this.in.readDouble();
				double mx = this.in.readDouble();
				double my = this.in.readDouble();
				double mz = this.in.readDouble();
				float fd = this.in.readFloat();
				Boolean iab = Boolean.valueOf(this.in.readBoolean());
				Boolean isn = Boolean.valueOf(this.in.readBoolean());
				Boolean isp = Boolean.valueOf(this.in.readBoolean());
				Boolean iog = Boolean.valueOf(this.in.readBoolean());
				Boolean ie = Boolean.valueOf(this.in.readBoolean());

				this.replayEntity.isAirBorne = iab.booleanValue();
				this.replayEntity.motionX = mx;
				this.replayEntity.motionY = my;
				this.replayEntity.motionZ = mz;
				this.replayEntity.fallDistance = fd;
				this.replayEntity.setSneaking(isn.booleanValue());
				this.replayEntity.setSprinting(isp.booleanValue());
				this.replayEntity.onGround = iog.booleanValue();
				this.replayEntity.setPositionAndRotation(x, y, z, yaw, pitch);
				this.replayEntity.setEating(ie.booleanValue());

				Boolean hasAction = Boolean.valueOf(this.in.readBoolean());
				if (hasAction.booleanValue()) 
				{
					byte type = this.in.readByte();
					switch (type) 
					{
					case 1:
						String msg = this.in.readUTF();

						PastAction ma = new PastAction((byte) 1);
						ma.message = msg;

						this.replayEntity.eventsList.add(ma);
						break;
					case 4:
						int aSlot = this.in.readInt();
						int aId = this.in.readInt();
						int aDmg = this.in.readInt();

						PastAction ma2 = new PastAction((byte) 4);
						ma2.armorSlot = aSlot;
						ma2.armorId = aId;
						ma2.armorDmg = aDmg;

						this.replayEntity.eventsList.add(ma2);
						break;
					case 2:
						PastAction ma3 = new PastAction((byte) 2);
						this.replayEntity.eventsList.add(ma3);
						break;
					case 3:
						PastAction ma4 = new PastAction((byte) 3);
						ma4.itemData = ((NBTTagCompound) NBTTagCompound.readNamedTag(this.in));

						this.replayEntity.eventsList.add(ma4);
						break;
					case 5:
						int aCharge = this.in.readInt();
						PastAction ma5 = new PastAction((byte) 5);
						ma5.arrowCharge = aCharge;
						this.replayEntity.eventsList.add(ma5);
						break;
					}
				}
				Thread.sleep(100L);
			}
		}
		catch (Exception e) 
		{
			System.out.println("Replay thread interrupted.");
			System.out.println("Error loading Past file, either not a past file or recorded by an older version.");
		
			e.printStackTrace();
		}
		this.replayEntity.setDead();
		
		try 
		{
			this.in.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
