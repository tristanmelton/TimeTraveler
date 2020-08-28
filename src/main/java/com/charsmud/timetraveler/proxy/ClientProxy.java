package com.charsmud.timetraveler.proxy;

import com.google.common.util.concurrent.ListenableFuture;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy 
{
	@Override
	public void registerItemRenderer(Item item, int meta, String id) 
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}
	

	@Override
    public ListenableFuture<Object> addScheduledTaskClient(Runnable runnableToSchedule)
	{
        return Minecraft.getMinecraft().addScheduledTask(runnableToSchedule);
    }
	
    @Override
    public EntityPlayer getClientPlayer()
    {
        return Minecraft.getMinecraft().player;
    }

}
