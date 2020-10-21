package com.charsmud.timetraveler.renderers;

import java.io.File;

import com.charsmud.timetraveler.entities.EntityPlayerPast;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPlayerPast extends RenderLiving<EntityPlayerPast>
{
    public static final ResourceLocation locationStevePng = new ResourceLocation("textures/entity/steve.png");
    public RenderPlayerPast(RenderManager manager)
    {
    	super(manager, new ModelPlayer(1.0f, false), 0.5f);
    }

    @Override
    protected void applyRotations(EntityPlayerPast entityLiving, float p, float rotationYaw, float partialTicks)
    {
    	super.applyRotations(entityLiving, p, rotationYaw, partialTicks);
    }

    @Override
    public void doRender(EntityPlayerPast entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        //TODO:  Animations
    	System.out.println("RENDERING PLAYERPAST");
    	//this.doRender(entity, x, y, z, entityYaw, partialTicks);
        EntityPlayerPast par1EntityMocap = (EntityPlayerPast)entity;
        ItemStack itemstack = entity.getHeldItemMainhand();
        //this.field_82423_g.heldItemRight = this.field_82425_h.heldItemRight = this.modelBipedMain.heldItemRight = itemstack != null ? 1 : 0;

        /*if (itemstack != null && par1EntityMocap.isEating())
        {
            EnumAction enumaction = itemstack.getItemUseAction();

            if (enumaction == EnumAction.eat)
            {
                this.field_82423_g.heldItemRight = this.field_82425_h.heldItemRight = this.modelBipedMain.heldItemRight = 4;
            }

            if (enumaction == EnumAction.block)
            {
                this.field_82423_g.heldItemRight = this.field_82425_h.heldItemRight = this.modelBipedMain.heldItemRight = 3;
            }
            else if (enumaction == EnumAction.bow)
            {
                this.field_82423_g.aimedBow = this.field_82425_h.aimedBow = this.modelBipedMain.aimedBow = true;
            }
        }*/

        //this.field_82423_g.isSneak = this.field_82425_h.isSneak = this.modelBipedMain.isSneak = entity.isSneaking();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Called from renderEquippedItems in RenderBiped.
     */
    /*@Override
    protected void func_130005_c(EntityLiving par1EntityLiving, float par2)
    {
    	EntityPlayerPast par1EntityMocap = (EntityPlayerPast)par1EntityLiving;
        ItemStack itemstack1 = par1EntityLiving.getHeldItem();
        ItemStack helmetItem = par1EntityLiving.func_130225_q(3);
        float f1 = 1.0F;
        GL11.glColor3f(f1, f1, f1);
        float f2;

        if (helmetItem != null)
        {
            GL11.glPushMatrix();
            this.modelBipedMain.bipedHead.postRender(0.0625F);
            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(helmetItem, EQUIPPED);
            boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, helmetItem, BLOCK_3D));

            if (helmetItem.getItem() instanceof ItemBlock)
            {
                if (is3D || RenderBlocks.renderItemIn3d(Block.blocksList[helmetItem.itemID].getRenderType()))
                {
                    f2 = 0.625F;
                    GL11.glTranslatef(0.0F, -0.25F, 0.0F);
                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScalef(f2, -f2, -f2);
                }

                this.renderManager.itemRenderer.renderItem(par1EntityLiving, helmetItem, 0);
            }
            else if (helmetItem.getItem() == Items.skull)
            {
                f2 = 1.0625F;
                GL11.glScalef(f2, -f2, -f2);
                String s = "";

                if (helmetItem.hasTagCompound() && helmetItem.getTagCompound().hasKey("SkullOwner"))
                {
                    s = helmetItem.getTagCompound().getString("SkullOwner");
                }

                TileEntitySkullRenderer.skullRenderer.func_82393_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, helmetItem.getItemDamage(), s);
            }

            GL11.glPopMatrix();
        }

        if (itemstack1 != null)
        {
            GL11.glPushMatrix();
            this.modelBipedMain.bipedRightArm.postRender(0.0625F);
            GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
            EnumAction enumaction = null;

            if (par1EntityMocap.isEating())
            {
                enumaction = itemstack1.getItemUseAction();
            }

            float f11;
            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack1, EQUIPPED);
            boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack1, BLOCK_3D));
            boolean isBlock = itemstack1.itemID < Block.blocksList.length && itemstack1.getItemSpriteNumber() == 0;

            if (is3D || (isBlock && RenderBlocks.renderItemIn3d(Block.blocksList[itemstack1.itemID].getRenderType())))
            {
                f11 = 0.5F;
                GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
                f11 *= 0.75F;
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(-f11, -f11, f11);
            }
            else if (itemstack1.getItem() == Items.bow)
            {
                f11 = 0.625F;
                GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(f11, -f11, f11);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }
            else if (Item.itemsList[itemstack1.itemID].isFull3D())
            {
                f11 = 0.625F;

                if (Item.itemsList[itemstack1.itemID].shouldRotateAroundWhenRendering())
                {
                    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glTranslatef(0.0F, -0.125F, 0.0F);
                }

                if (par1EntityMocap.isEating()  && enumaction == EnumAction.block)
                {
                    GL11.glTranslatef(0.05F, 0.0F, -0.1F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
                }

                GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
                GL11.glScalef(f11, -f11, f11);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }
            else
            {
                f11 = 0.375F;
                GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
                GL11.glScalef(f11, f11, f11);
                GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
            }

            float f12;
            float f13;
            float f6;
            int j;

            if (itemstack1.getItem().requiresMultipleRenderPasses())
            {
                for (j = 0; j < itemstack1.getItem().getRenderPasses(itemstack1.getItemDamage()); ++j)
                {
                    int k = itemstack1.getItem().getColorFromItemStack(itemstack1, j);
                    f13 = (float)(k >> 16 & 255) / 255.0F;
                    f12 = (float)(k >> 8 & 255) / 255.0F;
                    f6 = (float)(k & 255) / 255.0F;
                    GL11.glColor4f(f13, f12, f6, 1.0F);
                    this.renderManager.itemRenderer.renderItem(par1EntityLiving, itemstack1, j);
                }
            }
            else
            {
                j = itemstack1.getItem().getColorFromItemStack(itemstack1, 0);
                float f14 = (float)(j >> 16 & 255) / 255.0F;
                f13 = (float)(j >> 8 & 255) / 255.0F;
                f12 = (float)(j & 255) / 255.0F;
                GL11.glColor4f(f14, f13, f12, 1.0F);
                this.renderManager.itemRenderer.renderItem(par1EntityLiving, itemstack1, 0);
            }

            GL11.glPopMatrix();
        }
    }
*/
    /*protected void renderModelTest(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        this.bindEntityTexture(par1EntityLivingBase);
        GL11.glPushMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.35F);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
        this.mainModel.render(par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
        GL11.glPopMatrix();
        GL11.glDepthMask(true);
    }*/

    private static ThreadDownloadImageData getDownloadImage(ResourceLocation par0ResourceLocation, String par1Str, ResourceLocation par2ResourceLocation, IImageBuffer par3IImageBuffer)
    {
        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        Object object = texturemanager.getTexture(par0ResourceLocation);

        if (object == null)
        {
            object = new ThreadDownloadImageData((File)null, par1Str, par2ResourceLocation, par3IImageBuffer);
            texturemanager.loadTexture(par0ResourceLocation, (ITextureObject)object);
        }

        return (ThreadDownloadImageData)object;
    }

    public static ThreadDownloadImageData getDownloadImageSkin(ResourceLocation resourceLocationIn, String username)
    {
        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        ITextureObject itextureobject = texturemanager.getTexture(resourceLocationIn);

        if (itextureobject == null)
        {
        	String s = String.format("https://minecraft.tools/download-skin/%s", StringUtils.stripControlCodes(username));
            itextureobject = new ThreadDownloadImageData((File)null, s, /*DefaultPlayerSkin.getDefaultSkin(getOfflineUUID(username))*/DefaultPlayerSkin.getDefaultSkinLegacy(), new ImageBufferDownload());
            texturemanager.loadTexture(resourceLocationIn, itextureobject);
        }

        return (ThreadDownloadImageData)itextureobject;
    }

    public static String getSkinUrl(String par0Str)
    {
        return String.format("https://minecraft.tools/download-skin/%s.png", new Object[] {StringUtils.stripControlCodes(par0Str)});
    }

    public static String getSkindexUrl(String par0Str)
    {
        return String.format("http://www.minecraftskins.com/newuploaded_skins/skin_%s.png", new Object[] {StringUtils.stripControlCodes(par0Str)});
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityPlayerPast entity)
    {
        String skinName = entity.getSkinSource();
        ResourceLocation resourcelocation = null;

        if (skinName != null && skinName.length() > 0)
        {
            resourcelocation = AbstractClientPlayer.getLocationSkin(skinName);
            getDownloadImageSkin(resourcelocation, skinName);
        }

        return resourcelocation;
    }
}