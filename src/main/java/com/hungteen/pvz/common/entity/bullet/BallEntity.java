package com.hungteen.pvz.common.entity.bullet;

import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.world.World;

public class BallEntity extends PultBulletEntity {

	public BallEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}
	
	public BallEntity(World worldIn, LivingEntity living) {
		super(EntityRegister.BALL.get(), worldIn, living);
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.4F, 0.4F);
	}

	@Override
	protected void dealDamage(Entity target) {
		target.hurt(PVZDamageSource.ball(this, this.getThrower()), this.getAttackDamage());
	}

}
