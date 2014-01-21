package timeTraveler.render;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import timeTraveler.models.ModelCondenser;
import timeTraveler.models.ModelTimeMachine;
import timeTraveler.tileentity.TileEntityParadoxCondenser;
import timeTraveler.tileentity.TileEntityTimeTravel;

public class ItemTimeMachineRenderer implements IItemRenderer 
{
	
	private ModelTimeMachine model;

	public ItemTimeMachineRenderer()
	{
		model = new ModelTimeMachine();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) 
	{
		TileEntityRenderer.instance.renderTileEntityAt(new TileEntityTimeTravel(), 0.0D, 0.0D, 0.0D, 0.0F);
	}
}