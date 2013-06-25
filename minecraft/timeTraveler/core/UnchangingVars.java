package timeTraveler.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class UnchangingVars 
{
	static List<String> entityLocationData;
	public UnchangingVars()
	{
		entityLocationData = new ArrayList<String>();
	}
	
	public List<String> getEntiyLocData()
	{
		return entityLocationData;
	}
	public void setEntityLocData(String data)
	{
		entityLocationData.add(data);
	}
	
}
