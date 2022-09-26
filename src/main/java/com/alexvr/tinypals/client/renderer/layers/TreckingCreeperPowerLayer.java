package com.alexvr.tinypals.client.renderer.layers;

import com.alexvr.tinypals.client.model.TreckingCreeperModel;
import com.alexvr.tinypals.entities.TreckingCreeperEntity;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.ResourceLocation;

import static com.alexvr.tinypals.client.model.TreckingCreeperModel.ARMOR_LAYER_LOCATION;

public class TreckingCreeperPowerLayer extends EnergySwirlLayer<TreckingCreeperEntity, TreckingCreeperModel<TreckingCreeperEntity>> {
    private static final ResourceLocation POWER_LOCATION = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
    private final TreckingCreeperModel<TreckingCreeperEntity> model;

    public TreckingCreeperPowerLayer(RenderLayerParent<TreckingCreeperEntity, TreckingCreeperModel<TreckingCreeperEntity>> p_174471_, EntityModelSet p_174472_) {
        super(p_174471_);
        this.model = new TreckingCreeperModel<>(p_174472_.bakeLayer(ARMOR_LAYER_LOCATION));
    }

    protected float xOffset(float p_116683_) {
        return p_116683_ * 0.01F;
    }

    protected ResourceLocation getTextureLocation() {
        return POWER_LOCATION;
    }

    protected TreckingCreeperModel model() {
        return this.model;
    }
}
