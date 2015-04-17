package timeTraveler.network;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class Message implements IMessage
{
	public String data;
	public String entityName;
	public String uuid;
	public String playerUsr;
	
	public int futureTravel;
	public int entityX;
	public int entityY;
	public int entityZ;
	public Message() 
	{
	}
	public Message(String type, String name, String uuid, String username, int travel, int x, int y, int z)
	{
		this.data = type;
		this.entityName = name;
		this.uuid = uuid;
		this.playerUsr = username;
		
		this.futureTravel = travel;
		this.entityX = x;
		this.entityY = y;
		this.entityZ = z;
	}
	@Override
	public void fromBytes(ByteBuf buf) {
		data = ByteBufUtils.readUTF8String(buf); 
		entityName = ByteBufUtils.readUTF8String(buf);
		uuid = ByteBufUtils.readUTF8String(buf);
		playerUsr = ByteBufUtils.readUTF8String(buf);
		
        this.futureTravel = buf.readInt();
        this.entityX = buf.readInt();
        this.entityY = buf.readInt();
        this.entityZ = buf.readInt();


	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		ByteBufUtils.writeUTF8String(buf, data);
		ByteBufUtils.writeUTF8String(buf, entityName);
		ByteBufUtils.writeUTF8String(buf, uuid);
		ByteBufUtils.writeUTF8String(buf, playerUsr);
		
        buf.writeInt(futureTravel);
        buf.writeInt(entityX);
        buf.writeInt(entityY);
        buf.writeInt(entityZ);

	}

}
