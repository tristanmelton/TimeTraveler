package timeTraveler.core;
import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import timeTraveler.blocks.BlockParadoxCondenser;
import timeTraveler.blocks.BlockTimeTraveler;
import timeTraveler.entities.EntityPlayerPast;
import timeTraveler.gui.GuiHandler;
import timeTraveler.items.BottledParadox;
import timeTraveler.items.ItemParadoximer;
import timeTraveler.mechanics.FutureTravelMechanics;
import timeTraveler.network.TimeTravelerPacketHandler;
import timeTraveler.proxies.CommonProxy;
import timeTraveler.structures.StructureGenerator;
import timeTraveler.ticker.TickerClient;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;


@Mod(modid = "Charsmud_TimeTraveler", name = "Time Traveler", version = "0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, serverPacketHandlerSpec = @SidedPacketHandler (channels = {"futuretravel", "paradoxgui"}, packetHandler = TimeTravelerPacketHandler.class))

/**
 * Main laucher for TimeTraveler
 * @author Charsmud
 *
 */
public class TimeTraveler
{
	@SidedProxy(clientSide = "timeTraveler.proxies.ClientProxy", serverSide = "timeTraveler.proxies.CommonProxy")
	
	
	public static CommonProxy proxy;

	@Instance
	public static TimeTraveler instance = new TimeTraveler();
	
	public static Block travelTime;
	public static Block paradoxCondenser;
	
	public static Item paradoximer;
	public static Item bottledParadox;
	
	public static final String modid = "Charsmud_TimeTraveler";
	
	FutureTravelMechanics ftm;
	
	private GuiHandler guihandler;

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
		bottledParadox = new BottledParadox(2331).setUnlocalizedName("BottledParadox");
		
		travelTime = new BlockTimeTraveler(255).setUnlocalizedName("BlockTimeTraveler");
		paradoxCondenser = new BlockParadoxCondenser(254, true).setUnlocalizedName("BlockParadoxCondenser");
		
		GameRegistry.registerBlock(travelTime, "travelTime");
		GameRegistry.registerBlock(paradoxCondenser, "paradoxCondenser");
		
		LanguageRegistry.addName(travelTime, "Paradox Cube");
		LanguageRegistry.addName(paradoximer, "Paradoximer");
		LanguageRegistry.addName(paradoxCondenser, "Paradox Condenser");
		LanguageRegistry.addName(bottledParadox, "Bottled Paradox");
		
		GameRegistry.registerWorldGenerator(new StructureGenerator());
		
		guihandler = new GuiHandler();
		NetworkRegistry.instance().registerGuiHandler(this, guihandler);
		
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

		
		ftm = new FutureTravelMechanics();		

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