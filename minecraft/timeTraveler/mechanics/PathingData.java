package timeTraveler.mechanics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import timeTraveler.core.EntityData;
import timeTraveler.core.StringArrayHolder;

public class PathingData 
{
	/**
	 * Entity data array
	 */
	public Map<Integer, List<EntityData>> data;
		
	public PathingData()
	{
		data = new HashMap<Integer, List<EntityData>>();
	}
	
	/**
	 * Adds entity to the Map.  Takes the Unique ID stored as "identifier" inside of the LivingEntity's NBT.  
	 * @param entityID
	 */
	public void addEntity(Integer entityID)
	{
		if(!data.containsKey(entityID))
		{
			data.put(entityID, new ArrayList<EntityData>());
		}
		else
		{
			System.out.println("ENTITY EXISTS IN ARRAY");
		}
	}
	
	/**
	 * Adds data to the entity's location inside of the Map.  Takes the Unique ID stored as "identifier" inside of the LivingEntity's NBT
	 * as the Key and the data stored in an EntityData Object is used as the Value.  The EntityData is stored inside of a List of
	 * EntityData Objects.  
	 * @param entityID
	 * @param data
	 */
	public void addData(Integer entityID, EntityData data)
	{
		if(this.data.containsKey(entityID))
		{
			List<EntityData> entityData = this.data.get(entityID);
			entityData.add(data);
			this.data.put(entityID, entityData);
		}
	}
	/**
	 * Checks if the entity exists inside of the Map.  Takes the entityID of the entity that is stored in the LivingEntity's NBT as "identifier"
	 * @param entityID
	 * @return
	 */
	public boolean doesEntityExist(Integer entityID)
	{
		if(this.data.containsKey(entityID))
		{
			return true;
		}
		return false;
	}
}
