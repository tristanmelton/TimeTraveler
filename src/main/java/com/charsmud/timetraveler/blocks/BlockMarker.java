package com.charsmud.timetraveler.blocks;

import com.charsmud.timetraveler.tileentities.TileEntityMarker;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMarker extends BlockBase implements ITileEntityProvider {
	public static final AxisAlignedBB MARKER_AABB = new AxisAlignedBB(0.125D, 0D, 0.125D, 0.875D, 2D, 0.875D);
//TODO: Implement me
	public BlockMarker(String name, Material material) {
		super(name, material);
	}
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityMarker();
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
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return MARKER_AABB;
	}
	/*@Override
	public void registerModels()
	{
		super.registerModels();
		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(this), 0, TileEntityMarker.class);
	}*/
}
