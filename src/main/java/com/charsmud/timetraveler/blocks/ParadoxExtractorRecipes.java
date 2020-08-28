package com.charsmud.timetraveler.blocks;

import java.util.Map;
import java.util.Map.Entry;

import com.charsmud.timetraveler.items.ItemInit;
import com.google.common.collect.Maps;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ParadoxExtractorRecipes 
{
	private static final ParadoxExtractorRecipes INSTANCE = new ParadoxExtractorRecipes();
	private final Map<ItemStack, ItemStack> smeltingList = Maps.<ItemStack, ItemStack>newHashMap();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static ParadoxExtractorRecipes getInstance()
	{
		return INSTANCE;
	}
	private ParadoxExtractorRecipes()
	{
		//TODO: Fill with actual recipes
		this.addExtractorRecipe(new ItemStack(ItemInit.BOTTLED_PARADOX), new ItemStack(ItemInit.BOTTLED_PARADOX), 0.0f);
		this.addExtractorRecipe(new ItemStack(Items.GLASS_BOTTLE), new ItemStack(ItemInit.BOTTLED_PARADOX), 0.0f);
	}
	
	public void addExtractorRecipe(ItemStack input1, ItemStack result, float experience)
	{
		if(getExtractingResult(input1) != ItemStack.EMPTY) 
			return;
		this.smeltingList.put(input1, result);
		this.experienceList.put(result, Float.valueOf(experience));
	}
	public ItemStack getExtractingResult(ItemStack input1)
	{
		for(Entry<ItemStack, ItemStack> entry: this.smeltingList.entrySet())
		{
			if(this.compareItemStacks(input1, (ItemStack)entry.getKey()))
			{
				return (ItemStack)entry.getValue();
			}
		}
		return ItemStack.EMPTY;
	}
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	public Map<ItemStack, ItemStack> getDualSmeltingList()
	{
		return this.smeltingList;
	}
	public float getExtractingExperience(ItemStack stack)
	{
		for(Entry<ItemStack, Float> entry : this.experienceList.entrySet())
		{
			if(this.compareItemStacks(stack,  (ItemStack)entry.getKey()))
			{
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
}
