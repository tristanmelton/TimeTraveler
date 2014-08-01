package timeTraveler.futuretravel;

import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import timeTraveler.core.TimeTraveler;

public class WorldProviderFuture extends WorldProvider
{

	@Override
	public String getDimensionName()
	{
		// TODO Auto-generated method stub
		return "Future";
	}

	@Override
	public void registerWorldChunkManager()
	{    
		this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.desertHills, 0.8F, 0.1F);  
		this.dimensionId = TimeTraveler.dimensionId; 
	}
	
	@Override
    public IChunkProvider createChunkGenerator()     
	{               
		return new ChunkProviderFuture(worldObj, worldObj.getSeed(), true);      
	}
}
