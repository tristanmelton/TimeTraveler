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
	public static PathingData pathData;
	public static PastMechanics pMechanics;
	static int paradoxAmt;
	
	public UnchangingVars()
	{
		lastPastTimeSavedForWorld = null;
		hasSpawnedPlayerPast = false;
		nextSet = true;
		pathData = new PathingData();
		pMechanics = new PastMechanics();
		paradoxAmt = 0;
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
		this.paradoxAmt = paradox;
	}
}
