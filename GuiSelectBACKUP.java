package net.minecraft.src;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import net.minecraft.client.Minecraft;

public class GuiSelectBACKUP extends GuiScreen
{
    private final DateFormat dateFormatter = new SimpleDateFormat();
    protected GuiScreen parentScreen;
    protected String screenTitle;
    private boolean selected;
    private int selectedWorld;
    private List saveList;
    private GuiBACKUPSlot worldSlotContainer;
    private String localizedWorldText;
    private String localizedMustConvertText;
    private String localizedGameModeText[];
    private GuiButton buttonSelect;
    private File BACKUPDirectory;
    //private File seedDirectory;
    public GuiSelectBACKUP(File file)
    {
        screenTitle = "Select Time Zone";
        selected = false;
        localizedGameModeText = new String[2];
        parentScreen = null;
        BACKUPDirectory = new File(file, "Time");
      //  seedDirectory = new File(file, "Time/seed.txt");
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        screenTitle = stringtranslate.translateKey("Select a TimeZone");
        localizedWorldText = stringtranslate.translateKey("selectbackup.world");
        localizedMustConvertText = stringtranslate.translateKey("selectbackup.conversion");
        localizedGameModeText[0] = stringtranslate.translateKey("gameMode.survival");
        localizedGameModeText[1] = stringtranslate.translateKey("gameMode.creative");
        loadSaves();
        worldSlotContainer = new GuiBACKUPSlot(this);
        worldSlotContainer.registerScrollButtons(controlList, 4, 5);
        initButtons();
    }

    public List getSaveList2()
    {
        ArrayList arraylist = new ArrayList();
        File afile[] = BACKUPDirectory.listFiles();
        File afile1[] = afile;
        int i = afile1.length;

        for (int j = 0; j < i; j++)
        {
            File file = afile1[j];

            if (!file.isDirectory())
            {
                continue;
            }

            String s = file.getName();
            WorldInfo worldinfo = getWorldInfo2(s);

            if (worldinfo == null)
            {
                continue;
            }

            boolean flag = worldinfo.getSaveVersion() != 19132;
            String s1 = worldinfo.getWorldName();

            if (s1 == null || MathHelper.stringNullOrLengthZero(s1))
            {
                s1 = s;
            }

            long l = 0L;
            arraylist.add(new SaveFormatComparator(s, s1, worldinfo.getLastTimePlayed(), l, worldinfo.getGameType(), flag, worldinfo.isHardcoreModeEnabled()));
        }

        return arraylist;
    }

    public WorldInfo getWorldInfo2(String s)
    {
        File file = new File(BACKUPDirectory, s);

        if (!file.exists())
        {
            return null;
        }

        File file1 = new File(file, "level.dat");

        if (file1.exists())
        {
            try
            {
                NBTTagCompound nbttagcompound = CompressedStreamTools.readCompressed(new FileInputStream(file1));
                NBTTagCompound nbttagcompound2 = nbttagcompound.getCompoundTag("data");
                return new WorldInfo(nbttagcompound2);
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
        }

        file1 = new File(file, "level.dat_old");

        if (file1.exists())
        {
            try
            {
                NBTTagCompound nbttagcompound1 = CompressedStreamTools.readCompressed(new FileInputStream(file1));
                NBTTagCompound nbttagcompound3 = nbttagcompound1.getCompoundTag("data");
                return new WorldInfo(nbttagcompound3);
            }
            catch (Exception exception1)
            {
                exception1.printStackTrace();
            }
        }

        return null;
    }

    private void loadSaves()
    {
        saveList = getSaveList2();
        Collections.sort(saveList);
        selectedWorld = -1;
    }

    protected String getSaveFileName(int i)
    {
        return ((SaveFormatComparator)saveList.get(i)).getFileName();
    }

    protected String getSaveName(int i)
    {
        String s = ((SaveFormatComparator)saveList.get(i)).getDisplayName();

        if (s == null || MathHelper.stringNullOrLengthZero(s))
        {
            StringTranslate stringtranslate = StringTranslate.getInstance();
            s = (new StringBuilder()).append(stringtranslate.translateKey("selectWorld.world")).append(" ").append(i + 1).toString();
        }

        return s;
    }

    public void initButtons()
    {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        controlList.add(buttonSelect = new GuiButton(1, width / 2 - 154, height - 52, 150, 20, stringtranslate.translateKey("selectWorld.select")));
        controlList.add(new GuiButton(0, width / 2 + 4, height - 28, 150, 20, stringtranslate.translateKey("gui.cancel")));
        buttonSelect.enabled = false;
    }

    public File RecupererDossierSave()
    {
        Minecraft minecraft = ModLoader.getMinecraftInstance();
        World world = minecraft.theWorld;

        if (!world.isRemote)
        {
            return ((SaveHandler)world.saveHandler).getSaveDirectory();
        }
        else
        {
            return null;
        }
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton guibutton)
    {
        if (!guibutton.enabled)
        {
            return;
        }
        else if (guibutton.id == 1)
        {
        	//TODO: NULL STARTS HERE
            selectWorld(selectedWorld);
        }
        else if (guibutton.id == 3)
        {
            File file = RecupererDossierSave();
            File file1 = new File(file.getParentFile(), getSaveFileName(selectedWorld));
            file1.mkdir();
            File file2 = new File(file, "Time");
            File file3 = new File(file2, getSaveFileName(selectedWorld));

            try
            {
                mod_Time.copy(file3, file1);
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
            }

            mc.loadingScreen.printText("Pasted to Saves folder");
            mc.displayGuiScreen(null);
            mc.setIngameFocus();
        }
        else if (guibutton.id == 0)
        {
            mc.displayGuiScreen(null);
            mc.setIngameFocus();
        }
        else
        {
            worldSlotContainer.actionPerformed(guibutton);
        }
    }

    public void selectWorld(int i)
    {
        mc.displayGuiScreen(null);

        if (selected)
        {
            return;
        }

        selected = true;
        int j = ((SaveFormatComparator)saveList.get(i)).getGameType();

        if (j == 0)
        {
            mc.playerController = new PlayerControllerSP(mc);
        }
        else
        {
            mc.playerController = new PlayerControllerCreative(mc);
        }

        String s = getSaveFileName(i);

        if (s == null)
        {
            s = (new StringBuilder()).append("World").append(i).toString();
        }

        //TODO: Problem here!  
       /* Long l = null;
        if(seedDirectory.exists()) {
        	try {
                BufferedReader reader = new BufferedReader(new FileReader(seedDirectory));
                
                String seed = reader.readLine();
                l = Long.valueOf(seed);
        	}
        	catch(IOException ex) {
        		ex.printStackTrace();
        	}
        S
        }*/
        startWorld(s, getSaveName(i), null);//new WorldSettings(mc.theWorld.getSeed(), 0, true, false, WorldType.DEFAULT));
        mc.displayGuiScreen(null);

    }
    public void startWorld(String par1Str, String par2Str, WorldSettings par3WorldSettings)
    {
        mc.changeWorld1(null);
        System.gc();

        //net.minecraft.src.ISaveHandler isavehandler = saveLoader.getSaveLoader(par1Str, false);
        World world = null;
        // world = new World(isavehandler, par2Str, par3WorldSettings);
        world = mc.theWorld;
        mc.changeWorld2(world, StatCollector.translateToLocal("menu.loadingLevel"));
            
        
    }
    public void startWorld2(String s, String s1, WorldSettings ws)
    {
        Minecraft minecraft = ModLoader.getMinecraftInstance();
        minecraft.changeWorld1(null);
        System.gc();
        if (minecraft.loadingScreen != null)
        {
            minecraft.loadingScreen.printText("Switching level");
            minecraft.loadingScreen.displayLoadingString("");
        }
        ISaveHandler isavehandler = minecraft.getSaveLoader().getSaveLoader(s, false);
        System.out.println(s);
        System.out.println("1");
        System.out.println("2");
        System.out.println(isavehandler);
        System.out.println(s1);
        System.out.println(ws);
        World world = null;
        world = new World(isavehandler, s1, ws);// new WorldSettings(0L, 0, true, false, WorldType.DEFAULT));
        System.out.println("3");
        minecraft.changeWorld2(world, "Traveling...");
        System.out.println("4");
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int i, int j, float f)
    {
        worldSlotContainer.drawScreen(i, j, f);
        drawCenteredString(fontRenderer, screenTitle, width / 2, 20, 0xffffff);
        super.drawScreen(i, j, f);
    }

    static List getSize(GuiSelectBACKUP guiselectbackup)
    {
        return guiselectbackup.saveList;
    }

    static int onElementSelected(GuiSelectBACKUP guiselectbackup, int i)
    {
        return guiselectbackup.selectedWorld = i;
    }

    static int getSelectedWorld(GuiSelectBACKUP guiselectbackup)
    {
        return guiselectbackup.selectedWorld;
    }

    static GuiButton getSelectButton(GuiSelectBACKUP guiselectbackup)
    {
        return guiselectbackup.buttonSelect;
    }
    /**
     * Gets the localized world name
     */
    static String getLocalizedWorldName(GuiSelectBACKUP guiselectbackup)
    {
        return guiselectbackup.localizedWorldText;
    }

    static DateFormat getDateFormatter(GuiSelectBACKUP guiselectbackup)
    {
        return guiselectbackup.dateFormatter;
    }

    /**
     * Gets the localized must convert text
     */
    static String getLocalizedMustConvert(GuiSelectBACKUP guiselectbackup)
    {
        return guiselectbackup.localizedMustConvertText;
    }

    /**
     * Gets the localized GameMode
     */
    static String[] getLocalizedGameMode(GuiSelectBACKUP guiselectbackup)
    {
        return guiselectbackup.localizedGameModeText;
    }
}
