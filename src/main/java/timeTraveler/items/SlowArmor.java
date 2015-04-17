package timeTraveler.items; 
import java.util.List;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import timeTraveler.core.TimeTraveler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SlowArmor extends ItemArmor
{
	public SlowArmor(ArmorMaterial par2EnumArmorMaterial, int par3, int par4)
	{
		super(par2EnumArmorMaterial, par3, par4);
		setCreativeTab(TimeTraveler.tabTT);
		// TODO Auto-generated constructor stub 
	}
	@Override 
	@SideOnly(Side.CLIENT) 
	public void registerIcons(IIconRegister par1IconRegister) 
	{
		String itemName = getUnlocalizedName().substring(getUnlocalizedName().lastIndexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon(TimeTraveler.modid + ":" + itemName); 

	}
	@Override 
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) 
	{
		if (stack.getItem() == TimeTraveler.slowChestplate || stack.getItem() == TimeTraveler.slowBoots || stack.getItem() == TimeTraveler.slowHelmet)
		{ 
			return TimeTraveler.modid + ":" + "textures/armor/condensed.png";
		} 
		if (stack.getItem() == TimeTraveler.slowLeggings) 
		{ 
			return TimeTraveler.modid +":" + "textures/armor/legs.png";
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
				armorModel.heldItemRight = entityLiving.getHeldItem() != null ? 1 :0; 
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
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemstack)
	{
		if (player.getCurrentArmor(3) != null && player.getCurrentArmor(2) != null && player.getCurrentArmor(1) != null && player.getCurrentArmor(0) != null)
		{
			ItemStack helmet = player.getCurrentArmor(3);
			ItemStack plate = player.getCurrentArmor(2);
			ItemStack legs = player.getCurrentArmor(1);
			ItemStack boots = player.getCurrentArmor(0);

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
							if(!e.getEntityData().getBoolean("hasBeenTimeSlowed"))
							{
								e.getEntityData().setDouble("actualMotionX", e.motionX);
								e.getEntityData().setDouble("actualMotionY", e.motionY);
								e.getEntityData().setDouble("actualMotionZ", e.motionZ);
								e.setVelocity(e.motionX * .4, e.motionY + .003, e.motionZ * .4);
								e.getEntityData().setBoolean("hasBeenTimeSlowed", true);
							}
						}
					}
				}
			}
		}
	}
}