package com.charsmud.timetraveler.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockTimeField extends BlockBase {

	public BlockTimeField(String name, Material material) {
		super(name, material);
		this.setBlockUnbreakable();
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
}
