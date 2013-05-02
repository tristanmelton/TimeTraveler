package timeTraveler.gui;
//
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.StringTranslate;

import org.lwjgl.input.Keyboard;

import timeTraveler.mechanics.FutureTravelMechanics;

import cpw.mods.fml.client.FMLClientHandler;

/**
 * GUI for the paradoximer
 * @author Charsmud
 *
 */
public class GuiFutureTravel extends GuiScreen{

    private GuiScreen parentGuiScreen;
    private GuiTextField theGuiTextField;
    private final String yearsIntoFuture;


    
    public GuiFutureTravel(GuiScreen par1GuiScreen, String par2Str)
    {
        this.parentGuiScreen = par1GuiScreen;
        this.yearsIntoFuture = par2Str;  
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        this.theGuiTextField.updateCursorCounter();
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        StringTranslate var1 = StringTranslate.getInstance();
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, var1.translateKey("Travel Into the Future!")));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, var1.translateKey("gui.cancel")));
        this.theGuiTextField = new GuiTextField(this.fontRenderer, this.width / 2 - 100, 60, 200, 20);
        this.theGuiTextField.setFocused(true);
        this.theGuiTextField.setText(yearsIntoFuture);
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
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
                	
            		FutureTravelMechanics ftm = new FutureTravelMechanics();
            	
            		WorldClient world = FMLClientHandler.instance().getClient().theWorld;
                	System.out.println(run);
            		for (int i = 0; i < run; i++)
                	{
                		ftm.expandOres(world, 1, 1, 1, 1, 1, 1, 1);
                		ftm.expandForests(world, 2);
                	}
            	}
            }
        }
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char par1, int par2)
    {
    	Character c = par1;
    		if(c.isDigit(c))
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
    protected void mouseClicked(int par1, int par2, int par3)
    {
        super.mouseClicked(par1, par2, par3);
        this.theGuiTextField.mouseClicked(par1, par2, par3);
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        StringTranslate var4 = StringTranslate.getInstance();
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, var4.translateKey("Future Travel"), this.width / 2, this.height / 4 - 60 + 20, 16777215);
        this.drawString(this.fontRenderer, var4.translateKey("Years"), this.width / 2 - 100, 47, 10526880);
        this.theGuiTextField.drawTextBox();
        super.drawScreen(par1, par2, par3);
    }
}
