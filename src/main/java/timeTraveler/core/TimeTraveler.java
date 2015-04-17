package timeTraveler.core;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerSelector;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import timeTraveler.blocks.BlockMarker;
import timeTraveler.blocks.BlockParadoxCondenser;
import timeTraveler.blocks.BlockTime;
import timeTraveler.blocks.BlockTimeField;
import timeTraveler.blocks.BlockTimeFluid;
import timeTraveler.blocks.BlockTimeTraveler;
import timeTraveler.blocks.Collision;
import timeTraveler.blocks.ParadoxExtractor;
import timeTraveler.blocks.TimeDistorter;
import timeTraveler.entities.EntityChair;
import timeTraveler.entities.EntityParadoxHunter;
import timeTraveler.entities.EntityPlayerPast;
import timeTraveler.entities.EntityXPOrbTT;
import timeTraveler.futuretravel.FutureTravelMechanics;
import timeTraveler.futuretravel.WorldProviderFuture;
import timeTraveler.gui.GuiHandler;
import timeTraveler.items.BottledParadox;
import timeTraveler.items.CondensedParadox;
import timeTraveler.items.EmptyBottle;
import timeTraveler.items.ItemExpEnhance;
import timeTraveler.items.ItemParadoximer;
import timeTraveler.items.SlowArmor;
import timeTraveler.items.TimeFluidBucket;
import timeTraveler.mechanics.TTEventHandler;
import timeTraveler.network.Message;
import timeTraveler.network.TimeTravelerPacketHandler;
import timeTraveler.pasttravel.PastAction;
import timeTraveler.pasttravel.TimeTravelerPastRecorder;
import timeTraveler.proxies.CommonProxy;
import timeTraveler.structures.StructureGenerator;
import timeTraveler.ticker.Ticker;
import timeTraveler.tileentity.TileEntityCollision;
import timeTraveler.tileentity.TileEntityExtractor;
import timeTraveler.tileentity.TileEntityMarker;
import timeTraveler.tileentity.TileEntityParadoxCondenser;
import timeTraveler.tileentity.TileEntityTimeDistorter;
import timeTraveler.tileentity.TileEntityTimeTravel;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;


@Mod(modid = TimeTraveler.modid, name = "Time Traveler", version = "0.1")

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
	
	public static SimpleNetworkWrapper snw;

	public static Block travelTime;
	public static Block paradoxCondenser;
	public static Block paradoxExtractor;
	public static Block collisionBlock;
	public static Block timeTravel;
	public static Block marker;
	public static Block timeDistorter;
	public static Block timeField;
	public static Block timeLiquid;
	
	public static Item paradoximer;
	public static Item bottledParadox;
	public static Item condensedParadox;
	public static Item emptyBottle;
	public static Item expEnhance;
	public static Item flashback;
	public static Item bucket;
	
	public static Item slowHelmet;
	public static Item slowChestplate;
	public static Item slowLeggings;
	public static Item slowBoots;
	
	public static Fluid timeFluid;
	
	public static final String modid = "charsmud_timetraveler";
	
	FutureTravelMechanics ftm;
	private GuiHandler guihandler;

	static int startEntityId = 300;
	public static int dimensionId = 10;
	
	public static UnchangingVars vars = new UnchangingVars();
	

	
	@SuppressWarnings("rawtypes")
	public Map<EntityPlayer, TimeTravelerPastRecorder> recordThreads = Collections.synchronizedMap(new HashMap());
	 
	public static EntityPlayerMP getPlayerForName(ICommandSender sender, String name)
	{
		EntityPlayerMP var2 = PlayerSelector.matchOnePlayer(sender, name);
		if (var2 != null)
		{
			return var2;
		}
		return getPlayerForName(name);
	}

	public static EntityPlayerMP getPlayerForName(String name)
	{
		EntityPlayerMP tempPlayer = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().func_152612_a(name);
		if (tempPlayer != null) 
		{
			return tempPlayer;
		}
		List<EntityPlayerMP> possibles = new LinkedList();
		ArrayList<EntityPlayerMP> temp = (ArrayList) FMLCommonHandler.instance().getSidedDelegate().getServer().getConfigurationManager().playerEntityList;
		for (EntityPlayerMP player : temp)
		{
			if (player.getDisplayName().equalsIgnoreCase(name))
			{
				return player;
			}
			if (player.getDisplayName().toLowerCase().contains(name.toLowerCase())) 
			{
				possibles.add(player);
			}
		}
		if (possibles.size() == 1)
		{
			return (EntityPlayerMP) possibles.get(0);
		}
		return null;
	}
	
	public List<PastAction> getActionListForPlayer(EntityPlayer ep)
	{
		TimeTravelerPastRecorder aRecorder = (TimeTravelerPastRecorder) this.recordThreads.get(ep);
		if (aRecorder == null) 
		{
			return null;
		}
		return aRecorder.eventsList;
	}
	@EventHandler
	public void preLoad(FMLPreInitializationEvent event)
	{
		snw = NetworkRegistry.INSTANCE.newSimpleChannel(modid);
		snw.registerMessage(TimeTravelerPacketHandler.class, Message.class, 0, Side.CLIENT);
		
		FMLCommonHandler.instance().bus().register(new TTEventHandler());
		FMLCommonHandler.instance().bus().register(new Ticker());
		MinecraftForge.EVENT_BUS.register(new TTEventHandler());
		MinecraftForge.EVENT_BUS.register(new Ticker());
	}
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
		

		guihandler = new GuiHandler();
		GameRegistry.registerTileEntity(TileEntityCollision.class, "collide");
		GameRegistry.registerTileEntity(TileEntityExtractor.class, "extractor");
		GameRegistry.registerTileEntity(TileEntityParadoxCondenser.class, "condenser");
		GameRegistry.registerTileEntity(TileEntityTimeTravel.class, "timetravel");
		GameRegistry.registerTileEntity(TileEntityMarker.class, "marker");
		GameRegistry.registerTileEntity(TileEntityTimeDistorter.class, "timeDistorter");
		
		
		//TickRegistry.registerTickHandler(new TickerClient(), Side.CLIENT);		
		GameRegistry.registerWorldGenerator(new StructureGenerator(), 0);
		NetworkRegistry.INSTANCE.registerGuiHandler(this, guihandler);
		EntityRegistry.registerGlobalEntityID(EntityPlayerPast.class, "PlayerPast", EntityRegistry.findGlobalUniqueEntityId(), 0x191919, 0x000000);//registers the mobs name and id
		EntityRegistry.registerGlobalEntityID(EntityChair.class, "Chiar", 1001);
		EntityRegistry.registerGlobalEntityID(EntityXPOrbTT.class, "XP Orb", 1002);
		
		DimensionManager.registerProviderType(TimeTraveler.dimensionId, WorldProviderFuture.class, false); 
		DimensionManager.registerDimension(TimeTraveler.dimensionId, TimeTraveler.dimensionId); 
		
		
		FluidContainerRegistry.registerFluidContainer(timeFluid, new ItemStack(bucket), new ItemStack(Items.bucket));

		
		proxy.initCapes();

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
		timeFluid = new Fluid("timeFluid").setLuminosity(7).setDensity(2000).setViscosity(3000);
		FluidRegistry.registerFluid(timeFluid);
		timeLiquid = new BlockTimeFluid(timeFluid, Material.water);

		
		paradoximer = new ItemParadoximer();	
		bottledParadox = new BottledParadox();
		condensedParadox = new CondensedParadox();
		emptyBottle = new EmptyBottle();
		expEnhance = new ItemExpEnhance();
		//flashback = new ItemFlashback();
		bucket = new TimeFluidBucket(timeLiquid);
		
		slowHelmet = new SlowArmor(ArmorMaterial.IRON, 4, 0).setUnlocalizedName("slowHelmet");
		slowChestplate = new SlowArmor(ArmorMaterial.IRON, 4, 1).setUnlocalizedName("slowChestplate");
		slowLeggings = new SlowArmor(ArmorMaterial.IRON, 4, 2).setUnlocalizedName("slowLeggings");
		slowBoots = new SlowArmor(ArmorMaterial.IRON, 4, 3).setUnlocalizedName("slowBoots");
		
		timeDistorter = new TimeDistorter();
		marker = new BlockMarker();
		travelTime = new BlockTimeTraveler();
		paradoxCondenser = new BlockParadoxCondenser();
		paradoxExtractor = new ParadoxExtractor(true);
		collisionBlock = new Collision(Material.glass);
		timeTravel = new BlockTime();
		timeField = new BlockTimeField();
		ftm = new FutureTravelMechanics();	
		

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
		GameRegistry.registerBlock(marker, "marker");
		GameRegistry.registerBlock(timeDistorter, "timeDistorter");
		GameRegistry.registerBlock(timeField, "timeField");
		GameRegistry.registerBlock(timeLiquid, "timeLiquid");
		
		GameRegistry.registerItem(paradoximer, "ItemParadoximer");
		GameRegistry.registerItem(slowHelmet, "slowHelmet");
		GameRegistry.registerItem(slowChestplate, "slowChestplate");
		GameRegistry.registerItem(slowLeggings, "slowLeggings");
		GameRegistry.registerItem(slowBoots, "slowBoots");
		GameRegistry.registerItem(bottledParadox, "BottledParadox");
		GameRegistry.registerItem(condensedParadox, "CondensedParadox");
		GameRegistry.registerItem(emptyBottle, "emptyBottle");
		GameRegistry.registerItem(bucket, "timeFluidBucket");
		GameRegistry.registerItem(expEnhance, "ExpEnhancer");
	}
	/**
	 * Adds Names
	 */
	public void addNames()
	{
		/*LanguageRegistry.addName(travelTime, "Paradox Cube");
		LanguageRegistry.addName(paradoximer, "Paradoximer");
		LanguageRegistry.addName(paradoxCondenser, "Paradox Condenser");
		LanguageRegistry.addName(bottledParadox, "Bottled Paradox");
		LanguageRegistry.addName(condensedParadox, "Condensed Paradox");
		LanguageRegistry.addName(paradoxExtractor, "Paradox Extractor");
		LanguageRegistry.addName(emptyBottle, "Empty Bottle");
		LanguageRegistry.addName(timeTravel, "Time Machine");
		LanguageRegistry.addName(expEnhance, "XP Enhancer");
		LanguageRegistry.addName(marker, "Marker");
		LanguageRegistry.addName(timeDistorter, "Time Distorter");
		LanguageRegistry.addName(timeField, "Time Field");
		LanguageRegistry.addName(timeLiquid, "Liquid Time");
		LanguageRegistry.addName(bucket, "Bucket");
		LanguageRegistry.addName(slowBoots, "Paradox Boots");
		LanguageRegistry.addName(slowLeggings, "Paradox Leggings");
		LanguageRegistry.addName(slowChestplate, "Paradox Chestplate");
		LanguageRegistry.addName(slowHelmet, "Paradox Helmet");
		//LanguageRegistry.addName(flashback, "Flashback");*/
	}
	/**
	 * Adds Recipes
	 */
	public void addRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(emptyBottle,  1), new Object[] 
				{
			" o ", "x x", " x ", Character.valueOf('x'), Blocks.glass, Character.valueOf('o'), Items.clay_ball
				});
		GameRegistry.addRecipe(new ItemStack(paradoxCondenser, 1), new Object[]
				{
			"ooo", "iui", "ooo", Character.valueOf('o'), Blocks.diamond_block, Character.valueOf('i'), travelTime, Character.valueOf('u'), Items.golden_apple
				});
		GameRegistry.addRecipe(new ItemStack(paradoxExtractor, 1), new Object[]
				{
			"ooo", "iui", "ooo", Character.valueOf('o'), Blocks.iron_block, Character.valueOf('i'), travelTime, Character.valueOf('u'), emptyBottle
				});
		GameRegistry.addRecipe(new ItemStack(expEnhance, 1), new Object[]
				{
			"xox", "oxo", "xox", Character.valueOf('x'), TimeTraveler.condensedParadox, Character.valueOf('o'), Items.experience_bottle
				});
		/*GameRegistry.addRecipe(new ItemStack(flashback, 1), new Object[]
				{
			"xox", "yxy", "xox", Character.valueOf('x'), TimeTraveler.expEnhance, Character.valueOf('o'), Items.compass, Character.valueOf('y'), Items.pocketSundial
				});*/
		GameRegistry.addRecipe(new ItemStack(timeTravel, 1), new Object[]
				{
			"ooo", "ooo", "ooo", Character.valueOf('o'), TimeTraveler.travelTime
				});
		GameRegistry.addRecipe(new ItemStack(marker, 2), new Object[]
				{
			" o ", "ioi", " o ", Character.valueOf('o'), TimeTraveler.condensedParadox, Character.valueOf('i'), Blocks.glass
				});
		GameRegistry.addRecipe(new ItemStack(timeDistorter, 1), new Object[]
				{
			"ipi", "pnp", "ipi", Character.valueOf('i'), Blocks.iron_block, Character.valueOf('p'), TimeTraveler.condensedParadox, Character.valueOf('n'), Items.nether_star
				});
		GameRegistry.addRecipe(new ItemStack(slowBoots, 1), new Object[]
				{
			"i i", "p p", "   ", Character.valueOf('p'), TimeTraveler.condensedParadox, Character.valueOf('i'), Items.diamond
				});
		GameRegistry.addRecipe(new ItemStack(slowLeggings, 1), new Object[]
				{
			"ddd", "p p", "p p", Character.valueOf('d'), Items.diamond, Character.valueOf('p'), TimeTraveler.condensedParadox
				});
		GameRegistry.addRecipe(new ItemStack(slowChestplate, 1), new Object[]
				{
			"d d", "dpd", "ddd", Character.valueOf('d'), Items.diamond, Character.valueOf('p'), TimeTraveler.condensedParadox
				});
		GameRegistry.addRecipe(new ItemStack(slowHelmet, 1), new Object[]
				{
			"ddd", "p p", "   ", Character.valueOf('d'), Items.diamond, Character.valueOf('p'), TimeTraveler.condensedParadox
				});
	}
	/**
	 * Adds Smelting Recipes
	 */
	public void addSmelt()
	{
		FurnaceRecipes.smelting().func_151396_a(Items.glass_bottle, new ItemStack(TimeTraveler.emptyBottle), 0.2F);
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
		EntityRegistry.registerModEntity(EntityPlayerPast.class, "PastPlayer", 2, this, 80, 1, true);
		EntityRegistry.addSpawn(EntityParadoxHunter.class, 10, 2, 4, EnumCreatureType.creature, BiomeGenBase.beach, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.mushroomIsland, BiomeGenBase.mushroomIslandShore, BiomeGenBase.ocean, BiomeGenBase.plains, BiomeGenBase.river, BiomeGenBase.swampland);
		LanguageRegistry.instance().addStringLocalization("entity.Charsmud_TimeTraveler.ParadoxHunter.name", "Paradox Hunter");

		
		registerEntityEgg(EntityParadoxHunter.class, 0xffffff, 0x000000);
	}
	
	public static CreativeTabs tabTT = new CreativeTabs("tabTimeTraveler")
	{
		
		public Item getTabIconItem()
		{
			return Items.arrow;
		}
	};
}