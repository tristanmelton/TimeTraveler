package timeTraveler.render;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import timeTraveler.models.ModelTimeMachine;
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
		TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityTimeTravel(), 0.0D, 0.0D, 0.0D, 0.0F);
	}
}