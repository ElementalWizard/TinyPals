package com.alexvr.tinypals.client.renderer;

import com.alexvr.tinypals.client.model.RainSummonModel;
import com.alexvr.tinypals.entities.RainSummonEntity;
import com.alexvr.tinypals.utils.TinyReferences;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import static com.alexvr.tinypals.TinyPals.MODID;

;

@OnlyIn(Dist.CLIENT)
public class RainSummonRenderer extends MobRenderer<RainSummonEntity, RainSummonModel<RainSummonEntity>> {
    private static final ResourceLocation RESOURCE_LOCATION =  new ResourceLocation(MODID, "textures/entity/"+ TinyReferences.RAIN_SUMMON +".png");

    public RainSummonRenderer(EntityRendererProvider.Context context) {
        super(context, new RainSummonModel<>(context.bakeLayer(RainSummonModel.LAYER_LOCATION)), 0.2F);
        this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));

    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(RainSummonEntity pEntity) {
        return RESOURCE_LOCATION;
    }

    @Override
    protected int getBlockLightLevel(RainSummonEntity pEntity, BlockPos pPos) {return 15;}
}
