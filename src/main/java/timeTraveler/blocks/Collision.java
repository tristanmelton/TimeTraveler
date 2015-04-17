package timeTraveler.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import timeTraveler.core.TimeTraveler;
import timeTraveler.entities.EntityChair;
import timeTraveler.tileentity.TileEntityCollision;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Collision extends BlockContainer
{

	public Collision(Material material)
	{
		super(material);
		setBlockName("collisionBlock");
		this.setBlockUnbreakable();
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		TileEntityCollision tileEntity = (TileEntityCollision)par1World.getTileEntity(par2, par3, par4);
		String operator = tileEntity.operator;
		tileEntity.operatorFunctions(operator);
		return false;
	}
	
	// This block is called when block is broken and destroys the primary block.
	@Override
	public void breakBlock(World world, int i, int j, int k, Block par5, int par6) 
	{
		TileEntityCollision tileEntity = (TileEntityCollision) world.getTileEntity(i, j, k);
		// If not make this check, the game may crash if there's no tile entity
		// at i, j, k.
		if (tileEntity != null)
		{
			System.out.println("IN HERE");
			world.setBlockToAir(tileEntity.primary_x, tileEntity.primary_y, tileEntity.primary_z);
			world.removeTileEntity(tileEntity.primary_x, tileEntity.primary_y, tileEntity.primary_z);
			
			List<Entity> entitiesToSlow = world.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(i - 5, j - 5, k - 5, i + 5, j + 5, k + 5));
			if(entitiesToSlow.size() > 0)
			{
				for(int r = 0; r < entitiesToSlow.size(); r++)
				{
					Entity e = entitiesToSlow.get(r);
					System.out.println(e);

					if(e instanceof EntityChair)
					{
						e.setDead();
					}
				}
			}		
		}
		world.removeTileEntity(i, j, k);
	}

	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, Block par5)
	{
		TileEntityCollision tileEntity = (TileEntityCollision) world.getTileEntity(i, j, k);
		if (tileEntity != null) 
		{
			if (world.getBlock(tileEntity.primary_x, tileEntity.primary_y,
					tileEntity.primary_z) == Blocks.air) 
			{
				world.setBlockToAir(i, j, k);
				world.removeTileEntity(i, j, k);
			}
		}
	}

	// This makes our gag invisible.
	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i,
			int j, int k, int l) {
		return false;
	}

	// This tells minecraft to render surrounding blocks.
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityCollision();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(TimeTraveler.modid + ":"
				+ ("collisionBlock"));
	}
}
