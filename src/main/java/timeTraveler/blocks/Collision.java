package timeTraveler.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import timeTraveler.core.TimeTraveler;
import timeTraveler.tileentity.TileEntityCollision;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Collision extends BlockContainer
{

	public Collision(int id, Material material)
	{
		super(id, material);
		this.setBlockUnbreakable();
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		TileEntityCollision tileEntity = (TileEntityCollision)par1World.getBlockTileEntity(par2, par3, par4);
		String operator = tileEntity.operator;
		tileEntity.operatorFunctions(operator);
		return false;
	}
	
	// This block is called when block is broken and destroys the primary block.
	@Override
	public void breakBlock(World world, int i, int j, int k, int par5, int par6) 
	{
		TileEntityCollision tileEntity = (TileEntityCollision) world.getBlockTileEntity(i, j, k);
		// If not make this check, the game may crash if there's no tile entity
		// at i, j, k.
		if (tileEntity != null)
		{
			world.destroyBlock(tileEntity.primary_x, tileEntity.primary_y,
					tileEntity.primary_z, false);
			world.removeBlockTileEntity(tileEntity.primary_x,
					tileEntity.primary_y, tileEntity.primary_z);
		}
		world.removeBlockTileEntity(i, j, k);
	}

	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, int par5)
	{
		TileEntityCollision tileEntity = (TileEntityCollision) world
				.getBlockTileEntity(i, j, k);
		if (tileEntity != null) 
		{
			if (world.getBlockId(tileEntity.primary_x, tileEntity.primary_y,
					tileEntity.primary_z) < 1) 
			{
				world.destroyBlock(i, j, k, false);
				world.removeBlockTileEntity(i, j, k);
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
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityCollision();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(TimeTraveler.modid + ":"
				+ ("collisionBlock"));
	}

}
