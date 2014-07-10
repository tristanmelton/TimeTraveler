package timeTraveler.mechanics;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;

public class LivingPlaceBlockEvent extends LivingEvent
{
    public ItemStack theItem;
    public int xCoord;
    public int yCoord;
    public int zCoord;

    public LivingPlaceBlockEvent(EntityLivingBase entity, ItemStack theItem, int x, int y, int z)
    {
        super(entity);
        this.theItem = theItem;
        this.xCoord = x;
        this.yCoord = y;
        this.zCoord = z;
        System.out.println("**TIMETRAVELER - LivingPlaceBlockEvent works!");
    }
}
