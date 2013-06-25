package timeTraveler.render;

import timeTraveler.entities.EntityParadoxHunter;
import timeTraveler.models.ModelParadoxHunter;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

public class RenderParadoxHunter extends RenderLiving
{
	protected ModelParadoxHunter model;

	public RenderParadoxHunter(ModelParadoxHunter modelTutorial, float f) 
	{
		super(modelTutorial, f);
		model = ((ModelParadoxHunter) mainModel);
	}

	public void renderTutorial(EntityParadoxHunter entity, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRenderLiving(entity, par2, par4, par6, par8, par9);
	}

	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		renderTutorial((EntityParadoxHunter) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) 
	{
		renderTutorial((EntityParadoxHunter) par1Entity, par2, par4, par6, par8, par9);
	}
}
