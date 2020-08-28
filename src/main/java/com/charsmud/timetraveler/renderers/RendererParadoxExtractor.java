package com.charsmud.timetraveler.renderers;

import com.charsmud.timetraveler.models.ModelExtractor;
import com.charsmud.timetraveler.tileentities.TileEntityParadoxExtractor;
import com.charsmud.timetraveler.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class RendererParadoxExtractor extends TileEntitySpecialRenderer<TileEntityParadoxExtractor>
{
   private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":textures/blocks/paradox_extractor.png");
   private final ModelExtractor MODEL = new ModelExtractor();
   
   @Override
   public void render(TileEntityParadoxExtractor re, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
   {
	   super.render(re, x, y, z, partialTicks, destroyStage, alpha);
	   GlStateManager.enableDepth();
	   GlStateManager.depthFunc(515);
	   GlStateManager.depthMask(true);
	   
	   ModelExtractor model = MODEL;
	   this.bindTexture(TEXTURE);
	   
	   GlStateManager.pushMatrix(); 
	   GlStateManager.enableRescaleNormal();
       GlStateManager.translate((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
       GlStateManager.scale(1.0F, -1.0F, -1.0F);
        //GL11.glTranslatef(0.5F, 1.0F, 0.5F);
       model.renderModel(0.0625F); 
       GlStateManager.disableRescaleNormal();
       GlStateManager.popMatrix();
       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

   }
}
