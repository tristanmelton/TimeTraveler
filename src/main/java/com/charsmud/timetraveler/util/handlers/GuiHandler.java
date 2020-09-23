package com.charsmud.timetraveler.util.handlers;

import com.charsmud.timetraveler.blocks.ContainerParadoxCondenser;
import com.charsmud.timetraveler.blocks.ContainerParadoxExtractor;
import com.charsmud.timetraveler.gui.GuiParadoxBar;
import com.charsmud.timetraveler.gui.GuiFutureReturn;
import com.charsmud.timetraveler.gui.GuiFutureTravel;
import com.charsmud.timetraveler.gui.GuiParadoxCondenser;
import com.charsmud.timetraveler.gui.GuiParadoxExtractor;
import com.charsmud.timetraveler.gui.GuiPastTravel;
import com.charsmud.timetraveler.tileentities.TileEntityParadoxCondenser;
import com.charsmud.timetraveler.tileentities.TileEntityParadoxExtractor;
import com.charsmud.timetraveler.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	public static GuiParadoxBar GUI_PARADOX_BAR;
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if(ID == Reference.GUI_PARADOX_CONDENSER)
			return new ContainerParadoxCondenser(player.inventory, (TileEntityParadoxCondenser)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == Reference.GUI_PARADOX_EXTRACTOR)
			return new ContainerParadoxExtractor(player.inventory, (TileEntityParadoxExtractor)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if(ID == Reference.GUI_PARADOX_CONDENSER)
			return new GuiParadoxCondenser(player.inventory, (TileEntityParadoxCondenser)world.getTileEntity(new BlockPos(x,y,z)));
		else if(ID == Reference.GUI_FUTURE_TRAVEL)
			return new GuiFutureTravel(player);
		else if(ID == Reference.GUI_FUTURE_RETURN)
			return new GuiFutureReturn(player);
		else if(ID == Reference.GUI_PARADOX_EXTRACTOR)
			return new GuiParadoxExtractor(player.inventory, (TileEntityParadoxExtractor)world.getTileEntity(new BlockPos(x,y,z)));
		else if (ID == Reference.GUI_PAST_TRAVEL)
			return new GuiPastTravel(player);
		return null;
	}
	@SubscribeEvent
	public void onRenderGui(RenderGameOverlayEvent event)
	{
		if(event.getType() != ElementType.EXPERIENCE) 
			return;
		//TODO: Link with player paradox amount
		GUI_PARADOX_BAR = new GuiParadoxBar(Minecraft.getMinecraft(), 64);
	}
}
