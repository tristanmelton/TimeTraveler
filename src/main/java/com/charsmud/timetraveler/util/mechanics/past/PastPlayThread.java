package com.charsmud.timetraveler.util.mechanics.past;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.charsmud.timetraveler.TimeTraveler;
import com.charsmud.timetraveler.TimeTraveler.PlayerTemporalLocation;
import com.charsmud.timetraveler.entities.EntityPlayerPast;
import com.charsmud.timetraveler.util.mechanics.TimeTeleporter;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.util.math.MathHelper;

public class PastPlayThread implements Runnable
{
	private Thread t;
	private EntityPlayerPast replayEntity;
	private EntityPlayer playerTarget;
	private DataInputStream in;

	public PastPlayThread(EntityPlayer _player, EntityPlayerPast _playerpast, String recfile)
	{
		try 
		{
			this.in = new DataInputStream(new FileInputStream(new File(recfile).getAbsoluteFile()));
			//this.in = new RandomAccessFile(file.getAbsolutePath() + "/" + recfile, "r");
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		this.replayEntity = _playerpast;
		this.playerTarget = _player;
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
			while (true) 
			{
				//long timestamp = this.in.readLong();
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
				//Boolean ie = Boolean.valueOf(this.in.readBoolean());

				replayEntity.isAirBorne = iab;
				replayEntity.setPositionAndRotation(x, y, z, yaw, pitch);
				replayEntity.motionX = mx;
				replayEntity.motionY = my;
				replayEntity.motionZ = mz;
				replayEntity.fallDistance = fd;
				replayEntity.setSneaking(isn);
				replayEntity.setSprinting(isp);
				replayEntity.onGround = iog;
				//replayEntity.setEating(ie);

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

						if (aId != -1) {
							ma2.itemData = CompressedStreamTools.read(in);
						}
						
						ma2.armorSlot = aSlot;
						ma2.armorId = aId;
						ma2.armorDmg = aDmg;
						
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
						ma.blockType = Block.getBlockById(in.readInt());
						replayEntity.eventsList.add(ma);
						break;
					}
					case PastActionTypes.BREAKBLOCK:
					{
						PastAction ma = new PastAction(PastActionTypes.BREAKBLOCK);
						ma.xCoord = in.readInt();
						ma.yCoord = in.readInt();
						ma.zCoord = in.readInt();
						replayEntity.eventsList.add(ma);
					}	
					}
				}
				Thread.sleep(100L);
			}
		}
		catch (EOFException ex)
		{
			this.replayEntity.setDead();
			TimeTraveler.instance.playThreads.remove(this);
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
	public boolean getThreadIsAlive()
	{
		return t.isAlive();
	}
}
