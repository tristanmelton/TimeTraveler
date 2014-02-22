package timeTraveler.core;
import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import timeTraveler.blocks.BlockParadoxCondenser;
import timeTraveler.blocks.BlockTime;
import timeTraveler.blocks.BlockTimeTraveler;
import timeTraveler.blocks.Collision;
import timeTraveler.blocks.ParadoxExtractor;
import timeTraveler.entities.EntityChair;
import timeTraveler.entities.EntityHandler;
import timeTraveler.entities.EntityParadoxHunter;
import timeTraveler.entities.EntityPlayerPast;
import timeTraveler.gui.GuiHandler;
import timeTraveler.items.BottledParadox;
import timeTraveler.items.CondensedParadox;
import timeTraveler.items.EmptyBottle;
import timeTraveler.items.ItemExpEnhance;
import timeTraveler.items.ItemFlashback;
import timeTraveler.items.ItemParadoximer;
import timeTraveler.mechanics.FutureTravelMechanics;
import timeTraveler.network.TimeTravelerPacketHandler;
import timeTraveler.proxies.CommonProxy;
import timeTraveler.structures.StructureGenerator;
import timeTraveler.ticker.TickerClient;
import timeTraveler.tileentity.TileEntityCollision;
import timeTraveler.tileentity.TileEntityExtractor;
import timeTraveler.tileentity.TileEntityParadoxCondenser;
import timeTraveler.tileentity.TileEntityTimeTravel;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;


@Mod(modid = TimeTraveler.modid, name = "Time Traveler", version = "0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, serverPacketHandlerSpec = @SidedPacketHandler (channels = {"futuretravel", "paradoxgui", "entityspawn"}, packetHandler = TimeTravelerPacketHandler.class))

/**
 * Main launcher for TimeTraveler
 * @author Charsmud6
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
	public static Block paradoxExtractor;
	public static Block collisionBlock;
	public static Block timeTravel;
	
	public static Item paradoximer;
	public static Item bottledParadox;
	public static Item condensedParadox;
	public static Item emptyBottle;
	public static Item expEnhance;
	public static Item flashback;
	
	public static final String modid = "charsmud_timetraveler";
	
	FutureTravelMechanics ftm;
	
	private GuiHandler guihandler;

	static int startEntityId = 300;

	public static UnchangingVars vars = new UnchangingVars();
	
	/**
	 * Initiates mod, registers block and item for use.  Generates the necessary folders.
	 */
	@EventHandler
	public void load(FMLInitializationEvent event)
	{  	
		initialize();
		registerBlocks();
		addNames();
		addSmelt();
		mkDirs();
		registerEntities();
		addRecipes();
		
		proxy.initCapes();
		
		GameRegistry.registerTileEntity(TileEntityCollision.class, "collide");
		GameRegistry.registerTileEntity(TileEntityExtractor.class, "extractor");
		GameRegistry.registerTileEntity(TileEntityParadoxCondenser.class, "condenser");
		GameRegistry.registerTileEntity(TileEntityTimeTravel.class, "timetravel");
		
		TickRegistry.registerTickHandler(new TickerClient(), Side.CLIENT);		
		GameRegistry.registerWorldGenerator(new StructureGenerator());
		NetworkRegistry.instance().registerGuiHandler(this, guihandler);
		EntityRegistry.registerGlobalEntityID(EntityPlayerPast.class, "PlayerPast", EntityRegistry.findGlobalUniqueEntityId(), 0x191919, 0x000000);//registers the mobs name and id
		EntityRegistry.registerGlobalEntityID(EntityChair.class, "Chiar", EntityRegistry.findGlobalUniqueEntityId());
		
		MinecraftForge.EVENT_BUS.register(new EntityHandler());
		

		proxy.registerRenderThings();

	}
//	public static int getUniqueEntityId() 
//	{
//		do 
//	  	{
//			startEntityId++;
//	  	} 
//		while (EntityList.getStringFromID(startEntityId) != null);
//
//		return startEntityId;
//	}
	public static void registerEntityEgg(Class<? extends Entity> entity, int primaryColor, int secondaryColor) 
	{
		int id = EntityRegistry.findGlobalUniqueEntityId();
		EntityList.IDtoClassMapping.put(id, entity);
		EntityList.entityEggs.put(id, new EntityEggInfo(id, primaryColor, secondaryColor));
	}



	/**
	 * Initializes Variables
	 */
	public void initialize()
	{
		paradoximer = new ItemParadoximer(2330).setUnlocalizedName("ItemParadoximer");	
		bottledParadox = new BottledParadox(2331).setUnlocalizedName("BottledParadox");
		condensedParadox = new CondensedParadox(2332).setUnlocalizedName("CondensedParadox");
		emptyBottle = new EmptyBottle(2333).setUnlocalizedName("emptyBottle");
		expEnhance = new ItemExpEnhance(2334).setUnlocalizedName("ExpEnhancer");
		flashback = new ItemFlashback(2335).setUnlocalizedName("Flashback");
		
		travelTime = new BlockTimeTraveler(255).setUnlocalizedName("BlockTimeTraveler");
		paradoxCondenser = new BlockParadoxCondenser(254, true).setUnlocalizedName("BlockParadoxCondenser");
		paradoxExtractor = new ParadoxExtractor(253, true).setUnlocalizedName("ParadoxExtractor");
		collisionBlock = new Collision(252, Material.air).setUnlocalizedName("collisionBlock");
		timeTravel = new BlockTime(250, true).setUnlocalizedName("TimeTravel");

		ftm = new FutureTravelMechanics();		
		guihandler = new GuiHandler();
	}
	/**
	 * Registers Blocks
	 */
	public void registerBlocks()
	{
		GameRegistry.registerBlock(travelTime, "travelTime");
		GameRegistry.registerBlock(paradoxCondenser, "paradoxCondenser");
		GameRegistry.registerBlock(paradoxExtractor, "paradoxExtractor");
		GameRegistry.registerBlock(collisionBlock, "collisionBlock");
		GameRegistry.registerBlock(timeTravel, "timeTravel");
	}
	/**
	 * Adds Names
	 */
	public void addNames()
	{
		LanguageRegistry.addName(travelTime, "Paradox Cube");
		LanguageRegistry.addName(paradoximer, "Paradoximer");
		LanguageRegistry.addName(paradoxCondenser, "Paradox Condenser");
		LanguageRegistry.addName(bottledParadox, "Bottled Paradox");
		LanguageRegistry.addName(condensedParadox, "Condensed Paradox");
		LanguageRegistry.addName(paradoxExtractor, "Paradox Extractor");
		LanguageRegistry.addName(emptyBottle, "Empty Bottle");
		LanguageRegistry.addName(timeTravel, "Time Machine");
		LanguageRegistry.addName(expEnhance, "XP Enhancer");
		LanguageRegistry.addName(flashback, "Flashback");
	}
	/**
	 * Adds Recipes
	 */
	public void addRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(emptyBottle,  1), new Object[] 
				{
			" o ", "x x", " x ", Character.valueOf('x'), Block.glass, Character.valueOf('o'), Item.clay
				});
		GameRegistry.addRecipe(new ItemStack(expEnhance, 1), new Object[]
				{
			"xox", "oxo", "xox", Character.valueOf('x'), TimeTraveler.expEnhance, Character.valueOf('o'), Item.expBottle
				});
		GameRegistry.addRecipe(new ItemStack(flashback, 1), new Object[]
				{
			"xox", "yxy", "xox", Character.valueOf('x'), TimeTraveler.expEnhance, Character.valueOf('o'), Item.compass, Character.valueOf('y'), Item.pocketSundial
				});
		
	}
	/**
	 * Adds Smelting Recipes
	 */
	public void addSmelt()
	{
		FurnaceRecipes.smelting().addSmelting(Item.glassBottle.itemID, new ItemStack(TimeTraveler.emptyBottle), 0.2F);
	}
	
	/**
	 * Makes the Directories needed
	 */
	public void mkDirs()
	{
		File pastCreation = new File(FMLClientHandler.instance().getClient().mcDataDir + "/mods/TimeMod/past");
		File presentCreation = new File(FMLClientHandler.instance().getClient().mcDataDir + "/mods/TimeMod/present");
		File futureCreation = new File(FMLClientHandler.instance().getClient().mcDataDir +"/mods/TimeMod/future");

		
		pastCreation.mkdirs();
		presentCreation.mkdirs();
		futureCreation.mkdirs();

	}
	/**
	 * Registers Entities
	 */
	public void registerEntities()
	{
		EntityRegistry.registerModEntity(EntityParadoxHunter.class, "ParadoxHunter", 1, this, 80, 1, true);
		EntityRegistry.addSpawn(EntityParadoxHunter.class, 10, 2, 4, EnumCreatureType.creature, BiomeGenBase.beach, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.mushroomIsland, BiomeGenBase.mushroomIslandShore, BiomeGenBase.ocean, BiomeGenBase.plains, BiomeGenBase.river, BiomeGenBase.swampland);
		LanguageRegistry.instance().addStringLocalization("entity.Charsmud_TimeTraveler.ParadoxHunter.name", "Paradox Hunter");

		
		registerEntityEgg(EntityParadoxHunter.class, 0xffffff, 0x000000);
	}
}