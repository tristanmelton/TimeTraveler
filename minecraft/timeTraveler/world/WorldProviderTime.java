package timeTraveler.world;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import timeTraveler.core.TimeTraveler;

public class WorldProviderTime extends WorldProvider{

	@Override
	public String getDimensionName()
	{
		return "Time";
	}
	@Override
	public boolean canRespawnHere()
	{
		return false;
	}
	@Override
	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.beach, 0.8F, 0.1F);
		this.dimensionId = TimeTraveler.dimensionID;
	}
	@Override
	 public IChunkProvider createChunkGenerator()
	 {
		return new ChunkProviderTime(worldObj, worldObj.getSeed(), true);
	 }



}
