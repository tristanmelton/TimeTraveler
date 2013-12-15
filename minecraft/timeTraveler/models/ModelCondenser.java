package timeTraveler.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCondenser extends ModelBase
{
  //fields
    ModelRenderer base;
    ModelRenderer bar1;
    ModelRenderer bar2;
    ModelRenderer bar3;
    ModelRenderer bar4;
    ModelRenderer bar5;
    ModelRenderer bar6;
    ModelRenderer bar7;
    ModelRenderer bar8;
    ModelRenderer shard1;
    ModelRenderer shard2;
    ModelRenderer shard3;
    ModelRenderer shard4;
    ModelRenderer shard5;
    ModelRenderer shard6;
    ModelRenderer shard7;
    ModelRenderer shard8;
    ModelRenderer bar9;
    ModelRenderer bar10;
    ModelRenderer bar11;
    ModelRenderer bar12;
    ModelRenderer top;
  
  public ModelCondenser()
  {
    textureWidth = 65;
    textureHeight = 72;
    
      base = new ModelRenderer(this, 0, 1);
      base.addBox(-8F, 0F, -8F, 16, 8, 16);
      base.setRotationPoint(0F, 16F, 0F);
      base.setTextureSize(65, 72);
      base.mirror = true;
      setRotation(base, 0F, 0F, 0F);
      bar1 = new ModelRenderer(this, 0, 26);
      bar1.addBox(-6F, 0F, -1F, 12, 1, 1);
      bar1.setRotationPoint(0F, 15F, -7F);
      bar1.setTextureSize(65, 72);
      bar1.mirror = true;
      setRotation(bar1, 0F, 0F, 0F);
      bar2 = new ModelRenderer(this, 0, 26);
      bar2.addBox(-6F, 0F, 0F, 12, 1, 1);
      bar2.setRotationPoint(0F, 15F, 7F);
      bar2.setTextureSize(65, 72);
      bar2.mirror = true;
      setRotation(bar2, 0F, 0F, 0F);
      bar3 = new ModelRenderer(this, 4, 30);
      bar3.addBox(-1F, 0F, -6F, 1, 1, 12);
      bar3.setRotationPoint(-7F, 15F, 0F);
      bar3.setTextureSize(65, 72);
      bar3.mirror = true;
      setRotation(bar3, 0F, 0F, 0F);
      bar4 = new ModelRenderer(this, 4, 30);
      bar4.addBox(0F, 0F, -6F, 1, 1, 12);
      bar4.setRotationPoint(7F, 15F, 0F);
      bar4.setTextureSize(65, 72);
      bar4.mirror = true;
      setRotation(bar4, 0F, 0F, 0F);
      bar5 = new ModelRenderer(this, 35, 26);
      bar5.addBox(-1.5F, -5F, -1.5F, 3, 5, 3);
      bar5.setRotationPoint(3.5F, 16F, -3.5F);
      bar5.setTextureSize(65, 72);
      bar5.mirror = true;
      setRotation(bar5, 0F, 0F, 0F);
      bar6 = new ModelRenderer(this, 35, 26);
      bar6.addBox(-1.5F, -5F, -1.5F, 3, 5, 3);
      bar6.setRotationPoint(-3.5F, 16F, -3.5F);
      bar6.setTextureSize(65, 72);
      bar6.mirror = true;
      setRotation(bar6, 0F, 0F, 0F);
      bar7 = new ModelRenderer(this, 35, 26);
      bar7.addBox(-1.5F, -5F, -1.5F, 3, 5, 3);
      bar7.setRotationPoint(-3.5F, 16F, 3.5F);
      bar7.setTextureSize(65, 72);
      bar7.mirror = true;
      setRotation(bar7, 0F, 0F, 0F);
      bar8 = new ModelRenderer(this, 35, 26);
      bar8.addBox(-1.5F, -5F, -1.5F, 3, 5, 3);
      bar8.setRotationPoint(3.5F, 16F, 3.5F);
      bar8.setTextureSize(65, 72);
      bar8.mirror = true;
      setRotation(bar8, 0F, 0F, 0F);
      shard1 = new ModelRenderer(this, 48, 26);
      shard1.addBox(-2F, 0F, -2F, 4, 1, 4);
      shard1.setRotationPoint(2.5F, 11F, 2.5F);
      shard1.setTextureSize(65, 72);
      shard1.mirror = true;
      setRotation(shard1, 0F, 0F, 0F);
      shard2 = new ModelRenderer(this, 48, 26);
      shard2.addBox(-2F, 0F, -2F, 4, 1, 4);
      shard2.setRotationPoint(-2.5F, 11F, -2.5F);
      shard2.setTextureSize(65, 72);
      shard2.mirror = true;
      setRotation(shard2, 0F, 0F, 0F);
      shard3 = new ModelRenderer(this, 48, 26);
      shard3.addBox(-1F, 0F, -2F, 4, 1, 4);
      shard3.setRotationPoint(1.5F, 11F, -2.5F);
      shard3.setTextureSize(65, 72);
      shard3.mirror = true;
      setRotation(shard3, 0F, 0F, 0F);
      shard4 = new ModelRenderer(this, 48, 26);
      shard4.addBox(-2F, 0F, -2F, 4, 1, 4);
      shard4.setRotationPoint(-2.5F, 11F, 2.5F);
      shard4.setTextureSize(65, 72);
      shard4.mirror = true;
      setRotation(shard4, 0F, 0F, 0F);
      shard5 = new ModelRenderer(this, 48, 26);
      shard5.addBox(-2F, 0F, -2F, 4, 1, 4);
      shard5.setRotationPoint(-2.5F, 15F, 2.5F);
      shard5.setTextureSize(65, 72);
      shard5.mirror = true;
      setRotation(shard5, 0F, 0F, 0F);
      shard6 = new ModelRenderer(this, 48, 26);
      shard6.addBox(-2F, 0F, -2F, 4, 1, 4);
      shard6.setRotationPoint(2.5F, 15F, 2.5F);
      shard6.setTextureSize(65, 72);
      shard6.mirror = true;
      setRotation(shard6, 0F, 0F, 0F);
      shard7 = new ModelRenderer(this, 48, 26);
      shard7.addBox(-1F, 0F, -2F, 4, 1, 4);
      shard7.setRotationPoint(1.5F, 15F, -2.5F);
      shard7.setTextureSize(65, 72);
      shard7.mirror = true;
      setRotation(shard7, 0F, 0F, 0F);
      shard8 = new ModelRenderer(this, 48, 26);
      shard8.addBox(-2F, 0F, -2F, 4, 1, 4);
      shard8.setRotationPoint(-2.5F, 15F, -2.5F);
      shard8.setTextureSize(65, 72);
      shard8.mirror = true;
      setRotation(shard8, 0F, 0F, 0F);
      bar9 = new ModelRenderer(this, 9, 44);
      bar9.addBox(0F, -5F, -1F, 2, 5, 2);
      bar9.setRotationPoint(6F, 16F, -7F);
      bar9.setTextureSize(65, 72);
      bar9.mirror = true;
      setRotation(bar9, 0F, 0F, 0F);
      bar10 = new ModelRenderer(this, 0, 44);
      bar10.addBox(-1F, -5F, -1F, 2, 5, 2);
      bar10.setRotationPoint(-7F, 16F, -7F);
      bar10.setTextureSize(65, 72);
      bar10.mirror = true;
      setRotation(bar10, 0F, 0F, 0F);
      bar11 = new ModelRenderer(this, 0, 44);
      bar11.addBox(-1F, -5F, -1F, 2, 5, 2);
      bar11.setRotationPoint(7F, 16F, 7F);
      bar11.setTextureSize(65, 72);
      bar11.mirror = true;
      setRotation(bar11, 0F, 0F, 0F);
      bar12 = new ModelRenderer(this, 9, 44);
      bar12.addBox(0F, -5F, -1F, 2, 5, 2);
      bar12.setRotationPoint(-8F, 16F, 7F);
      bar12.setTextureSize(65, 72);
      bar12.mirror = true;
      setRotation(bar12, 0F, 0F, 0F);
      top = new ModelRenderer(this, 0, 53);
      top.addBox(-8F, 0F, -8F, 16, 2, 16);
      top.setRotationPoint(0F, 9F, 0F);
      top.setTextureSize(65, 72);
      top.mirror = true;
      setRotation(top, 0F, 0F, 0F);
  }
  
  public void renderModel(float f5)
  {
	    base.render(f5);
	    bar1.render(f5);
	    bar2.render(f5);
	    bar3.render(f5);
	    bar4.render(f5);
	    bar5.render(f5);
	    bar6.render(f5);
	    bar7.render(f5);
	    bar8.render(f5);
	    shard1.render(f5);
	    shard2.render(f5);
	    shard3.render(f5);
	    shard4.render(f5);
	    shard5.render(f5);
	    shard6.render(f5);
	    shard7.render(f5);
	    shard8.render(f5);
	    bar9.render(f5);
	    bar10.render(f5);
	    bar11.render(f5);
	    bar12.render(f5);
	    top.render(f5);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    base.render(f5);
    bar1.render(f5);
    bar2.render(f5);
    bar3.render(f5);
    bar4.render(f5);
    bar5.render(f5);
    bar6.render(f5);
    bar7.render(f5);
    bar8.render(f5);
    shard1.render(f5);
    shard2.render(f5);
    shard3.render(f5);
    shard4.render(f5);
    shard5.render(f5);
    shard6.render(f5);
    shard7.render(f5);
    shard8.render(f5);
    bar9.render(f5);
    bar10.render(f5);
    bar11.render(f5);
    bar12.render(f5);
    top.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
