package timeTraveler.core;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import timeTraveler.blocks.BlockTimeTraveler;
import timeTraveler.entities.EntityPlayerPast;
import timeTraveler.items.ItemParadoximer;
import timeTraveler.mechanics.FutureTravelMechanics;
import timeTraveler.proxies.CommonProxy;
import timeTraveler.structures.StructureGenerator;
import timeTraveler.ticker.TickerClient;
import timeTraveler.world.WorldProviderTime;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarted;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;


@Mod(modid = "Charsmud_TimeTraveler", name = "Time Traveler", version = "0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
/**
 * Main laucher for TimeTraveler
 * @author Charsmud
 *
 */
public class TimeTraveler
{
	@SidedProxy(clientSide = "timeTraveler.proxies.ClientProxy", serverSide = "timeTraveler.proxies.CommonProxy")
	
	
	public static CommonProxy proxy;

	public static Block travelTime;
	
	public static Item paradoximer;

	public static final String modid = "Charsmud_TimeTraveler";
	
	FutureTravelMechanics ftm;
	
	public static int dimensionID = 20;


	/**
	 * Initializes DeveloperCapes
	 * @param event
	 */
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.initCapes();
	}
	/**
	 * Initiates mod, registers block and item for use.  Generates the necessary folders.
	 */
	@Init
	public void load(FMLInitializationEvent event)
	{  	
		proxy.registerRenderThings();

		TickRegistry.registerTickHandler(new TickerClient(), Side.CLIENT);

		Minecraft m = FMLClientHandler.instance().getClient();
		MinecraftServer ms = m.getIntegratedServer();

		mkDirs();

		paradoximer = new ItemParadoximer(2330).setUnlocalizedName("ItemParadoximer");	
		
		travelTime = new BlockTimeTraveler(255).setUnlocalizedName("BlockTimeTraveler");
		
		GameRegistry.registerBlock(travelTime, "travelTime");
		
		LanguageRegistry.addName(travelTime, "Paradox Cube");
		LanguageRegistry.addName(paradoximer, "Paradoximer");
		
		GameRegistry.registerWorldGenerator(new StructureGenerator());

		GameRegistry.addRecipe(new ItemStack(travelTime,  13), new Object[] 
				{
			//
			"x", Character.valueOf('x'), Block.dirt
				});
		GameRegistry.addRecipe(new ItemStack(paradoximer,  13), new Object[] 
				{
			"x", "s", Character.valueOf('x'), Block.wood, Character.valueOf('s'), Block.dirt
				});
		ModLoader.registerEntityID(EntityPlayerPast.class, "PlayerPast", 100);//registers the mobs name and id
		// ModLoader.addSpawn(EntityPlayerPast.class, 25, 25, 25, EnumCreatureType.creature);
		DimensionManager.registerProviderType(dimensionID, WorldProviderTime.class, false);
		DimensionManager.registerDimension(dimensionID, dimensionID);

		
		ftm = new FutureTravelMechanics();		

	}

	@ServerStarted
	public void registerPackets(FMLServerStartedEvent event)
	{
        NetworkRegistry.instance().registerChannel(new IPacketHandler() {
            @Override
            public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
                    DataInputStream datainputstream = new DataInputStream(new ByteArrayInputStream(packet.data));
                    try
                    {
                    	System.out.println("A");
                            int run = datainputstream.readInt();
                            World world = DimensionManager.getWorld(0);
                            if (world != null) {
                            	System.out.println("B");
                                    for (int i = 0; i < run; i++)
                                    {
                                            ftm.expandOres(world, 1, 1, 1, 1, 1, 1, 1);
                                            ftm.expandForests(world, 2);
                                    }
                            }

                    }
                    catch (IOException ioexception)
                    {
                            ioexception.printStackTrace();
                    }
            }
    }, "futuretravel", Side.SERVER);

	}
	/**
	 * Makes the Directories needed
	 */
	public void mkDirs()
	{
		File pastCreation = new File(FMLClientHandler.instance().getClient().getMinecraftDir() + "/mods/TimeMod/past");
		pastCreation.mkdirs();
		File presentCreation = new File(FMLClientHandler.instance().getClient().getMinecraftDir() + "/mods/TimeMod/present");
		presentCreation.mkdirs();
		File futureCreation = new File(FMLClientHandler.instance().getClient().getMinecraftDir() + "/mods/TimeMod/future");

	}
}