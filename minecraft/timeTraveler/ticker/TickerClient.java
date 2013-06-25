package timeTraveler.ticker;
import java.util.EnumSet;
import java.util.List;


import org.lwjgl.opengl.GL11;

import timeTraveler.core.TimeTraveler;
import timeTraveler.gui.GuiTimeTravel;
import timeTraveler.mechanics.CopyFile;
import timeTraveler.mechanics.PastMechanics;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.EntityLiving;
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
import net.minecraft.potion.Potion;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
/**
 * Ticker
 * @author Charsmud
 *
 */
public class TickerClient implements ITickHandler {

	public int ctr;
	public int ct;
	public int count;
	public static int paradoxLevel;
	public int seconds = 10;
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

	String text;

	CopyFile copyFile = new CopyFile();

	public boolean hasRun = false;
	public boolean hasInitMobs = false;

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

	    text  = "Time Remaining: " + minutes + " Minute, " + seconds + " Seconds";

		isInPast = GuiTimeTravel.isInPast;

		if(ct == 20)
		{
			if(!isInPast)
			{
				//mechanics.addPlayerLoc(mc.getIntegratedServer(), mc, "");
				mechanics.addEntityData(mc.thePlayer, TimeTraveler.vars.getEntiyLocData());
				
				List<EntityLiving> allEntities = mc.theWorld.loadedEntityList;
				
				for(int i = 0; i < allEntities.size(); i++)
				{
					if(allEntities.get(i) instanceof EntityLiving)
					{
						mechanics.addEntityData(allEntities.get(i), TimeTraveler.vars.getEntiyLocData());
					}
				}
				ct = 0;
			}
		}
		if(!isInPast)
		{
			if(mc.thePlayer.isJumping)
			{
				//mechanics.addPlayerLoc(mc.getIntegratedServer(), mc, "jump");
			}
			if(mc.thePlayer.isSneaking())
			{
				//mechanics.addPlayerLoc(mc.getIntegratedServer(), mc, "sneak");
			}
		}
		if(ctr == 20 * 60)
		{
			if(!isInPast)
			{
				//mechanics.saveTime(mc.getIntegratedServer(), mc, copyFile);
				//mechanics.addPlayerLoc(mc.getIntegratedServer(), mc, "stop");
				//mechanics.addPlayerLoc(mc.getIntegratedServer(), mc, "newtime");
				mechanics.saveEntityData(TimeTraveler.vars.getEntiyLocData());
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
				WorldClient w = mc.theWorld;
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
				}
			}
			else
			{
				mechanics.returnToPresent(mc, paradoxLevel, mc.getIntegratedServer(), minutes, seconds);
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
				mechanics.outOfTime(mc, mc.getIntegratedServer(), minutes, seconds, text);
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
