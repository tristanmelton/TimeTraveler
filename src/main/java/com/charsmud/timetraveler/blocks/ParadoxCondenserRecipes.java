package com.charsmud.timetraveler.blocks;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import net.minecraft.item.ItemStack;

public class ParadoxCondenserRecipes 
{
	private static final ParadoxCondenserRecipes INSTANCE = new ParadoxCondenserRecipes();
	private final Map<ItemStack, ItemStack> smeltingList = Maps.<ItemStack, ItemStack>newHashMap();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static ParadoxCondenserRecipes getInstance()
	{
		return INSTANCE;
	}
	private ParadoxCondenserRecipes()
	{
		//TODO: Fill with actual recipes
	}
	
	public void addCondensingRecipe(ItemStack input1, ItemStack result, float experience)
	{
		if(getCondensingResult(input1) != ItemStack.EMPTY) 
			return;
		this.smeltingList.put(input1, result);
		this.experienceList.put(result, Float.valueOf(experience));
	}
	public ItemStack getCondensingResult(ItemStack input1)
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
	public float getCondensingExperience(ItemStack stack)
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
