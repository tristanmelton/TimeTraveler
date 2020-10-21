package com.charsmud.timetraveler.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.charsmud.timetraveler.TimeTraveler;
import com.charsmud.timetraveler.util.mechanics.past.PastAction;
import com.charsmud.timetraveler.util.mechanics.past.PastActionTypes;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityArrow.PickupStatus;
import net.minecraft.init.Blocks;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.client.FMLClientHandler;

public class EntityPlayerPast extends EntityLiving 
{
	/**
	 * A list of pending actions the Entity has to perform, injected by the replay
	 * thread.
	 */
	private int itemInUseCount = 0;
	protected static final DataParameter<String> DATA_SKIN_SOURCE = EntityDataManager
			.<String>createKey(EntityPlayerPast.class, DataSerializers.STRING);

	public List<PastAction> eventsList = Collections.synchronizedList(new ArrayList<PastAction>());

	public EntityPlayerPast(World par1World)
	{
		super(par1World);
		this.tasks.taskEntries.clear();
		this.targetTasks.taskEntries.clear();
	}
	
	public void setItemInUseCount(int itemInUseCount) 
	{
		this.itemInUseCount = itemInUseCount;
	}

	public int getItemInUseCount() 
	{
		return itemInUseCount;
	}

	private void replayShootArrow(PastAction ma) 
	{
		float f = (float) ma.arrowCharge / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;

		if ((double) f < 0.1D) 
		{
			return;
		}

		if (f > 1.0F)
		{
			f = 1.0F;
		}

		EntityArrow entityarrow = new EntityArrow(this.world, this)
		{

			@Override
			protected ItemStack getArrowStack() 
			{
				// TODO Auto-generated method stub
				return null;
			}
		};
		entityarrow.pickupStatus = PickupStatus.ALLOWED;
		this.playSound(SoundEvents.ENTITY_ARROW_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
		this.world.spawnEntity(entityarrow);
	}

	private void processActions(PastAction ma) 
	{
		switch (ma.type)
		{
			case PastActionTypes.CHAT:
			{
				// MinecraftServer.getServer().getConfigurationManager().sendChatMsg(
				// .createFromTranslationWithSubstitutions("chat.type.text", new Object[]
				// {getEntityName(), ma.message})
				// );
				break;
			}
	
			case PastActionTypes.SWIPE: 
			{
				swingArm(EnumHand.MAIN_HAND);
				break;
			}
	
			case PastActionTypes.EQUIP:
			{
				if (ma.armorId == -1) 
				{
					this.setItemStackToSlot(EntityEquipmentSlot.values()[ma.armorSlot], null);
				}
				else
				{
					ItemStack loadedEquip = new ItemStack(ma.itemData);
					System.out.println("EQUIPPING " + loadedEquip);
					this.setItemStackToSlot(EntityEquipmentSlot.values()[ma.armorSlot], loadedEquip);
					// System.out.println("erm " + loadedEquip.toString());
				}
	
				break;
			}
			case PastActionTypes.DROP:
			{
				ItemStack foo = new ItemStack(ma.itemData);
				EntityItem ea = new EntityItem(this.world, posX, posY - 0.30000001192092896D + (double) getEyeHeight(),
						posZ, foo);
				Random rand = new Random();
				float f = 0.3F;
				ea.motionX = (double) (-MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI)
						* MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * f);
				ea.motionZ = (double) (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI)
						* MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * f);
				ea.motionY = (double) (-MathHelper.sin(rotationPitch / 180.0F * (float) Math.PI) * f + 0.1F);
				f = 0.02F;
				float f1 = rand.nextFloat() * (float) Math.PI * 2.0F;
				f *= rand.nextFloat();
				ea.motionX += Math.cos((double) f1) * (double) f;
				ea.motionY += (double) ((rand.nextFloat() - rand.nextFloat()) * 0.1F);
				ea.motionZ += Math.sin((double) f1) * (double) f;
				world.spawnEntity(ea);
				break;
			}
	
			case PastActionTypes.SHOOTARROW:
			{
				replayShootArrow(ma);
				break;
			}
			case PastActionTypes.PLACEBLOCK: 
			{
				Block b = ma.blockType;
				world.setBlockState(new BlockPos(ma.xCoord, ma.yCoord, ma.zCoord), b.getDefaultState());

				break;
			}
			case PastActionTypes.BREAKBLOCK:
			{
				World world = DimensionManager.getWorld(0);
				world.setBlockToAir(new BlockPos(ma.xCoord, ma.yCoord, ma.zCoord));
				break;
			}
		}
	}

	@Override
	public void onLivingUpdate() 
	{
		super.onLivingUpdate();
		if (eventsList.size() > 0)
		{
			PastAction ma = eventsList.remove(0);
			processActions(ma);
		}
		this.updateArmSwingProgress();

		if (this.newPosRotationIncrements > 0)
		{
			double d0 = this.posX + (this.posX - this.prevPosX) / (double) this.newPosRotationIncrements;
			double d1 = this.posY + (this.posY - this.prevPosY) / (double) this.newPosRotationIncrements;
			double d2 = this.posZ + (this.posZ - this.prevPosZ) / (double) this.newPosRotationIncrements;
			double d3 = MathHelper.wrapDegrees(this.rotationYaw - (double) this.prevRotationYaw);
			this.rotationYaw = (float) ((double) this.rotationYaw + d3 / (double) this.newPosRotationIncrements);
			this.rotationPitch = (float) ((double) this.rotationPitch
					+ (this.rotationPitch - (double) this.prevRotationPitch) / (double) this.newPosRotationIncrements);
			--this.newPosRotationIncrements;
			this.setPosition(d0, d1, d2);
			this.setRotation(this.rotationYaw, this.rotationPitch);
		} 
		else if (!this.world.isRemote) 
		{
			this.motionX *= 0.98D;
			this.motionY *= 0.98D;
			this.motionZ *= 0.98D;
		}

		if (Math.abs(this.motionX) < 0.005D) 
		{
			this.motionX = 0.0D;
		}

		if (Math.abs(this.motionY) < 0.005D)
		{
			this.motionY = 0.0D;
		}

		if (Math.abs(this.motionZ) < 0.005D)
		{
			this.motionZ = 0.0D;
		}

		if (!this.world.isRemote)
		{
			this.rotationYawHead = this.rotationYaw;
		}

		this.prevLimbSwingAmount = this.limbSwingAmount;
		double d0 = this.posX - this.prevPosX;
		double d1 = this.posZ - this.prevPosZ;
		float f6 = MathHelper.sqrt(d0 * d0 + d1 * d1) * 4.0F;

		if (f6 > 1.0F) 
		{
			f6 = 1.0F;
		}

		this.limbSwingAmount += (f6 - this.limbSwingAmount) * 0.4F;
		this.limbSwing += this.limbSwingAmount;
	}

	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		// System.out.println(worldObj.isRemote);
		// System.out.println(getSkinSource());
		// System.out.println(posY);
		return true;
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(DATA_SKIN_SOURCE, "Charsmud");
		//this.getDataManager().set(DATA_SKIN_SOURCE, "Charsmud");// .addObject(20, "ridgedog");
	}

	public void setSkinSource(String par1Str) 
	{
		this.getDataManager().set(DATA_SKIN_SOURCE, par1Str);
	}

	public String getSkinSource() 
	{
		return this.getDataManager().get(DATA_SKIN_SOURCE);
	}

	protected boolean isAIEnabled() 
	{
		return false;
	}

	public boolean isEntitySeen(Entity entity) 
	{
		if (entity != null && entity.isInvisible())
		{
			Vec3d pastLook = this.getLook(0);

			Vec3d playerPos = entity.getPositionVector();
			Vec3d pastPos = this.getPositionVector();
			Vec3d displacement = playerPos.subtract(pastPos);
			// displacement.addVector(playerPos.xCoord - pastPos.xCoord, playerPos.yCoord -
			// pastPos.yCoord, playerPos.zCoord - pastPos.zCoord);
			double dP = displacement.normalize().dotProduct(pastLook.normalize());
			if (dP < 0) 
			{
				return false;
			}
			if (dP >= Math.cos((120 / 2))) 
			{
				if (this.canEntityBeSeen(entity)) 
				{
					return true;
				}
			}
			return false;
		}
		return false;
	}

	@Override
	public void onEntityUpdate() 
	{
		super.onEntityUpdate();
		if (isEntitySeen(FMLClientHandler.instance().getClient().player)) 
		{
			System.out.println("PLAYER SPOTTED!");
			// TODO: Implement player paradox
			// int paradox = TimeTraveler.vars.getParadoxAmt();
			// paradox = paradox + 2;
			// TimeTraveler.vars.setParadoxAmt(paradox);

		}
	}
    @Override
    public void setDead()
    {
        System.out.println("setDead() called");
        this.isDead = true;
    }
}
