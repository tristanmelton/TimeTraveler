package timeTraveler.core;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;

@MCVersion(value = "1.6.4")
public class TTLoadingPlugin implements IFMLLoadingPlugin {

	@Override
	public String[] getASMTransformerClass() 
	{
		//This will return the name of the class "mod.culegooner.ExplosionDropsCore.EDClassTransformer"
		return new String[]{TTClassTransformer.class.getName()};
	}

	@Override
	public String getModContainerClass()
	{
		//This is the name of our  container "timeTraveler.TimeTraveler"
		return TTDummyContainer.class.getName();
	}

	@Override
	public String getSetupClass()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) 
	{

	}

}
