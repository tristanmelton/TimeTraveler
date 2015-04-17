package timeTraveler.mechanics;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import timeTraveler.core.TimeTraveler;

public class ParadoxBlockLevels
{
    private static final ParadoxBlockLevels extractingBase = new ParadoxBlockLevels();

    /** The list of Blocks Paradox Levels gained upon mining. */
    private Map extractingList = new HashMap();

    /**
     * Used to call methods addSmelting and getSmeltingResult.
     */
    public static final ParadoxBlockLevels paradoxAmountsForBlocks()
    {
        return extractingBase;
    }

    private ParadoxBlockLevels()
    {
        this.addBlockParadoxLevel(Blocks.water, 10);
        this.addBlockParadoxLevel(Blocks.lava, 15);
        this.addBlockParadoxLevel(Blocks.gold_ore, 40);
        this.addBlockParadoxLevel(Blocks.iron_ore, 25);
        this.addBlockParadoxLevel(Blocks.coal_ore, 10);
        this.addBlockParadoxLevel(Blocks.lapis_ore, 30);
        this.addBlockParadoxLevel(Blocks.diamond_ore, 70);
        this.addBlockParadoxLevel(Blocks.redstone_ore, 40);
        this.addBlockParadoxLevel(Blocks.emerald_ore, 60);
        this.addBlockParadoxLevel(Blocks.cobblestone, 1);
        this.addBlockParadoxLevel(Blocks.obsidian, 7);
        this.addBlockParadoxLevel(Blocks.dispenser, 25);
        this.addBlockParadoxLevel(Blocks.piston, 45);
        this.addBlockParadoxLevel(Blocks.tnt, 30);
        this.addBlockParadoxLevel(Blocks.bookshelf, 20);
        this.addBlockParadoxLevel(Blocks.mossy_cobblestone, 5);
        this.addBlockParadoxLevel(Blocks.chest, 30);
        this.addBlockParadoxLevel(Blocks.redstone_wire, 8);
        this.addBlockParadoxLevel(Blocks.furnace, 25);
        this.addBlockParadoxLevel(Blocks.glass, 10);
        this.addBlockParadoxLevel(Blocks.cauldron, 50);
        this.addBlockParadoxLevel(Blocks.end_portal_frame, 100);
        this.addBlockParadoxLevel(Blocks.glowstone, 15);
        this.addBlockParadoxLevel(Blocks.quartz_ore, 9);
        this.addBlockParadoxLevel(Blocks.dragon_egg, 999);
        this.addBlockParadoxLevel(Blocks.anvil, 27);
        this.addBlockParadoxLevel(Blocks.beacon, 80);
        this.addBlockParadoxLevel(Blocks.ender_chest, 60);
        this.addBlockParadoxLevel(Blocks.enchanting_table, 50);
        this.addBlockParadoxLevel(Blocks.brewing_stand, 40);
        this.addBlockParadoxLevel(Blocks.bed, 30);
        this.addBlockParadoxLevel(Blocks.cake, 110);
        this.addBlockParadoxLevel(Blocks.coal_block, 50);
        this.addBlockParadoxLevel(Blocks.diamond_block, 120);
        this.addBlockParadoxLevel(Blocks.emerald_block, 90);
        this.addBlockParadoxLevel(Blocks.gold_block, 70);
        this.addBlockParadoxLevel(Blocks.iron_block, 60);
        this.addBlockParadoxLevel(Blocks.lapis_block, 60);
        this.addBlockParadoxLevel(Blocks.quartz_block, 50);
        this.addBlockParadoxLevel(Blocks.redstone_block, 50);
        this.addBlockParadoxLevel(Blocks.skull, 60);
        this.addBlockParadoxLevel(TimeTraveler.paradoxCondenser, 40);
        this.addBlockParadoxLevel(TimeTraveler.collisionBlock, 999);
        this.addBlockParadoxLevel(TimeTraveler.paradoxExtractor, 50);
        this.addBlockParadoxLevel(TimeTraveler.timeTravel, 999);
        this.addBlockParadoxLevel(TimeTraveler.travelTime, 30);
    }

    /**
     * Adds a block's paradox level upon mining.
     */
    public void addBlockParadoxLevel(Block par1, int par3)
    {
        this.extractingList.put(par1, par3);
    }

    public Map getParadoxBlockLevelList()
    {
        return this.extractingList;
    }
}
