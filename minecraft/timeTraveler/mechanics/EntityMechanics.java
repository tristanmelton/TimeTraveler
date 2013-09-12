package timeTraveler.mechanics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
 /**
  * Entity Mechanics
  * @author Charsmud
  *
  */
public class EntityMechanics
{
	public void spawnEntity(EntityLiving entity, World world, int x, int y, int z)
	{
		entity.initCreature();
		entity.posX = x;
		entity.posY = y;
		entity.posZ = z;
		world.spawnEntityInWorld(entity);
	}
	
	public void despawnAllEntities(World world)
	{
		List entitiesInWorld = world.loadedEntityList;
		
		for(int i = 0; i < entitiesInWorld.size(); i++)
		{
			if(entitiesInWorld.get(i) instanceof EntityLiving)
			{
				((EntityLiving)entitiesInWorld.get(i)).setDead();
			}
		}
	}
}
