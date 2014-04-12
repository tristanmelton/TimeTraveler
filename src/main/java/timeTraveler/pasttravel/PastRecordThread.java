package timeTraveler.pasttravel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import cpw.mods.fml.client.FMLClientHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.DimensionManager;
import timeTraveler.core.TimeTraveler;

class PastRecordThread implements Runnable 
{
	public Thread t;
	EntityPlayer player;
	public Boolean capture = Boolean.valueOf(false);
	RandomAccessFile in;
	Boolean lastTickSwipe = Boolean.valueOf(false);
	int[] itemsEquipped = new int[5];
	List<PastAction> eventList;

	PastRecordThread(EntityPlayer _player, String capname)
	{
		try {
			File f = new File(FMLClientHandler.instance().getClient().mcDataDir , "/mods/TimeMod/past/EntityLocations/" + MinecraftServer.getServer().getWorldName() + "/");
			if(!f.exists())
			{
				f.mkdirs();
			}
			int counter = f.list().length + 1;
			String time = "Time " + (String.format("%03d",counter));			
			File file = new File(FMLClientHandler.instance().getClient().mcDataDir + "/mods/TimeMod/past/EntityLocations/" + MinecraftServer.getServer().getWorldName() + "/" + time);
			if (!file.exists()) 
			{
				file.mkdirs();
			}
			this.in = new RandomAccessFile(file.getAbsolutePath() + "/" + capname + ".ppd", "rw");

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
					System.out.println("Stopped recording " + this.player.getDisplayName() + ".  RIP.");
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

	private void trackAndWriteMovement() throws IOException 
	{
		this.in.writeFloat(this.player.rotationYaw);
		this.in.writeFloat(this.player.rotationPitch);

		this.in.writeDouble(this.player.posX);
		this.in.writeDouble(this.player.posY);
		this.in.writeDouble(this.player.posZ);

		this.in.writeDouble(this.player.motionX);
		this.in.writeDouble(this.player.motionY);
		this.in.writeDouble(this.player.motionZ);

		this.in.writeFloat(this.player.fallDistance);
		this.in.writeBoolean(this.player.isAirBorne);
		this.in.writeBoolean(this.player.isSneaking());
		this.in.writeBoolean(this.player.isSprinting());
		this.in.writeBoolean(this.player.onGround);

		this.in.writeBoolean((this.player.getDataWatcher().getWatchableObjectByte(0) & 0x10) != 0);
	}

	private void trackArmor()
	{
		for (int ci = 1; ci < 5; ci++)
		{
			if (this.player.inventory.armorInventory[(ci - 1)] != null)
			{
				if (this.player.inventory.armorInventory[(ci - 1)].itemID != this.itemsEquipped[ci])
				{
					this.itemsEquipped[ci] = this.player.inventory.armorInventory[(ci - 1)].itemID;
					PastAction ma = new PastAction((byte) 4);
					ma.armorSlot = ci;
					ma.armorId = this.itemsEquipped[ci];
					ma.armorDmg = this.player.inventory.armorInventory[(ci - 1)].getItemDamage();
					this.eventList.add(ma);
				}
			} 
			else if (this.itemsEquipped[ci] != -1) 
			{
				this.itemsEquipped[ci] = -1;
				PastAction ma = new PastAction((byte) 4);
				ma.armorSlot = ci;
				ma.armorId = this.itemsEquipped[ci];
				ma.armorDmg = 0;
				this.eventList.add(ma);
			}
		}
	}

	private void trackHeldItem()
	{
		if (this.player.getHeldItem() != null)
		{
			if (this.player.getHeldItem().itemID != this.itemsEquipped[0])
			{
				this.itemsEquipped[0] = this.player.getHeldItem().itemID;
				PastAction ma = new PastAction((byte) 4);
				ma.armorSlot = 0;
				ma.armorId = this.itemsEquipped[0];
				ma.armorDmg = this.player.getHeldItem().getItemDamage();
				this.eventList.add(ma);
			}
		} 
		else if (this.itemsEquipped[0] != -1)
		{
			this.itemsEquipped[0] = -1;
			PastAction ma = new PastAction((byte) 4);
			ma.armorSlot = 0;
			ma.armorId = this.itemsEquipped[0];
			ma.armorDmg = 0;
			this.eventList.add(ma);
		}
	}

	private void trackSwing() 
	{
		if (this.player.isSwingInProgress)
		{
			if (!this.lastTickSwipe.booleanValue())
			{
				this.lastTickSwipe = Boolean.valueOf(true);
				this.eventList.add(new PastAction((byte) 2));
			}
		}
		else 
		{
			this.lastTickSwipe = Boolean.valueOf(false);
		}
	}

	private void writeActions() throws IOException 
	{
		if (this.eventList.size() > 0) 
		{
			this.in.writeBoolean(true);
			PastAction ma = (PastAction) this.eventList.get(0);
			this.in.writeByte(ma.type);
			switch (ma.type) 
			{
			case 1:
				this.in.writeUTF(ma.message);
				break;
			case 2:
				break;
			case 3:
				//TODO:  LINE IS IFFY, DONT KNOW IF THIS IS WHAT IT IS SUPPOSED TO BE
				//        by.a(ma.itemData, this.in);
				NBTTagCompound.writeNamedTag(ma.itemData, this.in);
				break;
			case 4:
				this.in.writeInt(ma.armorSlot);
				this.in.writeInt(ma.armorId);
				this.in.writeInt(ma.armorDmg);
				break;
			case 5:
				this.in.writeInt(ma.arrowCharge);
				break;
			case 6:
				TimeTraveler.instance.recordThreads.remove(this.player);
				System.out.println("Stopped recording " + this.player.getDisplayName() + ".  Bye!");
				this.capture = Boolean.valueOf(false);
			}
			this.eventList.remove(0);
		} 
		else 
		{
			this.in.writeBoolean(false);
		}
	}
}
