package timeTraveler.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import timeTraveler.mechanics.FutureTravelMechanics;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class FutureTravelPacketHandlerServer implements IPacketHandler {
@Override
public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
                 DataInputStream datainputstream = new DataInputStream(new ByteArrayInputStream(packet.data));
                 try
                 {
                	 FutureTravelMechanics ftm = new FutureTravelMechanics();
                                 int run = datainputstream.readInt();
                                 World world = DimensionManager.getWorld(0);
                                 System.out.println(run);
                                 if (world != null) {
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
}
