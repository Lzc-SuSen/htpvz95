package com.hungteen.pvz.entity;

import java.util.ArrayList;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.BiomeUtil;
import com.hungteen.pvz.utils.enums.Events;
import com.hungteen.pvz.world.data.WorldEventData;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class EntitySpawnHandler {

	public static final ArrayList<SpawnEntry> BUCKET_SPAWN = new ArrayList<>();
	
	public static void registerEntitySpawn() {
		BUCKET_SPAWN.add(new SpawnEntry(EntityRegister.NORMAL_ZOMBIE.get(), 60, 1, 3, EntityClassification.MONSTER, BiomeUtil.OVER_LAND));
		BUCKET_SPAWN.add(new SpawnEntry(EntityRegister.CONEHEAD_ZOMBIE.get(), 20, 1, 2, EntityClassification.MONSTER, BiomeUtil.OVER_LAND));
		BUCKET_SPAWN.add(new SpawnEntry(EntityRegister.POLE_ZOMBIE.get(), 20, 1, 1, EntityClassification.MONSTER, BiomeUtil.OVER_LAND));
		BUCKET_SPAWN.add(new SpawnEntry(EntityRegister.BUCKETHEAD_ZOMBIE.get(), 5, 1, 1, EntityClassification.MONSTER, BiomeUtil.OVER_LAND));
	}
	
	public static ArrayList<SpawnEntry> getEventSpawnList(Events ev){
		switch(ev) {
		case BUCKET:return BUCKET_SPAWN;
		default:{
			PVZMod.LOGGER.debug("No Event Spawn Error!");
			return null;
		}
		}
	}
	
	public static void updateEventSpawns(World world){
		WorldEventData data = WorldEventData.getOverWorldEventData(world);
		System.out.println("update data");
		for(Events event:Events.values()) {
			if(data.hasEvent(event)) {
				System.out.println(event);
				addEventSpawns(event);
			}
		}
	}
	
	public static void addEventSpawns(Events event) {
        ArrayList<SpawnEntry> spawnList = getEventSpawnList(event);
        if(spawnList==null) {
        	return ;
        }
        for (SpawnEntry entry : spawnList) {
            BiomeUtil.addSpawn(entry.entityType, entry.weight, entry.minGroupSize, entry.maxGroupSize, entry.creatureType, entry.biomes);
        }
    }

    public static void removeEventSpawns(Events event) {
    	ArrayList<SpawnEntry> spawnList = getEventSpawnList(event);
    	if(spawnList==null) {
    		return ;
    	}
        for (SpawnEntry entry : spawnList) {
            BiomeUtil.removeSpawn(entry.entityType, entry.creatureType, entry.biomes);
        }
    }
    
	private static class SpawnEntry {
		private final EntityType<? extends Entity> entityType;
		private final int weight;
		private final int minGroupSize;
		private final int maxGroupSize;
		private final EntityClassification creatureType;
		private final Biome[] biomes;

		private SpawnEntry(EntityType<? extends Entity> type, int weight, int minGroupSize, int maxGroupSize,
				EntityClassification creatureType, Biome... biomes) {
			this.entityType = type;
			this.weight = weight;
			this.minGroupSize = minGroupSize;
			this.maxGroupSize = maxGroupSize;
			this.creatureType = creatureType;
			this.biomes = biomes;
		}
	}
}
