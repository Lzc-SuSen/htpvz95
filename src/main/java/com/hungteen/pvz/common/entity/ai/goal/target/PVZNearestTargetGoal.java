package com.hungteen.pvz.common.entity.ai.goal.target;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import com.hungteen.pvz.utils.AlgorithmUtil;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.util.math.AxisAlignedBB;

public class PVZNearestTargetGoal extends TargetGoal {

	protected final AlgorithmUtil.EntitySorter sorter;
	protected final boolean longMemory;
	private final int targetChance;
	private final float upperHeight;
	private final float lowerHeight;
	private final float width;

	public PVZNearestTargetGoal(MobEntity mobIn, boolean checkSight, boolean memory, float w, float h) {
		this(mobIn, checkSight, memory, 5, w, h);
	}

	public PVZNearestTargetGoal(MobEntity mobIn, boolean checkSight, boolean memory, int chance, float w, float h) {
		this(mobIn, checkSight, memory, chance, w, h, h);
	}

	public PVZNearestTargetGoal(MobEntity mobIn, boolean checkSight, boolean memory, int chance, float w, float h1, float h2) {
		super(mobIn, checkSight);
		this.targetChance = chance;
		this.longMemory = memory;
		this.width = w;
		this.upperHeight = h1;
		this.lowerHeight = h2;
		this.sorter = new AlgorithmUtil.EntitySorter(mob);
		this.setFlags(EnumSet.of(Goal.Flag.TARGET));
	}

	@Override
	public boolean canUse() {
		if (this.targetChance > 0 && this.mob.getRandom().nextInt(this.targetChance) != 0) {
			return false;
		}
		List<LivingEntity> list1 = EntityUtil.getTargetableLivings(this.mob, getAABB()).stream().filter(target -> {
			return (! this.mustSee || this.checkSenses(target)) && this.checkOther(target);
		}).collect(Collectors.toList());
		if (list1.isEmpty()) {
			return false;
		}
		Collections.sort(list1, this.sorter);
		this.targetMob = list1.get(0);
		return true;
	}

	@Override
	public void start() {
		this.mob.setTarget(this.targetMob);
	}

	@Override
	public boolean canContinueToUse() {
		LivingEntity entity = this.mob.getTarget();
		if(! EntityUtil.isEntityValid(entity)) {
			entity = this.targetMob;
		}
		if(! EntityUtil.isEntityValid(entity)) {
			return false;
		}
		if(Math.abs(entity.getX() - this.mob.getX()) > this.width || Math.abs(entity.getZ() - this.mob.getZ()) > this.width) {
			return false;
		}
		if(EntityUtil.canAttackEntity(mob, entity) && (this.longMemory || (! this.mustSee || this.checkSenses(entity)))) {
			if (this.checkOther(entity)) {
				this.mob.setTarget(entity);
				return true;
			}
		}
		return false;
	}

	protected boolean checkSenses(Entity entity) {
		return EntityUtil.canSeeEntity(this.mob, entity);
	}

	protected boolean checkOther(LivingEntity entity) {
		return true;
	}

	private AxisAlignedBB getAABB() {
		return new AxisAlignedBB(this.mob.getX() + width, this.mob.getY() + this.upperHeight,
				this.mob.getZ() + width, this.mob.getX() - width,
				this.mob.getY() - this.lowerHeight, this.mob.getZ() - width);
	}

}
