package com.charsmud.timetraveler.blocks;

import com.charsmud.timetraveler.TimeTraveler;
import com.charsmud.timetraveler.items.ItemInit;
import com.charsmud.timetraveler.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel 
{
	public BlockBase(String name, Material material)
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(TimeTraveler.TIMETRAVELER_TAB);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModels() 
	{
		TimeTraveler.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
