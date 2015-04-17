package timeTraveler.proxies;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderXPOrb;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import timeTraveler.core.TimeTraveler;
import timeTraveler.entities.EntityParadoxHunter;
import timeTraveler.entities.EntityPlayerPast;
import timeTraveler.entities.EntityXPOrbTT;
import timeTraveler.models.ModelParadoxHunter;
import timeTraveler.models.ModelSlowTimeArmor;
import timeTraveler.render.ItemCondenserRenderer;
import timeTraveler.render.ItemMarkerRenderer;
import timeTraveler.render.ItemTimeDistorterRenderer;
import timeTraveler.render.ItemTimeMachineRenderer;
import timeTraveler.render.RenderCondenser;
import timeTraveler.render.RenderExtractor;
import timeTraveler.render.RenderMarker;
import timeTraveler.render.RenderParadoxHunter;
import timeTraveler.render.RenderPastPlayer;
import timeTraveler.render.RenderTimeDistorter;
import timeTraveler.render.RenderTimeMachine;
import timeTraveler.tileentity.TileEntityExtractor;
import timeTraveler.tileentity.TileEntityMarker;
import timeTraveler.tileentity.TileEntityParadoxCondenser;
import timeTraveler.tileentity.TileEntityTimeDistorter;
import timeTraveler.tileentity.TileEntityTimeTravel;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;


public class ClientProxy extends CommonProxy 
{

	private static final ModelSlowTimeArmor slowChest = new ModelSlowTimeArmor(1.0f);
	private static final ModelSlowTimeArmor slowLegs = new ModelSlowTimeArmor(0.3f);
	public void registerRenderThings()
	{		
	    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityExtractor.class, new RenderExtractor());
	    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityParadoxCondenser.class, new RenderCondenser());
	    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTimeTravel.class, new RenderTimeMachine());
	    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMarker.class, new RenderMarker());
	    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTimeDistorter.class, new RenderTimeDistorter());
	    
	    RenderingRegistry.registerEntityRenderingHandler(EntityParadoxHunter.class,  new RenderParadoxHunter(new ModelParadoxHunter(),  0.3F));
	    RenderingRegistry.registerEntityRenderingHandler(EntityPlayerPast.class, new RenderPastPlayer(new ModelBiped(), 0.3F));
	    RenderingRegistry.registerEntityRenderingHandler(EntityXPOrbTT.class, new RenderXPOrb());
	    MinecraftForgeClient.registerItemRenderer(new ItemStack(TimeTraveler.paradoxCondenser).getItem(), new ItemCondenserRenderer());
	    MinecraftForgeClient.registerItemRenderer(new ItemStack(TimeTraveler.timeTravel).getItem(), new ItemTimeMachineRenderer());
	    MinecraftForgeClient.registerItemRenderer(new ItemStack(TimeTraveler.marker).getItem(), new ItemMarkerRenderer());
	    MinecraftForgeClient.registerItemRenderer(new ItemStack(TimeTraveler.timeDistorter).getItem(), new ItemTimeDistorterRenderer());

	}
	@Override
	public void initCapes()
	{
	    // move this to dropbox
	}
	@Override public ModelBiped getArmorModel(int id)
	{ 
		switch (id) 
		{
			case 0: 
				return slowChest;
			case 1:
				return slowLegs;
			default:
				break;
		} 
		return slowChest; //default, if whenever you should have passed on a wrong id }
	}
}
