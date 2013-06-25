package timeTraveler.proxies;

import timeTraveler.entities.EntityParadoxHunter;
import timeTraveler.models.ModelParadoxHunter;
import timeTraveler.render.RenderExtractor;
import timeTraveler.render.RenderParadoxHunter;
import timeTraveler.tileentity.TileEntityExtractor;

import com.jadarstudios.api.developercapesapi.DeveloperCapesAPI;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	public void registerRenderThings()
	{		

	}
	@Override
	public void initCapes()
	{
		DeveloperCapesAPI.getInstance().init("https://dl.dropboxusercontent.com/u/85284082/cape.txt");
	    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityExtractor.class, new RenderExtractor());
	    RenderingRegistry.registerEntityRenderingHandler(EntityParadoxHunter.class,  new RenderParadoxHunter(new ModelParadoxHunter(),  0.3F));
	
	}
	
}
