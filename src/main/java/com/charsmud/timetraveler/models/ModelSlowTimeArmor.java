package com.charsmud.timetraveler.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSlowTimeArmor extends ModelBiped
{
  //fields
    ModelRenderer torsoBelt;
    ModelRenderer rightarmcrystal12;
    ModelRenderer rightarmstar1;
    ModelRenderer rightarmstar2;
    ModelRenderer leftarmstar1;
    ModelRenderer leftarmstar2;
    ModelRenderer rightarm;
    ModelRenderer torso;
    ModelRenderer leftarm;
    ModelRenderer torsostar1;
    ModelRenderer torsostar2;
    ModelRenderer torsostar3;
    ModelRenderer headhelmet1;
    ModelRenderer headhelmet2;
    ModelRenderer headhelmet3;
    ModelRenderer headhelmet4;
    ModelRenderer headhelmet5;
    ModelRenderer headhelmet6;
    ModelRenderer headcrystal1;
    ModelRenderer headhelmet7;
    ModelRenderer headcrystal2;
    ModelRenderer headhelmet8;
    ModelRenderer headhelmet9;
    ModelRenderer headcrystal3;
    ModelRenderer rightlegboots1;
    ModelRenderer rightlegboots2;
    ModelRenderer headhelmet10;
    ModelRenderer leftlegboots1;
    ModelRenderer leftlegboots2;
    ModelRenderer rightleg;
    ModelRenderer torsotube;
    ModelRenderer rightarmshouldercap;
    ModelRenderer rightarmcrystal1;
    ModelRenderer leftarmcrystal1;
    ModelRenderer leftarmcrystal2;
    ModelRenderer leftarmcrystal3;
    ModelRenderer rightarmcrystal2;
    ModelRenderer headhelmet11;
    ModelRenderer leftarmcrystal4;
    ModelRenderer rightarmcrystal3;
    ModelRenderer torsojar1;
    ModelRenderer leftarmshouldercap;
    ModelRenderer torsojar2;
    ModelRenderer torsojar3;
    ModelRenderer torsojar4;
    ModelRenderer torsojar5;
    ModelRenderer torsolid1;
    ModelRenderer torsolid2;
    ModelRenderer torsolid3;
    ModelRenderer torsolid4;
    ModelRenderer torsolid5;
    ModelRenderer torsolid6;
    ModelRenderer headcrystal4;
    ModelRenderer leftleg;
  
  public ModelSlowTimeArmor(float f)
  {
	 super(f, 0 , 64, 64);
	 //textureWidth = 64;
	 //textureHeight = 64;
    
      torsoBelt = new ModelRenderer(this, 55, 14);
      torsoBelt.addBox(-2.8F, 0F, 1.8F, 1, 10, 3);
      torsoBelt.setRotationPoint(0F, 0F, 0F);
      torsoBelt.setTextureSize(64, 64);
      torsoBelt.mirror = true;
      setRotation(torsoBelt, 0F, 0F, -0.5235988F);
      this.bipedBody.addChild(torsoBelt);
      rightarmcrystal12 = new ModelRenderer(this, 0, 27);
      rightarmcrystal12.addBox(1.8F, -6.5F, 1F, 1, 4, 1);
      rightarmcrystal12.setRotationPoint(0F, 0F, 0F);
      rightarmcrystal12.setTextureSize(64, 64);
      rightarmcrystal12.mirror = true;
      setRotation(rightarmcrystal12, 0F, 0F, 0.7853982F);
      this.bipedLeftArm.addChild(rightarmcrystal12);
      rightarmstar1 = new ModelRenderer(this, 2, 19);
      rightarmstar1.addBox(3.5F, 3.7F, -5.5F, 1, 2, 2);
      rightarmstar1.setRotationPoint(0F, 0F, 0F);
      rightarmstar1.setTextureSize(64, 64);
      rightarmstar1.mirror = true;
      setRotation(rightarmstar1, 0.7853982F, 0F, 0F);
      this.bipedLeftArm.addChild(rightarmstar1);
      rightarmstar2 = new ModelRenderer(this, 2, 19);
      rightarmstar2.addBox(3.5F, 5.5F, -0.8F, 1, 2, 2);
      rightarmstar2.setRotationPoint(0F, 0F, 0F);
      rightarmstar2.setTextureSize(64, 64);
      rightarmstar2.mirror = true;
      setRotation(rightarmstar2, 0F, 0F, 0F);
      this.bipedLeftArm.addChild(rightarmstar2);
      leftarmstar1 = new ModelRenderer(this, 2, 19);
      leftarmstar1.addBox(-4.5F, 5.7F, -1F, 1, 2, 2);
      leftarmstar1.setRotationPoint(0F, 0F, 0F);
      leftarmstar1.setTextureSize(64, 64);
      leftarmstar1.mirror = true;
      setRotation(leftarmstar1, 0F, 0F, 0F);
      this.bipedRightArm.addChild(leftarmstar1);
      leftarmstar2 = new ModelRenderer(this, 2, 19);
      leftarmstar2.addBox(-4.5F, 3.7F, -5.8F, 1, 2, 2);
      leftarmstar2.setRotationPoint(0F, 0F, 0F);
      leftarmstar2.setTextureSize(64, 64);
      leftarmstar2.mirror = true;
      setRotation(leftarmstar2, 0.7853982F, 0F, 0F);
      this.bipedRightArm.addChild(leftarmstar2);
      rightarm = new ModelRenderer(this, 0, 47);
      rightarm.addBox(-1F, -3F, -2.5F, 5, 12, 5);
      rightarm.setRotationPoint(0F, 0F, 0F);
      rightarm.setTextureSize(64, 64);
      rightarm.mirror = true;
      setRotation(rightarm, 0F, 0F, 0F);
      this.bipedLeftArm.addChild(rightarm);
      torso = new ModelRenderer(this, 0, 0);
      torso.addBox(-4F, 0F, -2.5F, 8, 12, 5);
      torso.setRotationPoint(0F, 0F, 0F);
      torso.setTextureSize(64, 64);
      torso.mirror = true;
      setRotation(torso, 0F, 0F, 0F);
      this.bipedBody.addChild(torso);
      leftarm = new ModelRenderer(this, 0, 47);
      leftarm.addBox(-4F, -3F, -2.5F, 5, 12, 5);
      leftarm.setRotationPoint(0F, 0F, 0F);
      leftarm.setTextureSize(64, 64);
      leftarm.mirror = true;
      setRotation(leftarm, 0F, 0F, 0F);
      this.bipedRightArm.addChild(leftarm);
      torsostar1 = new ModelRenderer(this, 0, 19);
      torsostar1.addBox(-1.5F, 1.5F, -3F, 3, 3, 1);
      torsostar1.setRotationPoint(0F, 0F, 0F);
      torsostar1.setTextureSize(64, 64);
      torsostar1.mirror = true;
      setRotation(torsostar1, 0F, 0F, 0F);
      this.bipedBody.addChild(torsostar1);
      torsostar2 = new ModelRenderer(this, 0, 19);
      torsostar2.addBox(0.7F, 0.7F, -3F, 3, 3, 1);
      torsostar2.setRotationPoint(0F, 0F, 0F);
      torsostar2.setTextureSize(64, 64);
      torsostar2.mirror = true;
      setRotation(torsostar2, 0F, 0F, 0.7853982F);
      this.bipedBody.addChild(torsostar2);
      torsostar3 = new ModelRenderer(this, 0, 41);
      torsostar3.addBox(-1F, 2F, -3.5F, 2, 2, 1);
      torsostar3.setRotationPoint(0F, 0F, 0F);
      torsostar3.setTextureSize(64, 64);
      torsostar3.mirror = true;
      setRotation(torsostar3, 0F, 0F, 0F);
      this.bipedBody.addChild(torsostar3);
      headhelmet1 = new ModelRenderer(this, 34, 55);
      headhelmet1.addBox(2F, -7.3F, -5.3F, 5, 2, 1);
      headhelmet1.setRotationPoint(0F, 0F, 0F);
      headhelmet1.setTextureSize(64, 64);
      headhelmet1.mirror = true;
      setRotation(headhelmet1, 0F, 0F, -0.2617994F);
      this.bipedHead.addChild(headhelmet1);
      headhelmet2 = new ModelRenderer(this, 34, 55);
      headhelmet2.addBox(-7F, -7.3F, -5.3F, 5, 2, 1);
      headhelmet2.setRotationPoint(0F, 0F, 0F);
      headhelmet2.setTextureSize(64, 64);
      headhelmet2.mirror = true;
      setRotation(headhelmet2, 0F, 0F, 0.2617994F);
      this.bipedHead.addChild(headhelmet2);
      headhelmet3 = new ModelRenderer(this, 27, 27);
      headhelmet3.addBox(4F, -8F, -4F, 1, 5, 9);
      headhelmet3.setRotationPoint(0F, 0F, 0F);
      headhelmet3.setTextureSize(64, 64);
      headhelmet3.mirror = true;
      setRotation(headhelmet3, 0F, 0F, 0F);
      this.bipedHead.addChild(headhelmet3);
      headhelmet4 = new ModelRenderer(this, 27, 27);
      headhelmet4.addBox(-5F, -8F, -4F, 1, 5, 9);
      headhelmet4.setRotationPoint(0F, 0F, 0F);
      headhelmet4.setTextureSize(64, 64);
      headhelmet4.mirror = true;
      setRotation(headhelmet4, 0F, 0F, 0F);
      this.bipedHead.addChild(headhelmet4);
      headhelmet5 = new ModelRenderer(this, 22, 52);
      headhelmet5.addBox(-5F, -10F, -5F, 10, 2, 10);
      headhelmet5.setRotationPoint(0F, 0F, 0F);
      headhelmet5.setTextureSize(64, 64);
      headhelmet5.mirror = true;
      setRotation(headhelmet5, 0F, 0F, 0F);
      this.bipedHead.addChild(headhelmet5);
      headhelmet6 = new ModelRenderer(this, 34, 0);
      headhelmet6.addBox(-5F, -3F, -5F, 1, 2, 10);
      headhelmet6.setRotationPoint(0F, 0F, 0F);
      headhelmet6.setTextureSize(64, 64);
      headhelmet6.mirror = true;
      setRotation(headhelmet6, 0F, 0F, 0F);
      this.bipedHead.addChild(headhelmet6);
      headcrystal1 = new ModelRenderer(this, 18, 19);
      headcrystal1.addBox(-4.5F, -3.166667F, -13F, 1, 1, 4);
      headcrystal1.setRotationPoint(0F, 0F, 0F);
      headcrystal1.setTextureSize(64, 64);
      headcrystal1.mirror = true;
      setRotation(headcrystal1, -0.7853982F, 0F, 0F);
      this.bipedHead.addChild(headcrystal1);
      headhelmet7 = new ModelRenderer(this, 34, 0);
      headhelmet7.addBox(4F, -3F, -5F, 1, 2, 10);
      headhelmet7.setRotationPoint(0F, 0F, 0F);
      headhelmet7.setTextureSize(64, 64);
      headhelmet7.mirror = true;
      setRotation(headhelmet7, 0F, 0F, 0F);
      this.bipedHead.addChild(headhelmet7);
      headcrystal2 = new ModelRenderer(this, 29, 19);
      headcrystal2.addBox(3.5F, -14.8F, -7.7F, 1, 4, 1);
      headcrystal2.setRotationPoint(0F, 0F, 0F);
      headcrystal2.setTextureSize(64, 64);
      headcrystal2.mirror = true;
      setRotation(headcrystal2, 0F, 0F, 0F);
      this.bipedHead.addChild(headcrystal2);
      headhelmet8 = new ModelRenderer(this, 22, 43);
      headhelmet8.addBox(-4F, -8F, 4F, 8, 7, 1);
      headhelmet8.setRotationPoint(0F, 0F, 0F);
      headhelmet8.setTextureSize(64, 64);
      headhelmet8.mirror = true;
      setRotation(headhelmet8, 0F, 0F, 0F);
      this.bipedHead.addChild(headhelmet8);
      headhelmet9 = new ModelRenderer(this, 28, 0);
      headhelmet9.addBox(-4F, -3F, -5F, 1, 2, 1);
      headhelmet9.setRotationPoint(0F, 0F, 0F);
      headhelmet9.setTextureSize(64, 64);
      headhelmet9.mirror = true;
      setRotation(headhelmet9, 0F, 0F, 0F);
      this.bipedHead.addChild(headhelmet9);
      headcrystal3 = new ModelRenderer(this, 29, 19);
      headcrystal3.addBox(-4.5F, -14.8F, -7.7F, 1, 4, 1);
      headcrystal3.setRotationPoint(0F, 0F, 0F);
      headcrystal3.setTextureSize(64, 64);
      headcrystal3.mirror = true;
      setRotation(headcrystal3, 0F, 0F, 0F);
      this.bipedHead.addChild(headcrystal3);
      rightlegboots1 = new ModelRenderer(this, 0, 0);
      rightlegboots1.addBox(-2F, 8F, -3F, 6, 4, 6);
      rightlegboots1.setRotationPoint(0F, 0F, 0F);
      rightlegboots1.setTextureSize(64, 64);
      rightlegboots1.mirror = true;
      setRotation(rightlegboots1, 0F, 0F, 0F);
      this.bipedRightLeg.addChild(rightlegboots1);
      rightlegboots2 = new ModelRenderer(this, 0, 7);
      rightlegboots2.addBox(-2F, 10F, -6F, 6, 2, 3);
      rightlegboots2.setRotationPoint(0F, 0F, 0F);
      rightlegboots2.setTextureSize(64, 64);
      rightlegboots2.mirror = true;
      setRotation(rightlegboots2, 0F, 0F, 0F);
      this.bipedRightLeg.addChild(rightlegboots2);
      headhelmet10 = new ModelRenderer(this, 28, 0);
      headhelmet10.addBox(3F, -3F, -5F, 1, 2, 1);
      headhelmet10.setRotationPoint(0F, 0F, 0F);
      headhelmet10.setTextureSize(64, 64);
      headhelmet10.mirror = true;
      setRotation(headhelmet10, 0F, 0F, 0F);
      this.bipedHead.addChild(headhelmet10);
      leftlegboots1 = new ModelRenderer(this, 0, 0);
      leftlegboots1.addBox(-4F, 8F, -3F, 6, 4, 6);
      leftlegboots1.setRotationPoint(0F, 0F, 0F);
      leftlegboots1.setTextureSize(64, 64);
      leftlegboots1.mirror = true;
      setRotation(leftlegboots1, 0F, 0F, 0F);
      this.bipedLeftLeg.addChild(leftlegboots1);
      leftlegboots2 = new ModelRenderer(this, 0, 7);
      leftlegboots2.addBox(-4F, 10F, -6F, 6, 2, 3);
      leftlegboots2.setRotationPoint(0F, 0F, 0F);
      leftlegboots2.setTextureSize(64, 64);
      leftlegboots2.mirror = true;
      setRotation(leftlegboots2, 0F, 0F, 0F);
      this.bipedLeftLeg.addChild(leftlegboots2);
      rightleg = new ModelRenderer(this, 0, 0);
      rightleg.addBox(-2F, 0F, -2.5F, 5, 8, 5);
      rightleg.setRotationPoint(0F, 0F, 0F);
      rightleg.setTextureSize(64, 64);
      rightleg.mirror = true;
      setRotation(rightleg, 0F, 0F, 0F);
      this.bipedRightLeg.addChild(rightleg);
      torsotube = new ModelRenderer(this, 12, 26);
      torsotube.addBox(-1F, 4F, -2.8F, 1, 6, 1);
      torsotube.setRotationPoint(0F, 0F, 0F);
      torsotube.setTextureSize(64, 64);
      torsotube.mirror = true;
      setRotation(torsotube, 0F, 0F, 0.2617994F);
      this.bipedBody.addChild(torsotube);
      rightarmshouldercap = new ModelRenderer(this, 0, 40);
      rightarmshouldercap.addBox(1F, -3.5F, -1F, 2, 1, 2);
      rightarmshouldercap.setRotationPoint(0F, 0F, 0F);
      rightarmshouldercap.setTextureSize(64, 64);
      rightarmshouldercap.mirror = true;
      setRotation(rightarmshouldercap, 0F, 0F, 0F);
      this.bipedLeftArm.addChild(rightarmshouldercap);
      rightarmcrystal1 = new ModelRenderer(this, 0, 27);
      rightarmcrystal1.addBox(5.0F, -5.8F, -2F, 1, 3, 1);
      rightarmcrystal1.setRotationPoint(0F, 0F, 0F);
      rightarmcrystal1.setTextureSize(64, 64);
      rightarmcrystal1.mirror = true;
      setRotation(rightarmcrystal1, 0F, 0F, 0F);
      this.bipedLeftArm.addChild(rightarmcrystal1);
      leftarmcrystal1 = new ModelRenderer(this, 0, 27);
      leftarmcrystal1.addBox(-2.8F, -6.5F, -2F, 1, 4, 1);
      leftarmcrystal1.setRotationPoint(0F, 0F, 0F);
      leftarmcrystal1.setTextureSize(64, 64);
      leftarmcrystal1.mirror = true;
      setRotation(leftarmcrystal1, 0F, 0F, -0.7853982F);
      this.bipedRightArm.addChild(leftarmcrystal1);
      leftarmcrystal2 = new ModelRenderer(this, 0, 27);
      leftarmcrystal2.addBox(-2.8F, -6.5F, 1F, 1, 4, 1);
      leftarmcrystal2.setRotationPoint(0F, 0F, 0F);
      leftarmcrystal2.setTextureSize(64, 64);
      leftarmcrystal2.mirror = true;
      setRotation(leftarmcrystal2, 0F, 0F, -0.7853982F);
      this.bipedRightArm.addChild(leftarmcrystal2);
      leftarmcrystal3 = new ModelRenderer(this, 0, 27);
      leftarmcrystal3.addBox(-6.5F, -5.8F, -2F, 1, 3, 1);
      leftarmcrystal3.setRotationPoint(0F, 0F, 0F);
      leftarmcrystal3.setTextureSize(64, 64);
      leftarmcrystal3.mirror = true;
      setRotation(leftarmcrystal3, 0F, 0F, 0F);
      this.bipedRightArm.addChild(leftarmcrystal3);
      rightarmcrystal2 = new ModelRenderer(this, 0, 27);
      rightarmcrystal2.addBox(1.8F, -6.5F, -2F, 1, 4, 1);
      rightarmcrystal2.setRotationPoint(0F, 0F, 0F);
      rightarmcrystal2.setTextureSize(64, 64);
      rightarmcrystal2.mirror = true;
      setRotation(rightarmcrystal2, 0F, 0F, 0.7853982F);
      this.bipedLeftArm.addChild(rightarmcrystal2);
      headhelmet11 = new ModelRenderer(this, 34, 55);
      headhelmet11.addBox(-1F, -8F, -8F, 2, 4, 1);
      headhelmet11.setRotationPoint(0F, 0F, 0F);
      headhelmet11.setTextureSize(64, 64);
      headhelmet11.mirror = true;
      setRotation(headhelmet11, -0.2268928F, 0F, 0F);
      this.bipedHead.addChild(headhelmet11);
      leftarmcrystal4 = new ModelRenderer(this, 0, 27);
      leftarmcrystal4.addBox(-6.5F, -5.8F, 1F, 1, 3, 1);
      leftarmcrystal4.setRotationPoint(0F, 0F, 0F);
      leftarmcrystal4.setTextureSize(64, 64);
      leftarmcrystal4.mirror = true;
      setRotation(leftarmcrystal4, 0F, 0F, 0F);
      this.bipedRightArm.addChild(leftarmcrystal4);
      rightarmcrystal3 = new ModelRenderer(this, 0, 27);
      rightarmcrystal3.addBox(5.5F, -5.8F, 1F, 1, 3, 1);
      rightarmcrystal3.setRotationPoint(0F, 0F, 0F);
      rightarmcrystal3.setTextureSize(64, 64);
      rightarmcrystal3.mirror = true;
      setRotation(rightarmcrystal3, 0F, 0F, 0F);
      this.bipedLeftArm.addChild(rightarmcrystal3);
      torsojar1 = new ModelRenderer(this, 10, 39);
      torsojar1.addBox(0.6F, 0.8F, 2.5F, 2, 3, 2);
      torsojar1.setRotationPoint(0F, 0F, 0F);
      torsojar1.setTextureSize(64, 64);
      torsojar1.mirror = true;
      setRotation(torsojar1, 0F, 0F, 1.047198F);
      this.bipedBody.addChild(torsojar1);
      leftarmshouldercap = new ModelRenderer(this, 0, 40);
      leftarmshouldercap.addBox(-3F, -3.5F, -1F, 2, 1, 2);
      leftarmshouldercap.setRotationPoint(0F, 0F, 0F);
      leftarmshouldercap.setTextureSize(64, 64);
      leftarmshouldercap.mirror = true;
      setRotation(leftarmshouldercap, 0F, 0F, 0F);
      this.bipedRightArm.addChild(leftarmshouldercap);
      torsojar2 = new ModelRenderer(this, 10, 39);
      torsojar2.addBox(-3F, 9F, -4.5F, 2, 3, 2);
      torsojar2.setRotationPoint(0F, 0F, 0F);
      torsojar2.setTextureSize(64, 64);
      torsojar2.mirror = true;
      setRotation(torsojar2, 0F, 0F, 0F);
      this.bipedBody.addChild(torsojar2);
      torsojar3 = new ModelRenderer(this, 10, 39);
      torsojar3.addBox(7.5F, 0.8F, 2.5F, 2, 3, 2);
      torsojar3.setRotationPoint(0F, 0F, 0F);
      torsojar3.setTextureSize(64, 64);
      torsojar3.mirror = true;
      setRotation(torsojar3, 0F, 0F, 1.047198F);
      this.bipedBody.addChild(torsojar3);
      torsojar4 = new ModelRenderer(this, 10, 39);
      torsojar4.addBox(5.2F, 0.8F, 2.5F, 2, 3, 2);
      torsojar4.setRotationPoint(0F, 0F, 0F);
      torsojar4.setTextureSize(64, 64);
      torsojar4.mirror = true;
      setRotation(torsojar4, 0F, 0F, 1.047198F);
      this.bipedBody.addChild(torsojar4);
      torsojar5 = new ModelRenderer(this, 10, 39);
      torsojar5.addBox(3F, 0.8F, 2.5F, 2, 3, 2);
      torsojar5.setRotationPoint(0F, 0F, 0F);
      torsojar5.setTextureSize(64, 64);
      torsojar5.mirror = true;
      setRotation(torsojar5, 0F, 0F, 1.047198F);
      this.bipedBody.addChild(torsojar5);
      torsolid1 = new ModelRenderer(this, 0, 20);
      torsolid1.addBox(-2.5F, 8.5F, -4F, 1, 1, 1);
      torsolid1.setRotationPoint(0F, 0F, 0F);
      torsolid1.setTextureSize(64, 64);
      torsolid1.mirror = true;
      setRotation(torsolid1, 0F, 0F, 0F);
      this.bipedBody.addChild(torsolid1);
      torsolid2 = new ModelRenderer(this, 0, 20);
      torsolid2.addBox(7.9F, 0.3F, 3F, 1, 1, 1);
      torsolid2.setRotationPoint(0F, 0F, 0F);
      torsolid2.setTextureSize(64, 64);
      torsolid2.mirror = true;
      setRotation(torsolid2, 0F, 0F, 1.047198F);
      this.bipedBody.addChild(torsolid2);
      torsolid3 = new ModelRenderer(this, 0, 20);
      torsolid3.addBox(5.7F, 0.3F, 3F, 1, 1, 1);
      torsolid3.setRotationPoint(0F, 0F, 0F);
      torsolid3.setTextureSize(64, 64);
      torsolid3.mirror = true;
      setRotation(torsolid3, 0F, 0F, 1.047198F);
      this.bipedBody.addChild(torsolid3);
      torsolid4 = new ModelRenderer(this, 0, 20);
      torsolid4.addBox(0F, 0F, 0F, 1, 1, 1);
      torsolid4.setRotationPoint(0F, 0F, 0F);
      torsolid4.setTextureSize(64, 64);
      torsolid4.mirror = true;
      setRotation(torsolid4, 0F, 0F, 1.047198F);
      this.bipedBody.addChild(torsolid4);
      torsolid5 = new ModelRenderer(this, 0, 20);
      torsolid5.addBox(1.2F, 0.2F, 3F, 1, 1, 1);
      torsolid5.setRotationPoint(0F, 0F, 0F);
      torsolid5.setTextureSize(64, 64);
      torsolid5.mirror = true;
      setRotation(torsolid5, 0F, 0F, 1.047198F);
      this.bipedBody.addChild(torsolid5);
      torsolid6 = new ModelRenderer(this, 0, 20);
      torsolid6.addBox(3.5F, 0.2F, 3F, 1, 1, 1);
      torsolid6.setRotationPoint(0F, 0F, 0F);
      torsolid6.setTextureSize(64, 64);
      torsolid6.mirror = true;
      setRotation(torsolid6, 0F, 0F, 1.047198F);
      this.bipedBody.addChild(torsolid6);
      headcrystal4 = new ModelRenderer(this, 18, 19);
      headcrystal4.addBox(3.5F, -3.166667F, -13F, 1, 1, 4);
      headcrystal4.setRotationPoint(0F, 0F, 0F);
      headcrystal4.setTextureSize(64, 64);
      headcrystal4.mirror = true;
      setRotation(headcrystal4, -0.7853982F, 0F, 0F);
      this.bipedHead.addChild(headcrystal4);
      leftleg = new ModelRenderer(this, 0, 0);
      leftleg.addBox(-3F, 0F, -2.5F, 5, 8, 5);
      leftleg.setRotationPoint(0, 0F, 0F);
      leftleg.setTextureSize(64, 64);
      leftleg.mirror = true;
      setRotation(leftleg, 0F, 0F, 0F);
      this.bipedLeftLeg.addChild(leftleg);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    /*torsoBelt.render(f5);
    rightarmcrystal1.render(f5);
    rightarmstar1.render(f5);
    rightarmstar2.render(f5);
    leftarmstar1.render(f5);
    leftarmstar2.render(f5);
    rightarm.render(f5);
    torso.render(f5);
    leftarm.render(f5);
    torsostar1.render(f5);
    torsostar2.render(f5);
    torsostar3.render(f5);
    headhelmet1.render(f5);
    headhelmet2.render(f5);
    headhelmet3.render(f5);
    headhelmet4.render(f5);
    headhelmet5.render(f5);
    headhelmet6.render(f5);
    headcrystal1.render(f5);
    headhelmet7.render(f5);
    headcrystal2.render(f5);
    headhelmet8.render(f5);
    headhelmet9.render(f5);
    headcrystal3.render(f5);
    rightlegboots1.render(f5);
    rightlegboots2.render(f5);
    headhelmet10.render(f5);
    leftlegboots1.render(f5);
    leftlegboots2.render(f5);
    rightleg.render(f5);
    torsotube.render(f5);
    rightarmshouldercap.render(f5);
    rightarmcrystal1.render(f5);
    leftarmcrystal1.render(f5);
    leftarmcrystal2.render(f5);
    leftarmcrystal3.render(f5);
    rightarmcrystal2.render(f5);
    headhelmet11.render(f5);
    leftarmcrystal4.render(f5);
    rightarmcrystal3.render(f5);
    torsojar1.render(f5);
    leftarmshouldercap.render(f5);
    torsojar2.render(f5);
    torsojar3.render(f5);
    torsojar4.render(f5);
    torsojar5.render(f5);
    torsolid1.render(f5);
    torsolid2.render(f5);
    torsolid3.render(f5);
    torsolid4.render(f5);
    torsolid5.render(f5);
    torsolid6.render(f5);
    headcrystal4.render(f5);
    leftleg.render(f5);*/
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

}
