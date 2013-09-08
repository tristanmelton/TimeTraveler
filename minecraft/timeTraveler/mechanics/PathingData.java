package timeTraveler.mechanics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PathingData 
{
	/**
	 * Entity data array
	 */
	private Map<String, List<int[]>> allEntityData;
	
	public PathingData()
	{
		allEntityData = new HashMap<String, List<int[]>>();
	}
	/**
	 * Adds an entity UUID (Unique ID) to the entity data ArrayList.  If the entity already exists inside of the ArrayList, then it skips it.
	 * @param uuid
	 */
	public void addEntity(String uuid)
	{		
		if(!allEntityData.containsKey(uuid))
		{
			allEntityData.put(uuid, new ArrayList<int[]>());
		}
		else
		{
			System.out.println("ENTITY ALREADY EXISTS IN ARRAY");
		}
	}
	/**
	 * Adds data (X, Y, and Z) to the corresponding UUID (Unique ID) for the entity.  If the entity's UUID does not exist, then it prints out a line that says the UUID cannot be found.
	 * @param uuid
	 * @param data
	 */
	public void addData(String uuid, String data)
	{
		if(allEntityData.containsKey(uuid))
		{
			int[] rawData = new int[3];
			String[] pureData = data.split(",");
			
			rawData[0] = Integer.parseInt(pureData[0]);
			rawData[1] = Integer.parseInt(pureData[1]);
			rawData[2] = Integer.parseInt(pureData[2]);
			
			List<int[]> entityData = allEntityData.get(uuid);
			entityData.add(rawData);
			allEntityData.put(uuid, entityData);
		}
		else
		{
			System.out.println("ENTITY DOES NOT EXIST IN ARRAY!  :(");
		}
	}
	/**
	 * Gets the data for a specific UUID (Unique ID) for an entity.
	 * @param uuid
	 * @return
	 */
	public List<int[]> getDataForUUID(String uuid)
	{
		List<int[]> entityData = allEntityData.get(uuid);
		return entityData;
	}
	/**
	 * Clears all entities and their corresponding data from the map.
	 */
	public void clearAllEntitiesAndData()
	{
		allEntityData.clear();
	}
}
