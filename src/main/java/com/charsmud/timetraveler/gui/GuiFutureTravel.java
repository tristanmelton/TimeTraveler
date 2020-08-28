package com.charsmud.timetraveler.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.charsmud.timetraveler.TimeTraveler;
import com.charsmud.timetraveler.TimeTraveler.PlayerTemporalLocation;
import com.charsmud.timetraveler.util.mechanics.TimeTeleporter;
import com.charsmud.timetraveler.world.DimensionInit;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldServer;

/**
 * GUI for the paradoximer
 * @author Charsmud
 *
 */
public class GuiFutureTravel extends GuiScreen 
{

    private GuiTextField theGuiTextField;
    private EntityPlayer player;
    public GuiFutureTravel(EntityPlayer playerClicked)
    {
    	player = playerClicked;
    }
    /**
     * Called from the main game loop to update the screen.
     */
    @Override
    public void updateScreen()
    {
    	if(this.theGuiTextField != null)
    		this.theGuiTextField.updateCursorCounter();
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    @Override
    public void initGui()
    {
    	System.out.println("Init gui");
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, ("Travel Into the Future!")));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, ("Cancel")));
        this.theGuiTextField = new GuiTextField(2, this.fontRenderer, this.width / 2 - 100, 60, 200, 20);
        this.theGuiTextField.setFocused(true);
        //this.theGuiTextField.setText(yearsIntoFuture);
        this.buttonList.get(0).enabled = false;
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    @Override
    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    @Override
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        if (par1GuiButton.enabled)
        {
            if (par1GuiButton.id == 1)
            {
                this.mc.displayGuiScreen(null);
            }
            else if (par1GuiButton.id == 0)
            {
            	if(theGuiTextField.getText() != "")
            	{
                	int run = Integer.parseInt(theGuiTextField.getText());
                	TimeTraveler.TEMPORAL_LOCATION = PlayerTemporalLocation.FUTURE;
                	TimeTraveler.past_mechanics.stopPastRecording(player);
                	TimeTeleporter.teleportToDimension(player, DimensionInit.DIMENSION_FUTURE_ID, player.posX, player.posY, player.posZ);
                	// get the height value so you don't get stuck in solid blocks or worse, in the void
                	double dy = player.world.getHeight(MathHelper.floor(player.posX), MathHelper.floor(player.posZ));
                	// still seem to need to set the position, +1 so you don't get in the void
                	player.setPositionAndUpdate(player.posX, dy + 1, player.posZ);

                	//TODO: Implement Future Travel
                	/*
            		FutureTravelMechanics ftm = new FutureTravelMechanics();
            	
            		WorldClient world = FMLClientHandler.instance().getClient().theWorld;
                	System.out.println(run);

                    ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                    DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);
                    try
                    {
                    	System.out.println(dataoutputstream + " :)");
                            dataoutputstream.writeInt(run);
                            System.out.println(":) :)");
                            TimeTraveler.snw.sendTo(new Message("futuretravel", "", "", mc.thePlayer.getDisplayName(), run, 0, 0, 0), (EntityPlayerMP)mc.getIntegratedServer().getEntityWorld().getPlayerEntityByName(mc.thePlayer.getDisplayName()));
                            System.out.println(":) :) :)");
                            TimeTraveler.vars.setFuture(run);
                            mc.displayGuiScreen(null);
                    }
                    catch (Exception exception)
                    {
                            exception.printStackTrace();
                    }*/

            	}
            }
        }
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    @Override
    protected void keyTyped(char par1, int par2)
    {
    	Character c = par1;
    		if(Character.isDigit(c))
    		{
	            this.theGuiTextField.textboxKeyTyped(par1, par2);
    		}
        ((GuiButton)this.buttonList.get(0)).enabled = this.theGuiTextField.getText().trim().length() > 0;
        if (par1 == 13)
        {
            this.actionPerformed((GuiButton)this.buttonList.get(0));
        }
    }

    /**
     * Called when the mouse is clicked.
     */
    @Override
    protected void mouseClicked(int par1, int par2, int par3)
    {
        try 
        {
			super.mouseClicked(par1, par2, par3);
	        this.theGuiTextField.mouseClicked(par1, par2, par3);
		} 
        catch (IOException e)
        {
			e.printStackTrace();
		}
    }

    /**
     * Draws the screen and all the components in it.
     */
    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, ("Future Travel"), this.width / 2, this.height / 4 - 60 + 20, 16777215);
        this.drawString(this.fontRenderer, ("Years"), this.width / 2 - 100, 47, 10526880);
        if(this.theGuiTextField != null)
        	this.theGuiTextField.drawTextBox();
        super.drawScreen(par1, par2, par3);

    }
}
