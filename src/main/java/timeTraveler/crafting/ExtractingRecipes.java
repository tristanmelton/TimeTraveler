package timeTraveler.crafting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import timeTraveler.core.TimeTraveler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ExtractingRecipes
{
    private static final ExtractingRecipes extractingBase = new ExtractingRecipes();

    /** The list of smelting results. */
    private Map extractingList = new HashMap();
    private HashMap<List<Object>, ItemStack> metaExtractingList = new HashMap<List<Object>, ItemStack>();

    /**
     * Used to call methods addSmelting and getSmeltingResult.
     */
    public static final ExtractingRecipes extracting()
    {
        return extractingBase;
    }

    private ExtractingRecipes()
    {
        this.addExtracting(TimeTraveler.emptyBottle, new ItemStack(TimeTraveler.bottledParadox, 1), 0.7F);
        this.addExtracting(TimeTraveler.bottledParadox, new ItemStack(TimeTraveler.bottledParadox, 1), 0.7F);
    }

    /**
     * Adds a smelting recipe.
     */
    public void addExtracting(Block par1, ItemStack par2ItemStack, float par3)
    {
        this.extractingList.put(par1, par2ItemStack);
    }
    public void addExtracting(Item par1, ItemStack par2ItemStack, float par3)
    {
        this.extractingList.put(par1, par2ItemStack);
    }

    /**
     * Returns the smelting result of an item.
     * Deprecated in favor of a metadata sensitive version
     */
  /*  @Deprecated
    public ItemStack getCondensingResult(int par1)
    {
        return (ItemStack)this.condensingList.get(Integer.valueOf(par1));
    }*/

    public Map getSmeltingList()
    {
        return this.extractingList;
    }
/*
    @Deprecated //In favor of ItemStack sensitive version
    public float getExperience(int par1)
    {
    	return 0;
    }*/

    /**
     * A metadata sensitive version of adding a furnace recipe.
     */
    public void addExtracting(Block block, int metadata, ItemStack itemstack, float experience)
    {
        metaExtractingList.put(Arrays.asList(block, metadata), itemstack);
    }
    public void addExtracting(Item item, int metadata, ItemStack itemstack, float experience)
    {
        metaExtractingList.put(Arrays.asList(item, metadata), itemstack);
    }


    /**
     * Used to get the resulting ItemStack form a source ItemStack
     * @param item The Source ItemStack
     * @return The result ItemStack
     */
    public ItemStack getExtractingResult(ItemStack item) 
    {
        if (item == null)
        {
            return null;
        }
        ItemStack ret = (ItemStack)metaExtractingList.get(Arrays.asList(item, item.getItemDamage()));
        if (ret != null) 
        {
            return ret;
        }
        return (ItemStack)extractingList.get((item));
    }

    /**
     * Grabs the amount of base experience for this item to give when pulled from the furnace slot.
     */
    public float getExperience(ItemStack item)
    {
    	return 0;
    }
}
