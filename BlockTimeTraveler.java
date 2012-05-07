package net.minecraft.src;

public class BlockTimeTraveler extends Block
{

     protected BlockTimeTraveler(int i, int j)
    {
        super(i, Material.ground);
    }
        
     public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer) {

    	     double d = i;
    	     double d1 = j;
    	     double d2 = k;
  	  	        	 ModLoader.openGUI(entityplayer, new GuiTimeTravel());
  	 

    	 
    

    	 
			return true;
    	}
     
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

	
