package com.charsmud.timetraveler.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class ModelTimeMachine extends ModelBase
{
  //fields
    ModelRenderer base;
    ModelRenderer pipe_small1;
    ModelRenderer pipe_right;
    ModelRenderer pipe_left;
    ModelRenderer pipe_small2;
    ModelRenderer chairback;
    ModelRenderer bottomarmright;
    ModelRenderer controlpanelleft;
    ModelRenderer armright;
    ModelRenderer armleft;
    ModelRenderer bottomarmleft;
    ModelRenderer controlpanelright;
    ModelRenderer seat;
    ModelRenderer side1;
    ModelRenderer base2;
    ModelRenderer block_ignor_this;
    ModelRenderer side2;
    ModelRenderer side3;
    ModelRenderer side4;
    ModelRenderer futuretravel_bg;
    ModelRenderer monitor;
    ModelRenderer future_traveler_attachment;
  
  public ModelTimeMachine()
  {
    textureWidth = 256;
    textureHeight = 256;
    
      base = new ModelRenderer(this, 0, 178);
      base.addBox(-18F, -3F, -18F, 36, 2, 36);
      base.setRotationPoint(0F, -8F, 0F);
      base.setTextureSize(256, 256);
      base.mirror = true;
      setRotation(base, 0F, 0F, 0F);
      pipe_small1 = new ModelRenderer(this, 0, 45);
      pipe_small1.addBox(0F, 0F, 0F, 23, 3, 3);
      pipe_small1.setRotationPoint(-11.5F, 17.5F, 11.5F);
      pipe_small1.setTextureSize(256, 256);
      pipe_small1.mirror = true;
      setRotation(pipe_small1, 0F, 0F, 0F);
      pipe_right = new ModelRenderer(this, 0, 53);
      pipe_right.addBox(0F, 0F, 0F, 3, 3, 29);
      pipe_right.setRotationPoint(-14.5F, 17.5F, -14.5F);
      pipe_right.setTextureSize(256, 256);
      pipe_right.mirror = true;
      setRotation(pipe_right, 0F, 0F, 0F);
      pipe_left = new ModelRenderer(this, 0, 53);
      pipe_left.addBox(0F, 0F, 0F, 3, 3, 29);
      pipe_left.setRotationPoint(11.5F, 17.5F, -14.5F);
      pipe_left.setTextureSize(256, 256);
      pipe_left.mirror = true;
      setRotation(pipe_left, 0F, 0F, 0F);
      pipe_small2 = new ModelRenderer(this, 0, 45);
      pipe_small2.addBox(0F, 0F, 0F, 23, 3, 3);
      pipe_small2.setRotationPoint(-11.5F, 17.5F, -14.5F);
      pipe_small2.setTextureSize(256, 256);
      pipe_small2.mirror = true;
      setRotation(pipe_small2, 0F, 0F, 0F);
      chairback = new ModelRenderer(this, 0, 105);
      chairback.addBox(-6F, -11F, -2F, 12, 13, 3);
      chairback.setRotationPoint(0F, 15.5F, -5F);
      chairback.setTextureSize(256, 256);
      chairback.mirror = true;
      setRotation(chairback, 0F, 0F, 0F);
      bottomarmright = new ModelRenderer(this, 32, 105);
      bottomarmright.addBox(-0.5F, -1F, -6F, 1, 3, 10);
      bottomarmright.setRotationPoint(-6F, 13.5F, 1F);
      bottomarmright.setTextureSize(256, 256);
      bottomarmright.mirror = true;
      setRotation(bottomarmright, 0F, 0F, 0F);
      controlpanelleft = new ModelRenderer(this, 0, 139);
      controlpanelleft.addBox(-6F, -1F, -6F, 6, 12, 12);
      controlpanelleft.setRotationPoint(16F, 10F, 0F);
      controlpanelleft.setTextureSize(256, 256);
      controlpanelleft.mirror = true;
      setRotation(controlpanelleft, 0F, 0F, 0F);
      armright = new ModelRenderer(this, 58, 104);
      armright.addBox(-2F, -1F, -6F, 3, 3, 11);
      armright.setRotationPoint(-5.5F, 10.5F, 1F);
      armright.setTextureSize(256, 256);
      armright.mirror = true;
      setRotation(armright, 0F, 0F, 0F);
      armleft = new ModelRenderer(this, 89, 104);
      armleft.addBox(-2F, -1F, -6F, 3, 3, 11);
      armleft.setRotationPoint(6.5F, 10.5F, 1F);
      armleft.setTextureSize(256, 256);
      armleft.mirror = true;
      setRotation(armleft, 0F, 0F, 0F);
      bottomarmleft = new ModelRenderer(this, 32, 119);
      bottomarmleft.addBox(-0.5F, -1F, -6F, 1, 3, 10);
      bottomarmleft.setRotationPoint(6F, 13.5F, 1F);
      bottomarmleft.setTextureSize(256, 256);
      bottomarmleft.mirror = true;
      setRotation(bottomarmleft, 0F, 0F, 0F);
      controlpanelright = new ModelRenderer(this, 39, 139);
      controlpanelright.addBox(-6F, -1F, -6F, 6, 12, 12);
      controlpanelright.setRotationPoint(-10F, 10F, 0F);
      controlpanelright.setTextureSize(256, 256);
      controlpanelright.mirror = true;
      setRotation(controlpanelright, 0F, 0F, 0F);
      seat = new ModelRenderer(this, 121, 105);
      seat.addBox(-6F, -1F, -6F, 12, 3, 12);
      seat.setRotationPoint(0F, 16.5F, 0F);
      seat.setTextureSize(256, 256);
      seat.mirror = true;
      setRotation(seat, 0F, 0F, 0F);
      side1 = new ModelRenderer(this, 174, 97);
      side1.addBox(-18F, -14F, -2F, 36, 30, 3);
      side1.setRotationPoint(0F, 5F, 17F);
      side1.setTextureSize(256, 256);
      side1.mirror = true;
      setRotation(side1, 0F, 0F, 0F);
      base2 = new ModelRenderer(this, 0, 217);
      base2.addBox(-18F, -3F, -18F, 36, 3, 36);
      base2.setRotationPoint(0F, 24F, 0F);
      base2.setTextureSize(256, 256);
      base2.mirror = true;
      setRotation(base, 0F, 0F, 0F);
      block_ignor_this = new ModelRenderer(this, 7, 87);
      block_ignor_this.addBox(-8F, -4F, -4F, 16, 8, 8);
      block_ignor_this.setRotationPoint(0F, 17F, -11F);
      block_ignor_this.setTextureSize(256, 256);
      block_ignor_this.mirror = true;
      block_ignor_this.mirror = true;
      setRotation(block_ignor_this, 0F, 0F, 0F);
      block_ignor_this.mirror = false;
      side2 = new ModelRenderer(this, 174, 63);
      side2.addBox(-18F, -14F, -2F, 36, 30, 3);
      side2.setRotationPoint(0F, 5F, -16F);
      side2.setTextureSize(256, 256);
      side2.mirror = true;
      setRotation(side2, 0F, 0F, 0F);
      side3 = new ModelRenderer(this, 150, 134);
      side3.addBox(-1F, -15F, -15F, 2, 30, 30);
      side3.setRotationPoint(-17F, 6F, 0F);
      side3.setTextureSize(256, 256);
      side3.mirror = true;
      setRotation(side3, 0F, 0F, 0F);
      side4 = new ModelRenderer(this, 148, 195);
      side4.addBox(-1F, -15F, -15F, 2, 30, 30);
      side4.setRotationPoint(17F, 6F, 0F);
      side4.setTextureSize(256, 256);
      side4.mirror = true;
      setRotation(side4, 0F, 0F, 0F);
      futuretravel_bg = new ModelRenderer(this, 129, 122);
      futuretravel_bg.addBox(0F, -8F, -8F, 1, 16, 16);
      futuretravel_bg.setRotationPoint(15.5F, 0F, 0F);
      futuretravel_bg.setTextureSize(256, 256);
      futuretravel_bg.mirror = true;
      setRotation(futuretravel_bg, 0F, 0F, 0F);
      monitor = new ModelRenderer(this, 92, 138);
      monitor.addBox(0F, -8F, -8F, 1, 16, 16);
      monitor.setRotationPoint(-16.5F, 0F, 0F);
      monitor.setTextureSize(256, 256);
      monitor.mirror = true;
      setRotation(monitor, 0F, 0F, 0F);
      future_traveler_attachment = new ModelRenderer(this, 132, 78);
      future_traveler_attachment.addBox(0F, -6F, -6F, 1, 12, 12);
      future_traveler_attachment.setRotationPoint(15F, 0F, 0F);
      future_traveler_attachment.setTextureSize(256, 256);
      future_traveler_attachment.mirror = true;
      setRotation(future_traveler_attachment, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    base.render(f5);
    pipe_small1.render(f5);
    pipe_right.render(f5);
    pipe_left.render(f5);
    pipe_small2.render(f5);
    chairback.render(f5);
    bottomarmright.render(f5);
    controlpanelleft.render(f5);
    armright.render(f5);
    armleft.render(f5);
    bottomarmleft.render(f5);
    controlpanelright.render(f5);
    seat.render(f5);
    side1.render(f5);
    base2.render(f5);
    block_ignor_this.render(f5);
    side2.render(f5);
    side3.render(f5);
    side4.render(f5);
    futuretravel_bg.render(f5);
    monitor.render(f5);
    future_traveler_attachment.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void renderModel(float f5)
  {
	    base.render(f5);
	    pipe_small1.render(f5);
	    pipe_right.render(f5);
	    pipe_left.render(f5);
	    pipe_small2.render(f5);
	    chairback.render(f5);
	    bottomarmright.render(f5);
	    controlpanelleft.render(f5);
	    armright.render(f5);
	    armleft.render(f5);
	    bottomarmleft.render(f5);
	    controlpanelright.render(f5);
	    seat.render(f5);
	    side1.render(f5);
	    base2.render(f5);
	    block_ignor_this.render(f5);
	    side2.render(f5);
	    side3.render(f5);
	    side4.render(f5);
	    futuretravel_bg.render(f5);
	    monitor.render(f5);
	    future_traveler_attachment.render(f5);

  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
