package timeTraveler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;


public class BlockTimeTraveler extends Block
{	
	//
    public BlockTimeTraveler(int id, int texture)
    {
        super(id, texture, Material.iron);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
     
     /**
      * Called when block is right-clicked.  Handles opening of the GUI
      */
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
    	 Minecraft mc = ModLoader.getMinecraftInstance();
    	 EntityPlayer ep = mc.thePlayer;
    	 GuiTimeTravel gtt = new GuiTimeTravel();
    	 if(gtt.isInPast == false) {
    	 ModLoader.openGUI(ep, new GuiTimeTravel());
    	 }
    	 else {
    		 ep.addChatMessage("Cannot go to the past when in the past!");
    	 }
    	 return true;
    }
     /**
      * Used for using multiple textures for different sides of the block
      */
    @Override
    public int getBlockTextureFromSide(int i)
    {
    	switch(i)
    	{
    	//0 = Bottom, 1 = Top, 2-5 = Sides
    	case 0: return 3;
    	case 1: return 0;
    	case 2: return 2;
    	case 3: return 1;
    	case 4: return 2;
    	case 5: return 2;
    	default: return 2;
    	}
    }
     public String getTextureFile()
     {
    	 return ClientProxy.TIME_TRAVELER_BLOCK_TEX;
     }
}

	
