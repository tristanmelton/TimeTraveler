package timeTraveler.proxies;

import timeTraveler.render.RenderExtractor;
import timeTraveler.tileentity.TileEntityExtractor;

import com.jadarstudios.api.developercapesapi.DeveloperCapesAPI;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	public void registerRenderThings()
	{		

	}
	@Override
	public void initCapes()
	{
		DeveloperCapesAPI.getInstance().init("https://dl.dropboxusercontent.com/u/85284082/cape.txt");
	    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityExtractor.class, new RenderExtractor());

	
	}
	
}
