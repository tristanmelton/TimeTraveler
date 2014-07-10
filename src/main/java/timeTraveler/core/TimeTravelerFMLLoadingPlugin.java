package timeTraveler.core;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;

@MCVersion(value = "1.6.4")
public class TimeTravelerFMLLoadingPlugin implements IFMLLoadingPlugin
{
    public String[] getLibraryRequestClass()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String[] getASMTransformerClass()
    {
        // TODO Auto-generated method stub
        return new String[] {TimeTravelerClassTransformer.class.getName()};
    }

    @Override
    public String getModContainerClass()
    {
        // TODO Auto-generated method stub
        return TimeTravelerCoreModContainer.class.getName();
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
        // TODO Auto-generated method stub
    }
}
