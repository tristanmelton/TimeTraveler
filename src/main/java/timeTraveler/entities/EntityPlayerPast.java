package timeTraveler.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import timeTraveler.core.TimeTraveler;
import timeTraveler.pasttravel.PastAction;
import timeTraveler.pasttravel.PastActionTypes;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.server.FMLServerHandler;

public class EntityPlayerPast extends EntityLiving
{
    /**
     * A list of pending actions the Entity has to perform, injected
     * by the replay thread.
     */
    private int itemInUseCount = 0;

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
        float f = (float)ma.arrowCharge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;

        if ((double)f < 0.1D)
        {
            return;
        }

        if (f > 1.0F)
        {
            f = 1.0F;
        }

        EntityArrow entityarrow = new EntityArrow(worldObj, this, f * 2.0F);
        entityarrow.canBePickedUp = 1;
        this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        worldObj.spawnEntityInWorld(entityarrow);
    }

    private void processActions(PastAction ma)
    {
        switch (ma.type)
        {
            case PastActionTypes.CHAT:
            {
                MinecraftServer.getServer().getConfigurationManager().sendChatMsg(
                    ChatMessageComponent.createFromTranslationWithSubstitutions("chat.type.text", new Object[] {getEntityName(), ma.message})
                );
                break;
            }

            case PastActionTypes.SWIPE:
            {
                swingItem();
                break;
            }

            case PastActionTypes.EQUIP:
            {
                if (ma.armorId == -1)
                {
                    setCurrentItemOrArmor(ma.armorSlot, null);
                }
                else
                {
                    ItemStack loadedEquip = ItemStack.loadItemStackFromNBT(ma.itemData);
                    System.out.println("EQUIPPING " + loadedEquip);
                    setCurrentItemOrArmor(ma.armorSlot, loadedEquip);
                    //System.out.println("erm " + loadedEquip.toString());
                }

                break;
            }

            case PastActionTypes.DROP:
            {
                ItemStack foo = ItemStack.loadItemStackFromNBT(ma.itemData);
                EntityItem ea = new EntityItem(worldObj, posX,
                                               posY - 0.30000001192092896D + (double)getEyeHeight(), posZ, foo);
                Random rand = new Random();
                float f = 0.3F;
                ea.motionX = (double) (-MathHelper.sin(rotationYaw / 180.0F
    					* (float) Math.PI)
    					* MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * f);
    			ea.motionZ = (double) (MathHelper.cos(rotationYaw / 180.0F
    					* (float) Math.PI)
    					* MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * f);
    			ea.motionY = (double) (-MathHelper.sin(rotationPitch / 180.0F
    					* (float) Math.PI)
    					* f + 0.1F);
    			f = 0.02F;
    			float f1 = rand.nextFloat() * (float) Math.PI * 2.0F;
    			f *= rand.nextFloat();
    			ea.motionX += Math.cos((double) f1) * (double) f;
    			ea.motionY += (double) ((rand.nextFloat() - rand.nextFloat()) * 0.1F);
    			ea.motionZ += Math.sin((double) f1) * (double) f;
    			worldObj.spawnEntityInWorld(ea);
                break;
            }

            case PastActionTypes.SHOOTARROW:
            {
                replayShootArrow(ma);
                break;
            }
    		case PastActionTypes.PLACEBLOCK: 
    		{
    			ItemStack foo = ItemStack.loadItemStackFromNBT(ma.itemData);

    			if (foo.getItem() instanceof ItemBlock)
    			{
    				ItemBlock f = (ItemBlock) foo.getItem();
    				f.placeBlockAt(foo, null, worldObj, ma.xCoord, ma.yCoord,
    						ma.zCoord, 0, 0, 0, 0, foo.getItemDamage());
    			}

    			break;
    		}
    		case PastActionTypes.BREAKBLOCK:
    		{
		        World world = DimensionManager.getWorld(0);
    			world.setBlockToAir(ma.xCoord, ma.yCoord, ma.zCoord);
    			break;
    		}
        }
    }

    public void onLivingUpdate()
    {
    	super.onLivingUpdate();
		if (eventsList.size() > 0) {
			PastAction ma = eventsList.remove(0);
			processActions(ma);
		}

		this.updateArmSwingProgress();

		if (this.newPosRotationIncrements > 0) {
			double d0 = this.posX + (this.newPosX - this.posX)
					/ (double) this.newPosRotationIncrements;
			double d1 = this.posY + (this.newPosY - this.posY)
					/ (double) this.newPosRotationIncrements;
			double d2 = this.posZ + (this.newPosZ - this.posZ)
					/ (double) this.newPosRotationIncrements;
			double d3 = MathHelper.wrapAngleTo180_double(this.newRotationYaw
					- (double) this.rotationYaw);
			this.rotationYaw = (float) ((double) this.rotationYaw + d3
					/ (double) this.newPosRotationIncrements);
			this.rotationPitch = (float) ((double) this.rotationPitch + (this.newRotationPitch - (double) this.rotationPitch)
					/ (double) this.newPosRotationIncrements);
			--this.newPosRotationIncrements;
			this.setPosition(d0, d1, d2);
			this.setRotation(this.rotationYaw, this.rotationPitch);
		} else if (!this.isClientWorld()) {
			this.motionX *= 0.98D;
			this.motionY *= 0.98D;
			this.motionZ *= 0.98D;
		}

		if (Math.abs(this.motionX) < 0.005D) {
			this.motionX = 0.0D;
		}

		if (Math.abs(this.motionY) < 0.005D) {
			this.motionY = 0.0D;
		}

		if (Math.abs(this.motionZ) < 0.005D) {
			this.motionZ = 0.0D;
		}

		if (!this.isClientWorld()) {
			this.rotationYawHead = this.rotationYaw;
		}

		this.prevLimbSwingAmount = this.limbSwingAmount;
		double d0 = this.posX - this.prevPosX;
		double d1 = this.posZ - this.prevPosZ;
		float f6 = MathHelper.sqrt_double(d0 * d0 + d1 * d1) * 4.0F;

		if (f6 > 1.0F) {
			f6 = 1.0F;
		}

		this.limbSwingAmount += (f6 - this.limbSwingAmount) * 0.4F;
		this.limbSwing += this.limbSwingAmount;
	}

    public boolean interact(EntityPlayer par1EntityPlayer)
    {
        //System.out.println(worldObj.isRemote);
        //System.out.println(getSkinSource());
        //System.out.println(posY);
        return true;
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(20, "ridgedog");
    }

    public void setSkinSource(String par1Str)
    {
        this.dataWatcher.updateObject(20, par1Str);
    }

    public String getSkinSource()
    {
        return this.dataWatcher.getWatchableObjectString(20);
    }

    protected boolean isAIEnabled()
    {
        return false;
    }

    public boolean isPlayerSeen(Entity par1Entity)
    {
    	Vec3 pastLook = this.getLook(0);
    	
    	Vec3 playerPos = FMLClientHandler.instance().getClient().thePlayer.getPosition(0);
    	Vec3 pastPos = this.getPosition(0);
    	
    	Vec3 displacement = Vec3.createVectorHelper(playerPos.xCoord - pastPos.xCoord, playerPos.yCoord - pastPos.yCoord, playerPos.zCoord - pastPos.zCoord);
    	//displacement.addVector(playerPos.xCoord - pastPos.xCoord, playerPos.yCoord - pastPos.yCoord, playerPos.zCoord - pastPos.zCoord);
    	double dP = displacement.normalize().dotProduct(pastLook.normalize());
    	if(dP < 0)
    	{
    		return false;
    	}
    	if(dP >= Math.cos((120/2)))
    	{    		
    		if(this.canEntityBeSeen(FMLClientHandler.instance().getClient().thePlayer))
    		{
    			return true;
    		
    		}
    	}
    	
    	return false;
    }
    
 public void onEntityUpdate()
   {
        super.onEntityUpdate();
        if(isPlayerSeen(FMLClientHandler.instance().getClient().thePlayer))
        {
        	System.out.println("PLAYER SPOTTED!");
			int paradox = TimeTraveler.vars.getParadoxAmt();
			paradox = paradox + 2;
			TimeTraveler.vars.setParadoxAmt(paradox);

        }
    }
}
