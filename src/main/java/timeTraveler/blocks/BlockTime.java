package timeTraveler.blocks;

import java.util.Random;

import timeTraveler.core.TimeTraveler;
import timeTraveler.render.ParadoxParticleFX;
import timeTraveler.render.ParticleEffects;
import timeTraveler.tileentity.TileEntityCollision;
import timeTraveler.tileentity.TileEntityTimeTravel;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
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
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTime extends BlockContainer
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

    public BlockTime(int par1, boolean par2)
    {
        super(par1, Material.iron);
        this.isActive = par2;
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
    //This makes our gag invisible.
    @Override
    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
            return false;
    }
    
    
    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return 0;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     * @return 
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.prepareBlock(par1World, par2, par3, par4);
        
        for(int i = -1; i < 2; i++)
        {
        	for(int j = 0; j < 3; j++)
        	{
        		for(int k = -1; k < 2; k++)
        		{
        			if((i == 0 && j == 0 && k == 0) || (i == 0 && j == 1 && k == 0))
        			{
        			}
        			else
        			{
        				System.out.println("ADDING");
            			par1World.setBlock(par2 + i, par3 + j, par4 + k, TimeTraveler.collisionBlock.blockID);
            	        TileEntityCollision collisionTile = (TileEntityCollision)par1World.getBlockTileEntity(par2 + i, par3 + j, par4 + k);

            	        if(collisionTile != null)
            	        {
                	        collisionTile.primary_x = par2;
                            collisionTile.primary_y = par3;
                            collisionTile.primary_z = par4;
                            collisionTile.operator = "tt";
            	        }
        			}
        		}
        	}
        }
    }

    /**
     * set a blocks direction
     */
    private void prepareBlock(World par1World, int par2, int par3, int par4)
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
    }
/*
    @Override
    public void registerIcons(IconRegister iconRegistry)
    {
    	textures[0] = iconRegistry.registerIcon(TimeTraveler.modid + ":" + "BlockParadoxCondenserBot");
    	textures[1] = iconRegistry.registerIcon(TimeTraveler.modid + ":" + "BlockParadoxCondenserTop");
    	textures[2] = iconRegistry.registerIcon(TimeTraveler.modid + ":" + "BlockParadoxCondenserBack");
    	textures[3] = iconRegistry.registerIcon(TimeTraveler.modid + ":" + "BLockParadoxCondenserFront");
    	textures[4] = iconRegistry.registerIcon(TimeTraveler.modid + ":" + "BlockParadoxCondenserSide");
    	textures[5] = iconRegistry.registerIcon(TimeTraveler.modid + ":" + "BlockParadoxCondenserSide");
    }
	@Override
	public Icon getIcon(int i, int j) 
	{
		
		if (j == 0 && i == 3)
		{
			return textures[3];
		}
		if(j == 0 && i == 2)
		{
			return textures[2];
		}
		if (i == j)
		{
			return textures[0];
		}
		switch (i) 
		{
			case 1:
				return textures[1];
			default:
				return textures[4];
		}
	}*/

    /**
     * Called upon block activation (right click on the block.)
     */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float t) {
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

		System.out.println(tile_entity);
		/*EntityChair chair = new EntityChair(world);
		chair.setPosition((double)x, (double)y, (double)z);
		world.spawnEntityInWorld(chair);
*/

	/*	if (tile_entity == null || player.isSneaking())
		{

			return false;
		}

		player.openGui(TimeTraveler.instance, 0, world, x, y, z);
*/
		return true;
	}

    /**
     * Update which block ID the furnace is using depending on whether or not it is burning
     */
   /* public static void updateFurnaceBlockState(boolean par0, World par1World, int par2, int par3, int par4)
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
    }*/

    @SideOnly(Side.CLIENT)

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        /*if (this.isActive)
        {
            int l = par1World.getBlockMetadata(par2, par3, par4);
            float f = (float)par2 + 0.5F;
            float f1 = (float)par3 + 0.0F + par5Random.nextFloat() * 6.0F / 16.0F;
            float f2 = (float)par4 + 0.5F;
            float f3 = 0.52F;
            float f4 = par5Random.nextFloat() * 0.6F - 0.3F;

            ParticleEffects.spawnParticle("paradox", (double)par2 + 0.5, (double)par3 + 0.75, (double)par4 + 0.5, 0.0D, 0.0D, 0.0D);
            ParticleEffects.spawnParticle("paradox", (double)par2 + 0.5, (double)par3 + 0.5, (double)par4 + 0.5, 0.0D, 0.0D, 0.0D);

        }*/
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World par1World)
    {
        return new TileEntityTimeTravel();
    }
    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {

        super.breakBlock(par1World, par2, par3, par4, par5, par6);
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
    @Override
    public int getRenderType() 
    {
            return -1;
    }
    
    @Override
    public boolean isOpaqueCube() 
    {
            return false;
    }
    public boolean renderAsNormalBlock()
    {
        return false;
    }

}