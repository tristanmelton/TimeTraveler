package net.minecraft.src;
import net.minecraft.client.Minecraft;

import java.io.*;
import java.util.List;
import java.util.logging.Logger;
import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class mod_Time extends BaseMod

{
	public static Block travelTime;
	public static Item paradoximer;
CopyFile copyfiletime;


public static final void zip( String origDir, File dirObj, ZipOutputStream out )
        throws IOException {
File[] files = dirObj.listFiles();
byte[] tmpBuf = new byte[1024];

for ( int i = 0; i < files.length; i++ ) {
if ( files[i].isDirectory() ) {
    zip( origDir, files[i], out );
    continue;
}
String wAbsolutePath =
files[i].getAbsolutePath().substring( origDir.length(),
    files[i].getAbsolutePath().length() );
FileInputStream in = 
    new FileInputStream( files[i].getAbsolutePath() );
out.putNextEntry( new ZipEntry( wAbsolutePath ) );
int len;
while ( (len = in.read( tmpBuf )) > 0 ) {
    out.write( tmpBuf, 0, len );
}
out.closeEntry();
in.close();
}
}
    
public void load()
{

	
paradoximer = new ItemParadoximer(2330).setItemName("paradoximer");
paradoximer.iconIndex = ModLoader.addOverride("/gui/items.png", "/TimeMod/Items/paradoximer.png");
ModLoader.setInGameHook(this, true, true);

travelTime = new BlockTimeTraveler(255, 0).setHardness(9.0F).setBlockName("travelTime");

ModLoader.registerBlock(travelTime);

ModLoader.addName(travelTime, "Paradox Cube");
ModLoader.addName(paradoximer, "Paradoximer");

ModLoader.addRecipe(new ItemStack(travelTime,  13), new Object[] {
    
"x", Character.valueOf('x'), Block.dirt

});

ModLoader.addRecipe(new ItemStack(paradoximer,  13), new Object[] {
    
"x", "s", Character.valueOf('x'), Block.wood, Character.valueOf('s'), Block.dirt

});




}

public int ctr;
public int counter = 0;



public boolean onTickInGame(float f, Minecraft minecraft)
{
  ctr++;
  if(ctr == 20 * 60) //20 is the amount of ticks per second. Times 60 for 1 minute.
	  //change to 600 for final release
  {
    //Do the stuff you want to do after x minutes here.

      int counterstart = new File(Minecraft.getMinecraftDir(), "mods/TimeMod/past").listFiles().length;

      counter = counterstart;

try
{
	String fname = ModLoader.getMinecraftInstance().getMinecraftDir() + "\\mods\\TimeMod\\past\\Time ";


    counter = counter + 1;
    //fname = fname.concat(String.valueOf(counter));
    fname = fname.concat(String.format("%03d",counter));
    fname = fname.concat(".zip");




        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(fname) );
        zip("./saves/New World", new File(Minecraft.getMinecraftDir(), "/saves/New World" ), out );
        out.close();
       System.out.println("Created a time!");
        
        
                
//File destination = new File(Minecraft.getMinecraftDir(), "mods/TimeMod/past");
//File zipped = new File(Minecraft.getMinecraftDir(), "mods/TimeMod/past/w1.zip");

        
       // copyfiletime.unzip(zipped, destination);     
//Unzip files when NEEDED! TODO: Zip back up when user leaves.  

        

        

    }
catch ( FileNotFoundException e )
{
        e.printStackTrace();
        
    }
catch ( IOException e )
{
        
    e.printStackTrace();
}






           
    

    ctr = 0;
 //Reset the count.
  }
return true;
}






public String getVersion() 
   
{
        return "1.1.0";
    
}
}