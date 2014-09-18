package timeTraveler.proxies;

import timeTraveler.models.ModelSlowTimeArmor;
import net.minecraft.client.model.ModelBiped;


public class CommonProxy
{
	private static final ModelSlowTimeArmor tutChest = new ModelSlowTimeArmor(1.0f);

	public void registerRenderThings()
	{
		
	}
	public void initCapes()
	{
		
	}
	public ModelBiped getArmorModel(int id)
	{ 
		switch (id)
		{ 
		case 0:
			return tutChest; 
		default:
			break;
		}
		return tutChest; //default, if whenever you should have passed on a wrong id }[
	}
}
