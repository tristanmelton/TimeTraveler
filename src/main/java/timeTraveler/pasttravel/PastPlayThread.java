package timeTraveler.pasttravel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import timeTraveler.core.TimeTraveler;
import timeTraveler.entities.EntityPlayerPast;
import cpw.mods.fml.client.FMLClientHandler;

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

				replayEntity.isAirBorne = iab;
				replayEntity.motionX = mx;
				replayEntity.motionY = my;
				replayEntity.motionZ = mz;
				replayEntity.fallDistance = fd;
				replayEntity.setSneaking(isn);
				replayEntity.setSprinting(isp);
				replayEntity.onGround = iog;
				replayEntity.setPositionAndRotation(x, y, z, yaw, pitch);
				replayEntity.setEating(ie);

				Boolean hasAction = Boolean.valueOf(this.in.readBoolean());
				if (hasAction.booleanValue()) 
				{
					byte type = this.in.readByte();
					switch (type) 
					{
					case PastActionTypes.CHAT:
					{
						String msg = this.in.readUTF();

						PastAction ma = new PastAction(PastActionTypes.CHAT);
						ma.message = msg;

						this.replayEntity.eventsList.add(ma);
						break;
					}
					case PastActionTypes.EQUIP:
					{
						int aSlot = this.in.readInt();
						int aId = this.in.readInt();
						int aDmg = this.in.readInt();

						PastAction ma2 = new PastAction(PastActionTypes.EQUIP);

						/* If not a "Clear Slot" event, load item data. */
						if (aId != -1) {
							ma2.itemData = CompressedStreamTools.read(in);
						}
						
						ma2.armorSlot = aSlot;
						ma2.armorId = aId;
						ma2.armorDmg = aDmg;


						
						System.out.println(ma2.armorSlot + " " + ma2.armorId + " " + ma2.armorDmg);
						
						this.replayEntity.eventsList.add(ma2);
						break;
					}
					case PastActionTypes.SWIPE:
					{
						PastAction ma3 = new PastAction(PastActionTypes.SWIPE);
						this.replayEntity.eventsList.add(ma3);
						break;
					}
					case PastActionTypes.DROP:
					{
						PastAction ma4 = new PastAction((byte) 3);
						ma4.itemData = CompressedStreamTools.read(this.in);

						this.replayEntity.eventsList.add(ma4);
						break;
					}
					case PastActionTypes.SHOOTARROW:
					{
						int aCharge = this.in.readInt();
						PastAction ma5 = new PastAction(PastActionTypes.SHOOTARROW);
						ma5.arrowCharge = aCharge;
						this.replayEntity.eventsList.add(ma5);
						break;
					}
					case PastActionTypes.PLACEBLOCK:
					{
						PastAction ma = new PastAction(PastActionTypes.PLACEBLOCK);
						ma.xCoord = in.readInt();
						ma.yCoord = in.readInt();
						ma.zCoord = in.readInt();
						ma.itemData = CompressedStreamTools.read(in);
						replayEntity.eventsList.add(ma);
						break;
					}
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
