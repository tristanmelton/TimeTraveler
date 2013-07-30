package timeTraveler.ticker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.EnumSet;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import timeTraveler.core.TimeTraveler;
import timeTraveler.entities.EntityPlayerPast;
import timeTraveler.gui.GuiTimeTravel;
import timeTraveler.mechanics.CopyFile;
import timeTraveler.mechanics.EntityMechanics;
import timeTraveler.mechanics.PastMechanics;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

/**
 * Ticker
 * @author Charsmud
 *
 */
public class TickerClient implements ITickHandler 
{

	public int ctr;
	public int ct;
	public int count;
	
	public static int paradoxLevel;
	
	public static int seconds = 10;
	public static int minutes = 1;
	
	public int invisPotTime = 0;
	public int sneakTime = 0;
	
	private int timeNumber = 1;


	/*int prevSheep;
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
	int prevZom;*/

	String text;

	CopyFile copyFile = new CopyFile();

	public boolean hasRun = false;
	public boolean hasInitMobs = false;
	
	private boolean nextSet = true;
 
	private boolean isInPast;
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
	}
	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		if(type.equals(EnumSet.of(TickType.RENDER)))
		{
			onRenderTick();
		}
		else
			if(type.equals(EnumSet.of(TickType.CLIENT)))
			{
			     GuiScreen curScreen = Minecraft.getMinecraft().currentScreen;
			     if(curScreen != null)
			     {
			          onTickInGui(FMLClientHandler.instance().getClient(), curScreen);
			     }
			     else 
			     {
			    	 onTickInGame(FMLClientHandler.instance().getClient());
			     }

			}
	}
	private void onTickInGame(Minecraft mc)
	{
		ctr++;
		ct++;

		PastMechanics mechanics = new PastMechanics();
		EntityMechanics entityMechanics = new EntityMechanics();
		
	    text  = "Time Remaining: " + minutes + " Minute, " + seconds + " Seconds";

		isInPast = GuiTimeTravel.isInPast;		
		if(!isInPast)
		{
			if(mc.thePlayer.isJumping)
			{
			}
			if(mc.thePlayer.isSneaking())
			{
			}
		}
		
		if(ct == 20)
		{
			if(!isInPast)
			{
				mechanics.addEntityData(TimeTraveler.vars.getEntiyLocData());
				
				ct = 0;
			}
		}
		
		if(ctr == 20 * 60)
		{
			if(!isInPast)
			{
				mechanics.saveTime(mc.getIntegratedServer(), mc, copyFile);
				//mechanics.addPlayerLoc(mc.getIntegratedServer(), mc, "stop");
				//mechanics.addPlayerLoc(mc.getIntegratedServer(), mc, "newtime");
				mechanics.saveEntityData(TimeTraveler.vars.getEntiyLocData(), mc.getIntegratedServer());
			}
			ctr = 0;
		}
		if(!hasRun)
		{
			hasRun = true;
			mechanics.firstTime(mc.getIntegratedServer(), mc);
		}
		if(isInPast)
		{			
			
			mc.theWorld.getGameRules().setOrCreateGameRule("doMobSpawning", "false");
			
			
			File allEntityData = new File(mc.getMinecraftDir() + "/mods/TimeMod/past/EntityLocations/" + FMLClientHandler.instance().getServer().getWorldName() + "/" + TimeTraveler.vars.getPastTime() + ".epd");
					
			try 
			{
				BufferedReader reader = new BufferedReader(new FileReader(allEntityData));
				String line;
				while (((line = reader.readLine()) != null) && nextSet) 
				{
					if(line.equals("===================="))
					{
						nextSet = false;
					}
					else 
					{
						String[] entityData = line.split(",");
									
						String entityName = entityData[0];
						int entityX = Integer.parseInt(entityData[1]);
						int entityY = Integer.parseInt(entityData[2]);
						int entityZ = Integer.parseInt(entityData[3]);
									
						//System.out.println(entityName + " " + entityX + " " + entityY + " " + entityZ);
						Entity pastEntity = EntityList.createEntityByName(entityName, mc.thePlayer.worldObj);
							
						if(pastEntity != null)
						{
							PathEntity path = ((EntityLiving)pastEntity).getNavigator().getPath();
							if(pastEntity != null)
							{
								pastEntity.posX = (double)entityX;
								pastEntity.posY = (double)entityY;
								pastEntity.posZ = (double)entityZ;
											
								System.out.println(pastEntity);
								mc.thePlayer.worldObj.spawnEntityInWorld(pastEntity);
							}

						}
						else
						{
							EntityPlayerPast pastPlayer = new EntityPlayerPast(mc.thePlayer.worldObj);
							pastPlayer.posX = (double)entityX;
							pastPlayer.posY = (double)entityY;
							pastPlayer.posZ = (double)entityZ;
							System.out.println(pastPlayer);
							mc.thePlayer.worldObj.spawnEntityInWorld(pastPlayer);
						}
																	
				        //path = ((EntityLiving)pastEntity).getNavigator().getPathToXYZ((double)entityX, (double)entityY, (double)entityZ);
				            		
				        //((EntityLiving)pastEntity).getNavigator().setPath(path, 1.0F);
				        //((EntityLiving)pastEntity).getNavigator().tryMoveToXYZ((double)entityX, (double)entityY, (double)entityZ, 0.5F);

					}
					System.out.println(nextSet);
				}
				reader.close();	
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			} 
			
			count++;
			if(paradoxLevel < 0)
			{
				paradoxLevel = 0;
			}
			if(paradoxLevel <= 128)
			{
				EntityPlayerSP eps = mc.thePlayer;
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
								/*WorldClient w = mc.theWorld;
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
				if(!hasInitMobs) 
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
				}*/
			}
			else
			{
				mechanics.returnToPresent(mc, paradoxLevel, mc.getIntegratedServer());
			}
		}
		if(isInPast)
		{
			if(count == 20)
			{
				seconds--;
				count = 0;
			}
			if(seconds == 0)
			{
				minutes--;
				seconds = 60;
			}
			if(minutes == 0)
			{
				text = "Time Remaining: " + seconds + " Seconds";
			}
			if(minutes <= 0 && seconds <= 1)
			{
				mechanics.outOfTime(mc, mc.getIntegratedServer(), text);
			}
		}		
	}
	private void onTickInGui(Minecraft mc, GuiScreen gui)
	{
	}
	private void onRenderTick()
	{
		Minecraft mc = FMLClientHandler.instance().getClient();
		PastMechanics mechanics = new PastMechanics();

        if(mc.currentScreen == null)
        {
        	if(isInPast)
        	{
				mechanics.drawTimeTicker(mc, text);
        	}
    		mechanics.updateParadoxBar(mc, paradoxLevel);
        }

	}
	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.CLIENT, TickType.RENDER);
	}
	@Override
	public String getLabel()
	{
		return "TickHandler.CLIENT";
	}
}
