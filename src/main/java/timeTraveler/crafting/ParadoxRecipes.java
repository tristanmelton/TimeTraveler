package timeTraveler.crafting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ParadoxRecipes
{
    private static final ParadoxRecipes condensingBase = new ParadoxRecipes();

    /** The list of smelting results. */
    private Map condensingList = new HashMap();
    private HashMap<List<Object>, ItemStack> metaCondensingList = new HashMap<List<Object>, ItemStack>();

    /**
     * Used to call methods addSmelting and getSmeltingResult.
     */
    public static final ParadoxRecipes condensing()
    {
        return condensingBase;
    }

    private ParadoxRecipes()
    {
        this.addCondensing(Items.golden_apple, new ItemStack(Items.golden_apple, 2), 0.7F);
        this.addCondensing(Items.blaze_rod, new ItemStack(Items.blaze_rod, 2), 0.7F);
        this.addCondensing(Items.book, new ItemStack(Items.book, 2), 0.7F);
        this.addCondensing(Items.coal, new ItemStack(Items.coal, 2), 0.7F);
        this.addCondensing(Items.diamond, new ItemStack(Items.diamond, 2), 0.7F);
        this.addCondensing(Items.emerald, new ItemStack(Items.emerald, 2), 0.7F);
        this.addCondensing(Items.ender_pearl, new ItemStack(Items.ender_pearl, 2), 0.7F);
        this.addCondensing(Items.ghast_tear, new ItemStack(Items.ghast_tear, 2), 0.7F);
        this.addCondensing(Items.golden_carrot, new ItemStack(Items.golden_carrot, 2), 0.7F);
        this.addCondensing(Items.gold_nugget, new ItemStack(Items.gold_nugget, 2), 0.7F);
        this.addCondensing(Items.gold_ingot, new ItemStack(Items.gold_ingot, 2), 0.7F);
        this.addCondensing(Items.iron_ingot, new ItemStack(Items.iron_ingot, 2), 0.7F);
        this.addCondensing(Items.leather, new ItemStack(Items.leather, 2), 0.7F);
    }

    /**
     * Adds a smelting recipe.
     */
    public void addCondensing(Item par1, ItemStack par2ItemStack, float par3)
    {
        this.condensingList.put((par1), par2ItemStack);
    }
    public void addCondensing(Block par1, ItemStack par2ItemStack, float par3)
    {
        this.condensingList.put((par1), par2ItemStack);
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
        return this.condensingList;
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
    public void addCondensing(Item itemID, int metadata, ItemStack itemstack, float experience)
    {
        metaCondensingList.put(Arrays.asList(itemID, metadata), itemstack);
    }
    public void addCondensing(Block itemID, int metadata, ItemStack itemstack, float experience)
    {
        metaCondensingList.put(Arrays.asList(itemID, metadata), itemstack);
    }

    /**
     * Used to get the resulting ItemStack form a source ItemStack
     * @param item The Source ItemStack
     * @return The result ItemStack
     */
    public ItemStack getCondensingResult(ItemStack item) 
    {
        if (item == null)
        {
            return null;
        }
        ItemStack ret = (ItemStack)metaCondensingList.get(Arrays.asList(item, item.getItemDamage()));
        if (ret != null) 
        {
            return ret;
        }
        return (ItemStack)condensingList.get((item));
    }

    /**
     * Grabs the amount of base experience for this item to give when pulled from the furnace slot.
     */
    public float getExperience(ItemStack item)
    {
    	return 0;
    }
}
