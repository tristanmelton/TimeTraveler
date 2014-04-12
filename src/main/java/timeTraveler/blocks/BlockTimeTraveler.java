package timeTraveler.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import timeTraveler.core.TimeTraveler;
import timeTraveler.gui.GuiTimeTravel;


public class BlockTimeTraveler extends Block
{	
	
	public static Icon[] textures = new Icon[7];
	
    public BlockTimeTraveler(int id)
    {
        super(id, Material.iron);
        this.setCreativeTab(CreativeTabs.tabBlock);
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
    				System.out.println(par1World.getBlockId(par2 + x, par3 + y, par4 + z));
    			}
    		}
    	}
    	System.out.println(":)");
        if(par1World.getBlockId(par2, par3, par4 + 1) == TimeTraveler.travelTime.blockID && par1World.getBlockId(par2,  par3, par4 + 2) == TimeTraveler.travelTime.blockID
        		&& par1World.getBlockId(par2 + 1, par3, par4) == TimeTraveler.travelTime.blockID && par1World.getBlockId(par2 + 1, par3, par4 + 1) == TimeTraveler.travelTime.blockID && par1World.getBlockId(par2 + 1, par3, par4 + 2) == TimeTraveler.travelTime.blockID
        		&& par1World.getBlockId(par2 + 2, par3, par4) == TimeTraveler.travelTime.blockID && par1World.getBlockId(par2 + 2, par3, par4 + 1) == TimeTraveler.travelTime.blockID && par1World.getBlockId(par2 + 2, par3, par4 + 2) == TimeTraveler.travelTime.blockID
        		
        		&&par1World.getBlockId(par2, par3 + 1, par4) == TimeTraveler.travelTime.blockID && par1World.getBlockId(par2, par3 + 1, par4 + 1) == TimeTraveler.travelTime.blockID && par1World.getBlockId(par2,  par3 + 1, par4 + 2) == TimeTraveler.travelTime.blockID
                && par1World.getBlockId(par2 + 1, par3 + 1, par4) == TimeTraveler.travelTime.blockID && par1World.getBlockId(par2 + 1, par3 + 1, par4 + 1) == TimeTraveler.travelTime.blockID && par1World.getBlockId(par2 + 1, par3 + 1, par4 + 2) == TimeTraveler.travelTime.blockID
                && par1World.getBlockId(par2 + 2, par3 + 1, par4) == TimeTraveler.travelTime.blockID && par1World.getBlockId(par2 + 2, par3 + 1, par4 + 1) == TimeTraveler.travelTime.blockID && par1World.getBlockId(par2 + 2, par3 + 1, par4 + 2) == TimeTraveler.travelTime.blockID
        		
                &&par1World.getBlockId(par2, par3 + 2, par4) == TimeTraveler.travelTime.blockID && par1World.getBlockId(par2, par3 + 2, par4 + 1) == TimeTraveler.travelTime.blockID && par1World.getBlockId(par2,  par3 + 2, par4 + 2) == TimeTraveler.travelTime.blockID
                && par1World.getBlockId(par2 + 1, par3 + 2, par4) == TimeTraveler.travelTime.blockID && par1World.getBlockId(par2 + 1, par3 + 2, par4 + 1) == TimeTraveler.travelTime.blockID && par1World.getBlockId(par2 + 1, par3 + 2, par4 + 2) == TimeTraveler.travelTime.blockID
                && par1World.getBlockId(par2 + 2, par3 + 2, par4) == TimeTraveler.travelTime.blockID && par1World.getBlockId(par2 + 2, par3 + 2, par4 + 1) == TimeTraveler.travelTime.blockID && par1World.getBlockId(par2 + 2, par3 + 2, par4 + 2) == TimeTraveler.travelTime.blockID)
        {
        	System.out.println("MULTIBLOCKING");
        	par1World.setBlock(par2 + 1, par3, par4 + 1, TimeTraveler.timeTravel.blockID);
        }
        return par9;
    }
    
    @Override
    public void registerIcons(IconRegister iconRegistry)
    {
    	textures[6] = iconRegistry.registerIcon(TimeTraveler.modid + ":" + "BlockTimeTraveler");
    	textures[0] = iconRegistry.registerIcon(TimeTraveler.modid + ":" + "BlockTimeTravelerBot");
    	textures[1] = iconRegistry.registerIcon(TimeTraveler.modid + ":" + "BlockTimeTravelerTop");
    	textures[2] = iconRegistry.registerIcon(TimeTraveler.modid + ":" + "BlockTimeTravelerSide");
    	textures[3] = iconRegistry.registerIcon(TimeTraveler.modid + ":" + "BlockTimeTravelerFront");
    	textures[4] = iconRegistry.registerIcon(TimeTraveler.modid + ":" + "BlockTimeTravelerSide");
    	textures[5] = iconRegistry.registerIcon(TimeTraveler.modid + ":" + "BlockTimeTravelerSide");
    
    }
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
	public Icon getIcon(int i, int j) 
	{
    	return textures[6];
	}
}

	
