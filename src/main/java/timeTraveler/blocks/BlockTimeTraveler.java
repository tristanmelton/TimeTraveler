package timeTraveler.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import timeTraveler.core.TimeTraveler;
import timeTraveler.entities.EntityChair;
import timeTraveler.gui.GuiTimeTravel;


public class BlockTimeTraveler extends Block
{	
	
	public static IIcon[] textures = new IIcon[7];
	
    public BlockTimeTraveler()
    {
        super(Material.iron);
        setBlockName("BlockTimeTraveler");
        this.setCreativeTab(TimeTraveler.tabTT);
    }
     
     /**
      * Called when block is right-clicked.  Handles opening of the GUI
      */
    
   /* public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
    	 Minecraft mc = Minecraft.getMinecraft();
    	 EntityPlayer ep = mc.thePlayer;
    	 GuiTimeTravel gtt = new GuiTimeTravel();
    	 if(gtt.isInPast == false) {
    	     mc.displayGuiScreen(new GuiTimeTravel());
    	 }
    	 else {
    		 ep.addChatMessage("Cannot go to the past when in the past!");
    	 }
    	 return true;
    }
    */
    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
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
    }
    
    @Override
    public void registerBlockIcons(IIconRegister iconRegistry)
    {
    	textures[6] = iconRegistry.registerIcon(TimeTraveler.modid + ":" + "BlockTimeTraveler");
    	textures[0] = iconRegistry.registerIcon(TimeTraveler.modid + ":" + "BlockTimeTravelerBot");
    	textures[1] = iconRegistry.registerIcon(TimeTraveler.modid + ":" + "BlockTimeTravelerTop");
    	textures[2] = iconRegistry.registerIcon(TimeTraveler.modid + ":" + "BlockTimeTravelerSide");
    	textures[3] = iconRegistry.registerIcon(TimeTraveler.modid + ":" + "BlockTimeTravelerFront");
    	textures[4] = iconRegistry.registerIcon(TimeTraveler.modid + ":" + "BlockTimeTravelerSide");
    	textures[5] = iconRegistry.registerIcon(TimeTraveler.modid + ":" + "BlockTimeTravelerSide");
    
    }
    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int noIdea)
    {
		List<Entity> entitiesToSlow = world.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(x - 2, y - 2, z - 2, x + 2, y + 2, z + 2));
		if(entitiesToSlow.size() > 0)
		{
			for(int i = 0; i < entitiesToSlow.size(); i++)
			{
				Entity e = entitiesToSlow.get(i);
				System.out.println(e);

				if(e instanceof EntityChair)
				{
					e.setDead();
				}
			}
		}
    }
    @Override
    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion)
    {
		List<Entity> entitiesToSlow = world.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(x - 2, y - 2, z - 2, x + 2, y + 2, z + 2));
		if(entitiesToSlow.size() > 0)
		{
			for(int i = 0; i < entitiesToSlow.size(); i++)
			{
				Entity e = entitiesToSlow.get(i);
				if(e instanceof EntityChair)
				{
					e.setDead();
				}
			}
		}    }
    
   /*
    @Override
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
    @Override
	public IIcon getIcon(int i, int j) 
	{
    	return textures[6];
	}
}

	
