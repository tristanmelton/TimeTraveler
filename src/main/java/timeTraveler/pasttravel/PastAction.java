package timeTraveler.pasttravel;

import net.minecraft.nbt.NBTTagCompound;

public class PastAction 
{
	public byte type;
	public String message;
	public int armorId;
	public int armorSlot;
	public int armorDmg;
	public NBTTagCompound itemData;
	public int arrowCharge;

	public PastAction(byte chat)
	{
		this.type = chat;
		this.itemData = new NBTTagCompound("itemdata");
	}
}
