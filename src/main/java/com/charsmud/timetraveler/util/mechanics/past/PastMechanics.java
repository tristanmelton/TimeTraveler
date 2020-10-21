package com.charsmud.timetraveler.util.mechanics.past;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;

import com.charsmud.timetraveler.TimeTraveler;
import com.charsmud.timetraveler.entities.EntityPlayerPast;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

/**
 * Contains information about the new Mechanics for the past
 * @author Charsmud
 *
 */
//TODO: Implement / clean me

public class PastMechanics
{
	/**
	 * Starts recording the actions of a player.
	 * @param player
	 * @param pastName
	 */
	public void beginPastRecording(EntityPlayer player)
	{
		TimeTravelerPastRecorder aRecorder = (TimeTravelerPastRecorder) TimeTraveler.instance.recordThreads.get(player);
		synchronized (TimeTraveler.instance.recordThreads) 
		{
			if (aRecorder != null) 
			{
				System.out.println("'" + aRecorder.fileName + ".ppd' is already being recorded to?");
				return;
			}
			else
			{
				String name = player.getDisplayNameString();
				System.out.println("Started recording " + name + " to file " + name + ".ppd");
				TimeTravelerPastRecorder mcr = new TimeTravelerPastRecorder();
				mcr.fileName = name;
				mcr.recordThread = new PastRecordThread(player);
				TimeTraveler.instance.recordThreads.put(player, mcr);

				return;
			}

		}
	}
	public void stopPastRecording(EntityPlayer player)
	{
		TimeTravelerPastRecorder recorder = (TimeTravelerPastRecorder) TimeTraveler.instance.recordThreads.get(player);
		if(recorder != null)
		{
			recorder.recordThread.capture = Boolean.valueOf(false);
			System.out.println("Stopped recording " + player.getDisplayNameString() + " to file " + recorder.fileName + ".ppd");
			TimeTraveler.instance.recordThreads.remove(player);	
		}
		else
			System.out.println("Player is not being recorded!");
	}
	
	public void replayPast(EntityPlayer player) 
	{
		System.out.println(player.getDisplayNameString());
		File file = new File(FMLClientHandler.instance().getClient().mcDataDir + "/mods/TimeMod/past/" + Minecraft.getMinecraft().getIntegratedServer().getWorldName() + "/" + player.getDisplayNameString() + ".ppd");
		if (!file.exists()) 
		{
			System.out.println("Can't find " + file + " past file!");
			return;
		}
		double x = 0.0D;
		double y = 0.0D;
		double z = 0.0D;
		try 
		{
			RandomAccessFile in = new RandomAccessFile(file, "r");
			short magic = in.readShort();
			if (magic != -4885)
			{
				System.out.println(file + " isn't a .ppd file.");
				in.close();
				return;
			}
			//long timestamp = in.readLong();
			float yaw = in.readFloat();
			float pitch = in.readFloat();
			x = in.readDouble();
			y = in.readDouble();
			z = in.readDouble();

			in.close();
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		World world = player.world;
		if(!world.isRemote)
		{
			EntityPlayerPast entity = new EntityPlayerPast(world);

			entity.setPosition(x, y, z);

			entity.setSkinSource("Charsmud"/*player.getDisplayNameString()*/);
			entity.setCustomNameTag(player.getDisplayNameString());
			entity.setAlwaysRenderNameTag(true);

			world.spawnEntity(entity);
			System.out.println("Spawned entity in world at " + x + ", " + y + ", " + z);
			for (Iterator<PastPlayThread> iterator = TimeTraveler.instance.playThreads.iterator(); iterator.hasNext();)
			{
				PastPlayThread item = (PastPlayThread) iterator.next();
				if (!item.getThreadIsAlive())
				{
					iterator.remove();
				}
			}
			TimeTraveler.instance.playThreads.add(new PastPlayThread(player, entity, file.toString()));
		}
	}
}