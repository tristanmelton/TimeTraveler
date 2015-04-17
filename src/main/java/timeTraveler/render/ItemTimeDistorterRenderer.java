package timeTraveler.render;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import timeTraveler.models.ModelTimeDistorter;
import timeTraveler.tileentity.TileEntityTimeDistorter;

public class ItemTimeDistorterRenderer implements IItemRenderer 
{
	
	private ModelTimeDistorter model;

	public ItemTimeDistorterRenderer()
	{
		model = new ModelTimeDistorter();
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
		TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityTimeDistorter(), 0.0D, 0.0D, 0.0D, 0.0F);
	}
}