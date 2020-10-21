package com.charsmud.timetraveler.util.handlers;

import com.charsmud.timetraveler.entities.EntityPlayerPast;
import com.charsmud.timetraveler.renderers.RenderPlayerPast;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler 
{
	public static void registerEntityRenders()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityPlayerPast.class, RenderPlayerPast::new);
	}
}
