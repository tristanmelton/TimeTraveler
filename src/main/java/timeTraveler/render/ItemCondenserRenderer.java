package timeTraveler.render;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import timeTraveler.models.ModelCondenser;
import timeTraveler.tileentity.TileEntityParadoxCondenser;

public class ItemCondenserRenderer implements IItemRenderer 
{
	
	private ModelCondenser model;

	public ItemCondenserRenderer()
	{
		model = new ModelCondenser();
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
		TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityParadoxCondenser(), 0.0D, 0.0D, 0.0D, 0.0F);
	}
}