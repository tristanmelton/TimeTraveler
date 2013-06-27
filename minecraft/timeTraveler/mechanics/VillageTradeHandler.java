package timeTraveler.mechanics;

import java.util.Random;

import timeTraveler.core.TimeTraveler;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

public class VillageTradeHandler implements IVillageTradeHandler
{

  public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipes, Random random) 
  {
     // Trade 4 emeralds and 3 apples for a diamond axe
     recipes.add(new MerchantRecipe(
        new ItemStack(Item.emerald, 8),
        new ItemStack(Item.diamond, 8),
        new ItemStack(TimeTraveler.travelTime)));   
  }
}
