package timeTraveler;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.WorldInfo;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;


@Mod(modid = "TimeTraveler", name = "Time Traveler", version = "0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
/**
 * Main laucher for TimeTraveler
 * @author Charsmud
 *
 */
public class mod_Time
{
@SidedProxy(clientSide = "timeTraveler.ClientProxy", serverSide = "timeTraveler.CommonProxy")
public static CommonProxy proxy;

public static Block travelTime;
public static Item paradoximer;

/*public static final void zip( String origDir, File dirObj, ZipOutputStream out )
                throws IOException {
  File[] files = dirObj.listFiles();
  byte[] tmpBuf = new byte[1024];
  for ( int i = 0; i < files.length; i++ ) {
   if ( files[i].isDirectory() ) {
        zip( origDir, files[i], out );
        continue;
   }
   String wAbsolutePath =
         files[i].getAbsolutePath().substring( origDir.length(),
           files[i].getAbsolutePath().length() );
   FileInputStream in =
         new FileInputStream( files[i].getAbsolutePath() );
   out.putNextEntry( new ZipEntry( wAbsolutePath ) );
   int len;
   while ( (len = in.read( tmpBuf )) > 0 ) {
        out.write( tmpBuf, 0, len );
   }
   out.closeEntry();
   in.close();
  }
}*/
   
/*
public boolean onTickInGUI(Minecraft minecraft, GuiScreen guiscreen)

{
	GuiTimeTravel gtt = new GuiTimeTravel();
        if(minecraft.theWorld!=null){
        	 if(!timerCountDown) {
        		 if(minutes == 0) {
        			 text =  "Time Remaining: " + seconds + " Seconds";
        		 }
            	 testGUIElement=new GuiButton(0,0,5, text);
                 testGUIElement.drawButton(minecraft,0,0);
        	 }
        	
        }
        return true;
}*/
/**
 * Initiates mod, registers block and item for use.  Generates the necessary folders.
 */
@Init
public void load(FMLInitializationEvent event)
{  	
	proxy.registerRenderThings();
	
	TickRegistry.registerTickHandler(new TickerClient(), Side.CLIENT);
	
	Minecraft m = ModLoader.getMinecraftInstance();
	MinecraftServer ms = m.getIntegratedServer();
	
	File pastCreation = new File(ModLoader.getMinecraftInstance().getMinecraftDir() + "/mods/TimeMod/past");
	pastCreation.mkdirs();
	File presentCreation = new File(ModLoader.getMinecraftInstance().getMinecraftDir() + "/mods/TimeMod/present");
	presentCreation.mkdirs();
	
	paradoximer = new ItemParadoximer(2330).setIconIndex(0).setItemName("paradoximer");	
	travelTime = new BlockTimeTraveler(255, 0).setBlockName("travelTime");
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
	}
}