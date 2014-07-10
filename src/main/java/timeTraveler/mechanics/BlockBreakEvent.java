package timeTraveler.mechanics;

import net.minecraftforge.event.Event;



public class BlockBreakEvent extends Event
{
    public int xCoord;
    public int yCoord;
    public int zCoord;

    public BlockBreakEvent(int x, int y, int z)
    {
        this.xCoord = x;
        this.yCoord = y;
        this.zCoord = z;
        System.out.println("**TIMETRAVELER - BlockBreakEvent works!");
    }
}
