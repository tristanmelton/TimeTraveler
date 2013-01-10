package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class BlockTimeTraveler extends Block
{	
     protected BlockTimeTraveler(int i, int j)
    {
        super(i, Material.ground);
        this.setHardness(9.0F);
    }
     
     /**
      * Called when block is right-clicked.  Handles opening of the GUI
      */
     public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
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
     public static int[] textures = new int[6];
     static
     {
       int ctr = 0;
       textures[ctr++] = ModLoader.addOverride("/terrain.png", "/TimeMod/botoftime.png");  // face 0
       textures[ctr++] = ModLoader.addOverride("/terrain.png", "/TimeMod/topoftime.png"); // face 1
       textures[ctr++] = ModLoader.addOverride("/terrain.png", "/TimeMod/sideoftime3.png"); // face 2
       textures[ctr++] = ModLoader.addOverride("/terrain.png", "/TimeMod/frontoftime.png"); // face 3
       textures[ctr++] = ModLoader.addOverride("/terrain.png", "/TimeMod/sideoftime2.png"); // face 4
       textures[ctr++] = ModLoader.addOverride("/terrain.png", "/TimeMod/sideoftime.png"); // face 5
     }
     public int getBlockTextureFromSide(int face)
     {
       if(face >= 0 && face <= 5)
         return BlockTimeTraveler.textures[face];
       return BlockTimeTraveler.textures[0];
     }
}

	
