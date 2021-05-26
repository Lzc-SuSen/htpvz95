package com.hungteen.pvz.model.entity.zombie.plantzombie;

import com.hungteen.pvz.entity.zombie.zombotany.TallNutZombieEntity;
import com.hungteen.pvz.model.entity.zombie.PVZZombieModel;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class TallNutZombieModel extends PVZZombieModel<TallNutZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;

	public TallNutZombieModel() {
		texWidth = 256;
		texHeight = 256;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		up = new ModelRenderer(this);
		up.setPos(0.0F, -24.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		up.addChild(body);
		body.texOffs(0, 41).addBox(-8.0F, -24.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -24.0F, 0.0F);
		up.addChild(head);
		head.texOffs(170, 190).addBox(-10.0F, -40.0F, -10.0F, 20.0F, 40.0F, 20.0F, 0.0F, false);
		head.texOffs(182, 156).addBox(-9.0F, -41.0F, -9.0F, 18.0F, 1.0F, 18.0F, 0.0F, false);
		head.texOffs(216, 5).addBox(-11.0F, -39.0F, -9.0F, 1.0F, 38.0F, 18.0F, 0.0F, false);
		head.texOffs(5, 207).addBox(11.0F, -37.0F, -7.0F, 1.0F, 34.0F, 14.0F, 0.0F, false);
		head.texOffs(42, 170).addBox(10.0F, -39.0F, -9.0F, 1.0F, 38.0F, 18.0F, 0.0F, false);
		head.texOffs(93, 178).addBox(-12.0F, -37.0F, -7.0F, 1.0F, 34.0F, 14.0F, 0.0F, false);
		head.texOffs(217, 68).addBox(-9.0F, -39.0F, -11.0F, 18.0F, 38.0F, 1.0F, 0.0F, false);
		head.texOffs(176, 3).addBox(-7.0F, -37.0F, -12.0F, 14.0F, 34.0F, 1.0F, 0.0F, false);
		head.texOffs(159, 52).addBox(-9.0F, -39.0F, 10.0F, 18.0F, 38.0F, 1.0F, 0.0F, false);
		head.texOffs(124, 112).addBox(-7.0F, -37.0F, 11.0F, 14.0F, 34.0F, 1.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(12.0F, -20.0F, 0.0F);
		up.addChild(left_hand);
		left_hand.texOffs(96, 60).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-12.0F, -20.0F, 0.0F);
		up.addChild(right_hand);
		right_hand.texOffs(96, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-4.0F, -24.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.texOffs(44, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setPos(4.0F, -24.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.texOffs(0, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);
	}

	@Override
	public ModelRenderer getZombieLeftHand() {
		return this.left_hand;
	}

	@Override
	public ModelRenderer getZombieRightHand() {
		return this.right_hand;
	}

	@Override
	public ModelRenderer getZombieLeftLeg() {
		return this.left_leg;
	}

	@Override
	public ModelRenderer getZombieRightLeg() {
		return this.right_leg;
	}

	@Override
	public ModelRenderer getZombieHead() {
		return this.head;
	}
	
	@Override
	public ModelRenderer getZombieUpBody() {
		return this.up;
	}

	@Override
	public ModelRenderer getZombieWholeBody() {
		return this.total;
	}
}