package com.charsmud.timetraveler.blocks;

import com.charsmud.timetraveler.TimeTraveler;
import com.charsmud.timetraveler.TimeTraveler.PlayerTemporalLocation;
import com.charsmud.timetraveler.gui.GuiPastTravel;
import com.charsmud.timetraveler.tileentities.TileEntityTimeMachine;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
//TODO: Implement me
public class BlockTimeMachine extends BlockBase implements ITileEntityProvider 
{
	
	//public static final AxisAlignedBB TIMEMACHINE_AABB = new AxisAlignedBB(-0.625D, 0D, -0.625D, 1+0.625D, 2+0.1875D, 1+0.625D);

	public BlockTimeMachine(String name, Material material) {
		super(name, material);
	}
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityTimeMachine();
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
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(!worldIn.isRemote)
		{
			System.out.println("Open GUI");
			if(TimeTraveler.TEMPORAL_LOCATION == PlayerTemporalLocation.PRESENT)
				Minecraft.getMinecraft().displayGuiScreen(new GuiPastTravel(playerIn));
			else
				System.out.println("Can't use in the past!");
			//playerIn.openGui(TimeTraveler.instance, Reference.GUI_PAST_TRAVEL, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	/*@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return TIMEMACHINE_AABB;
	}*/
}
