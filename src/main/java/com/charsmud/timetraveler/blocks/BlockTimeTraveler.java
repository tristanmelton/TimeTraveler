package com.charsmud.timetraveler.blocks;

import net.minecraft.block.material.Material;

public class BlockTimeTraveler extends BlockBase {

	public BlockTimeTraveler(String name, Material material) {
		super(name, material);
	}
	//TODO: Multiblock this to a TimeMachine block
	/*@Override
    public void onBlockPlacedBy(World par1World, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
		
    	for(int x = 0; x < 3; x++)
    	{
    		for(int y = 0; y < 3; y++)
    		{
    			for(int z = 0; z < 3; z++)
    			{
    				System.out.println(par1World.getBlock(par2 + x, par3 + y, par4 + z));
    			}
    		}
    	}
    	System.out.println(":)");
        if(par1World.getBlock(par2, par3, par4 + 1) == TimeTraveler.travelTime && par1World.getBlock(par2,  par3, par4 + 2) == TimeTraveler.travelTime
        		&& par1World.getBlock(par2 + 1, par3, par4) == TimeTraveler.travelTime && par1World.getBlock(par2 + 1, par3, par4 + 1) == TimeTraveler.travelTime && par1World.getBlock(par2 + 1, par3, par4 + 2) == TimeTraveler.travelTime
        		&& par1World.getBlock(par2 + 2, par3, par4) == TimeTraveler.travelTime && par1World.getBlock(par2 + 2, par3, par4 + 1) == TimeTraveler.travelTime && par1World.getBlock(par2 + 2, par3, par4 + 2) == TimeTraveler.travelTime
        		
        		&&par1World.getBlock(par2, par3 + 1, par4) == TimeTraveler.travelTime && par1World.getBlock(par2, par3 + 1, par4 + 1) == TimeTraveler.travelTime && par1World.getBlock(par2,  par3 + 1, par4 + 2) == TimeTraveler.travelTime
                && par1World.getBlock(par2 + 1, par3 + 1, par4) == TimeTraveler.travelTime && par1World.getBlock(par2 + 1, par3 + 1, par4 + 1) == TimeTraveler.travelTime && par1World.getBlock(par2 + 1, par3 + 1, par4 + 2) == TimeTraveler.travelTime
                && par1World.getBlock(par2 + 2, par3 + 1, par4) == TimeTraveler.travelTime && par1World.getBlock(par2 + 2, par3 + 1, par4 + 1) == TimeTraveler.travelTime && par1World.getBlock(par2 + 2, par3 + 1, par4 + 2) == TimeTraveler.travelTime
        		
                &&par1World.getBlock(par2, par3 + 2, par4) == TimeTraveler.travelTime && par1World.getBlock(par2, par3 + 2, par4 + 1) == TimeTraveler.travelTime && par1World.getBlock(par2,  par3 + 2, par4 + 2) == TimeTraveler.travelTime
                && par1World.getBlock(par2 + 1, par3 + 2, par4) == TimeTraveler.travelTime && par1World.getBlock(par2 + 1, par3 + 2, par4 + 1) == TimeTraveler.travelTime && par1World.getBlock(par2 + 1, par3 + 2, par4 + 2) == TimeTraveler.travelTime
                && par1World.getBlock(par2 + 2, par3 + 2, par4) == TimeTraveler.travelTime && par1World.getBlock(par2 + 2, par3 + 2, par4 + 1) == TimeTraveler.travelTime && par1World.getBlock(par2 + 2, par3 + 2, par4 + 2) == TimeTraveler.travelTime)
        {
        	System.out.println("MULTIBLOCKING");
        	par1World.setBlock(par2 + 1, par3, par4 + 1, TimeTraveler.timeTravel);
        }
        return par9;
    }*/
}
