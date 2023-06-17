package com.hungteen.pvz.common.entity.plant.assist;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.ai.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.ai.goal.target.PVZTargetGoal;
import com.hungteen.pvz.common.entity.bullet.itembullet.MetalItemEntity;
import com.hungteen.pvz.common.entity.bullet.itembullet.MetalItemEntity.MetalStates;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.grass.FootballZombieEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.remove.MetalTypes;
import com.hungteen.pvz.utils.AlgorithmUtil;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.PAZAlmanacs;
import com.hungteen.pvz.utils.interfaces.IHasMetal;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MagnetShroomEntity extends PVZPlantEntity {

	private static final DataParameter<Integer> METAL_TYPE = EntityDataManager.defineId(MagnetShroomEntity.class,
			DataSerializers.INT);
	
	public MagnetShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		entityData.define(METAL_TYPE, MetalTypes.EMPTY.ordinal());
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, false, this.getAbsorbRange(), this.getAbsorbRange()));
		this.targetSelector.addGoal(1, new TargetLadderGoal(this, true, false, this.getAbsorbRange()));
	}
	
	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(! level.isClientSide) {
			if(this.getAttackTime() > 0) {
				this.setAttackTime(this.getAttackTime() - 1);
			} else if(this.getAttackTime() == 0) {
				this.setMetalType(MetalTypes.EMPTY);
			}
			LivingEntity target = this.getTarget();
			if(EntityUtil.isEntityValid(target) && this.isMagnetActive()) {
				this.dragMetal(target);
			}
		}
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		final float range = this.getAbsorbRange();
		int cnt = this.getSuperDragCnt();
		EntityUtil.playSound(this, SoundRegister.MAGNET.get());
		for(LivingEntity target : level.getEntitiesOfClass(LivingEntity.class, EntityUtil.getEntityAABB(this, range, range), (entity) -> {
			return this.checkCanPAZTarget(entity);
		})){
			if(! (target instanceof IHasMetal)) continue;
			((IHasMetal) target).decreaseMetal();
			MetalItemEntity metal = new MetalItemEntity(level, this, ((IHasMetal) target).getMetalType());
			metal.setMetalState(MetalStates.BULLET);
			metal.setPos(target.getX(), target.getY() + target.getEyeHeight(), target.getZ());
			metal.summonByOwner(this);
			metal.setAttackDamage(this.getAttackDamage());
			level.addFreshEntity(metal);
			if(-- cnt == 0) return ;
		};
	}
	
	/**
	 * {@link #normalPlantTick()}
	 */
	public void dragMetal(LivingEntity target) {
		if(target instanceof IHasMetal) {
			((IHasMetal) target).decreaseMetal();
			this.setAttackTime(this.getWorkCD());
			MetalItemEntity metal = new MetalItemEntity(level, this, ((IHasMetal) target).getMetalType());
			metal.setPos(target.getX(), target.getY() + target.getEyeHeight(), target.getZ());
			metal.summonByOwner(this);
			level.addFreshEntity(metal);
			EntityUtil.playSound(this, SoundRegister.MAGNET.get());
		} else {
			PVZMod.LOGGER.warn("Wrong target for MagnetShroom.");
		}
	}
	
	public int getAttackDamage() {
		return 100;
	}
	
	public int getSuperDragCnt() {
		return 4;
	}

	@Override
	public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
		super.addAlmanacEntries(list);
		list.addAll(Arrays.asList(
				Pair.of(PAZAlmanacs.WORK_CD, this.getWorkCD()),
				Pair.of(PAZAlmanacs.WORK_RANGE, this.getAbsorbRange())
		));
	}

	public float getAbsorbRange(){
		return 15;
	}

	public int getWorkCD() {
		return (int) this.getSkillValue(SkillTypes.LESS_WORK_CD);
	}
	
	/**
	 * is not consuming metal.
	 */
	public boolean isMagnetActive() {
		return this.getAttackTime() == 0;
	}
	
	public ItemStack getMetalRenderItem() {
		if(this.getMetalType() == null) return ItemStack.EMPTY;
		return new ItemStack(MetalTypes.getMetalItem(getMetalType()));
	}
	//不能吸橄榄球僵尸
	@Override
	public boolean canPAZTarget(Entity entity) {
		return entity instanceof IHasMetal && ((IHasMetal) entity).hasMetal()&&!(entity instanceof FootballZombieEntity);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("metal_type", this.getMetalType().ordinal());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("metal_type")) {
			this.setMetalType(MetalTypes.values()[compound.getInt("metal_type")]);
		}
	}

	public MetalTypes getMetalType() {
		return MetalTypes.values()[entityData.get(METAL_TYPE)];
	}

	public void setMetalType(MetalTypes type) {
		entityData.set(METAL_TYPE, type.ordinal());
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.5f, 1.3f);
	}
	
	@Override
	public int getSuperTimeLength() {
		return 60;
	}
	
	@Override
	public IPlantType getPlantType() {
		return PVZPlants.MAGNET_SHROOM;
	}
	
	private static class TargetLadderGoal extends PVZTargetGoal {

		protected final AlgorithmUtil.EntitySorter sorter;

		public TargetLadderGoal(MagnetShroomEntity mobIn, boolean checkSight, boolean mustReach, float range) {
			super(mobIn, checkSight, mustReach, range, range);
			this.sorter = new AlgorithmUtil.EntitySorter(mob);
		}

		@Override
		public boolean canUse() {
			if (this.targetChance > 0 && this.mob.getRandom().nextInt(this.targetChance) != 0) {
				return false;
			}
			List<LivingEntity> list1 = new ArrayList<LivingEntity>();
			this.mob.level.getEntitiesOfClass(PVZPlantEntity.class, getAABB()).forEach(plant -> {
				if(! EntityUtil.canTargetEntity(mob, plant) && this.checkSenses(plant)) {
					if(plant.hasMetal()) {
						list1.add(plant);
					}
				}
			});
			if (list1.isEmpty()) {
				return false;
			}
			Collections.sort(list1, this.sorter);
			this.targetMob = list1.get(0);
			return true;
		}

		@Override
		public boolean canContinueToUse() {
			LivingEntity entity = this.mob.getTarget();
			if (entity == null) {
				entity = this.targetMob;
			}
			if(entity == null || ! entity.isAlive()) {
				return false;
			}
			if(! EntityUtil.canTargetEntity(mob, entity) && this.checkSenses(entity)) {
				this.mob.setTarget(entity);
				return true;
			}
			return false;
		}

	}

}
