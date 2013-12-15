package timeTraveler.core;

import java.io.Serializable;
import java.util.Arrays;

public class EntityData implements Serializable
{
    private final String[] data;

    public EntityData(String[] data) 
    {
        this.data = data.clone();
    }
    public String[] getData() 
    {
        return this.data.clone();
    }
    @Override
    public int hashCode()
    {
        return Arrays.hashCode(data);
    }
    @Override
    public boolean equals(Object o)
    {
        if (o == null) 
        {
        	System.out.println("NULL");
        	return false;
        }
        if (o == this)
        {
        	System.out.println("THIS");
        	return true;
        }
        
        if (o instanceof StringArrayHolder) 
        {
        	StringArrayHolder other = (StringArrayHolder)o;
            return Arrays.equals(this.data, other.getData());
        }
        return false;
    }
    @Override
    public String toString() 
    {
        return Arrays.deepToString(data);
    }
}
