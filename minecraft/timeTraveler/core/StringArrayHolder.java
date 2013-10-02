package timeTraveler.core;

import java.util.Arrays;

public class StringArrayHolder
{
    private final String[] data;
    //I do not want any client could change the array reference
    //this also explains why this field doesn't have a setter
    public StringArrayHolder(String[] data) 
    {
        this.data = data;
    }
    public String[] getData() 
    {
        return this.data;
    }
    @Override
    public int hashCode()
    {
        return Arrays.hashCode(data);
    }
    @Override
    public boolean equals(Object o)
    {
        if (o == null) return false;
        if (o == this) return true;
        
        if (o instanceof StringArrayHolder) 
        {
        	StringArrayHolder other = (StringArrayHolder)o;
            return Arrays.equals(this.data, other.data);
        }
        return false;
    }
    //just to print in console for testing purposes
    @Override
    public String toString() 
    {
        return Arrays.deepToString(data);
    }
}
