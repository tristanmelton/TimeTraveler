package timeTraveler.mechanics;

import net.minecraft.server.MinecraftServer;

public class ClientMethods
{
	public static boolean isSinglePlayer()
	{
		try
		{
			if(MinecraftServer.getServer().isServerRunning())
			{
				return true;
			}
			return false;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
}
