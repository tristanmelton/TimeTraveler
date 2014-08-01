package timeTraveler.core;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import timeTraveler.mechanics.PathingData;
import timeTraveler.pasttravel.PastMechanics;

public class UnchangingVars 
{
	static String lastPastTimeSavedForWorld;
	
	static boolean nextSet;
	static boolean hasSpawnedPlayerPast;
	static boolean isInFuture;
	static boolean isPreGenerated;
	
	public static PathingData pathData;
	public static PastMechanics pMechanics;
	
	static int paradoxAmt;
	static int currentFuture;
	
	public UnchangingVars()
	{
		lastPastTimeSavedForWorld = null;
		
		nextSet = true;
		hasSpawnedPlayerPast = false;
		isInFuture = false;
		isPreGenerated = false;
		paradoxAmt = 0;
		currentFuture = 0;
		
		pathData = new PathingData();
		pMechanics = new PastMechanics();
	}
		
	public String getLastPastTimeSavedForWorld()
	{
		return lastPastTimeSavedForWorld;
	}
	public void setLastPastTimeSavedForWorld(String pastTime)
	{
		this.lastPastTimeSavedForWorld = pastTime;
	}
	public boolean getNextSet()
	{
		return nextSet;
	}
	public void setNextSet(boolean goToNextSet)
	{
		nextSet = goToNextSet;
	}
	public void setIsSpawnedPastPlayer(boolean spawn)
	{
		hasSpawnedPlayerPast = spawn;
	}
	public boolean getIsSpawnedPastPlayer()
	{
		return hasSpawnedPlayerPast;
	}
	public int getParadoxAmt()
	{
		return paradoxAmt;
	}
	public void setParadoxAmt(int paradox)
	{
		paradoxAmt = paradox;
	}
	public int getFuture()
	{
		return currentFuture;
	}
	public void setFuture(int future)
	{
		currentFuture = future;
	}
	public boolean getIsInFuture()
	{
		return isInFuture;
	}
	public void setIsInFuture(boolean future)
	{
		isInFuture = future;
	}
	public boolean getIsPreGenerated()
	{
		return isPreGenerated;
	}
	public void setIsPreGenerated(boolean gen)
	{
		isPreGenerated = gen;
	}
}
