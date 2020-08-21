package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.entity.bullet.PeaEntity;
import com.hungteen.pvz.entity.bullet.PotatoEntity;
import com.hungteen.pvz.entity.drop.CoinEntity;
import com.hungteen.pvz.entity.drop.EnergyEntity;
import com.hungteen.pvz.entity.drop.SunEntity;
import com.hungteen.pvz.entity.plant.appease.PeaShooterEntity;
import com.hungteen.pvz.entity.plant.appease.RepeaterEntity;
import com.hungteen.pvz.entity.plant.defence.WallNutEntity;
import com.hungteen.pvz.entity.plant.explosion.CherryBombEntity;
import com.hungteen.pvz.entity.plant.explosion.PotatoMineEntity;
import com.hungteen.pvz.entity.plant.ice.SnowPeaEntity;
import com.hungteen.pvz.entity.plant.light.SunFlowerEntity;
import com.hungteen.pvz.entity.zombie.grassday.BucketHeadZombieEntity;
import com.hungteen.pvz.entity.zombie.grassday.ConeHeadZombieEntity;
import com.hungteen.pvz.entity.zombie.grassday.FlagZombieEntity;
import com.hungteen.pvz.entity.zombie.grassday.NormalZombieEntity;
import com.hungteen.pvz.entity.zombie.grassday.PoleZombieEntity;
import com.hungteen.pvz.render.entity.bullet.PeaRender;
import com.hungteen.pvz.render.entity.bullet.PotatoRender;
import com.hungteen.pvz.render.entity.drop.CoinRender;
import com.hungteen.pvz.render.entity.drop.EnergyRender;
import com.hungteen.pvz.render.entity.drop.SunRender;
import com.hungteen.pvz.render.entity.plant.appease.PeaShooterRender;
import com.hungteen.pvz.render.entity.plant.appease.RepeaterRender;
import com.hungteen.pvz.render.entity.plant.defence.WallNutRender;
import com.hungteen.pvz.render.entity.plant.explosion.CherryBombRender;
import com.hungteen.pvz.render.entity.plant.explosion.PotatoMineRender;
import com.hungteen.pvz.render.entity.plant.ice.SnowPeaRender;
import com.hungteen.pvz.render.entity.plant.light.SunFlowerRender;
import com.hungteen.pvz.render.entity.zombie.grassday.BucketHeadZombieRender;
import com.hungteen.pvz.render.entity.zombie.grassday.ConeHeadZombieRender;
import com.hungteen.pvz.render.entity.zombie.grassday.FlagZombieRender;
import com.hungteen.pvz.render.entity.zombie.grassday.NormalZombieRender;
import com.hungteen.pvz.render.entity.zombie.grassday.PoleZombieRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityType.IFactory;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID,bus=Mod.EventBusSubscriber.Bus.MOD)
public class EntityRegister {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, PVZMod.MOD_ID);
    
	//entitytype
	//drop
	public static final RegistryObject<EntityType<SunEntity>> SUN = registerEntityType(SunEntity::new, "sun", EntityClassification.MISC);
	public static final RegistryObject<EntityType<CoinEntity>> COIN = registerEntityType(CoinEntity::new, "coin", EntityClassification.MISC);
	public static final RegistryObject<EntityType<EnergyEntity>> ENERGY = registerEntityType(EnergyEntity::new, "energy", EntityClassification.MISC,0.9f,2f);
	
	//bullet
	public static final RegistryObject<EntityType<PeaEntity>> PEA = registerEntityType(PeaEntity::new, "pea", EntityClassification.MISC);
	public static final RegistryObject<EntityType<PotatoEntity>> POTATO = registerEntityType(PotatoEntity::new, "potato", EntityClassification.MISC);
	
	//zombie 
	public static final RegistryObject<EntityType<NormalZombieEntity>> NORMAL_ZOMBIE = registerEntityType(NormalZombieEntity::new, "normal_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<FlagZombieEntity>> FLAG_ZOMBIE = registerEntityType(FlagZombieEntity::new, "flag_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<ConeHeadZombieEntity>> CONEHEAD_ZOMBIE = registerEntityType(ConeHeadZombieEntity::new, "conehead_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<PoleZombieEntity>> POLE_ZOMBIE = registerEntityType(PoleZombieEntity::new, "pole_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<BucketHeadZombieEntity>> BUCKETHEAD_ZOMBIE = registerEntityType(BucketHeadZombieEntity::new, "buckethead_zombie", EntityClassification.MONSTER);
	
	//plant
	public static final RegistryObject<EntityType<PeaShooterEntity>> PEA_SHOOTER = registerEntityType(PeaShooterEntity::new, "pea_shooter", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<SunFlowerEntity>> SUN_FLOWER = registerEntityType(SunFlowerEntity::new, "sun_flower", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<CherryBombEntity>> CHERRY_BOMB = registerEntityType(CherryBombEntity::new, "cherry_bomb", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<WallNutEntity>> WALL_NUT = registerEntityType(WallNutEntity::new, "wall_nut", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<PotatoMineEntity>> POTATO_MINE = registerEntityType(PotatoMineEntity::new, "potato_mine", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<SnowPeaEntity>> SNOW_PEA = registerEntityType(SnowPeaEntity::new, "snow_pea", EntityClassification.CREATURE);
	
	public static final RegistryObject<EntityType<RepeaterEntity>> REPEATER = registerEntityType(RepeaterEntity::new, "repeater", EntityClassification.CREATURE);
	
	@SubscribeEvent
    public static void onClientSetUpEvent(FMLClientSetupEvent event) {
		//drop
        RenderingRegistry.registerEntityRenderingHandler(SUN.get(), SunRender::new);
        RenderingRegistry.registerEntityRenderingHandler(COIN.get(), CoinRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ENERGY.get(), EnergyRender::new);
        
        //bullet
        RenderingRegistry.registerEntityRenderingHandler(PEA.get(), PeaRender::new);
        RenderingRegistry.registerEntityRenderingHandler(POTATO.get(), PotatoRender::new);
        
        //zombie
        RenderingRegistry.registerEntityRenderingHandler(NORMAL_ZOMBIE.get(), NormalZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(FLAG_ZOMBIE.get(), FlagZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(CONEHEAD_ZOMBIE.get(), ConeHeadZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(POLE_ZOMBIE.get(), PoleZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(BUCKETHEAD_ZOMBIE.get(), BucketHeadZombieRender::new);
        
        //plant
        RenderingRegistry.registerEntityRenderingHandler(PEA_SHOOTER.get(), PeaShooterRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SUN_FLOWER.get(), SunFlowerRender::new);
        RenderingRegistry.registerEntityRenderingHandler(CHERRY_BOMB.get(), CherryBombRender::new);
        RenderingRegistry.registerEntityRenderingHandler(WALL_NUT.get(), WallNutRender::new);
        RenderingRegistry.registerEntityRenderingHandler(POTATO_MINE.get(), PotatoMineRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SNOW_PEA.get(), SnowPeaRender::new);
        
        RenderingRegistry.registerEntityRenderingHandler(REPEATER.get(), RepeaterRender::new);
    }

	private static <T extends Entity> RegistryObject<EntityType<T>> registerEntityType(IFactory<T> factory,String name,EntityClassification classification)
	{
		return ENTITY_TYPES.register(name, () -> {return EntityType.Builder.create(factory, classification).build(StringUtil.prefix(name).toString());});
	}
	
	private static <T extends Entity> RegistryObject<EntityType<T>> registerEntityType(IFactory<T> factory,String name,EntityClassification classification,float w,float h)
	{
		return ENTITY_TYPES.register(name, () -> {return EntityType.Builder.create(factory, classification).size(w, h).build(StringUtil.prefix(name).toString());});
	}
}
