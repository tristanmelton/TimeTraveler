package com.charsmud.timetraveler.blocks;

import com.charsmud.timetraveler.tileentities.TileEntityTimeDistorter;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;
//TODO: Implement me
public class BlockTimeDistorter extends BlockBase implements ITileEntityProvider{

	public BlockTimeDistorter(String name, Material material) {
		super(name, material);
	}
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityTimeDistorter();
	}
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
	}
}
