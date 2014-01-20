package timeTraveler.crafting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ParadoxRecipes
{
    private static final ParadoxRecipes condensingBase = new ParadoxRecipes();

    /** The list of smelting results. */
    private Map condensingList = new HashMap();
    private HashMap<List<Integer>, ItemStack> metaCondensingList = new HashMap<List<Integer>, ItemStack>();

    /**
     * Used to call methods addSmelting and getSmeltingResult.
     */
    public static final ParadoxRecipes condensing()
    {
        return condensingBase;
    }

    private ParadoxRecipes()
    {
        this.addCondensing(Item.appleGold.itemID, new ItemStack(Item.appleGold, 2), 0.7F);
        this.addCondensing(Item.blazeRod.itemID, new ItemStack(Item.blazeRod, 2), 0.7F);
        this.addCondensing(Item.book.itemID, new ItemStack(Item.book, 2), 0.7F);
        this.addCondensing(Item.coal.itemID, new ItemStack(Item.coal, 2), 0.7F);
        this.addCondensing(Item.diamond.itemID, new ItemStack(Item.diamond, 2), 0.7F);
        this.addCondensing(Item.emerald.itemID, new ItemStack(Item.emerald, 2), 0.7F);
        this.addCondensing(Item.enderPearl.itemID, new ItemStack(Item.enderPearl, 2), 0.7F);
        this.addCondensing(Item.ghastTear.itemID, new ItemStack(Item.ghastTear, 2), 0.7F);
        this.addCondensing(Item.goldenCarrot.itemID, new ItemStack(Item.goldenCarrot, 2), 0.7F);
        this.addCondensing(Item.goldNugget.itemID, new ItemStack(Item.goldNugget, 2), 0.7F);
        this.addCondensing(Item.ingotGold.itemID, new ItemStack(Item.ingotGold, 2), 0.7F);
        this.addCondensing(Item.ingotIron.itemID, new ItemStack(Item.ingotIron, 2), 0.7F);
        this.addCondensing(Item.leather.itemID, new ItemStack(Item.leather, 2), 0.7F);
    }

    /**
     * Adds a smelting recipe.
     */
    public void addCondensing(int par1, ItemStack par2ItemStack, float par3)
    {
        this.condensingList.put(Integer.valueOf(par1), par2ItemStack);
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
    public void addCondensing(int itemID, int metadata, ItemStack itemstack, float experience)
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
        ItemStack ret = (ItemStack)metaCondensingList.get(Arrays.asList(item.itemID, item.getItemDamage()));
        if (ret != null) 
        {
            return ret;
        }
        return (ItemStack)condensingList.get(Integer.valueOf(item.itemID));
    }

    /**
     * Grabs the amount of base experience for this item to give when pulled from the furnace slot.
     */
    public float getExperience(ItemStack item)
    {
    	return 0;
    }
}
