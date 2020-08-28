package com.charsmud.timetraveler.gui;

import org.lwjgl.input.Keyboard;

import com.charsmud.timetraveler.TimeTraveler;
import com.charsmud.timetraveler.TimeTraveler.PlayerTemporalLocation;
import com.charsmud.timetraveler.util.mechanics.TimeTeleporter;
import com.charsmud.timetraveler.world.DimensionInit;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;


/**
 * GUI for returning to the Present from the Future
 * @author Charsmud
 *
 */
public class GuiFutureReturn extends GuiScreen
{
	private EntityPlayer player;
    public GuiFutureReturn(EntityPlayer playerClicked)
    {
    	player = playerClicked;
    }

    /**
     * Called from the main game loop to update the screen.
     */
    @Override
    public void updateScreen()
    {
    }
    @Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    @Override
    public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, ("Yes")));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, ("No")));
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    @Override
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        if (par1GuiButton.enabled)
        {
            if (par1GuiButton.id == 0)
            {
            	TimeTraveler.TEMPORAL_LOCATION = PlayerTemporalLocation.PRESENT;
            	TimeTeleporter.teleportToDimension(player, 0, player.posX, player.posY, player.posZ);
            	TimeTraveler.past_mechanics.beginPastRecording(player);

            	// get the height value so you don't get stuck in solid blocks or worse, in the void
            	double dy = player.world.getHeight(MathHelper.floor(player.posX), MathHelper.floor(player.posZ));
            	// still seem to need to set the position, +1 so you don't get in the void
            	player.setPositionAndUpdate(player.posX, dy + 1, player.posZ);
            	/*File present = new File(mc.mcDataDir + "/mods/TimeMod/present/" + mc.getIntegratedServer().getWorldName());
            	File future = new File(mc.mcDataDir + "/mods/TimeMod/future/" + mc.getIntegratedServer().getWorldName() + "/" + TimeTraveler.vars.getFuture());
		        File worldFile = new File(mc.mcDataDir + "/saves/" + mc.getIntegratedServer().getWorldName() + "/region");
		        File futureDim = new File(mc.mcDataDir + "/saves/" + mc.getIntegratedServer().getWorldName() + "/DIM10/region");

				String worldName = mc.getIntegratedServer().getWorldName();
				String folderName = mc.getIntegratedServer().getFolderName();
				if(TimeTraveler.vars.getIsPreGenerated())
				{
					try
					{
						mc.displayGuiScreen(null);
						EntityPlayerMP player = null;
						MinecraftServer minecraft = FMLClientHandler.instance().getServer();
						String allNames[] = minecraft.getAllUsernames().clone();
						for(int i = 0; i < allNames.length; i++)
						{
							player = minecraft.getConfigurationManager().func_152612_a(allNames[i]);
							System.out.println(player);
						}
						CopyFile.moveMultipleFiles(futureDim, future);
                        mc.getIntegratedServer().getConfigurationManager().transferPlayerToDimension(player, 0, new TeleporterFuture(mc.getIntegratedServer().worldServerForDimension(1)));
                        TimeTraveler.vars.setIsPreGenerated(false);
					}
					catch(IOException ex)
					{
						ex.printStackTrace();
					}
				}
				else
				{
	            	try 
	            	{
	    		       // mc.statFileWriter.readStat(StatList.leaveGameStat, 1);
	    		        mc.theWorld.sendQuittingDisconnectingPacket();
	    		        mc.loadWorld((WorldClient)null);

	            		Thread.sleep(3000);
						CopyFile.moveMultipleFiles(worldFile, future);
						Thread.sleep(2000);
						CopyFile.moveMultipleFiles(present, worldFile);
					} 
	            	catch (Exception e)
	            	{
						e.printStackTrace();
						System.out.println("Couldn't move files!");
					}
			        mc.launchIntegratedServer(folderName, worldName, (WorldSettings)null);
				}
            	TimeTraveler.vars.setIsInFuture(false);
            	//mc.displayGuiScreen(null);
            	*/
            	 
            }
            else if (par1GuiButton.id == 1)
            {
                this.mc.displayGuiScreen(null);
            }
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, ("Return to the Present?"), this.width / 2, this.height / 4 - 60 + 20, 16777215);
        super.drawScreen(par1, par2, par3);
    }
}
