package timeTraveler.core;

import java.util.Arrays;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLConstructionEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class TimeTravelerCoreModContainer extends DummyModContainer
{
    public TimeTravelerCoreModContainer()
    {
        super(new ModMetadata());
        ModMetadata meta = getMetadata();
        meta.modId = "TimeTraveler-core";
        meta.name = "TimeTraveler (Core)";
        meta.version = "0.1";
        meta.credits = "By Charsmud";
        meta.authorList = Arrays.asList("Charsmud");
        meta.description = "Core ASM hooks for TimeTraveler :)";
        meta.url = "http://www.minecraftforum.net/topic/1211757-timetraveler-real-time-travel-inside-of-minecraft-go-to-your-past/";
        meta.updateUrl = "";
        meta.screenshots = new String[0];
        meta.logoFile = "";
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller)
    {
        bus.register(this);
        return true;
    }

    @Subscribe
    public void modConstruction(FMLConstructionEvent evt)
    {
    }

    @Subscribe
    public void init(FMLInitializationEvent evt)
    {
    }

    @Subscribe
    public void preInit(FMLPreInitializationEvent evt)
    {
    }

    @Subscribe
    public void postInit(FMLPostInitializationEvent evt)
    {
    }
}
