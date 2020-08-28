package com.charsmud.timetraveler.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;

public class ItemInit
{
	public static final List<Item> ITEMS = new ArrayList<Item>();
	public static final Item BOTTLED_PARADOX = new ItemBottledParadox("bottled_paradox");
	public static final Item CONDENSED_PARADOX = new ItemCondensedParadox("condensed_paradox");
	public static final Item EXP_ENHANCE = new ItemBase("exp_enhance");
	public static final Item FLASHBACK = new ItemBase("flashback");
	public static final Item PARADOXIMER = new ItemParadoximer("paradoximer");
	public static final Item SLOW_ARMOR = new ItemBase("slow_armor");
	public static final Item TIME_FLUID_BUCKET = new ItemBase("time_fluid_bucket");
	
}
