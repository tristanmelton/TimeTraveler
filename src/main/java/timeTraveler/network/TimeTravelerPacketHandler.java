package timeTraveler.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import timeTraveler.core.TimeTraveler;
import timeTraveler.entities.EntityPlayerPast;
import timeTraveler.futuretravel.FutureTravelMechanics;
import timeTraveler.futuretravel.TeleporterFuture;
import timeTraveler.gui.GuiFutureGenerating;
import timeTraveler.mechanics.CopyFile;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class TimeTravelerPacketHandler implements IMessageHandler<Message, IMessage>
{
	@Override
	public IMessage onMessage(Message message, MessageContext cxt)
	{
		System.out.println(message.data);
		if(message.data.equals("futuretravel"))
	    {
			Minecraft mc = FMLClientHandler.instance().getClient();
			MinecraftServer ms = Minecraft.getMinecraft().getIntegratedServer();
			GuiFutureGenerating gfg = new GuiFutureGenerating();

			FutureTravelMechanics ftm = new FutureTravelMechanics();
			int run = message.futureTravel;
			World world = DimensionManager.getWorld(0);
			System.out.println(run);
   
			gfg.setFutureYears(run);
			mc.displayGuiScreen(gfg);
			File present = new File(mc.mcDataDir + "/mods/TimeMod/present/" + ms.getWorldName());
			File worldFile = new File(mc.mcDataDir + "/saves/" + ms.getWorldName() + "/region");
			File future = new File(mc.mcDataDir + "/mods/TimeMod/future/" + ms.getWorldName() + "/" + run);
			File futureDim = new File(mc.mcDataDir + "/saves/" + mc.getIntegratedServer().getWorldName() + "/DIM10/region");

			
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
			       
					Thread.sleep(3000);

					CopyFile.moveMultipleFiles(future, futureDim);
			    	TimeTraveler.vars.setIsInFuture(true);
			    	TimeTraveler.vars.setIsPreGenerated(true);
			        ms.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)ms.getEntityWorld().getPlayerEntityByName(message.playerUsr), TimeTraveler.dimensionId, new TeleporterFuture(ms.worldServerForDimension(TimeTraveler.dimensionId)));
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
		if(message.data.equals("entityspawn"))
		{
			World world = DimensionManager.getWorld(0);
			
			try
			{
				String entityName = message.entityName;
				int entityX = message.entityX;
				int entityY = message.entityY;
				int entityZ = message.entityZ;
				UUID entityUniqueId = UUID.fromString(message.uuid);
				
				Entity pastEntity = EntityList.createEntityByName(entityName, world);
				
				if(pastEntity != null)
				{
					
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
			}			
			catch(Exception ex)
			{
				ex.printStackTrace();
			}

		}
		if(message.data.equals("entitypathupdate"))
		{
			World world = DimensionManager.getWorld(0);
			
			try
			{
				String entityName = message.entityName;
				int entityX = message.entityX;
				int entityY = message.entityY;
				int entityZ = message.entityZ;
				UUID entityUniqueId = UUID.fromString(message.uuid);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return null;
	}
}
