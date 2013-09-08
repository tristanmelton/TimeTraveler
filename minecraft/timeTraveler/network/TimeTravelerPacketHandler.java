package timeTraveler.network;

import java.util.List;
import java.util.UUID;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import timeTraveler.entities.EntityPlayerPast;
import timeTraveler.mechanics.FutureTravelMechanics;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

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
			DataInputStream datainputstream = new DataInputStream(new ByteArrayInputStream(packet.data));
			try
		    {
		    	FutureTravelMechanics ftm = new FutureTravelMechanics();
		        int run = datainputstream.readInt();
		        World world = DimensionManager.getWorld(0);
		        System.out.println(run);
		        if (world != null)
		        {
		        	for (int i = 0; i < run; i++)
		            {
		        		System.out.println(run);
		                ftm.expandOres(world);
		                ftm.expandForests(world, 2);
		            }
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
