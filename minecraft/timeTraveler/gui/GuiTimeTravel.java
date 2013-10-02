package timeTraveler.gui;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.server.FMLServerHandler;

import timeTraveler.core.StringArrayHolder;
import timeTraveler.core.TimeTraveler;
import timeTraveler.mechanics.CopyFile;
import timeTraveler.mechanics.EntityMechanics;
import timeTraveler.mechanics.PastMechanics;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraft.stats.StatList;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.storage.WorldInfo;

/**
 * GUI for the Paradox Cube
 * @author Charsmud
 *
 */
public class GuiTimeTravel extends GuiScreen {

public static boolean isInPast = false;
private ArrayList timeList;
//
/** the currently selected world */
private int selectedWorld;

private String localizedWorldText;

private boolean selected = false;

private GuiTimeSlot timeSlotContainer;

File source;
public static File staticsource;

private GuiButton buttonSelect;


  public GuiTimeTravel() {
   super();
  }
  Minecraft mc = ModLoader.getMinecraftInstance();
  WorldInfo wi = mc.theWorld.getWorldInfo();
  public  File directory;
  public File[] files;
  public int timezone;
  public int num;
  
  public File worldInPast;
/**
 * Initializes the GUI, sest up buttons and title and directories.
 */
        public void initGui() {
        	Minecraft minecraft = ModLoader.getMinecraftInstance();
        	MinecraftServer ms = minecraft.getIntegratedServer();
        	
        	directory = new File(FMLClientHandler.instance().getClient().mcDataDir + "/mods/TimeMod/past/");
        	directory.mkdir();
        	directory = new File(FMLClientHandler.instance().getClient().mcDataDir + "/mods/TimeMod/past/" + ms.getWorldName());
        	files = directory.listFiles(); 
            //i is index
            this.localizedWorldText = ("selectWorld.world");
            
            this.buttonList.add(this.buttonSelect = new GuiButton(1, this.width / 2 - 154, this.height - 52, 150, 20, "Travel"));
            this.buttonList.add(new GuiButton(0, this.width / 2 - 154, this.height - 28, 150, 20, ("Cancel")));
          
            this.buttonSelect.enabled = false;
            
            this.timeSlotContainer = new GuiTimeSlot(this);
            this.timeSlotContainer.registerScrollButtons(4, 5);
            this.loadSaves();

                
        }
        /**
         * Called whenever a button is pressed.  Handles past travel to different zones
         */ 
        	public void actionPerformed(GuiButton gButton) 
        	{
        		Minecraft minecraft = ModLoader.getMinecraftInstance();
        		MinecraftServer ms = minecraft.getIntegratedServer();
        		if(gButton.id == 0)
        		{
        			mc.displayGuiScreen(null);
        		}
        		if(gButton.id == 1)
        		{	
                    String nameOfTime = (this.timeList.get(this.getSelectedWorld(this)).toString());
        			System.out.println(nameOfTime);
        			for(int i = 0; i < files.length; i++)
        			{
        				System.out.println(files[i].toString());
        				if(files[i].toString().contains(nameOfTime)) 
        				{
            				try {
            					
                        		PastMechanics pMechanics = new PastMechanics();
                        		EntityMechanics eMechanics = new EntityMechanics();
                        		
            					WorldClient wc = minecraft.theWorld;
            					WorldInfo worldi = mc.theWorld.getWorldInfo();
            					
            					String worldName = ms.getWorldName();
            					String folderName = ms.getFolderName();
            					
            					TimeTraveler.vars.setPastTime(nameOfTime);
            					File allEntityData = new File(FMLClientHandler.instance().getClient().mcDataDir +"/mods/TimeMod/past/EntityLocations/" + FMLClientHandler.instance().getServer().getWorldName() + "/" + TimeTraveler.vars.getPastTime() + ".epd");
            					
            					mc.thePlayer.addChatMessage("Loading...");
            					File present = new File("./saves/" + ms.getWorldName() + "/region").getAbsoluteFile();
            					String fname = "\\mods\\TimeMod\\present\\" + ms.getWorldName();
                            
            					File directory = new File(mc.mcDataDir + fname);
                            
            					CopyFile cp = new CopyFile();           
                     
            					System.out.println("Present: " + present);
            					System.out.println("Directory: " + directory);
            					cp.copyDirectory(present, directory);                 
            					isInPast = true;
            					
            					EntityPlayer player = minecraft.thePlayer;

            					//eMechanics.despawnAllEntities(FMLServerHandler.instance().getServer().worldServerForDimension(0));
            					
            				    minecraft.theWorld.sendQuittingDisconnectingPacket();
            					minecraft.loadWorld((WorldClient)null);
            					minecraft.displayGuiScreen(new GuiMainMenu());
                           
            					source =  new File(FMLClientHandler.instance().getClient().mcDataDir +"/mods/TimeMod/past/" + ms.getWorldName() + "/" + nameOfTime); 
            					staticsource = source;
                            	worldInPast = source;
                            	File dest = new File(FMLClientHandler.instance().getClient().mcDataDir +"/saves/" + ms.getWorldName() + "/region");
                            
                            	try 
                            	{
                    		        if (minecraft.getSaveLoader().canLoadWorld(worldName))
                    		        {
                                		Thread.sleep(files.length * 750 * 2);
                                		System.out.println(nameOfTime);
                                		System.out.println(source);
                                		System.out.println(source.listFiles());
                                		System.out.println(dest.listFiles());
                                		
                                		CopyFile.moveMultipleFiles(source, dest);
                                		
                    					
                    					try 
                    					{
                    						BufferedReader reader = new BufferedReader(new FileReader(allEntityData));
                    						String line;
                    						while (((line = reader.readLine()) != null) && TimeTraveler.vars.getNextSet()) 
                    						{
                    							String[] rawData = line.split(",");
                    							
                    							String data = rawData[1] + "," + rawData[2] + "," + rawData[3];
                    							String uuid = rawData[4];
                    							String entityName = rawData[0];
                    							                    			
                    							String[] entityData = new String[2];
                    							entityData[0] = uuid;
                    							entityData[1] = entityName;
                    							
                    							StringArrayHolder data2 = new StringArrayHolder(entityData);
                    							TimeTraveler.vars.pathData.addEntity(data2);
                    							TimeTraveler.vars.pathData.addData(data2, data);
                    						}
                    						reader.close();	
                    					} 
                    					catch (IOException e) 
                    					{
                    						e.printStackTrace();
                    					}
                                		
                                		minecraft.launchIntegratedServer(folderName, worldName, (WorldSettings)null);
                    		        }
                            	}
                            catch (Exception ex)
                            {
                            	ex.printStackTrace();
                            }
            			}
            			
            		catch (IOException ex) 
            		{
            			ex.printStackTrace();
            		}
        		}	
        	}
        }
        else
        {
         this.timeSlotContainer.actionPerformed(gButton);
         }

}
        public boolean doesGuiPauseGame()
        {
                return false;
        }
        public void onGuiClosed()
        {
        }
        /**
         * Draws the screen and all the components in it.
         */
        public void drawScreen(int par1, int par2, float par3)
        {
            this.timeSlotContainer.drawScreen(par1, par2, par3);
            this.drawCenteredString(this.fontRenderer, "Travel To...", this.width / 2, 20, 16777215);
            super.drawScreen(par1, par2, par3);
        }        
        static List getSize(GuiTimeTravel par1)
        {
            return par1.timeList;
        }
        private void loadSaves()
        {
            this.timeList = new ArrayList();
            System.out.println(files);
            for(File file : files) {
            	System.out.println(files.length + " " + file.getName());
            	if(!file.getName().contains("Entity"))
            	{
                	timeList.add(file.getName());
            	}
            }
            this.selectedWorld = -1;
        }

        /**
         * called whenever an element in this gui is selected
         */
        static int onElementSelected(GuiTimeTravel par0GuiSelectWorld, int par1)
        {
            return par0GuiSelectWorld.selectedWorld = par1;
        }
        /**
         * returns the world currently selected
         */
        static int getSelectedWorld(GuiTimeTravel par0GuiSelectWorld)
        {
            return par0GuiSelectWorld.selectedWorld;
        }

        /**
         * returns the select button
         */
        static GuiButton getSelectButton(GuiTimeTravel par0GuiSelectWorld)
        {
            return par0GuiSelectWorld.buttonSelect;
        }
   
        /**
         * Gets the selected world.
         **/
        
        public void selectWorld(int par1)
        {
            this.mc.displayGuiScreen((GuiScreen)null);

            if (!this.selected)
            {
                this.selected = true;
                String var2 = this.getSaveFileName(par1);

                if (var2 == null)
                {
                    var2 = "World" + par1;
                }

                String var3 = this.getSaveName(par1);

                if (var3 == null)
                {
                    var3 = "World" + par1;
                }
                this.mc.launchIntegratedServer(var2, var3, (WorldSettings)null);
            }
        }
        protected String getSaveFileName(int par1)
        {
            String var2 = (this.timeList.get(par1).toString());
            return var2;
        }
          /**
         * returns the name of the saved game
         */
        protected String getSaveName(int par1)
        {
            String var2 = (this.timeList.get(par1)).toString();

            if (var2 == null || MathHelper.stringNullOrLengthZero(var2))
            {
                var2 = ("Select World") + " " + (par1 + 1);
            }

            return var2;
        }
        static String getLocalizedWorldName(GuiTimeTravel par0GuiSelectWorld)
        {
            return par0GuiSelectWorld.localizedWorldText;
        }
        public File getSaveNumber()
        {
        	return source;
        }
}