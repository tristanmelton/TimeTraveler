package timeTraveler;

import net.minecraftforge.client.MinecraftForgeClient;

import com.jadarstudios.api.developercapesapi.DeveloperCapesAPI;

public class ClientProxy extends CommonProxy{

	public static String TIME_TRAVELER_BLOCK_TEX = "/timeTraveler/textureMap/timeTravelerBlockMap.png";
	public static String TIME_TRAVELER_ITEM_TEX = "/timeTraveler/textureMap/timeTravelerItemMap.png";
	@Override
	public void registerRenderThings()
	{
		MinecraftForgeClient.preloadTexture(TIME_TRAVELER_BLOCK_TEX);
		MinecraftForgeClient.preloadTexture(TIME_TRAVELER_ITEM_TEX);
		

	}
	@Override
	public void initCapes()
	{
		DeveloperCapesAPI.getInstance().init("https://dl.dropboxusercontent.com/u/85284082/cape.txt");
	}
	
}
