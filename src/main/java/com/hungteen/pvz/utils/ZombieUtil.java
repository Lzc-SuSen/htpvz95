package com.hungteen.pvz.utils;

import java.util.HashMap;
import java.util.Optional;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.grassnight.DancingZombieEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.enums.Ranks;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;

public class ZombieUtil {
	
    // zombies speed
	public static final float WALK_SUPER_SLOW = 0.15F;
	public static final float WALK_HUGE_SLOW = 0.16F; 
	public static final float WALK_VERY_SLOW = 0.17F; 
	public static final float WALK_SLOW = 0.18F;
	public static final float WALK_LITTLE_SLOW = 0.19F; 
	public static final float WALK_NORMAL = 0.20F; 
	public static final float WALK_LITTLE_FAST = 0.225F;
	public static final float WALK_FAST = 0.25F; 
	public static final float WALK_VERY_FAST = 0.275F; 
	public static final float WALK_HUGE_FAST = 0.3F; 
	public static final float WALK_SUPER_FAST = 0.325F; 
	public static final float WATER_FAST = 0.8F;
	public static final float FLY_FAST = 0.5F;

	// zombies attackDamage
	public static final float VERY_LOW = 4; 
	public static final float LOW = 6;
	public static final float LITTLE_LOW = 8;
	public static final float NORMAL_DAMAGE = 10; 
	public static final float LITTLE_HIGH = 20; 
	public static final float HIGH = 30; 
	public static final float VERY_HIGH = 50;
	public static final float HUGE_HIGH = 100; 
	public static final float SUPER_HIGH = 200; 
	public static final float GIANT_HIT = 1000; 
	public static final float BOSS_HIT = 2000; 

	// range
	public static final float NORMAL_FOLLOW_RANGE = 50;
	public static final float CLOSE_TARGET_RANGE = 40;
	public static final float LITTLW_CLOSE_TARGET_RANGE = 50;
	public static final float NORMAL_TARGET_RANGE = 60;
	public static final float LOW_TARGET_HEIGHT = 20;
	public static final float NORMAL_TARGET_HEIGHT = 30;
	public static final float LITTLE_HIGH_TARGET_HEIGHT = 40;
	public static final float HIGH_TARGET_HEIGHT = 50;
	
	public static final HashMap<Zombies, Ranks> ZOMBIE_RANK = new HashMap<>();
	public static final HashMap<EntityType<? extends PVZZombieEntity>, Zombies> ENTITY_TYPE_ZOMBIE = new HashMap<>();
	public static final HashMap<Zombies, RegistryObject<? extends EntityType<? extends PVZZombieEntity>>> ZOMBIE_ENTITY = new HashMap<>();
    public static int ZOMBIE_NUM = 0;
	
	static {
		//grass day
		putZombieInfoToMap(Zombies.NORMAL_ZOMBIE, Ranks.WHITE, EntityRegister.NORMAL_ZOMBIE);
		putZombieInfoToMap(Zombies.FLAG_ZOMBIE, Ranks.WHITE, EntityRegister.FLAG_ZOMBIE);
		putZombieInfoToMap(Zombies.CONEHEAD_ZOMBIE, Ranks.GREEN, EntityRegister.CONEHEAD_ZOMBIE);
		putZombieInfoToMap(Zombies.POLE_ZOMBIE, Ranks.GREEN, EntityRegister.POLE_ZOMBIE);
		putZombieInfoToMap(Zombies.BUCKETHEAD_ZOMBIE, Ranks.BLUE, EntityRegister.BUCKETHEAD_ZOMBIE);
		//grass night
		putZombieInfoToMap(Zombies.NEWSPAPER_ZOMBIE, Ranks.WHITE, EntityRegister.NEWSPAPER_ZOMBIE);
		putZombieInfoToMap(Zombies.TOMB_STONE, Ranks.GRAY, EntityRegister.TOMB_STONE);
		putZombieInfoToMap(Zombies.SCREENDOOR_ZOMBIE, Ranks.WHITE, EntityRegister.SCREENDOOR_ZOMBIE);
		putZombieInfoToMap(Zombies.FOOTBALL_ZOMBIE, Ranks.BLUE, EntityRegister.FOOTBALL_ZOMBIE);
		putZombieInfoToMap(Zombies.DANCING_ZOMBIE, Ranks.GREEN, EntityRegister.DANCING_ZOMBIE);
		putZombieInfoToMap(Zombies.BACKUP_DANCER, Ranks.WHITE, EntityRegister.BACKUP_DANCER);
		putZombieInfoToMap(Zombies.GIGA_FOOTBALL_ZOMBIE, Ranks.GOLD, EntityRegister.GIGA_FOOTBALL_ZOMBIE);
		putZombieInfoToMap(Zombies.OLD_ZOMBIE, Ranks.BLUE, EntityRegister.OLD_ZOMBIE);
		putZombieInfoToMap(Zombies.SUNDAY_EDITION_ZOMBIE, Ranks.GOLD, EntityRegister.SUNDAY_EDITION_ZOMBIE);
		putZombieInfoToMap(Zombies.COFFIN, Ranks.GOLD, EntityRegister.COFFIN);
		putZombieInfoToMap(Zombies.MOURNER_ZOMBIE, Ranks.GREEN, EntityRegister.MOURNER_ZOMBIE);
		putZombieInfoToMap(Zombies.NOBLE_ZOMBIE, Ranks.MEGA, EntityRegister.NOBLE_ZOMBIE);
		//pool day
		putZombieInfoToMap(Zombies.SNORKEL_ZOMBIE, Ranks.WHITE, EntityRegister.SNORKEL_ZOMBIE);
		putZombieInfoToMap(Zombies.ZOMBONI, Ranks.BLUE, EntityRegister.ZOMBONI);
		putZombieInfoToMap(Zombies.BOBSLE_TEAM, Ranks.GREEN, EntityRegister.BOBSLE_TEAM);
		putZombieInfoToMap(Zombies.BOBSLE_ZOMBIE, Ranks.WHITE, EntityRegister.BOBSLE_ZOMBIE);
		putZombieInfoToMap(Zombies.DOLPHIN_RIDER, Ranks.GREEN, EntityRegister.DOLPHIN_RIDER);
		putZombieInfoToMap(Zombies.DOLPHIN_RIDER_ZOMBIE, Ranks.WHITE, EntityRegister.DOLPHIN_RIDER_ZOMBIE);
		putZombieInfoToMap(Zombies.ZOMBIE_DOLPHIN, Ranks.GRAY, EntityRegister.ZOMBIE_DOLPHIN);
		putZombieInfoToMap(Zombies.LAVA_ZOMBIE, Ranks.PURPLE, EntityRegister.LAVA_ZOMBIE);
		//pool night
		putZombieInfoToMap(Zombies.JACK_IN_BOX_ZOMBIE, Ranks.GREEN, EntityRegister.JACK_IN_BOX_ZOMBIE);
		putZombieInfoToMap(Zombies.BALLOON_ZOMBIE, Ranks.WHITE, EntityRegister.BALLOON_ZOMBIE);
		putZombieInfoToMap(Zombies.DIGGER_ZOMBIE, Ranks.GREEN, EntityRegister.DIGGER_ZOMBIE);
		putZombieInfoToMap(Zombies.POGO_ZOMBIE, Ranks.GREEN, EntityRegister.POGO_ZOMBIE);
		putZombieInfoToMap(Zombies.YETI_ZOMBIE, Ranks.BLUE, EntityRegister.YETI_ZOMBIE);
		//roof
		putZombieInfoToMap(Zombies.BUNGEE_ZOMBIE, Ranks.GREEN, EntityRegister.BUNGEE_ZOMBIE);
		putZombieInfoToMap(Zombies.LADDER_ZOMBIE, Ranks.GREEN, EntityRegister.LADDER_ZOMBIE);
		putZombieInfoToMap(Zombies.CATAPULT_ZOMBIE, Ranks.BLUE, EntityRegister.CATAPULT_ZOMBIE);
		putZombieInfoToMap(Zombies.GARGANTUAR, Ranks.PURPLE, EntityRegister.GARGANTUAR);
		putZombieInfoToMap(Zombies.IMP, Ranks.GRAY, EntityRegister.IMP);
		putZombieInfoToMap(Zombies.SAD_GARGANTUAR, Ranks.GOLD, EntityRegister.SAD_GARGANTUAR);
		putZombieInfoToMap(Zombies.ZOMBOSS, Ranks.MEGA, EntityRegister.ZOMBOSS);
		//plant zombie
		putZombieInfoToMap(Zombies.PEASHOOTER_ZOMBIE, Ranks.WHITE, EntityRegister.PEASHOOTER_ZOMBIE);
		putZombieInfoToMap(Zombies.WALLNUT_ZOMBIE, Ranks.BLUE, EntityRegister.WALLNUT_ZOMBIE);
		putZombieInfoToMap(Zombies.GATLINGPEA_ZOMBIE, Ranks.WHITE, EntityRegister.GATLINGPEA_ZOMBIE);
		putZombieInfoToMap(Zombies.TALLNUT_ZOMBIE, Ranks.PURPLE, EntityRegister.TALLNUT_ZOMBIE);
		putZombieInfoToMap(Zombies.SQUASH_ZOMBIE, Ranks.WHITE, EntityRegister.SQUASH_ZOMBIE);
		putZombieInfoToMap(Zombies.JALAPENO_ZOMBIE, Ranks.GREEN, EntityRegister.JALAPENO_ZOMBIE);
		putZombieInfoToMap(Zombies.PUMPKIN_ZOMBIE, Ranks.PURPLE, EntityRegister.PUMPKIN_ZOMBIE);
		//other
		putZombieInfoToMap(Zombies.TRICK_ZOMBIE, Ranks.GRAY, EntityRegister.TRICK_ZOMBIE);
		putZombieInfoToMap(Zombies.RA_ZOMBIE, Ranks.WHITE, EntityRegister.RA_ZOMBIE);
		putZombieInfoToMap(Zombies.GIGA_TOMB_STONE, Ranks.GRAY, EntityRegister.GIGA_TOMB_STONE);
	}
	
	/**
	 * init zombie map that must after RegistryObject present.
	 */
	public static void initZombieMap() {
		for(Zombies zombie : Zombies.values()) {
			EntityType<? extends PVZZombieEntity> type = getZombieEntityType(zombie);
			ENTITY_TYPE_ZOMBIE.put(type, zombie);
		}
	}
	
	public static void putZombieInfoToMap(Zombies zombie, Ranks rank, RegistryObject<? extends EntityType<? extends PVZZombieEntity>> type) {
		ZOMBIE_RANK.put(zombie, rank);
		ZOMBIE_ENTITY.put(zombie, type);
		++ ZOMBIE_NUM;
	}
	
	public static PVZZombieEntity getZombieEntity(World world, Zombies zombie) {
		if(ZOMBIE_ENTITY.containsKey(zombie)) {
			return ZOMBIE_ENTITY.get(zombie).get().create(world);
		}
		PVZMod.LOGGER.debug("Zombie get entity error");
		return null;
	}
	
	public static EntityType<? extends PVZZombieEntity> getZombieEntityType(Zombies zombie) {
		if(ZOMBIE_ENTITY.containsKey(zombie)) {
			return ZOMBIE_ENTITY.get(zombie).get();
		}
		System.out.println("Zombie get entity error");
		return null;
	}

	public static Ranks getZombieRank(Zombies zombie){
		if(ZOMBIE_RANK.containsKey(zombie)) {
			return ZOMBIE_RANK.get(zombie);
		}
		PVZMod.LOGGER.debug("Zombie get rank error");
		return null;
	}
	
	public static int getZombieXp(Zombies zombie){
		Ranks rank = getZombieRank(zombie);
		switch(rank) {
		case GRAY:return 1;
		case WHITE:return 2;
		case GREEN:return 3;
		case BLUE:return 5;
		case PURPLE:return 8;
		case GOLD:return 15;
		case MEGA:return 50;
		default:return 0;
		}
	}
	
	/**
	 * copy some data to new zombie ,which is necessary to implement.
	 * {@link DancingZombieEntity#summonEmptyDancers()}
	 */
	public static void copySummonZombieData(PVZZombieEntity old, PVZZombieEntity now) {
		now.setCharmed(old.isCharmed());
		now.setMiniZombie(old.isMiniZombie());
	}
	
	/**
	 * use by @PVZZombieEntity to check spawn.
	 */
	public static Optional<Zombies> getZombieNameByType(EntityType<? extends PVZZombieEntity> zombieType){
		return Optional.ofNullable(ZombieUtil.ENTITY_TYPE_ZOMBIE.get(zombieType));
	}
	
	public static int caculateZombieXp(PVZZombieEntity zombie) {
		int lvlBonus = zombie.getZombieLevel() / 2;
		int rankBonus = zombie.getZombieRank().ordinal() / 2;
		return rankBonus + lvlBonus + 3;
	}
	
}
