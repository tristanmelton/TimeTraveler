package timeTraveler.render;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLLoadEvent;

import timeTraveler.models.ModelCondenser;
import timeTraveler.models.ModelExtractor;
import timeTraveler.models.ModelTimeMachine;
import timeTraveler.tileentity.TileEntityParadoxCondenser;

public class RenderTimeMachine extends TileEntitySpecialRenderer
{
   
   ModelTimeMachine model;
   
   public RenderTimeMachine()
   {
      //super();
      model = new ModelTimeMachine();
   }
   
   @Override
   public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) 
   {
      renderTimeMachineTileEntityAt(d0,d1,d2,f);
      
   }
   private void renderTimeMachineTileEntityAt(double d, double d1, double d2, float f)
   {
	 /*  int i = 0;

      if(te.worldObj != null) 
      {
         i = (te.worldObj.getBlockMetadata(te.xCoord, te.yCoord, te.zCoord)); 
      }*/

                  //directory of the model's texture file
   		ResourceLocation texture = new ResourceLocation("charsmud_timetraveler", "textures/blocks/BlockTimePast.png");
   		ResourceLocation texture2 = new ResourceLocation("charsmud_timetraveler", "textures/blocks/BlockTimeFuture.png");
	   FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture2);
	   GL11.glPushMatrix(); 
	   GL11.glEnable(GL12.GL_RESCALE_NORMAL);
       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
       GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F);
       GL11.glScalef(1.0F, -1.0F, -1.0F);
        //GL11.glTranslatef(0.5F, 1.0F, 0.5F);
       model.renderModel(0.0625F); 
       GL11.glDisable(GL12.GL_RESCALE_NORMAL);
       GL11.glPopMatrix();
       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
   }
}
