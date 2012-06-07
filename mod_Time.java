package net.minecraft.src;

import java.io.*;
import java.util.*;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Mouse;

public class mod_Time extends BaseMod
{
	public static Block travelTime;
	public static Item paradoximer;
    private Date now;
    private Minecraft mc;
    private World CeMonde;
    private GuiButton buttonBACKUP;
    public boolean FromMainMenu;
    Long l;
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
    public mod_Time()
    {
        mc = ModLoader.getMinecraftInstance();
        CeMonde = mc.theWorld;
        FromMainMenu = false;
        ModLoader.setInGUIHook(this, true, true);
        ModLoader.setInGameHook(this, true, true);
        ModLoader.addLocalization("backup.button", "Times");
        ModLoader.addLocalization("selectbackup.title", "Select Time (selectbackup.title)");
        ModLoader.addLocalization("selectbackup.world", "Select Time (selectbackup.world)");
        ModLoader.addLocalization("selectbackup.conversion", "Select Time");
    }

    public boolean onTickInGUI(float f, Minecraft minecraft, GuiScreen guiscreen)
    {
        Minecraft minecraft1 = ModLoader.getMinecraftInstance();

        if (guiscreen instanceof GuiSelectWorld)
        {
            FromMainMenu = false;
            StringTranslate stringtranslate = StringTranslate.getInstance();
            guiscreen.controlList.add(buttonBACKUP = new GuiButton(10, guiscreen.width - 75, 5, 70, 20, stringtranslate.translateKey("backup.button")));
            GuiButton guibutton = (GuiButton)guiscreen.controlList.get(1);
            buttonBACKUP.enabled = false;

            if (guibutton.enabled)
            {
                GuiSelectWorld _tmp = (GuiSelectWorld)guiscreen;
                int i = GuiSelectWorld.getSelectedWorld((GuiSelectWorld)guiscreen);
                String s = ((GuiSelectWorld)guiscreen).getSaveFileName(i);
                File file = new File(new File(minecraft1.mcDataDir, "saves"), s);
                File file1 = new File(file, "Time");
                File file2 = new File(new File(minecraft1.mcDataDir, "saves"), "HARDCORE");
                File file3 = new File(file2, s);
                File file4 = new File(file3, "Time");

                if (file1.exists() || file4.exists())
                {
                    buttonBACKUP.enabled = true;
                }
            }

            if (Mouse.getEventButtonState())
            {
                int j = (Mouse.getEventX() * guiscreen.width) / minecraft1.displayWidth;
                int k = guiscreen.height - (Mouse.getEventY() * guiscreen.height) / minecraft1.displayHeight - 1;
                int l = Mouse.getEventButton();

                if (l == 0)
                {
                    for (int i1 = 0; i1 < guiscreen.controlList.size(); i1++)
                    {
                        GuiButton guibutton1 = (GuiButton)guiscreen.controlList.get(i1);
                        GuiSelectWorld _tmp1 = (GuiSelectWorld)guiscreen;
                        int j1 = GuiSelectWorld.getSelectedWorld((GuiSelectWorld)guiscreen);

                        if (!guibutton1.mousePressed(minecraft1, j, k) || guibutton1.id != 10)
                        {
                            continue;
                        }

                        String s1 = ((GuiSelectWorld)guiscreen).getSaveFileName(j1);
                        File file5 = new File(new File(minecraft1.mcDataDir, "saves"), s1);
                        File file6 = new File(file5, "Time");

                        if (file6.exists())
                        {
                            minecraft1.displayGuiScreen(new GuiSelectBACKUP(file5));
                            FromMainMenu = true;
                        }
                    }
                }
            }
        }

        return true;
    }

    public static void copy(InputStream inputstream, OutputStream outputstream, int i) throws IOException
    {
        byte abyte0[] = new byte[i];
        int j;

        while ((j = inputstream.read(abyte0)) != -1)
        {
            outputstream.write(abyte0, 0, j);
        }
    }

    public static void copyDirectory(File file, File file1) throws IOException
    {
        if (!file1.exists())
        {
            file1.mkdir();
        }

        File afile[] = file.listFiles();

        for (int i = 0; i < afile.length; i++)
        {
            File file2 = afile[i];
            copy(file2, new File(file1, file2.getName()));
        }
    }

    public static void copyFile(File file, File file1) throws IOException
    {
        FileInputStream fileinputstream = new FileInputStream(file);
        FileOutputStream fileoutputstream = new FileOutputStream(file1);
        copy(fileinputstream, fileoutputstream, (int)Math.min(file.length(), 4096L));
        fileinputstream.close();
        fileoutputstream.close();
    }

    public static void copy(File file, File file1) throws IOException
    {
        if (file.isFile())
        {
            copyFile(file, file1);
        }
        else if (file.isDirectory())
        {
            copyDirectory(file, file1);
        }
        else
        {
            throw new FileNotFoundException((new StringBuilder()).append(file.toString()).append(" does not exist").toString());
        }
    }

    public File RecupererDossierSave()
    {
        Minecraft minecraft = ModLoader.getMinecraftInstance();
        World world = minecraft.theWorld;

        if ((!world.isRemote) & (!minecraft.theWorld.getWorldInfo().isHardcoreModeEnabled()))
        {
            return ((SaveHandler)world.saveHandler).getSaveDirectory();
        }

        if (!world.isRemote && minecraft.theWorld.getWorldInfo().isHardcoreModeEnabled())
        {
            File file = ((SaveHandler)world.saveHandler).getSaveDirectory();
            File file1 = new File(file.getParentFile(), "HARDCORE");
            File file2 = new File(file1, file.getName());

            if (!file2.exists())
            {
                file2.mkdirs();
            }

            return file2;
        }
        else
        {
            return null;
        }
    }

    public File CreerdossierBACKUP(File file)
    {
        File file1 = new File(file, "Time");

        if (!file1.exists())
        {
            file1.mkdir();
        }

        return file1;
    }

    public File CopierDossierNouvelleBACKUP(File file)
    {
        GregorianCalendar gregoriancalendar = new GregorianCalendar();
        long l = gregoriancalendar.getTime().getTime() / 1000L;
        gregoriancalendar.setTime(new Date(l * 1000L));
        String s = (new StringBuilder()).append("Time Zone ").append(gregoriancalendar.get(1)).append(" ").append(gregoriancalendar.get(2) + 1).append(" ").append(gregoriancalendar.get(5)).append(" - ").append(gregoriancalendar.get(11)).append("h").append(gregoriancalendar.get(12)).append("min").append(gregoriancalendar.get(13)).toString();
        String s1 = (new StringBuilder()).append(s).toString();
        File file1 = new File(file, s1);

        if (!file1.exists())
        {
            file1.mkdirs();
        }

        return file1;
    }

    public void TransfertVersNouvelleBACKUP(File file, File file1) throws IOException
    {
        File file2 = new File(file, "region");

        if (file2.exists())
        {
            File file3 = new File(file1, "region");
            file3.mkdirs();
            copy(file2, file3);
        }

        File file4 = new File(file, "data");

        if (file4.exists())
        {
            File file5 = new File(file1, "data");
            file5.mkdirs();
            copy(file4, file5);
        }

        File file6 = new File(file, "DIM1");

        if (file6.exists())
        {
            File file7 = new File(file1, "DIM1");
            file7.mkdirs();
            File file9 = new File(file6, "region");

            if (file6.exists())
            {
                File file12 = new File(file7, "region");
                file7.mkdirs();
                copy(file9, file12);
            }
        }

        File file8 = new File(file, "DIM-1");

        if (file8.exists())
        {
            File file10 = new File(file1, "DIM-1");
            file10.mkdirs();
            File file13 = new File(file8, "region");

            if (file8.exists())
            {
                File file15 = new File(file10, "region");
                file10.mkdirs();
                copy(file13, file15);
            }
        }

        File file11 = new File(file, "Players");

        if (file11.exists())
        {
            File file14 = new File(file1, "Players");
            file14.mkdirs();
            copy(file11, file14);
        }
    }

    public void renameWorld2(File file, String s, String s1)
    {
        File file1 = new File(file, s);

        if (!file1.exists())
        {
            return;
        }

        File file2 = new File(file1, "level.dat");

        if (file2.exists())
        {
            try
            {
                NBTTagCompound nbttagcompound = CompressedStreamTools.readCompressed(new FileInputStream(file2));
                NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("data");
                nbttagcompound1.setString("LevelName", s1);
                CompressedStreamTools.writeCompressed(nbttagcompound, new FileOutputStream(file2));
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
        }
    }


    public int ctr;
    public boolean onTickInGame(float f, Minecraft minecraft) {
    	ctr++;
    	  if(ctr == 20 * 60) 
    		  //20 is the amount of ticks per second. Times 60 for 1 minute.
    		  //change to 600 for final release
    	  {
    	    //Do the stuff you want to do after x minutes here.
            EntityPlayerSP entityplayersp = minecraft.thePlayer;
            entityplayersp.addChatMessage("Attempting to backup...");
            minecraft.theWorld.saveWorld(true, null);
            File file = RecupererDossierSave();

            if (file == null)
            {
                entityplayersp.addChatMessage("Impossible to backup in SMP");
            }
            else
            {
                File file2 = CreerdossierBACKUP(file);

                if (file2 == null)
                {
                    entityplayersp.addChatMessage("Backup unsuccessful!");
                }
                else
                {
                    File file3 = CopierDossierNouvelleBACKUP(file2);

                    if (file3 == null)
                    {
                        entityplayersp.addChatMessage("Backup unsuccessful!");
                    }
                    else
                    {
                        try
                        {
                            TransfertVersNouvelleBACKUP(file, file3);
                        }
                        catch (IOException ioexception)
                        {
                            entityplayersp.addChatMessage("Backup unsuccessful!");
                            ioexception.printStackTrace();
                        }

                        File file4 = new File(file3, "level.dat");
                        WorldInfo worldinfo = minecraft.theWorld.getWorldInfo();
                        List list = minecraft.theWorld.playerEntities;
                        NBTTagCompound nbttagcompound = worldinfo.getNBTTagCompoundWithPlayers(list);
                        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                        nbttagcompound1.setTag("data", nbttagcompound);
                        try
                        {
                            CompressedStreamTools.writeCompressed(nbttagcompound1, new FileOutputStream(file4));
                        }
                        catch (FileNotFoundException filenotfoundexception)
                        {
                            entityplayersp.addChatMessage("Backup unsuccessful!");
                            filenotfoundexception.printStackTrace();
                        }
                        catch (IOException ioexception1)
                        {
                            entityplayersp.addChatMessage("Backup unsuccessful!");
                            ioexception1.printStackTrace();
                        }

                        renameWorld2(file2, file3.getName(), file3.getName());
                        entityplayersp.addChatMessage("Backup successful");
                    }
                }
            }
            ctr = 0;
        }
    	  return true;
    }
    	  public void returnToPresent(Minecraft minecraft) {
            File file1 = RecupererDossierSave();
            String s = file1.getParentFile().getName();
            System.out.println(file1);
            String s1 = "Time";
            int i = s.compareTo(s1);

            if (i == 0 && !FromMainMenu)
            {
                System.out.println("Inside Time");
                minecraft.changeWorld1(null);
                System.gc();

                if (minecraft.loadingScreen != null)
                {
                    minecraft.loadingScreen.printText("Switching level");
                    minecraft.loadingScreen.displayLoadingString("");
                }

                ISaveHandler isavehandler = minecraft.getSaveLoader().getSaveLoader(file1.getParentFile().getParentFile().getName(), false);
                World world = null;
                File file6 = file1.getParentFile().getParentFile();
                System.out.println(file6);
                WorldInfo worldinfo1 = getWorldInfo2(file6);
                String s2 = worldinfo1.getWorldName();
                System.out.println(s2);
                world = new World(isavehandler, s2, null);
                minecraft.changeWorld2(world, "Back to the Original");
                minecraft.displayGuiScreen(null);
            }
            else
            {
                System.out.println("Outside Time");
                File file5 = new File(file1, "Time");

                if (file5.exists())
                {
                    minecraft.displayGuiScreen(new GuiSelectBACKUP(file1));
                }
                else
                {
                    minecraft.displayGuiScreen(null);
                }
            }  
    }

    public WorldInfo getWorldInfo2(File file)
    {
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

    public String Author()
    {
        return "Charsmud";
    }

    public String Version()
    {
        return getVersion();
    }

    public String getVersion()
    {
        return "TimeTraveler 1.0.0";
    }
    Long getWorldSeed()
    {
    	return l;
    }


}
