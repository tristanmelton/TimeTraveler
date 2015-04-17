package timeTraveler.pasttravel;

import net.minecraft.block.Block;
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
	public int xCoord;
	public int yCoord;
	public int zCoord;
	public Block blockType;
	public int blockMeta;
	public PastAction(byte chat)
	{
		super();
		this.type = chat;
		this.itemData = new NBTTagCompound();
	}
}
