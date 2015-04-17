package timeTraveler.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import timeTraveler.core.TimeTraveler;
import timeTraveler.tileentity.TileEntityCollision;
import timeTraveler.tileentity.TileEntityMarker;
import timeTraveler.tileentity.TileEntityTimeDistorter;

public class TimeDistorter extends BlockContainer
{
	public static IIcon[] textures = new IIcon[6];

	int xcoord = 0;
	int zcoord = 0;

	
	
    public TimeDistorter()
    {
        super(Material.iron);
        setBlockName("BlockTimeDistorter");
        this.setCreativeTab(TimeTraveler.tabTT);

    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    /*
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return TimeTraveler.timeDistorter.blockID;
    }
*/
    /**
     * set a blocks direction
     */
    /*
    @Override
    private void setDefaultDirection(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            int l = par1World.getBlockId(par2, par3, par4 - 1);
            int i1 = par1World.getBlockId(par2, par3, par4 + 1);
            int j1 = par1World.getBlockId(par2 - 1, par3, par4);
            int k1 = par1World.getBlockId(par2 + 1, par3, par4);
            byte b0 = 3;

            if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
            {
                b0 = 3;
            }

            if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
            {
                b0 = 2;
            }

            if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
            {
                b0 = 5;
            }

            if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
            {
                b0 = 4;
            }

            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
        }
    }*/
    @Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborID)
	{	
		
	}


    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     * @return 
     */
    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
		super.onBlockAdded(par1World, par2, par3, par4);

		System.out.println("ADDING");
		par1World.setBlock(par2, par3 + 1, par4, TimeTraveler.collisionBlock);
		TileEntityCollision collisionTile = (TileEntityCollision) par1World.getTileEntity(par2, par3 + 1, par4);
		if (collisionTile != null)
		{
			collisionTile.primary_x = par2;
			collisionTile.primary_y = par3;
			collisionTile.primary_z = par4;
			collisionTile.operator = "distorter";
		}
    }
    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
    public TileEntity createNewTileEntity(World par1World, int metadata)
    {
        return new TileEntityTimeDistorter();
    }

    /**
     * If this returns true, then comparators facing away from this block will use the value from
     * getComparatorInputOverride instead of the actual redstone signal strength.
     */
    @Override
    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    /**
     * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
     * strength when this block inputs to a comparator.
     */
    @Override
    public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
    {
        return Container.calcRedstoneFromInventory((IInventory)par1World.getTileEntity(par2, par3, par4));
    }
    @Override
    public int getRenderType() 
    {
            return -1;
    }
    
    //It's not an opaque cube, so you need this.
    @Override
    public boolean isOpaqueCube() 
    {
            return false;
    }
}
