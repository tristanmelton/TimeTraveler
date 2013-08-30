package timeTraveler.core;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.src.ModLoader;
import net.minecraft.world.biome.BiomeGenBase;
import timeTraveler.blocks.BlockParadoxCondenser;
import timeTraveler.blocks.BlockTimeTraveler;
import timeTraveler.blocks.Collision;
import timeTraveler.blocks.ParadoxExtractor;
import timeTraveler.entities.EntityParadoxHunter;
import timeTraveler.entities.EntityPlayerPast;
import timeTraveler.gui.GuiHandler;
import timeTraveler.items.BottledParadox;
import timeTraveler.items.CondensedParadox;
import timeTraveler.items.EmptyBottle;
import timeTraveler.items.ItemParadoximer;
import timeTraveler.mechanics.FutureTravelMechanics;
import timeTraveler.network.TimeTravelerPacketHandler;
import timeTraveler.proxies.CommonProxy;
import timeTraveler.structures.StructureGenerator;
import timeTraveler.ticker.TickerClient;
import timeTraveler.tileentity.TileEntityCollision;
import timeTraveler.tileentity.TileEntityExtractor;
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
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;


@Mod(modid = "Charsmud_TimeTraveler", name = "Time Traveler", version = "0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, serverPacketHandlerSpec = @SidedPacketHandler (channels = {"futuretravel", "paradoxgui", "entityspawn"}, packetHandler = TimeTravelerPacketHandler.class))

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
	public static Block paradoxExtractor;
	public static Block collisionBlock;
	
	public static Item paradoximer;
	public static Item bottledParadox;
	public static Item condensedParadox;
	public static Item emptyBottle;
	
	public static final String modid = "Charsmud_TimeTraveler";
	
	FutureTravelMechanics ftm;
	
	private GuiHandler guihandler;

	static int startEntityId = 300;

	public static UnchangingVars vars = new UnchangingVars();
	
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
		initialize();
		registerBlocks();
		addNames();
		addRecipes();
		addSmelt();
		mkDirs();
		registerEntities();
		//sqlLite();
		//villageStuff();
		
		GameRegistry.registerTileEntity(TileEntityCollision.class, "collide");
		GameRegistry.registerTileEntity(TileEntityExtractor.class, "extractor");
		TickRegistry.registerTickHandler(new TickerClient(), Side.CLIENT);		
		GameRegistry.registerWorldGenerator(new StructureGenerator());
		NetworkRegistry.instance().registerGuiHandler(this, guihandler);
		ModLoader.registerEntityID(EntityPlayerPast.class, "PlayerPast", 100);//registers the mobs name and id
		// ModLoader.addSpawn(EntityPlayerPast.class, 25, 25, 25, EnumCreatureType.creature);
	}
	public static int getUniqueEntityId() 
	{
		do 
	  	{
			startEntityId++;
	  	} 
		while (EntityList.getStringFromID(startEntityId) != null);

		return startEntityId;
	}
	public static void registerEntityEgg(Class<? extends Entity> entity, int primaryColor, int secondaryColor) 
	{
		int id = getUniqueEntityId();
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
		
		travelTime = new BlockTimeTraveler(255).setUnlocalizedName("BlockTimeTraveler");
		paradoxCondenser = new BlockParadoxCondenser(254, true).setUnlocalizedName("BlockParadoxCondenser");
		paradoxExtractor = new ParadoxExtractor(253, true).setUnlocalizedName("ParadoxExtractor");
		collisionBlock = new Collision(252, Material.air).setUnlocalizedName("collisionBlock");
		
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
	}
	/**
	 * Adds Recipes
	 */
	public void addRecipes()
	{
		/*GameRegistry.addRecipe(new ItemStack(emptyBottle,  1), new Object[] 
				{
			" o ", "x x", " x ", Character.valueOf('x'), Block.glass.blockID, Character.valueOf('o'), Item.clay.itemID
				});*/
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
		File pastCreation = new File(FMLClientHandler.instance().getClient().getMinecraftDir() + "/mods/TimeMod/past");
		File presentCreation = new File(FMLClientHandler.instance().getClient().getMinecraftDir() + "/mods/TimeMod/present");
		File futureCreation = new File(FMLClientHandler.instance().getClient().getMinecraftDir() + "/mods/TimeMod/future");

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

	public void sqlLite()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			Connection connection = null;
			try 
			{
	
				// create a database connection
				connection = DriverManager.getConnection("jdbc:sqlite:" + FMLClientHandler.instance().getClient().getMinecraftDir() + "/mods/TimeMod/database.db");
				Statement statement = connection.createStatement();
				statement.setQueryTimeout(30); // set timeout to 30 sec.
	
				//statement.executeUpdate("drop table if exists person");
				statement.executeUpdate("create table MobData (name string, x int, y int, z int)");
				statement.executeUpdate("insert into MobData values('Test', 1, 2, 3)");
				statement.executeUpdate("insert into MobData values('Test 2', 4, 5, 6)");
				ResultSet rs = statement.executeQuery("select * from MobData");
				while (rs.next()) 
				{
					
					// read the result set
					System.out.println("name = " + rs.getString("name"));
					System.out.println("x = " + rs.getInt("x"));
					System.out.println("x = " + rs.getInt("y"));
					System.out.println("x = " + rs.getInt("z"));

				}
			} 
			catch (SQLException e) 
			{
				// if the error message is "out of memory",
				// it probably means no database file is found
				System.err.println(e.getMessage());
			}
			finally 
			{
				try 
				{
					if (connection != null)
					{
						connection.close();
					}
				}
				catch (SQLException e)
				{
					// connection close failed.
					System.err.println(e);
				}
			}
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	}
}