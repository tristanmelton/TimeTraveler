package timeTraveler.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import timeTraveler.core.TimeTraveler;
import timeTraveler.tileentity.TileEntityCollision;

public class Collision extends BlockContainer
{
        
        public Collision(int id, Material material)
        {
        	super(id, material);
        	this.setBlockUnbreakable();
        }
        //This block is called when block is broken and destroys the primary block.
        @Override
        public void breakBlock(World world, int i, int j, int k, int par5, int par6)
        {
        }
        //This method checks if primary block exists. 
        @Override
        public void onNeighborBlockChange(World world, int i, int j, int k, int par5)
        {
        }
        //This makes our gag invisible.
        @Override
        public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
        {
                return false;
        }
        //This tells minecraft to render surrounding blocks.
        @Override
        public boolean isOpaqueCube()
        {
                return false;
        }
        @Override
        public TileEntity createNewTileEntity(World world)
        {
                return new TileEntityCollision();
        }
}