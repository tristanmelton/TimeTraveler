package timeTraveler.gui;

import timeTraveler.container.ContainerExtractor;
import timeTraveler.container.ContainerParadox;
import timeTraveler.tileentity.TileEntityExtractor;
import timeTraveler.tileentity.TileEntityParadoxCondenser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
		
		if(tile_entity instanceof TileEntityParadoxCondenser)
		{
			return new ContainerParadox(player.inventory, (TileEntityParadoxCondenser) tile_entity);
		}
		if(tile_entity instanceof TileEntityExtractor)
		{
			return new ContainerExtractor(player.inventory, (TileEntityExtractor) tile_entity);
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
		if(tile_entity instanceof TileEntityParadoxCondenser)
		{
			return new GuiParadoxCondenser(player.inventory, (TileEntityParadoxCondenser) tile_entity);
		}
		if(tile_entity instanceof TileEntityExtractor)
		{
			return new GuiExtractor(player.inventory, (TileEntityExtractor)tile_entity);
		}
		return null;
	}

}
