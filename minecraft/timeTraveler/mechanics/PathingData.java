package timeTraveler.mechanics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.minecraft.entity.EntityLiving;

public class PathingData 
{
	/**
	 * Entity data array
	 */
	public static Map<String[], List<int[]>> allEntityData;
	
	public PathingData()
	{
		allEntityData = new HashMap<String[], List<int[]>>();
	}
	/**
	 * Adds an entity UUID (Unique ID) to the entity data ArrayList.  If the entity already exists inside of the ArrayList, then it skips it.
	 * @param uuid
	 */
	public void addEntity(String[] entityData)
	{		
		System.out.println(entityData[0]);
		if(!allEntityData.containsKey(entityData))
		{
			allEntityData.put(entityData, new ArrayList<int[]>());
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
	public void addData(String[] entityData, String data)
	{
		System.out.println(entityData[0]);
		if(allEntityData.containsKey(entityData))
		{
			int[] rawData = new int[3];
			String[] pureData = data.split(",");
			
			rawData[0] = Integer.parseInt(pureData[0]);
			rawData[1] = Integer.parseInt(pureData[1]);
			rawData[2] = Integer.parseInt(pureData[2]);
			
			List<int[]> entityLocData = allEntityData.get(entityData);
			entityLocData.add(rawData);
			allEntityData.put(entityData, entityLocData);
		}
		else
		{
			System.out.println("ENTITY DOES NOT EXIST IN ARRAY! :(");
			//addEntity(entityData);
		}
	}
	/**
	 * Gets the data for a specific UUID (Unique ID) for an entity.
	 * @param uuid
	 * @return
	 */
	public List<int[]> getDataForUUID(String[] entityData)
	{
		List<int[]> entityLoc = allEntityData.get(entityData);
		return entityLoc;
	}
	/**
	 * Clears all entities and their corresponding data from the map.
	 */
	public void clearAllEntitiesAndData()
	{
		allEntityData.clear();
	}
	
	/**
	 * Checks if entity exists inside of array
	 * @param uuid
	 * @return
	 */
	public boolean doesEntityExist(String[] entityData)
	{
		List<int[]> entityLoc = allEntityData.get(entityData);
		if(entityData != null)
		{
			return true;
		}
		return false;
	}
}
