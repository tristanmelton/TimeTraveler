package timeTraveler.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.server.MinecraftServer;

/**
 * GUI for Future Loading
 * 
 * @author Charsmud
 * 
 */
public class GuiFutureGenerating extends GuiScreen 
{
	public GuiFutureGenerating() 
	{
		super();
	}

	static final Minecraft mc = Minecraft.getMinecraft();
	static final MinecraftServer ms = mc.getIntegratedServer();
	
	int futureYear;
	int yearsGenerated;
	
	/**
	 * Initializes the GUI, sest up buttons and title and directories.
	 */
	public void initGui()
	{
	}

	/**
	 * Called whenever a button is pressed. Handles past travel to different
	 * zones
	 */
	public void actionPerformed(GuiButton gButton)
	{
		if (gButton.id == 0) 
		{
			mc.displayGuiScreen(null);
		}
	}

	public boolean doesGuiPauseGame()
	{
		return true;
	}

	public void onGuiClosed() 
	{
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	public void drawScreen(int par1, int par2, float par3)
	{
		this.drawCenteredString(this.fontRendererObj, "Loading Future...",
				this.width / 2, 20, 16777215);
		this.drawCenteredString(this.fontRendererObj, futureYear - yearsGenerated + " Years Left!",
				this.width / 2, 40, 16777215);
		super.drawScreen(par1, par2, par3);
		
		if(futureYear == yearsGenerated)
		{
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
	}
	public void setFutureYears(int future)
	{
		futureYear = future;
	}
	public void setGenerated(int generated)
	{
		yearsGenerated = generated;
	}
}