package com.charsmud.timetraveler.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	public static final Block MARKER = new BlockMarker("marker", Material.AIR); //TESR
	public static final Block PARADOX_CONDENSER = new BlockParadoxCondenser("paradox_condenser"); //TESR
	public static final Block TIME_MACHINE = new BlockTimeMachine("time_machine", Material.IRON); //TESR
	public static final Block TIME_FIELD = new BlockTimeField("time_field", Material.IRON);
	public static final Block TIME_TRAVELER = new BlockTimeTraveler("time_traveler", Material.IRON);
	//public static final Block COLLISION = new BlockBase("collision", Material.IRON);
	public static final Block PARADOX_EXTRACTOR = new BlockParadoxExtractor("paradox_extractor"); //TESR
	public static final Block TIME_DISTORTER = new BlockTimeDistorter("time_distorter", Material.IRON); //TESR
	
}
