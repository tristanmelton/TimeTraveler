package com.charsmud.timetraveler.items;

import com.charsmud.timetraveler.TimeTraveler;
import com.charsmud.timetraveler.util.IHasModel;

import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel
{
	public ItemBase(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(TimeTraveler.TIMETRAVELER_TAB);
		
		ItemInit.ITEMS.add(this);
	}

	@Override
	public void registerModels()
	{
		TimeTraveler.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
