package com.charsmud.timetraveler.util.handlers;


import com.charsmud.timetraveler.tileentities.TileEntityMarker;
import com.charsmud.timetraveler.tileentities.TileEntityParadoxCondenser;
import com.charsmud.timetraveler.tileentities.TileEntityParadoxExtractor;
import com.charsmud.timetraveler.tileentities.TileEntityTimeDistorter;
import com.charsmud.timetraveler.tileentities.TileEntityTimeMachine;
import com.charsmud.timetraveler.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler 
{
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityParadoxCondenser.class, new ResourceLocation(Reference.MODID + ":paradox_condenser"));
		GameRegistry.registerTileEntity(TileEntityMarker.class, new ResourceLocation(Reference.MODID + ":marker"));
		GameRegistry.registerTileEntity(TileEntityParadoxExtractor.class, new ResourceLocation(Reference.MODID + ":paradox_extractor"));
		GameRegistry.registerTileEntity(TileEntityTimeDistorter.class, new ResourceLocation(Reference.MODID + ":time_distorter"));
		GameRegistry.registerTileEntity(TileEntityTimeMachine.class, new ResourceLocation(Reference.MODID + ":time_machine"));

	}
}
