package timeTraveler.proxies;

import net.minecraftforge.client.MinecraftForgeClient;
import timeTraveler.core.TimeTraveler;
import timeTraveler.entities.EntityParadoxHunter;
import timeTraveler.models.ModelParadoxHunter;
import timeTraveler.render.ItemCondenserRenderer;
import timeTraveler.render.ItemTimeMachineRenderer;
import timeTraveler.render.RenderCondenser;
import timeTraveler.render.RenderExtractor;
import timeTraveler.render.RenderParadoxHunter;
import timeTraveler.render.RenderTimeMachine;
import timeTraveler.tileentity.TileEntityExtractor;
import timeTraveler.tileentity.TileEntityParadoxCondenser;
import timeTraveler.tileentity.TileEntityTimeTravel;

import com.jadarstudios.developercapes.DevCapes;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;


public class ClientProxy extends CommonProxy {

	public void registerRenderThings()
	{		
	    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityExtractor.class, new RenderExtractor());
	    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityParadoxCondenser.class, new RenderCondenser());
	    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTimeTravel.class, new RenderTimeMachine());
	    
	    RenderingRegistry.registerEntityRenderingHandler(EntityParadoxHunter.class,  new RenderParadoxHunter(new ModelParadoxHunter(),  0.3F));
	    
	    MinecraftForgeClient.registerItemRenderer(TimeTraveler.paradoxCondenser.blockID, new ItemCondenserRenderer());
	    MinecraftForgeClient.registerItemRenderer(TimeTraveler.timeTravel.blockID, new ItemTimeMachineRenderer());
	}
	@Override
	public void initCapes()
	{
		DevCapes.getInstance().registerConfig("https://raw2.github.com/jadar/TimeTraveler/master/capes/capes.json", TimeTraveler.modid);
	}
	
}
