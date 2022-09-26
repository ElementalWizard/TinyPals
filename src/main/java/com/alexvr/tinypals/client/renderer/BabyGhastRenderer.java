package com.alexvr.tinypals.client.renderer;

import com.alexvr.tinypals.TinyPals;
import com.alexvr.tinypals.client.model.BabyGhastModel;
import com.alexvr.tinypals.entities.BabyGhastEntity;
import com.alexvr.tinypals.utils.TinyReferences;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;


public class BabyGhastRenderer extends MobRenderer<BabyGhastEntity, BabyGhastModel<BabyGhastEntity>> {

    private static final ResourceLocation RESOURCE_LOCATION =  new ResourceLocation(TinyPals.MODID, "textures/entity/" + TinyReferences.BABY_GHAST_REGNAME +".png");

    public BabyGhastRenderer(EntityRendererProvider.Context context) {
        super(context,  new BabyGhastModel<>(context.bakeLayer(BabyGhastModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(BabyGhastEntity pEntity) {
        return RESOURCE_LOCATION;
    }

}
