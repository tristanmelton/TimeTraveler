package timeTraveler.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import timeTraveler.core.TimeTraveler;
import timeTraveler.tileentity.TileEntityCollision;
import timeTraveler.tileentity.TileEntityExtractor;
import timeTraveler.tileentity.TileEntityParadoxCondenser;

public class ParadoxExtractor extends BlockContainer 
{
    /**
     * Is the random generator used by furnace to drop the inventory contents in random directions.
     */
    private final Random paradoxRand = new Random();

    /** True if this is an active furnace, false if idle */
    private final boolean isActive;

    
	public static Icon[] textures = new Icon[6];

    /**
     * This flag is used to prevent the furnace inventory to be dropped upon block removal, is used internally when the
     * furnace block changes from idle to active and vice-versa.
     */
    private static boolean keepInventory = false;

	
   public ParadoxExtractor (int id, boolean par2) 
   {
        super(id, Material.iron);
        this.isActive = par2;
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
       if (!keepInventory)
       {
       	TileEntityExtractor tileEntityParadox = (TileEntityExtractor)world.getBlockTileEntity(x, y, z);

           if (tileEntityParadox != null)
           {
               for (int j1 = 0; j1 < tileEntityParadox.getSizeInventory(); ++j1)
               {
                   ItemStack itemstack = tileEntityParadox.getStackInSlot(j1);

                   if (itemstack != null)
                   {
                       float f = this.paradoxRand.nextFloat() * 0.8F + 0.1F;
                       float f1 = this.paradoxRand.nextFloat() * 0.8F + 0.1F;
                       float f2 = this.paradoxRand.nextFloat() * 0.8F + 0.1F;

                       while (itemstack.stackSize > 0)
                       {
                           int k1 = this.paradoxRand.nextInt(21) + 10;

                           if (k1 > itemstack.stackSize)
                           {
                               k1 = itemstack.stackSize;
                           }

                           itemstack.stackSize -= k1;
                           EntityItem entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));

                           if (itemstack.hasTagCompound())
                           {
                               entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                           }

                           float f3 = 0.05F;
                           entityitem.motionX = (double)((float)this.paradoxRand.nextGaussian() * f3);
                           entityitem.motionY = (double)((float)this.paradoxRand.nextGaussian() * f3 + 0.2F);
                           entityitem.motionZ = (double)((float)this.paradoxRand.nextGaussian() * f3);
                           world.spawnEntityInWorld(entityitem);
                       }
                   }
               }

               world.func_96440_m(x, y, z, par5);
           }
       }

       super.breakBlock(world, x, y, z, par5, par6);

	   
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
   /**
    * Returns the ID of the items to drop on destruction.
    */
   public int idDropped(int par1, Random par2Random, int par3)
   {
       return TimeTraveler.paradoxExtractor.blockID;
   }
   /**
    * Called upon block activation (right click on the block.)
    */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float t) {
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

		if (tile_entity == null || player.isSneaking()) {

			return false;
		}

		player.openGui(TimeTraveler.instance, 0, world, x, y, z);

		return true;
	}
    /**
     * Update which block ID the furnace is using depending on whether or not it is burning
     */
    public static void updateFurnaceBlockState(boolean par0, World par1World, int par2, int par3, int par4)
    {
        int l = par1World.getBlockMetadata(par2, par3, par4);
        TileEntity tileentity = par1World.getBlockTileEntity(par2, par3, par4);
        keepInventory = true;

        if (par0)
        {
            //par1World.setBlock(par2, par3, par4, mod_MainClass.TutFurnaceBurning.blockID);
        }
        else
        {
           // par1World.setBlock(par2, par3, par4, mod_MainClass.TutFurnaceIdle.blockID);
        }

        keepInventory = false;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);

        if (tileentity != null)
        {
            tileentity.validate();
            par1World.setBlockTileEntity(par2, par3, par4, tileentity);
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (this.isActive)
        {
            int l = par1World.getBlockMetadata(par2, par3, par4);
            float f = (float)par2 + 0.5F;
            float f1 = (float)par3 + 0.0F + par5Random.nextFloat() * 6.0F / 16.0F;
            float f2 = (float)par4 + 0.5F;
            float f3 = 0.52F;
            float f4 = par5Random.nextFloat() * 0.6F - 0.3F;

            if (l == 4)
            {
                par1World.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                par1World.spawnParticle("flame", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 5)
            {
                par1World.spawnParticle("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                par1World.spawnParticle("flame", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 2)
            {
                par1World.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
                par1World.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 3)
            {
                par1World.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
                par1World.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    /**
     * If this returns true, then comparators facing away from this block will use the value from
     * getComparatorInputOverride instead of the actual redstone signal strength.
     */
    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    /**
     * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
     * strength when this block inputs to a comparator.
     */
    public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
    {
        return Container.calcRedstoneFromInventory((IInventory)par1World.getBlockTileEntity(par2, par3, par4));
    }
    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
        }

        if (l == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
        }

        if (l == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
        }

        if (l == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
        }

        if (par6ItemStack.hasDisplayName())
        {
            ((TileEntityExtractor)par1World.getBlockTileEntity(par2, par3, par4)).func_94129_a(par6ItemStack.getDisplayName());
        }
    }

}
