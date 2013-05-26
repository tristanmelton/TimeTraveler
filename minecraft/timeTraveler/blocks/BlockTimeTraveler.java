package timeTraveler.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import timeTraveler.core.TimeTraveler;
import timeTraveler.gui.GuiTimeTravel;


public class BlockTimeTraveler extends Block
{	
	
	public static Icon[] textures = new Icon[6];
	
    public BlockTimeTraveler(int id)
    {
        super(id, Material.iron);
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
    @Override
    public void registerIcons(IconRegister iconRegistry)
    {
    	textures[0] = iconRegistry.registerIcon("BlockTimeTravelerBot");
    	textures[1] = iconRegistry.registerIcon("BlockTimeTravelerTop");
    	textures[2] = iconRegistry.registerIcon("BlockTimeTravelerSide");
    	textures[3] = iconRegistry.registerIcon("BlockTimeTravelerFront");
    	textures[4] = iconRegistry.registerIcon("BlockTimeTravelerSide");
    	textures[5] = iconRegistry.registerIcon("BlockTimeTravelerSide");
    }
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
	}
}

	
