package com.hungteen.pvz.common.entity.zombie.grass;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.impl.zombie.GrassZombies;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.remove.MetalTypes;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.interfaces.IHasMetal;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.world.World;

public class FootballZombieEntity extends PVZZombieEntity implements IHasMetal {

	public FootballZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.canLostHand = false;
		this.increaseMetal();
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		if(this.isMiniZombie()) return EntitySize.scalable(0.5f, 0.75f);
		return EntitySize.scalable(0.8f, 2.4f);
	}

	@Override
	public float getWalkSpeed() {
		return ZombieUtil.WALK_FAST;
	}
    //血量+175
	@Override
	public float getLife() {
		return 195;
	}
	
	@Override
	public float getInnerLife() {
		return 140;
	}
	
	@Override
	public ZombieType getZombieType() {
		return GrassZombies.FOOTBALL_ZOMBIE;
	}

	@Override
	public boolean hasMetal() {
		return this.getInnerDefenceLife() > 0;
	}

	@Override
	public void decreaseMetal() {
		this.setInnerDefenceLife(0);
	}

	@Override
	public void increaseMetal() {
		this.setInnerDefenceLife(this.getInnerLife());
	}

	@Override
	public MetalTypes getMetalType() {
		return MetalTypes.FOOTBALL_HELMET;
	}

}
