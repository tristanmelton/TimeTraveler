package timeTraveler.blocks;

import com.sun.org.apache.xml.internal.dtm.Axis;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import timeTraveler.core.TimeTraveler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class BlockTimeField extends Block
{		
    public BlockTimeField()
    {
        super(Material.iron);
        setBlockName("timeField");
        this.setResistance(1000F);
        this.setBlockUnbreakable();

    }
    @SideOnly(Side.CLIENT) 
    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {  
    	this.blockIcon = par1IconRegister.registerIcon(TimeTraveler.modid + ":" + (this.getUnlocalizedName().substring(5)));
    }

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this
	 * box can change after the pool has been cleared to be reused)
	 */
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int wx, int wy, int wz)
	{
		return null;
	}

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
	@Override
	public boolean isOpaqueCube() 
	{
		return false;
	}
}
