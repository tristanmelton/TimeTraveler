package com.charsmud.timetraveler.util.mechanics.past;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import com.charsmud.timetraveler.TimeTraveler;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraftforge.fml.client.FMLClientHandler;

class PastRecordThread implements Runnable 
{
	public Thread t;
	EntityPlayer player;
	public Boolean capture = Boolean.valueOf(false);
	RandomAccessFile in;
	Boolean lastTickSwipe = Boolean.valueOf(false);
	int[] itemsEquipped = new int[5];
	List<PastAction> eventList;

	PastRecordThread(EntityPlayer _player)
	{
		try {
			File f = new File(FMLClientHandler.instance().getClient().mcDataDir , "/mods/TimeMod/past/" + Minecraft.getMinecraft().getIntegratedServer().getWorldName() + "/");
			if(!f.exists())
			{
				f.mkdirs();
			}
			this.in = new RandomAccessFile(f.getAbsolutePath() + "/" + _player.getDisplayNameString() + ".ppd", "rw");

			this.in.setLength(0L);
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		this.player = _player;
		this.capture = Boolean.valueOf(true);
		this.eventList = TimeTraveler.instance.getActionListForPlayer(this.player);
		if(eventList == null)
			this.eventList = new ArrayList<PastAction>();
		this.t = new Thread(this, "TimeTraveler Past Record Thread");

		this.t.start();
	}

	public void run()
	{
		try
		{
			this.in.writeShort(60651);
			while (this.capture.booleanValue())
			{
				this.eventList.addAll(TimeTraveler.instance.getActionListForPlayer(player));
				TimeTraveler.instance.clearActionList(player);
				//writeTime();
				
				trackAndWriteMovement();

				trackSwing();

				trackHeldItem();

				trackArmor();

				writeActions();

				Thread.sleep(100L);
				if (this.player.isDead) 
				{
					this.capture = Boolean.valueOf(false);
					TimeTraveler.instance.recordThreads.remove(this.player);
					System.out.println("Stopped recording " + this.player.getDisplayNameString() + ".  RIP.");
				}
			}
			this.in.close();
		}
		catch (InterruptedException e) 
		{
			System.out.println("Child interrupted.");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Exiting child thread.");
	}
	private void writeTime() throws IOException
	{
		in.writeLong(System.currentTimeMillis());
	}
	private void trackAndWriteMovement() throws IOException 
	{
		in.writeFloat(player.rotationYaw);
		in.writeFloat(player.rotationPitch);
		in.writeDouble(player.posX);
		in.writeDouble(player.posY);
		in.writeDouble(player.posZ);
		in.writeDouble(player.motionX);
		in.writeDouble(player.motionY);
		in.writeDouble(player.motionZ);
		in.writeFloat(player.fallDistance);
		in.writeBoolean(player.isAirBorne);
		in.writeBoolean(player.isSneaking());
		in.writeBoolean(player.isSprinting());
		in.writeBoolean(player.onGround);
		//in.writeBoolean((player.getDataWatcher().getWatchableObjectByte(0) & 1 << 4) != 0);
	}

	private void trackSwing() 
	{
		if (this.player.isSwingInProgress)
		{
			if (!this.lastTickSwipe.booleanValue())
			{
				this.lastTickSwipe = Boolean.valueOf(true);
				this.eventList.add(new PastAction(PastActionTypes.SWIPE));
			}
		}
		else 
		{
			this.lastTickSwipe = Boolean.valueOf(false);
		}
	}
	private void trackHeldItem()
	{
		if (this.player.getHeldItemMainhand() != ItemStack.EMPTY)
		{
			if (Item.getIdFromItem(player.getHeldItemMainhand().getItem()) != itemsEquipped[0])
			{
				itemsEquipped[0] = Item.getIdFromItem(player.getHeldItemMainhand().getItem());
				PastAction ma = new PastAction(PastActionTypes.EQUIP);
				ma.armorSlot = 0;
				ma.armorId = this.itemsEquipped[0];
				ma.armorDmg = this.player.getHeldItemMainhand().getItemDamage();
				player.getHeldItemMainhand().writeToNBT(ma.itemData);
				this.eventList.add(ma);
			}
		} 
		else if (this.player.getHeldItemMainhand() == ItemStack.EMPTY && this.itemsEquipped[0] != -1)
		{
			this.itemsEquipped[0] = -1;
			PastAction ma = new PastAction(PastActionTypes.EQUIP);
			ma.armorSlot = 0;
			ma.armorId = 0;//this.itemsEquipped[0];
			ma.armorDmg = 0;
			this.eventList.add(ma);
		}
	}
	private void trackArmor()
	{
		for (int ci = 1; ci < 5; ci++)
		{
			if (this.player.inventory.armorInventory.get(ci - 1) != null)
			{
				if (Item.getIdFromItem(player.inventory.armorInventory.get(ci - 1).getItem()) != itemsEquipped[ci]) {
					itemsEquipped[ci] = Item.getIdFromItem(player.inventory.armorInventory.get(ci - 1).getItem());	
					PastAction ma = new PastAction(PastActionTypes.EQUIP);
					ma.armorSlot = ci;
					ma.armorId = this.itemsEquipped[ci];
					ma.armorDmg = this.player.inventory.armorInventory.get(ci - 1).getItemDamage();

					this.eventList.add(ma);
				}
			} 
			else if (this.itemsEquipped[ci] != -1) 
			{
				this.itemsEquipped[ci] = -1;
				PastAction ma = new PastAction(PastActionTypes.EQUIP);
				ma.armorSlot = ci;
				ma.armorId = this.itemsEquipped[ci];
				ma.armorDmg = 0;
				this.eventList.add(ma);
			}
		}
	}
	private void writeActions() throws IOException 
	{
		if (this.eventList.size() > 0) 
		{
			this.in.writeBoolean(true);
			PastAction ma = (PastAction) this.eventList.get(0);
			System.out.println("PAST ACTION TYPE " + ma.type);
			this.in.writeByte(ma.type);
			switch (ma.type) 
			{
			//TODO: Not Implemented
			case PastActionTypes.CHAT:
			{
				this.in.writeUTF(ma.message);
				break;
			}
			case PastActionTypes.SWIPE:
			{
				break;
			}
			case PastActionTypes.DROP:
			{
				System.out.println("DROP");
				CompressedStreamTools.write(ma.itemData, this.in);
				break;
			}
			case PastActionTypes.EQUIP:
			{
				this.in.writeInt(ma.armorSlot);
				this.in.writeInt(ma.armorId);
				this.in.writeInt(ma.armorDmg);
				
				if (ma.armorId != -1) {
					CompressedStreamTools.write(ma.itemData, in);
				}
				break;
			}
			case PastActionTypes.SHOOTARROW:
			{
				System.out.println("SHOOTARROW");
				this.in.writeInt(ma.arrowCharge);
				break;
			}
			case PastActionTypes.PLACEBLOCK: 
			{
				System.out.println("PLACEBLOCK");
				in.writeInt(ma.xCoord);
				in.writeInt(ma.yCoord);
				in.writeInt(ma.zCoord);
				in.writeInt(Block.getIdFromBlock(ma.blockType));
				break;
			}
			case PastActionTypes.LOGOUT:
			{
				TimeTraveler.instance.recordThreads.remove(this.player);
				System.out.println("Stopped recording " + this.player.getDisplayNameString() + ".  Bye!");
				this.capture = Boolean.valueOf(false);
				break;
			}
			case PastActionTypes.BREAKBLOCK:
			{
				System.out.println("BREAKBLOCK");
				in.writeInt(ma.xCoord);
				in.writeInt(ma.yCoord);
				in.writeInt(ma.zCoord);
			}
			}
			this.eventList.remove(0);
		} 
		else 
		{
			this.in.writeBoolean(false);
		}
	}
}
