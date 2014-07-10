package timeTraveler.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.common.DimensionManager;
import timeTraveler.core.TimeTraveler;
import timeTraveler.entities.EntityPlayerPast;
import timeTraveler.gui.GuiFutureGenerating;
import timeTraveler.gui.GuiFutureTravel;
import timeTraveler.mechanics.CopyFile;
import timeTraveler.mechanics.FutureTravelMechanics;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class TimeTravelerPacketHandler implements IPacketHandler {
@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
	    
		if(packet.channel.equals("paradoxgui"))
		{
			handlePacket(packet);
		}
		if(packet.channel.equals("futuretravel"))
	    {
			Minecraft mc = FMLClientHandler.instance().getClient();
			MinecraftServer ms = Minecraft.getMinecraft().getIntegratedServer();
			GuiFutureGenerating gfg = new GuiFutureGenerating();

			DataInputStream datainputstream = new DataInputStream(new ByteArrayInputStream(packet.data));
			try
		    {
				String worldName = ms.getWorldName();
				String folderName = ms.getFolderName();
				
		    	FutureTravelMechanics ftm = new FutureTravelMechanics();
		        int run = datainputstream.readInt();
		        World world = DimensionManager.getWorld(0);
		        System.out.println(run);
		   
		        gfg.setFutureYears(run);
		        mc.displayGuiScreen(gfg);
		        File present = new File(mc.mcDataDir + "/mods/TimeMod/present/" + ms.getWorldName());
		        File worldFile = new File(mc.mcDataDir + "/saves/" + ms.getWorldName() + "/region");
		        File future = new File(mc.mcDataDir + "/mods/TimeMod/future/" + ms.getWorldName() + "/" + run);
		        try
		        {
		        	Thread.sleep(3000);
					CopyFile.copyDirectory(worldFile, present);
					System.out.println("COPYING PRESENT");
		        }
		        catch(Exception ex)
		        {
		        	ex.printStackTrace();
		        }
		        if(future.exists())
		        {
		        	try
		        	{
				        System.out.println("THIS FUTURE EXISTS, MOVING THE FUTURE IN");
				        mc.displayGuiScreen(null);
				        	
					    mc.statFileWriter.readStat(StatList.leaveGameStat, 1);
						mc.theWorld.sendQuittingDisconnectingPacket();
						ms.stopServer();
						// mc.loadWorld((WorldClient)null);

						Thread.sleep(3000);

						CopyFile.moveMultipleFiles(future, worldFile);
						FutureTravelMechanics.launchWorld(folderName, worldName, (WorldSettings)null);
						//ms.startServerThread();
						//mc.launchIntegratedServer(folderName, worldName, (WorldSettings) null);

		        	}
		        	catch(Exception ex)
		        	{
		        		ex.printStackTrace();
		        	}
		        }
		        else
		        {
		        	System.out.println("THIS FUTURE DOES NOT EXIST, GENERATING");
			        future.mkdirs();

		        	if (world != null)
		        	{
		        		for (int i = 0; i < run; i++)
		        		{
		        			gfg.setGenerated(i);
		        			System.out.println(run);
		        			ftm.expandOres(world);
		        			ftm.expandForests(world, 2);
		        		}
		        	}
		        	mc.displayGuiScreen(null);
		        	mc.displayInGameMenu();
		        	TimeTraveler.vars.setIsInFuture(true);
		        }
		    }
		    catch (IOException ioexception)
		    {
		    	ioexception.printStackTrace();
		    }

	    }
		if(packet.channel.equals("entityspawn"))
		{
			World world = DimensionManager.getWorld(0);
			//FMLClientHandler.instance().getClient().theWorld.getGameRules().setOrCreateGameRule("doMobSpawning", "false");

			DataInputStream datainputstream = new DataInputStream(new ByteArrayInputStream(packet.data));
			
			try
			{
				String entityName = datainputstream.readUTF();
				int entityX = datainputstream.readInt();
				int entityY = datainputstream.readInt();
				int entityZ = datainputstream.readInt();
				UUID entityUniqueId = UUID.fromString(datainputstream.readUTF());
				
				Entity pastEntity = EntityList.createEntityByName(entityName, world);
				
				if(pastEntity != null)
				{
					//PathEntity path = ((EntityLiving)pastEntity).getNavigator().getPath();
					
					pastEntity.posX = (double)entityX;
					pastEntity.posY = (double)entityY;
					pastEntity.posZ = (double)entityZ;
									
					System.out.println(pastEntity);
										
					world.spawnEntityInWorld(pastEntity);

				}
				else
				{
					EntityPlayerPast pastPlayer = new EntityPlayerPast(world);
					pastPlayer.posX = (double)entityX;
					pastPlayer.posY = (double)entityY;
					pastPlayer.posZ = (double)entityZ;
					System.out.println(pastPlayer);
					world.spawnEntityInWorld(pastPlayer);
				}
				//System.out.println(entityName + " " + entityX + " " + entityY + " " + entityZ);
				
		        //path = ((EntityLiving)pastEntity).getNavigator().getPathToXYZ((double)entityX, (double)entityY, (double)entityZ);
		            		
		        //((EntityLiving)pastEntity).getNavigator().setPath(path, 1.0F);
		        //((EntityLiving)pastEntity).getNavigator().tryMoveToXYZ((double)entityX, (double)entityY, (double)entityZ, 0.5F);

			}			
			catch(Exception ex)
			{
				ex.printStackTrace();
			}

		}
		if(packet.channel.equals("entitypathupdate"))
		{
			World world = DimensionManager.getWorld(0);
			//FMLClientHandler.instance().getClient().theWorld.getGameRules().setOrCreateGameRule("doMobSpawning", "false");

			DataInputStream datainputstream = new DataInputStream(new ByteArrayInputStream(packet.data));
			
			try
			{
				String entityName = datainputstream.readUTF();
				int entityX = datainputstream.readInt();
				int entityY = datainputstream.readInt();
				int entityZ = datainputstream.readInt();
				UUID entityUniqueId = UUID.fromString(datainputstream.readUTF());
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
	public void handlePacket(Packet250CustomPayload packet){
		DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
	
		int randomInt1;
		int randomInt2;
	
		try
		{
			randomInt1 = inputStream.readInt();
			randomInt2 = inputStream.readInt();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return;
		}
	
		System.out.println(randomInt1 + "" + randomInt2);
	}
}
