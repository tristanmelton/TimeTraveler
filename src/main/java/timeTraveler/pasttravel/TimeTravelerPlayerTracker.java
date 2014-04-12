package timeTraveler.pasttravel;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.relauncher.Side;
import java.util.List;

import timeTraveler.core.TimeTraveler;

import net.minecraft.entity.player.EntityPlayer;

public class TimeTravelerPlayerTracker implements IPlayerTracker 
{
	public void onPlayerLogin(EntityPlayer player) 
	{
		
	}

	public void onPlayerLogout(EntityPlayer player)
	{
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if (side == Side.SERVER) 
		{
			List<PastAction> aList = TimeTraveler.instance.getActionListForPlayer(player);
			if (aList != null)
			{
				PastAction ma = new PastAction((byte) 6);
				aList.add(ma);
			}
		}
	}

	public void onPlayerChangedDimension(EntityPlayer player) 
	{
	}

	public void onPlayerRespawn(EntityPlayer player) 
	{
	}
}
