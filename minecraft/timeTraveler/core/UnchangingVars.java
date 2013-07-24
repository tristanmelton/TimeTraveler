package timeTraveler.core;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class UnchangingVars 
{
	static List<String> entityLocationData;
	static String pastTime;
	
	public UnchangingVars()
	{
		entityLocationData = new ArrayList<String>();
		pastTime = null;
	}
	
	public List<String> getEntiyLocData()
	{
		return entityLocationData;
	}
	public void setEntityLocData(String data)
	{
		entityLocationData.add(data);
	}
	
	public String getPastTime()
	{
		return pastTime;
	}
	
	public void setPastTime(String pastTime)
	{
		this.pastTime = pastTime;
	}
}
