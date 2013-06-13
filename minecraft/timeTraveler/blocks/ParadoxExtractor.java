package timeTraveler.blocks;

import java.util.List;

import cpw.mods.fml.client.FMLClientHandler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import timeTraveler.core.TimeTraveler;
import timeTraveler.tileentity.TileEntityCollision;
import timeTraveler.tileentity.TileEntityExtractor;

public class ParadoxExtractor extends BlockContainer 
{
   public ParadoxExtractor (int id, Material material) 
   {
        super(id, material);
        this.setHardness(0.5F);
        this.setStepSound(Block.soundSnowFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
   }
   
   @Override
   public void registerIcons(IconRegister ires)
   {
      this.blockIcon = ires.registerIcon("BlockTimeTravelerFront");
      
   }
   
   @Override
   public boolean renderAsNormalBlock()
   {
      return false;
   }
   
   @Override
   public int getRenderType()
   {
      return -1;
   } // where and what to render

   @Override
   public boolean isOpaqueCube() 
   {
      return false;
   } // make it opaque cube, or else you will be able to see trough the world !
   @Override
   public TileEntity createNewTileEntity(World world) 
   {
      return new TileEntityExtractor();
   }
   @Override
   public void onBlockAdded(World world, int x, int y, int z)
   {
	   world.setBlock(x + 1, y, z, TimeTraveler.collisionBlock.blockID);
	   world.setBlock(x + 1, y + 1, z, TimeTraveler.collisionBlock.blockID);
	   world.setBlock(x - 1, y, z, TimeTraveler.collisionBlock.blockID);
	   world.setBlock(x - 1, y + 1, z, TimeTraveler.collisionBlock.blockID);
	   world.setBlock(x, y + 1, z, TimeTraveler.collisionBlock.blockID);
	   world.setBlock(x, y + 2, z, TimeTraveler.collisionBlock.blockID);
   }
   @Override
   public void breakBlock(World world, int x, int y, int z, int par5, int par6)
   {
	   world.destroyBlock(x + 1, y, z, false);
	   world.destroyBlock(x + 1, y + 1, z, false);
	   world.destroyBlock(x - 1, y, z, false);
	   world.destroyBlock(x - 1, y + 1, z, false);
	   world.destroyBlock(x, y + 1, z, false);
	   world.destroyBlock(x, y + 2, z, false);
	   world.removeBlockTileEntity(x + 1, y, z);
	   world.removeBlockTileEntity(x + 1, y + 1, z);
	   world.removeBlockTileEntity(x - 1, y, z);
	   world.removeBlockTileEntity(x - 1,  y + 1, z);
	   world.removeBlockTileEntity(x, y + 1, z);
	   world.removeBlockTileEntity(x, y + 2, z);
	   
	   world.destroyBlock(x, y, z, true);
	   world.removeBlockTileEntity(x, y, z);
   }
}
