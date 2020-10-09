package com.hungteen.pvz.render.entity.misc;

import com.hungteen.pvz.entity.PVZMultiPartEntity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EmptyRender<T extends PVZMultiPartEntity> extends EntityRenderer<T> {

	public EmptyRender(EntityRendererManager renderManager) {
		super(renderManager);
	}

	@Override
	public ResourceLocation getEntityTexture(T entity) {
		return null;
	}

}