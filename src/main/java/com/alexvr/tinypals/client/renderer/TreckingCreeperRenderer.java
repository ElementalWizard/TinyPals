package com.alexvr.tinypals.client.renderer;

import com.alexvr.tinypals.TinyPals;
import com.alexvr.tinypals.client.model.TreckingCreeperModel;
import com.alexvr.tinypals.client.renderer.layers.TreckingCreeperPowerLayer;
import com.alexvr.tinypals.entities.TreckingCreeperEntity;
import com.alexvr.tinypals.utils.TinyReferences;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;


public class TreckingCreeperRenderer extends MobRenderer<TreckingCreeperEntity, TreckingCreeperModel<TreckingCreeperEntity>> {

    private static final ResourceLocation RESOURCE_LOCATION =  new ResourceLocation(TinyPals.MODID, "textures/entity/" + TinyReferences.TRECKING_CREEPER_REGNAME +"/"+ TinyReferences.TRECKING_CREEPER_REGNAME +".png");
    private static final ResourceLocation RESOURCE_LOCATION_CYAN =  new ResourceLocation(TinyPals.MODID, "textures/entity/" + TinyReferences.TRECKING_CREEPER_REGNAME +"/"+ TinyReferences.TRECKING_CREEPER_REGNAME +"_cyan.png");
    private static final ResourceLocation RESOURCE_LOCATION_GS =  new ResourceLocation(TinyPals.MODID, "textures/entity/" + TinyReferences.TRECKING_CREEPER_REGNAME +"/"+ TinyReferences.TRECKING_CREEPER_REGNAME +"_greyscale.png");
    private static final ResourceLocation RESOURCE_LOCATION_MAGENTA =  new ResourceLocation(TinyPals.MODID, "textures/entity/" + TinyReferences.TRECKING_CREEPER_REGNAME +"/"+ TinyReferences.TRECKING_CREEPER_REGNAME +"_magenta.png");
    private static final ResourceLocation RESOURCE_LOCATION_PURPLE =  new ResourceLocation(TinyPals.MODID, "textures/entity/" + TinyReferences.TRECKING_CREEPER_REGNAME +"/"+ TinyReferences.TRECKING_CREEPER_REGNAME +"_purple.png");
    private static final ResourceLocation RESOURCE_LOCATION_RED =  new ResourceLocation(TinyPals.MODID, "textures/entity/" + TinyReferences.TRECKING_CREEPER_REGNAME +"/"+ TinyReferences.TRECKING_CREEPER_REGNAME +"_red.png");
    private static final ResourceLocation RESOURCE_LOCATION_SEPIA =  new ResourceLocation(TinyPals.MODID, "textures/entity/" + TinyReferences.TRECKING_CREEPER_REGNAME +"/"+ TinyReferences.TRECKING_CREEPER_REGNAME +"_sepia.png");
    private static final ResourceLocation RESOURCE_LOCATION_VOID =  new ResourceLocation(TinyPals.MODID, "textures/entity/" + TinyReferences.TRECKING_CREEPER_REGNAME +"/"+ TinyReferences.TRECKING_CREEPER_REGNAME +"_void.png");
    private static final ResourceLocation RESOURCE_LOCATION_YELLOW =  new ResourceLocation(TinyPals.MODID, "textures/entity/" + TinyReferences.TRECKING_CREEPER_REGNAME +"/"+ TinyReferences.TRECKING_CREEPER_REGNAME +"_yellow.png");
    public TreckingCreeperRenderer(EntityRendererProvider.Context context) {
        super(context,  new TreckingCreeperModel<>(context.bakeLayer(TreckingCreeperModel.LAYER_LOCATION)), 0.2F);
        this.addLayer(new TreckingCreeperPowerLayer(this, context.getModelSet()));
    }

    protected void scale(TreckingCreeperEntity pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
        float f = pLivingEntity.getSwelling(pPartialTickTime);
        float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
        f = Mth.clamp(f, 0.0F, 1.0F);
        f *= f;
        f *= f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        pMatrixStack.scale(f2, f3, f2);
    }

    protected float getWhiteOverlayProgress(TreckingCreeperEntity pLivingEntity, float pPartialTicks) {
        float f = pLivingEntity.getSwelling(pPartialTicks);
        return (int)(f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(TreckingCreeperEntity pEntity) {
        return switch (pEntity.getTypeDir()) {
            case 1 -> RESOURCE_LOCATION_CYAN;
            case 2 -> RESOURCE_LOCATION_GS;
            case 3 -> RESOURCE_LOCATION_MAGENTA;
            case 4 -> RESOURCE_LOCATION_PURPLE;
            case 5 -> RESOURCE_LOCATION_RED;
            case 6 -> RESOURCE_LOCATION_SEPIA;
            case 7 -> RESOURCE_LOCATION_VOID;
            case 8 -> RESOURCE_LOCATION_YELLOW;
            default -> RESOURCE_LOCATION;
        };
    }

}
