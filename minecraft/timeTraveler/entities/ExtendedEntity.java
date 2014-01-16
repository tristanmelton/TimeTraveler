package timeTraveler.entities;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedEntity implements IExtendedEntityProperties 
{
	/*
	 * Here I create a constant EXT_PROP_NAME for this class of properties. You
	 * need a unique name for every instance of IExtendedEntityProperties you
	 * make, and doing it at the top of each class as a constant makes it very
	 * easy to organize and avoid typos. It's easiest to keep the same constant
	 * name in every class, as it will be distinguished by the class name:
	 * ExtendedPlayer.EXT_PROP_NAME vs. ExtendedEntity.EXT_PROP_NAME
	 * 
	 * Note that a single entity can have multiple extended properties, so each
	 * property should have a unique name. Try to come up with something more
	 * unique than the tutorial example.
	 */
	public final static String EXT_PROP_NAME = "TimeTravelerUID";

	private EntityLiving entity;
	private int identifierID;

	Random rand;
	public ExtendedEntity(EntityLiving entity) 
	{
		rand = new Random();
		
		this.entity = entity;
		this.identifierID = rand.nextInt();
	}

	/**
	 * Used to register these extended properties for the player during
	 * EntityConstructing event. This method is for convenience only.
	 */
	public static final void register(EntityLiving entity)
	{
		entity.registerExtendedProperties(ExtendedEntity.EXT_PROP_NAME, new ExtendedEntity(entity));
	}

	/**
	 * Returns ExtendedEntity properties for entity. This method is for
	 * convenience only.
	 */
	public static final ExtendedEntity get(EntityLiving entity) 
	{
		return (ExtendedEntity) entity.getExtendedProperties(EXT_PROP_NAME);
	}

	// Save any custom data that needs saving here
	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		// We need to create a new tag compound that will save everything for our Extended Properties
		NBTTagCompound properties = new NBTTagCompound();

		// We only have 2 variables currently; save them both to the new tag
		properties.setInteger("identifier", this.identifierID);
		/*
		 * Now add our custom tag to the entity's tag with a unique name (our
		 * property's name). This will allow you to save multiple types of
		 * properties and distinguish between them. If you only have one type,
		 * it isn't as important, but it will still avoid conflicts between your
		 * tag names and vanilla tag names. For instance, if you add some
		 * "Items" tag, that will conflict with vanilla. Not good. So just use a
		 * unique tag name.
		 */
		compound.setTag(EXT_PROP_NAME, properties);
	}

	//Load saved data
	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
 		//Fetch the unique tag compound set for this class of Extended Properties
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		//Get data from the custom tag compound
		this.identifierID = properties.getInteger("identifier");
		//To test if it is working
		System.out.println("[TimeTraveler] Identifier from NBT: " + this.identifierID);
	}

	/**
	 * No use for this method as far as I know
	 */
	@Override
	public void init(Entity entity, World world) 
	{
		saveNBTData(entity.getEntityData());
	}
	
	public int getEntityUID()
	{
		return this.identifierID;
	}
	public void setEntityUID(int uid)
	{
		this.identifierID = uid;
		this.saveNBTData(entity.getEntityData());
	}
}