package net.minecraft.src;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;
import java.io.*;

import org.lwjgl.opengl.GL11;

import static java.nio.file.StandardCopyOption.*;

public class mod_Time extends BaseMod
{
public static Block travelTime;
public static Item paradoximer;

CopyFile cf;

public static boolean b = false;
boolean hasRun = false;

public int ctr;
public int ct;
public int count;
public int paradoxLevel;
public int seconds = 30;
public int minutes = 1;
public int invisPotTime = 0;
public int sneakTime = 0;

int prevSheep;
int prevPig;
int prevCow;
int prevChick;
int prevSqu;
int prevWol;
int prevOce;
int prevBat;
int prevGol;
int prevMoo;
int prevVil;

int prevEnd;
int prevZpi;
int prevBla;
int prevCsp;
int prevCre;
int prevGha;
int prevMag;
int prevSil;
int prevSke;
int prevSli;
int prevSpi;
int prevWit;
int prevZom;

public int counter = 0;

GuiButton testGUIElement;

String text;

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
public void load()
{  	
	Minecraft m = ModLoader.getMinecraftInstance();
	MinecraftServer ms = m.getIntegratedServer();
	
	File pastCreation = new File(ModLoader.getMinecraftInstance().getMinecraftDir() + "/mods/TimeMod/past");
	pastCreation.mkdirs();
	File presentCreation = new File(ModLoader.getMinecraftInstance().getMinecraftDir() + "/mods/TimeMod/present");
	presentCreation.mkdirs();
	//File playerLoc = new File(ModLoader.getMinecraftInstance().getMinecraftDir() + "/mods/TimeMod/past/" + ms.getWorldName() + "/playerLoc");
	//playerLoc.mkdirs();
	
	paradoximer = new ItemParadoximer(2330).setItemName("paradoximer");	
	ModLoader.setInGameHook(this, true, false);
	travelTime = new BlockTimeTraveler(255, 0).setBlockName("travelTime");
	ModLoader.registerBlock(travelTime);
	ModLoader.addName(travelTime, "Paradox Cube");
	ModLoader.addName(paradoximer, "Paradoximer");
	ModLoader.addRecipe(new ItemStack(travelTime,  13), new Object[] 
			{
				"x", Character.valueOf('x'), Block.dirt
			});
  ModLoader.addRecipe(new ItemStack(paradoximer,  13), new Object[] 
		  {
	  			"x", "s", Character.valueOf('x'), Block.wood, Character.valueOf('s'), Block.dirt
		  });
  	ModLoader.registerEntityID(EntityPlayerPast.class, "PlayerPast", 100);//registers the mobs name and id

    ModLoader.addSpawn(EntityPlayerPast.class, 25, 25, 25, EnumCreatureType.creature);
}

/**
 * Adds render for PlayerPast
 */
public void addRenderer(Map var1)
{
var1.put(EntityPlayerPast.class, new RenderLiving(new ModelBiped(),.5f));//says that the pigman should use the living renderer and the biped model note you can change the renderer and the model
}


/**
 * Runs once every tick.  Currently handles Paradox Level, Rendering paradox bar, handling Time Zone saving
 */
public boolean onTickInGame(float f, Minecraft minecraft)
{
	MinecraftServer ms = minecraft.getIntegratedServer();
	
	//WorldClient w = minecraft.theWorld;
	//WorldInfo wi = w.getWorldInfo();
	
    ScaledResolution var5 = new ScaledResolution(minecraft.gameSettings, minecraft.displayWidth, minecraft.displayHeight);
   
    int var6 = var5.getScaledWidth();
    int var7 = var5.getScaledHeight();
    int var8 = var7 / var7 + 25;
    
    int playerX = (int) minecraft.thePlayer.posX;
    int playerY = (int) minecraft.thePlayer.posY;
    int playerZ = (int) minecraft.thePlayer.posZ;
    
    ctr++;
	count++;

    
    text  = "Time Remaining: " + minutes + " Minutes, " + seconds + " Seconds";
	
    GuiTimeTravel gtt = new GuiTimeTravel();
	
    b = gtt.isInPast;
	
    GuiIngame gig = new GuiIngame(minecraft);
	
    //Render Paradox Bar
    GL11.glBindTexture(GL11.GL_TEXTURE_2D, minecraft.renderEngine.getTexture("/TimeMod/gui/newGUIElements.png"));
	gig.drawTexturedModalRect(var6 / 2 - 200, var8, 0, 0, 128, 8);
	
    File beginningOfWorld = new File(ModLoader.getMinecraftInstance().getMinecraftDir() + "/mods/TimeMod/present/" + ms.getWorldName());
	File playerLoc = new File(ModLoader.getMinecraftInstance().getMinecraftDir() + "/mods/TimeMod/past/" + ms.getWorldName() + "/playerLoc");
    
    if(count == 240)
    {
    	playerLoc(playerLoc, minecraft);
    	count = 0;
    }
    
    if(beginningOfWorld.length() == 0)
    {
    	File initWorldGen = new File(ModLoader.getMinecraftInstance().getMinecraftDir() + "/mods/TimeMod/past/" + ms.getWorldName());
    	initWorldGen.mkdirs();
        File fi = new File(ModLoader.getMinecraftInstance().getMinecraftDir() + "/saves/" + ms.getWorldName() + "/region");
        File moveTo = new File(ModLoader.getMinecraftInstance().getMinecraftDir() + "/mods/TimeMod/past/" + ms.getWorldName() + "/Time 001");
        try
        {
            cf.copyDirectory(fi, moveTo);
        }
        catch(IOException ex)
        {
        	ex.printStackTrace();
        }
    }
	//Paradox Bar Computations
	if(b)
	{
		if(paradoxLevel < 0) {
			paradoxLevel = 0;
		}
		if(paradoxLevel <= 128)
		{
			EntityPlayerSP eps = minecraft.thePlayer;
			if(eps.isPotionActive(Potion.invisibility))
			{
				invisPotTime++;
				if(invisPotTime == 10)
				{
					paradoxLevel = paradoxLevel - 5;
					invisPotTime = 0;
				}
			}
			if(eps.isEating()) 
			{
				paradoxLevel++;
				eps.experienceTotal = eps.experienceTotal + 2;
			}
			if(eps.isSneaking())
			{
				sneakTime++;
				if(sneakTime == 30)
					{
					paradoxLevel = paradoxLevel - 2;
					sneakTime = 0;
					}
			}
		/*	WorldClient w = minecraft.theWorld;
			//Passive Mobs
			EntitySheep es = new EntitySheep(w);
			EntityPig ep = new EntityPig(w);
			EntityCow ec = new EntityCow(w);
			EntityChicken eck = new EntityChicken(w);
			EntitySquid esq = new EntitySquid(w);
			EntityWolf ew = new EntityWolf(w);
			EntityOcelot eo = new EntityOcelot(w);
			EntityBat eb = new EntityBat(w);
			EntityIronGolem ei = new EntityIronGolem(w);
			EntityMooshroom em = new EntityMooshroom(w);
			EntityVillager ev = new EntityVillager(w);
			EntityEnderman ee = new EntityEnderman(w);
			EntityPigZombie ezp = new EntityPigZombie(w);
			EntityBlaze ebl = new EntityBlaze(w);
			EntityCaveSpider ecs = new EntityCaveSpider(w);
			EntityCreeper ecr = new EntityCreeper(w);
			EntityGhast eg = new EntityGhast(w);
			EntityMagmaCube emc = new EntityMagmaCube(w);
			EntitySilverfish esi = new EntitySilverfish(w);
			EntitySkeleton esk = new EntitySkeleton(w);
			EntitySlime esl = new EntitySlime(w);
			EntitySpider esp = new EntitySpider(w);
			EntityWitch ewi = new EntityWitch(w);
			EntityZombie ez = new EntityZombie(w);
			if(!hasRun) 
			{
				 prevSheep = w.countEntities(es.getClass());
				 prevPig = w.countEntities(ep.getClass());
				 prevCow = w.countEntities(ec.getClass());
				 prevChick = w.countEntities(eck.getClass());
				 prevSqu = w.countEntities(esq.getClass());
				 prevWol = w.countEntities(ew.getClass());
				 prevOce = w.countEntities(eo.getClass());
				 prevBat = w.countEntities(eb.getClass());
				 prevGol = w.countEntities(ei.getClass());
				 prevMoo = w.countEntities(ei.getClass());
				 prevVil = w.countEntities(ev.getClass());
				 prevEnd = w.countEntities(ee.getClass());
				 prevZpi = w.countEntities(ezp.getClass());
				 prevBla = w.countEntities(ebl.getClass());
				 prevCsp = w.countEntities(ecs.getClass());
				 prevCre = w.countEntities(ecr.getClass());
				 prevGha = w.countEntities(eg.getClass());
				 prevMag = w.countEntities(emc.getClass());
				 prevSil = w.countEntities(esi.getClass());
				 prevSke = w.countEntities(esk.getClass());
				 prevSli = w.countEntities(esl.getClass());
				 prevSpi = w.countEntities(esp.getClass());
				 prevWit = w.countEntities(ewi.getClass());
				 prevZom = w.countEntities(ez.getClass());
				 hasRun = true;
			}
			if(prevSheep > w.countEntities(es.getClass()))
			{
				paradoxLevel = paradoxLevel + 4;
				prevSheep = w.countEntities(es.getClass());
			}
			if(prevPig > w.countEntities(ep.getClass()))
			{
				paradoxLevel = paradoxLevel + 8;
				prevPig = w.countEntities(ep.getClass());
			}
			if(prevCow > w.countEntities(ec.getClass()))
			{
				paradoxLevel = paradoxLevel + 12;
				prevCow = w.countEntities(ec.getClass());
			}
			if(prevChick > w.countEntities(eck.getClass()))
			{
				paradoxLevel = paradoxLevel + 7;
				prevChick = w.countEntities(eck.getClass());
			}
			if(prevSqu > w.countEntities(esq.getClass()))
			{
				paradoxLevel = paradoxLevel + 2;
				prevSqu = w.countEntities(esq.getClass());
			}
			if(prevWol > w.countEntities(ew.getClass()))
			{
				paradoxLevel = paradoxLevel + 14;
				prevWol = w.countEntities(ew.getClass());
			}
			if(prevOce > w.countEntities(eo.getClass())){
				paradoxLevel = paradoxLevel + 20;
				prevOce = w.countEntities(eo.getClass());
			}
			if(prevBat > w.countEntities(eb.getClass()))
			{
				paradoxLevel++;
				prevBat = w.countEntities(eo.getClass());
			}
			if(prevMoo > w.countEntities(em.getClass()))
			{
				paradoxLevel = paradoxLevel + 14;
				prevMoo = w.countEntities(em.getClass());
			}
			if(prevGol > w.countEntities(ei.getClass()))
			{
				paradoxLevel = paradoxLevel + 78;
				prevBat = w.countEntities(ei.getClass());
			}
			if(prevVil > w.countEntities(ev.getClass()))
			{
				paradoxLevel = paradoxLevel + 55;
				prevVil = w.countEntities(ev.getClass());
			}
			if(prevEnd > w.countEntities(ee.getClass()))
			{
				paradoxLevel = paradoxLevel + 25;
				prevEnd = w.countEntities(ee.getClass());
			}
			if(prevZpi > w.countEntities(ezp.getClass()))
			{
				paradoxLevel = paradoxLevel + 19;
				prevZpi = w.countEntities(ezp.getClass());
			}
			if(prevBla > w.countEntities(ebl.getClass())) 
			{
				paradoxLevel = paradoxLevel + 33;
				prevBla = w.countEntities(ebl.getClass());
			}
			if(prevCsp > w.countEntities(ecs.getClass()))
			{
				paradoxLevel = paradoxLevel + 29;
				prevCsp = w.countEntities(ecs.getClass());
			}
			if(prevCre > w.countEntities(ecr.getClass()))
			{
				paradoxLevel = paradoxLevel + 69;
				prevCre = w.countEntities(ecr.getClass());
			}
			if(prevGha > w.countEntities(eg.getClass()))
			{
				paradoxLevel = paradoxLevel + 56;
				prevGha = w.countEntities(eg.getClass());
			}
			if(prevMag > w.countEntities(emc.getClass()))
			{
				paradoxLevel = paradoxLevel + 10;
				prevMag = w.countEntities(emc.getClass());
			}
			if(prevSil > w.countEntities(esi.getClass()))
			{
				paradoxLevel = paradoxLevel + 4;
				prevSil = w.countEntities(esi.getClass());
			}
			if(prevSke > w.countEntities(esk.getClass()))
			{
				paradoxLevel = paradoxLevel + 34;
				prevSke = w.countEntities(esk.getClass());
			}
			if(prevSli > w.countEntities(esl.getClass()))
			{
				paradoxLevel = paradoxLevel + 10;
				prevSli = w.countEntities(esl.getClass());
			}
			if(prevSpi > w.countEntities(esp.getClass()))
			{
				paradoxLevel = paradoxLevel + 27;
				prevSpi = w.countEntities(esp.getClass());
			}
			if(prevWit > w.countEntities(ewi.getClass()))
			{
				paradoxLevel = paradoxLevel + 49;
				prevWit = w.countEntities(ewi.getClass());
			}
			if(prevZom > w.countEntities(ez.getClass()))
			{
				paradoxLevel = paradoxLevel + 20;
				prevZom = w.countEntities(ez.getClass());
			}
			
			if(prevSheep < w.countEntities(es.getClass()))
			{
				prevSheep = w.countEntities(es.getClass());
			}
			if(prevPig < w.countEntities(ep.getClass()))
			{
				prevPig = w.countEntities(ep.getClass());
			}
			if(prevCow < w.countEntities(ec.getClass()))
			{
				prevCow = w.countEntities(ec.getClass());
			}
			if(prevChick < w.countEntities(eck.getClass()))
			{
				prevChick = w.countEntities(eck.getClass());
			}
			if(prevSqu < w.countEntities(esq.getClass()))
			{
				prevSqu = w.countEntities(esq.getClass());
			}
			if(prevWol < w.countEntities(ew.getClass()))
			{
				prevWol = w.countEntities(ew.getClass());
			}
			if(prevOce < w.countEntities(eo.getClass()))
			{
				prevOce = w.countEntities(eo.getClass());
			}
			if(prevBat < w.countEntities(eb.getClass()))
			{
				prevBat = w.countEntities(eb.getClass());
			}
			if(prevGol < w.countEntities(ei.getClass()))
			{
				prevGol = w.countEntities(ei.getClass());
			}
			if(prevMoo < w.countEntities(em.getClass()))
			{
				prevMoo = w.countEntities(em.getClass());
			}
			if(prevVil < w.countEntities(ev.getClass()))
			{
				prevVil = w.countEntities(ev.getClass());
			}
			if(prevEnd < w.countEntities(ee.getClass())) 
			{
				prevEnd = w.countEntities(ee.getClass());
			}
			if(prevZpi < w.countEntities(ezp.getClass()))
			{
				prevZpi = w.countEntities(ezp.getClass());
			}
			if(prevBla < w.countEntities(ebl.getClass()))
			{
				prevBla = w.countEntities(ebl.getClass());
			}
			if(prevCsp < w.countEntities(ecs.getClass()))
			{
				prevCsp = w.countEntities(ecs.getClass());
			}
			if(prevCre < w.countEntities(ecr.getClass()))
			{
				prevCre = w.countEntities(ecr.getClass());
			}
			if(prevGha < w.countEntities(eg.getClass()))
			{
				prevGha = w.countEntities(eg.getClass());
			}
			if(prevMag < w.countEntities(emc.getClass()))
			{
				prevMag = w.countEntities(emc.getClass());
			}
			if(prevSil < w.countEntities(esi.getClass()))
			{
				prevSil = w.countEntities(esi.getClass());
			}
			if(prevSke < w.countEntities(esk.getClass()))
			{
				prevSke = w.countEntities(esk.getClass());
			}
			if(prevSli < w.countEntities(esl.getClass()))
			{
				prevSli = w.countEntities(esl.getClass());
			}
			if(prevSpi < w.countEntities(esp.getClass()))
			{
				prevSpi = w.countEntities(esp.getClass());
			}
			if(prevWit < w.countEntities(ewi.getClass()))
			{
				prevWit = w.countEntities(ewi.getClass());
			}
			if(prevZom < w.countEntities(ez.getClass()))
			{
				prevZom = w.countEntities(ez.getClass());
			}
*/
			//Update Paradox Bar Depending Upon Gain or Loss of Paradox
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, minecraft.renderEngine.getTexture("/TimeMod/gui/newGUIElements.png"));
			gig.drawTexturedModalRect(var6 / 2 - 200, var8, 0, 8, paradoxLevel, 8);

		}
		else 
		{
			//Player exceeded Paradox Limit
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, minecraft.renderEngine.getTexture("/TimeMod/gui/newGUIElements.png"));
			gig.drawTexturedModalRect(var6 / 2 - 200, var8, 0, 8, 128, 8);
			
			EntityPlayer ep = minecraft.thePlayer;
			ep.setDead();
			
			paradoxLevel = 0;
			
			
            minecraft.statFileWriter.readStat(StatList.leaveGameStat, 1);
            minecraft.theWorld.sendQuittingDisconnectingPacket();
            minecraft.loadWorld((WorldClient)null);
            minecraft.displayGuiScreen(new GuiMainMenu());
            
            //minecraft.loadWorld(w);
            
            File present = new File(ModLoader.getMinecraftInstance().getMinecraftDir() + "/mods/TimeMod/present/" + ms.getWorldName());
            File worldFileDest = GuiTimeTravel.staticsource;
            File worldFile = new File(ModLoader.getMinecraftInstance().getMinecraftDir() + "/saves/" + ms.getWorldName() + "/region");
            
            System.out.println(present);
            System.out.println(worldFileDest);
            System.out.println(worldFile);
            try {
            	Thread.sleep(3000);
            	CopyFile.moveMultipleFiles(worldFile, worldFileDest);
                Thread.sleep(1000);
            	CopyFile.moveMultipleFiles(present, worldFile);
                gtt.isInPast = false;
            }
            catch (Exception ex) {
            	ex.printStackTrace();
            }
            minutes = 1;
            seconds = 30;
            
		}
	}
	   
	   if(b) 
	   {
			ct++;
			if(minutes <= 0) {
				text = "Time Remaining: " + seconds + " Seconds";
				if(seconds <= 0) {					
		            minecraft.statFileWriter.readStat(StatList.leaveGameStat, 1);
		            minecraft.theWorld.sendQuittingDisconnectingPacket();
		            minecraft.loadWorld((WorldClient)null);
		            minecraft.displayGuiScreen(new GuiMainMenu());
		           
		            //minecraft.loadWorld(w);
		            File present = new File(ModLoader.getMinecraftInstance().getMinecraftDir() + "/mods/TimeMod/present/" + ms.getWorldName());
		            File worldFileDest = GuiTimeTravel.staticsource;
		            File worldFile = new File(ModLoader.getMinecraftInstance().getMinecraftDir() + "/saves/" + ms.getWorldName() + "/region");
		           
		            System.out.println(worldFileDest + " " + gtt.getSaveNumber());
		            System.out.println(present);
		            System.out.println(worldFileDest);
		            System.out.println(worldFile);
		            
		            minutes = 1;
		            seconds = 30;

		            try {
		            	Thread.sleep(3000);
		            	CopyFile.moveMultipleFiles(worldFile, worldFileDest);
                        Thread.sleep(1000);
		            	CopyFile.moveMultipleFiles(present, worldFile);
                        gtt.isInPast = false;
		            }
		            catch (Exception ex) {
		            	ex.printStackTrace();
		            }
		          
				}
			}
			if(b) {
				if(ct == 20) {
					seconds--;
					ct = 0;
					if(seconds == 0) {
						minutes--;
						seconds = 60;
						if(minutes == 0) {
							text = "Time Remaining: " + seconds + " Seconds";
						}
					}
				}
			} 
			testGUIElement=new GuiButton(0,0,5, text);
			testGUIElement.drawButton(minecraft,0,0);
		   
	   }
	   
  if(ctr == 20 * 60) //20 is the amount of ticks per second. Times 60 for 1 minute.
   //change to 350 for final release
	  
  {
   if(!b) {
   WorldInfo we = minecraft.theWorld.getWorldInfo();
   
   File fil = new File(Minecraft.getMinecraftDir(), "mods/TimeMod/past/" + ms.getWorldName());
   
   if(!fil.exists())
   {
	   fil.mkdir();
   }

   		  //Happens after ctr ticks
   		  int counterstart = new File(Minecraft.getMinecraftDir(), "mods/TimeMod/past/" + ms.getWorldName()).listFiles().length;
          counter = counterstart;
          
          try
          {
           WorldInfo worldinfo = minecraft.theWorld.getWorldInfo();
  
           File fi = new File(ModLoader.getMinecraftInstance().getMinecraftDir() + "\\saves\\" + ms.getWorldName() + "\\region");
           File f2 = new File (ModLoader.getMinecraftInstance().getMinecraftDir() + "\\mods\\TimeMod\\past\\" + ms.getWorldName());
           f2.mkdir();

           String fname = ModLoader.getMinecraftInstance().getMinecraftDir() + "\\mods\\TimeMod\\past\\" + ms.getWorldName() + "\\Time ";
           counter = counter + 1;
           fname = fname.concat(String.format("%03d",counter));
          
           File directoryToMoveTo = new File(fname);
                
           cf = new CopyFile();
   
           cf.copyDirectory(fi, directoryToMoveTo);
          
           System.out.println("Created a time!");       
           //File destination = new File(Minecraft.getMinecraftDir(), "mods/TimeMod/past");
           //File zipped = new File(Minecraft.getMinecraftDir(), "mods/TimeMod/past/w1.zip");
           
           // copyfiletime.unzip(zipped, destination);  
          }
          catch(Exception ex) {
           ex.printStackTrace();
          }
          ctr = 0;
         
          //Reset the count.
   }//ends ifInPast
   
   else {
        System.out.println("Player in past, cannot backup");
   }
  }
  return true;
}

/**
 * Gets Mod Version
 */
public String getVersion()
{
	return "1.4.6";
}

/**
 * Generate the Time Travel Structure
 */
public void generateSurface(World world, Random rand, int y, int z)
	{
     for(int k = 0; k < 100; k++)
     {
         int RandPosX = y + rand.nextInt(16);
         int RandPosY = rand.nextInt(128);
         int RandPosZ = z + rand.nextInt(16);
         (new WorldGenPastTemple()).generate(world, rand, RandPosX, RandPosY, RandPosZ);

     }
 }
public void playerLoc(File destToSave, Minecraft minecraft)
{
	EntityPlayer ep = minecraft.thePlayer;
	
	int playerX = (int)ep.posX;
	int playerY = (int)ep.posY;
	int playerZ = (int)ep.posZ;
	
	System.out.println(playerX + " " + playerY + " " + playerZ);
	
	if(!destToSave.exists())
	{
		destToSave.mkdirs();
	}
	
	/*if(destToSave.listFiles().length == 0)
	{
		File primaryLoc = new File(destToSave + "/loc1.txt");		
		try
		{
	        BufferedWriter out = new BufferedWriter(new FileWriter(primaryLoc));
	        out.write(Integer.toString(playerX));
	        out.newLine();
	        out.write(Integer.toString(playerY));
	        out.newLine();
	        out.write(Integer.toString(playerZ));
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
	}*/
	//else
	//{
		File nextLoc = new File(destToSave + "/loc" + ((destToSave.listFiles().length) + 1) + ".txt");
		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(nextLoc));
			out.write(Integer.toString(playerX));
			out.newLine();
			out.write(Integer.toString(playerY));
			out.newLine();
			out.write(Integer.toString(playerZ));
			out.flush();
			out.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	//}
}
}