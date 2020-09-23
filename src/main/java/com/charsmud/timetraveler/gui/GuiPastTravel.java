package com.charsmud.timetraveler.gui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.charsmud.timetraveler.TimeTraveler;
import com.charsmud.timetraveler.TimeTraveler.PlayerTemporalLocation;
import com.charsmud.timetraveler.util.mechanics.TimeTeleporter;
import com.charsmud.timetraveler.world.DimensionInit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.fml.client.FMLClientHandler;

/**
 * GUI for the Paradox Cube
 * 
 * @author Charsmud
 *
 */
public class GuiPastTravel extends GuiScreen 
{

	public static boolean isInPast = false;
	private ArrayList<String> timeList;

	/** the currently selected world */
	private int selectedWorld;

	private String localizedWorldText;

	private boolean selected = false;

	private GuiTimeSlot timeSlotContainer;

	File source;
	public static File staticsource;

	private GuiButton buttonSelect;
	
	private EntityPlayer player;

	public GuiPastTravel(EntityPlayer player)
	{
		super();
		this.player = player;
	}

	static final Minecraft mc = Minecraft.getMinecraft();
	static final MinecraftServer ms = mc.getIntegratedServer();
	WorldInfo wi = mc.world.getWorldInfo();
	public File directory;
	public File[] files;
	public int timezone;
	public int num;

	public File worldInPast;

	/**
	 * Initializes the GUI, sest up buttons and title and directories.
	 */
	public void initGui() 
	{
		directory = new File(FMLClientHandler.instance().getClient().mcDataDir + "/mods/TimeMod/past/");
		if(!directory.exists())
			directory.mkdir();
		directory = new File(
				FMLClientHandler.instance().getClient().mcDataDir + "/mods/TimeMod/past/" + ms.getWorldName());
		files = directory.listFiles();
		this.loadSaves();

		this.localizedWorldText = ("selectWorld.world");
		
		this.buttonList.add(new GuiButton(0, this.width / 2 - 154, this.height - 28, 150, 20, ("Cancel")));
		this.buttonList.add(this.buttonSelect = new GuiButton(1, this.width / 2 - 154, this.height - 52, 150, 20, "Travel"));

		this.buttonSelect.enabled = false;

		this.timeSlotContainer = new GuiTimeSlot(this, timeList);
		//this.timeSlotContainer.registerScrollButtons(4, 5);

	}

	/**
	 * Called whenever a button is pressed. Handles past travel to different zones
	 */
	public void actionPerformed(GuiButton gButton)
	{
		if (gButton.id == 0) 
		{
			mc.displayGuiScreen(null);
		}
		if (gButton.id == 1) 
		{
        	TimeTraveler.TEMPORAL_LOCATION = PlayerTemporalLocation.PAST;
        	TimeTraveler.past_mechanics.stopPastRecording(this.player);
        	TimeTeleporter.teleportToDimension(this.player, DimensionInit.DIMENSION_PAST_ID, player.posX, player.posY, player.posZ);
        	TimeTraveler.past_mechanics.replayPast(this.player);
        	// get the height value so you don't get stuck in solid blocks or worse, in the void
        	double dy = player.world.getHeight(MathHelper.floor(player.posX), MathHelper.floor(player.posZ));
        	// still seem to need to set the position, +1 so you don't get in the void
        	player.setPositionAndUpdate(player.posX, dy + 1, player.posZ);

			//TODO: Implement past travel
			/*String nameOfTime = (this.timeList.get(this.getSelectedWorld(this)).toString());
			System.out.println(nameOfTime);
			for (int i = 0; i < files.length; i++)
			{
				System.out.println(files[i].toString());
				if (files[i].toString().contains(nameOfTime)) 
				{
					try 
					{

						PastMechanics pMechanics = new PastMechanics();
						EntityMechanics eMechanics = new EntityMechanics();

						WorldClient wc = mc.theWorld;
						WorldInfo worldi = mc.theWorld.getWorldInfo();

						String worldName = ms.getWorldName();
						String folderName = ms.getFolderName();

						TimeTraveler.vars.setLastPastTimeSavedForWorld(nameOfTime);
						// File allEntityData = new
						// File(FMLClientHandler.instance().getClient().mcDataDir
						// +"/mods/TimeMod/past/EntityLocations/" +
						// FMLClientHandler.instance().getServer().getWorldName() + "/" +
						// TimeTraveler.vars.getPastTime() + ".epd");

						mc.thePlayer.sendChatMessage("Loading...");
						File present = new File("./saves/" + ms.getWorldName() + "/region").getAbsoluteFile();
						String fname = "\\mods\\TimeMod\\present\\" + ms.getWorldName();

						File directory = new File(mc.mcDataDir + fname);

						CopyFile cp = new CopyFile();

						System.out.println("Present: " + present);
						System.out.println("Directory: " + directory);
						cp.copyDirectory(present, directory);
						isInPast = true;

						EntityPlayer player = mc.thePlayer;
						// pMechanics.loadEntityData();

						// eMechanics.despawnAllEntities(FMLServerHandler.instance().getServer().worldServerForDimension(0));

						mc.theWorld.sendQuittingDisconnectingPacket();
						mc.loadWorld((WorldClient) null);
						mc.displayGuiScreen(new GuiMainMenu());

						source = new File(FMLClientHandler.instance().getClient().mcDataDir + "/mods/TimeMod/past/"
								+ ms.getWorldName() + "/" + nameOfTime);
						staticsource = source;
						worldInPast = source;
						File dest = new File(FMLClientHandler.instance().getClient().mcDataDir + "/saves/"
								+ ms.getWorldName() + "/region");

						try
						{
							if (mc.getSaveLoader().canLoadWorld(worldName))
							{
								Thread.sleep(files.length * 750 * 2);
								System.out.println(nameOfTime);
								System.out.println(source);
								System.out.println(source.listFiles());
								System.out.println(dest.listFiles());

								CopyFile.moveMultipleFiles(source, dest);

								mc.launchIntegratedServer(folderName, worldName, (WorldSettings) null);
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
			}*/
		} 
		else 
		{
			this.timeSlotContainer.actionPerformed(gButton);
		}

	}
	public void setSelectedEnabled(boolean value)
	{
		this.buttonSelect.enabled = value;
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
		if(this.timeSlotContainer != null)
		{
			this.timeSlotContainer.drawScreen(par1, par2, par3);
			this.drawCenteredString(this.fontRenderer, "Travel To...", this.width / 2, 20, 16777215);
		}
		super.drawScreen(par1, par2, par3);
	}

	private void loadSaves()
	{
		this.timeList = new ArrayList();
		System.out.println(files);
		if (files != null) {
			for (File file : files) {
				System.out.println(files.length + " " + file.getName());
				if (!file.getName().contains("Entity"))
				{
					timeList.add(file.getName());
				}
			}
			this.selectedWorld = -1;
		}
	}

    public void selectTime(int index)
    {
        this.selectedWorld = index;
    }

	/**
	 * called whenever an element in this gui is selected
	 */
	static int onElementSelected(GuiPastTravel par0GuiSelectWorld, int par1)
	{
		return par0GuiSelectWorld.selectedWorld = par1;
	}

	/**
	 * returns the world currently selected
	 */
	public int getSelectedWorld()
	{
		return this.selectedWorld;
	}

	/**
	 * Gets the selected world.
	 **/

	public void selectWorld(int par1) 
	{
		this.mc.displayGuiScreen((GuiScreen) null);

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
			this.mc.launchIntegratedServer(var2, var3, (WorldSettings) null);
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

		if (var2 == null || var2.length() == 0) 
		{
			var2 = ("Select World") + " " + (par1 + 1);
		}

		return var2;
	}

	static String getLocalizedWorldName(GuiPastTravel par0GuiSelectWorld) 
	{
		return par0GuiSelectWorld.localizedWorldText;
	}

	public File getSaveNumber() 
	{
		return source;
	}
}