package timeTraveler.items; 
import java.util.List;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import timeTraveler.core.TimeTraveler;
import timeTraveler.entities.EntityParadoxHunter;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SlowArmor extends ItemArmor
{
	public SlowArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4)
	{
		super(par1, par2EnumArmorMaterial, par3, par4); 
		// TODO Auto-generated constructor stub 
	}
	@Override 
	@SideOnly(Side.CLIENT) 
	public void registerIcons(IconRegister par1IconRegister) 
	{
		String itemName = getUnlocalizedName().substring(getUnlocalizedName().lastIndexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon(itemName); 
	}
	@Override 
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) 
	{
		if (stack.itemID == TimeTraveler.slowChestplate.itemID || stack.itemID == TimeTraveler.slowBoots.itemID || stack.itemID == TimeTraveler.slowHelmet.itemID)
		{ 
			return TimeTraveler.modid + ":" + "/armor/condensed.png";
		} 
		if (stack.itemID == TimeTraveler.slowLeggings.itemID) 
		{ 
			return TimeTraveler.modid +":" + "/armor/legs.png";
		}
		return null; 
	}
	ModelBiped armorModel = new ModelBiped();
	@Override 
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) 
	{
		if(itemStack != null)
		{
			if(itemStack.getItem() instanceof SlowArmor)
			{
				int type = ((ItemArmor)itemStack.getItem()).armorType; 
				if(type == 0 ||type == 1 || type == 3)
				{
					armorModel = TimeTraveler.proxy.getArmorModel(0);
				}
				else
				{
					armorModel = TimeTraveler.proxy.getArmorModel(1); 
				} 
			} 
			if(armorModel != null)
			{ 
				armorModel.bipedHead.showModel = armorSlot == 0;
				armorModel.bipedHeadwear.showModel = armorSlot == 0; 
				armorModel.bipedBody.showModel = armorSlot == 1; //|| armorSlot == 2;
				armorModel.bipedRightArm.showModel = armorSlot == 1; 
				armorModel.bipedLeftArm.showModel = armorSlot == 1; 
				armorModel.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
				armorModel.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;
				armorModel.isSneak = entityLiving.isSneaking(); 
				armorModel.isRiding = entityLiving.isRiding();
				armorModel.isChild = entityLiving.isChild(); 
				armorModel.heldItemRight = entityLiving.getCurrentItemOrArmor(0) != null ? 1 :0; 
				if(entityLiving instanceof EntityPlayer)
				{ 
					armorModel.aimedBow =((EntityPlayer)entityLiving).getItemInUseDuration() > 2; 
				}
				return armorModel;
			} 
		}
		return null;
	} 
	// 1.7.2: method name changed to "onArmorTick"
	// 1.7.2: addChatMessage changed to require a ChatComponentText
	@Override
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemstack)
	{
		if (player.getCurrentItemOrArmor(4) != null && player.getCurrentArmor(3) != null && player.getCurrentItemOrArmor(2) != null && player.getCurrentItemOrArmor(1) != null)
		{
			ItemStack helmet = player.getCurrentItemOrArmor(4);
			ItemStack plate = player.getCurrentItemOrArmor(3);
			ItemStack legs = player.getCurrentItemOrArmor(2);
			ItemStack boots = player.getCurrentItemOrArmor(1);

			if (helmet.getItem() == TimeTraveler.slowHelmet && plate.getItem() == TimeTraveler.slowChestplate && legs.getItem() == TimeTraveler.slowLeggings && boots.getItem() == TimeTraveler.slowBoots)
			{
				List<Entity> entitiesToSlow = world.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(player.posX - 5, player.posY - 5, player.posZ - 5, player.posX + 5, player.posY + 5, player.posZ + 5));
				if(entitiesToSlow.size() > 0)
				{
					for(int i = 0; i < entitiesToSlow.size(); i++)
					{
						Entity e = entitiesToSlow.get(i);
						if(e instanceof EntityPlayer)
						{
							
						}
						else
						{
							
							/*double velx = (e.posX - e.prevPosX);
							double vely = (e.posY - e.prevPosY);
							double velz = (e.posZ - e.prevPosZ);

							if(velx != e.getEntityData().getDouble("slowX") || vely != e.getEntityData().getDouble("slowY") || velz != e.getEntityData().getDouble("slowZ"))
							{
								e.getEntityData().setBoolean("inSlowZone", false);
							}
							if(!e.getEntityData().getBoolean("inSlowZone") )
							{ 	
								double factor = 0.7D;
								
								e.setVelocity(velx * factor, vely * factor, velz * factor);
								e.getEntityData().setBoolean("inSlowZone", true);
								e.getEntityData().setDouble("slowX", velx * factor);
								e.getEntityData().setDouble("slowY", vely * factor);
								e.getEntityData().setDouble("slowZ", velz * factor);
							}*/
						}
					}
				}
				
				System.out.println(":D :D LEL");
			}
		}
	}
}