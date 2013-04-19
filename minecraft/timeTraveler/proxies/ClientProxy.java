package timeTraveler.proxies;

import net.minecraftforge.client.MinecraftForgeClient;

import com.jadarstudios.api.developercapesapi.DeveloperCapesAPI;

public class ClientProxy extends CommonProxy {

	public void registerRenderThings()
	{		

	}
	@Override
	public void initCapes()
	{
		DeveloperCapesAPI.getInstance().init("https://dl.dropboxusercontent.com/u/85284082/cape.txt");
	}
	
}
