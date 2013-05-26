package timeTraveler.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import timeTraveler.core.TimeTraveler;
import timeTraveler.mechanics.TeleporterTime;


public class BlockPortalTime extends BlockPortal
{	
	
	public static Icon[] textures = new Icon[6];
	
    public BlockPortalTime(int id)
    {
        super(id);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
     
     /**
      * Called when block is right-clicked.  Handles opening of the GUI
      */
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
    	 return true;
    }
    @Override
    public void registerIcons(IconRegister iconRegistry)
    {
    	this.blockIcon = iconRegistry.registerIcon("BlockPortalTime");
    	/*textures[0] = iconRegistry.registerIcon("BlockPortalTimeBot");
    	textures[1] = iconRegistry.registerIcon("BlockPortalTimeTop");
    	textures[2] = iconRegistry.registerIcon("BlockPortalTimeSide");
    	textures[3] = iconRegistry.registerIcon("BlockPortalTimeFront");
    	textures[4] = iconRegistry.registerIcon("BlockPortalTimeSide");
    	textures[5] = iconRegistry.registerIcon("BlockPortalTimeSide");*/
    }
	/*@Override
	public Icon getIcon(int i, int j) 
	{
		
		if (j == 0 && i == 3)
		{
			return textures[3];
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
     * Updates Block and Ticks it
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {

    }
    /**
     * Triesto make the portal
     */
    public boolean tryToCreatePortal(World par1World, int par2, int par3, int par4)
    {
        byte var5 = 0;
        byte var6 = 0;

        if (par1World.getBlockId(par2 - 1, par3, par4) == Block.sandStone.blockID || par1World.getBlockId(par2 + 1, par3, par4) == Block.sandStone.blockID)
        {
            var5 = 1;
        }

        if (par1World.getBlockId(par2, par3, par4 - 1) == Block.sandStone.blockID || par1World.getBlockId(par2, par3, par4 + 1) == Block.sandStone.blockID)
        {
            var6 = 1;
        }

        if (var5 == var6)
        {
            return false;
        }
        else
        {
            if (par1World.getBlockId(par2 - var5, par3, par4 - var6) == 0)
            {
                par2 -= var5;
                par4 -= var6;
            }

            int var7;
            int var8;

            for (var7 = -1; var7 <= 2; ++var7)
            {
                for (var8 = -1; var8 <= 3; ++var8)
                {
                    boolean var9 = var7 == -1 || var7 == 2 || var8 == -1 || var8 == 3;

                    if (var7 != -1 && var7 != 2 || var8 != -1 && var8 != 3)
                    {
                        int var10 = par1World.getBlockId(par2 + var5 * var7, par3 + var8, par4 + var6 * var7);

                        if (var9)
                        {
                            if (var10 != Block.sandStone.blockID)
                            {
                                return false;
                            }
                        }
                        else if (var10 != 0 && var10 != Block.fire.blockID)
                        {
                            return false;
                        }
                    }
                }
            }

            //par1World.editingBlocks = true;

            for (var7 = 0; var7 < 2; ++var7)
            {
                for (var8 = 0; var8 < 3; ++var8)
                {
                    par1World.setBlock(par2 + var5 * var7, par3 + var8, par4 + var6 * var7, this.blockID);
                }
            }

            //par1World.editingBlocks = false;
            return true;
        }
    }
    /**
     * Stuff for Particles for portal
     */
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        
    }
    /**
     * Causes portal to dissapear if portal frame breaks
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
    	byte var6 = 0;
    	byte var7 = 1;
     
    	if (par1World.getBlockId(par2 - 1, par3, par4) == this.blockID || par1World.getBlockId(par2 + 1, par3, par4) == this.blockID)
    	{
    		var6 = 1;
    		var7 = 0;
    	}

    	int var8;

    	for (var8 = par3; par1World.getBlockId(par2, var8 - 1, par4) == this.blockID; --var8)
    	{
    		;
    	}

    	if (par1World.getBlockId(par2, var8 - 1, par4) != Block.sandStone.blockID)
    	{
    		par1World.setBlock(par2, par3, par4, 0, 0, 0);
    	}
    	else
    	{
    		int var9;
	
	       for (var9 = 1; var9 < 4 && par1World.getBlockId(par2, var8 + var9, par4) == this.blockID; ++var9)
	       {
	    	   ;
	       }

	       if (var9 == 3 && par1World.getBlockId(par2, var8 + var9, par4) == Block.sandStone.blockID)
	       {
	    	   boolean var10 = par1World.getBlockId(par2 - 1, par3, par4) == this.blockID || par1World.getBlockId(par2 + 1, par3, par4) == this.blockID;
	    	   boolean var11 = par1World.getBlockId(par2, par3, par4 - 1) == this.blockID || par1World.getBlockId(par2, par3, par4 + 1) == this.blockID;

	    	   if (var10 && var11)
	    	   {
	    		   par1World.setBlock(par2, par3, par4, 0, 0, 0);
	    	   }
	    	   else
	    	   {
	    		   if ((par1World.getBlockId(par2 + var6, par3, par4 + var7) != Block.sandStone.blockID || par1World.getBlockId(par2 - var6, par3, par4 - var7) != this.blockID) && (par1World.getBlockId(par2 - var6, par3, par4 - var7) != Block.sandStone.blockID || par1World.getBlockId(par2 + var6, par3, par4 + var7) != this.blockID))
	    		   {
	    			   par1World.setBlock(par2, par3, par4, 0, 0, 0);
	    		   }
	    	   }
	       }
	       else
	       {
	    	   par1World.setBlock(par2, par3, par4, 0, 0, 0);
	       }
    	}
    }

    /**
     * Runs when player collides with block
     */
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
    	if (par5Entity.ridingEntity == null && par5Entity.riddenByEntity == null)
    	{
    		if (par5Entity instanceof EntityPlayerMP)
    		{
    			EntityPlayerMP thePlayer = (EntityPlayerMP) par5Entity;
    		    if (par5Entity.dimension != TimeTraveler.dimensionID)
    		    {
    		    	thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, TimeTraveler.dimensionID, new TeleporterTime(thePlayer.mcServer.worldServerForDimension(TimeTraveler.dimensionID)));
    		    }
    		    else
    		    {
    		    	thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, new TeleporterTime(thePlayer.mcServer.worldServerForDimension(0)));
    		    }

    		}
    	}
    }

}

	
