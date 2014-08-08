package timeTraveler.mechanics;

import java.util.HashMap;
import java.util.Map;

import timeTraveler.core.TimeTraveler;

import net.minecraft.block.Block;

public class ParadoxBlockLevels
{
    private static final ParadoxBlockLevels extractingBase = new ParadoxBlockLevels();

    /** The list of Block Paradox Levels gained upon mining. */
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
        this.addBlockParadoxLevel(Block.waterMoving, 10);
        this.addBlockParadoxLevel(Block.waterStill, 10);
        this.addBlockParadoxLevel(Block.lavaStill, 15);
        this.addBlockParadoxLevel(Block.lavaMoving, 15);
        this.addBlockParadoxLevel(Block.oreGold, 40);
        this.addBlockParadoxLevel(Block.oreIron, 25);
        this.addBlockParadoxLevel(Block.oreCoal, 10);
        this.addBlockParadoxLevel(Block.oreLapis, 30);
        this.addBlockParadoxLevel(Block.oreDiamond, 70);
        this.addBlockParadoxLevel(Block.oreRedstone, 40);
        this.addBlockParadoxLevel(Block.oreEmerald, 60);
        this.addBlockParadoxLevel(Block.cobblestone, 1);
        this.addBlockParadoxLevel(Block.obsidian, 7);
        this.addBlockParadoxLevel(Block.dispenser, 25);
        this.addBlockParadoxLevel(Block.pistonStickyBase, 45);
        this.addBlockParadoxLevel(Block.tnt, 30);
        this.addBlockParadoxLevel(Block.bookShelf, 20);
        this.addBlockParadoxLevel(Block.cobblestoneMossy, 5);
        this.addBlockParadoxLevel(Block.chest, 30);
        this.addBlockParadoxLevel(Block.redstoneWire, 8);
        this.addBlockParadoxLevel(Block.furnaceBurning, 25);
        this.addBlockParadoxLevel(Block.furnaceIdle, 25);
        this.addBlockParadoxLevel(Block.glass, 10);
        this.addBlockParadoxLevel(Block.cauldron, 50);
        this.addBlockParadoxLevel(Block.endPortalFrame, 100);
        this.addBlockParadoxLevel(Block.glowStone, 15);
        this.addBlockParadoxLevel(Block.oreNetherQuartz, 9);
        this.addBlockParadoxLevel(Block.dragonEgg, 999);
        this.addBlockParadoxLevel(Block.anvil, 27);
        this.addBlockParadoxLevel(Block.beacon, 80);
        this.addBlockParadoxLevel(Block.enderChest, 60);
        this.addBlockParadoxLevel(Block.enchantmentTable, 50);
        this.addBlockParadoxLevel(Block.brewingStand, 40);
        this.addBlockParadoxLevel(Block.bed, 30);
        this.addBlockParadoxLevel(Block.cake, 110);
        this.addBlockParadoxLevel(Block.coalBlock, 50);
        this.addBlockParadoxLevel(Block.blockDiamond, 120);
        this.addBlockParadoxLevel(Block.blockEmerald, 90);
        this.addBlockParadoxLevel(Block.blockGold, 70);
        this.addBlockParadoxLevel(Block.blockIron, 60);
        this.addBlockParadoxLevel(Block.blockLapis, 60);
        this.addBlockParadoxLevel(Block.blockNetherQuartz, 50);
        this.addBlockParadoxLevel(Block.blockRedstone, 50);
        this.addBlockParadoxLevel(Block.skull, 60);
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
