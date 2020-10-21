package com.charsmud.timetraveler.util.handlers;

import com.charsmud.timetraveler.TimeTraveler;
import com.charsmud.timetraveler.blocks.BlockInit;
import com.charsmud.timetraveler.entities.EntityInit;
import com.charsmud.timetraveler.items.ItemInit;
import com.charsmud.timetraveler.renderers.RendererMarker;
import com.charsmud.timetraveler.renderers.RendererParadoxCondenser;
import com.charsmud.timetraveler.renderers.RendererParadoxExtractor;
import com.charsmud.timetraveler.renderers.RendererTimeDistorter;
import com.charsmud.timetraveler.renderers.RendererTimeMachine;
import com.charsmud.timetraveler.tileentities.TileEntityMarker;
import com.charsmud.timetraveler.tileentities.TileEntityParadoxCondenser;
import com.charsmud.timetraveler.tileentities.TileEntityParadoxExtractor;
import com.charsmud.timetraveler.tileentities.TileEntityTimeDistorter;
import com.charsmud.timetraveler.tileentities.TileEntityTimeMachine;
import com.charsmud.timetraveler.util.IHasModel;
import com.charsmud.timetraveler.world.DimensionInit;
import com.charsmud.timetraveler.world.WorldGenCustomStructures;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler 
{
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityParadoxCondenser.class, new RendererParadoxCondenser());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMarker.class, new RendererMarker());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityParadoxExtractor.class, new RendererParadoxExtractor());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTimeDistorter.class, new RendererTimeDistorter());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTimeMachine.class, new RendererTimeMachine());
	}
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		TimeTraveler.proxy.registerItemRenderer(Item.getItemFromBlock(BlockInit.PARADOX_CONDENSER), 0, "inventory");
		TimeTraveler.proxy.registerItemRenderer(Item.getItemFromBlock(BlockInit.PARADOX_EXTRACTOR), 0, "inventory");
		TimeTraveler.proxy.registerItemRenderer(Item.getItemFromBlock(BlockInit.MARKER), 0, "inventory");
		TimeTraveler.proxy.registerItemRenderer(Item.getItemFromBlock(BlockInit.TIME_MACHINE), 0, "inventory");
		TimeTraveler.proxy.registerItemRenderer(Item.getItemFromBlock(BlockInit.TIME_DISTORTER), 0, "inventory");
		for(Item item : ItemInit.ITEMS)
		{
			if(item instanceof IHasModel)
			{
				((IHasModel)item).registerModels();
			}
		}
		for(Block block : BlockInit.BLOCKS)
		{
			if(block instanceof IHasModel)
			{
				((IHasModel)block).registerModels();
			}
		}
	}
	public static void preInitRegistries()
	{
		GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 2);
		DimensionInit.registerDimensions();
		EntityInit.registerEntities();
		RenderHandler.registerEntityRenders();
	}
	public static void initRegistries()
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(TimeTraveler.instance, new GuiHandler());
	}
	public static void postInitRegistries()
	{
	}
}
