package net.minecraft.src;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import net.minecraft.client.Minecraft;



import net.minecraft.client.Minecraft;

public class GuiTimeTravel extends GuiScreen {
    public GuiTimeTravel() {
    	super();
    }

    File directory = new File(Minecraft.getMinecraftDir(), "mods/TimeMod/past");
    private boolean selected;

  File[] files = directory.listFiles();

    
    int xpos = 0;
    
    
    public void initGui() {
selected = false;
        controlList.clear();
        //i is index
        for ( int i = 0; i < files.length; i++ ) {
        	xpos = xpos + 1;
        	System.out.println(files[i].getName());       		
        	if(i < 35) {
        	System.out.println("Creating Buttons");
        	controlList.add(new GuiButton(i, 30 + ((i)/7) * 70,60 + ((i) % 7) * 20, 70, 20, (new StringBuilder()).append(files[i].getName()).toString()));

        	}
        	
        	else {
        		
        	}

        	
        	if (i > 34) {
                	controlList.add(new GuiButton(999, 310, 210, 70, 20, (new StringBuilder()).append("Next Page").toString()));

        	}
        }
        
        controlList.add(new GuiButton(1000, 135, 210, 150, 20, "Exit"));
    }
    public void actionPerformed(GuiButton guibutton)
    {
        if(guibutton.id <= 999)
        {
            mc.thePlayer.addChatMessage("Loading...");
        }
        if(guibutton.id == 999)
        {
        	  for ( int i = 0; i < 35; i++ ) {
        		  controlList.remove(0);
        		  
        	  }
        	  
        	   for ( int i = 35; i < files.length; i++ ) {
               	xpos = xpos + 1;
               	System.out.println(files[i].getName());   
           		
               	if(i < 70) {
               	
               	controlList.add(new GuiButton(i - 35, 30 + ((i - 35)/7) * 70,60 + ((i - 35) % 7) * 20, 70, 20, (new StringBuilder()).append(files[i].getName()).toString()));
               	}
               	
               	else {
               		
               	}
               	if (i > 70) {
                	controlList.add(new GuiButton(1001, 310, 210, 70, 20, (new StringBuilder()).append("Next Page").toString()));

               	}
               }
        }
        
        if(guibutton.id == 1000)
        {
        	mc.displayGuiScreen(null);
        }
      if(guibutton.id == 1001) {
    	  for ( int i = 0; i < 35; i++) {
    		  controlList.remove(0);
    	  }
    	  for ( int i = 70; i < files.length; i++) {
    		  System.out.println(files[i].getName());
    		  
    		  if (i < 104) {
    			  controlList.add(new GuiButton(i - 70, 30 + ((i - 70)/7) * 70, 60 + ((i - 70)%7) * 20, 70, 20, (new StringBuilder()).append(files[i].getName()).toString()));
    			  
    		  }
    		  else {
    			  
    		  }
    		  if (i > 104){ 
              	controlList.add(new GuiButton(1002, 310, 210, 70, 20, (new StringBuilder()).append("Next Page").toString()));

    		  }
    	  }
      }
      if(guibutton.id == 1002) {
    	  for (int i = 0; i < 35; i++) {
    		  controlList.remove(0);
    	  }
    		  for (int i = 70; i < files.length; i++) {
        		  System.out.println(files[i].getName());

    		  }
    		  for (int i = 105; i < files.length; i++) {
    			  if (i < 150) {
        			  controlList.add(new GuiButton(i - 105, 30 + ((i - 105)/7) * 70, 60 + ((i - 70)%7) * 20, 70, 20, (new StringBuilder()).append(files[i].getName()).toString()));

    			  }
    			  else {
    				  
    			  }
    			  if( i > 150)
    			  {
    	              	controlList.add(new GuiButton(1003, 310, 210, 70, 20, (new StringBuilder()).append("Next Page").toString()));

    			  }
    		  }
    		  
    	  
      }
    }
    
    public boolean doesGuiPauseGame()
    {
        return false;
    }
    public void onGuiClosed()
    {
    }
    public void drawScreen(int i, int j, float f)
    {
    	drawDefaultBackground();
        int k = (width - 176) / 2;
        int l = (height - 166) / 2;
        drawCenteredString(fontRenderer, "Available Times", width / 2, 45, 0xffffff);
        super.drawScreen(i, j, f);
    }
}