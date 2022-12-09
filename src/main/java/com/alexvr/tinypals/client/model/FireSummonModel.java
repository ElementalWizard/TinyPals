package com.alexvr.tinypals.client.model;

import com.alexvr.tinypals.entities.FireSummonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;

import static com.alexvr.tinypals.TinyPals.MODID;


public class FireSummonModel<T extends Entity> extends HierarchicalModel<FireSummonEntity> implements ArmedModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MODID, "fire_summon"), "main");
    private ModelPart head;

    private final ModelPart right_arm;
    private final ModelPart left_arm;
    private ModelPart left_wing;
    private ModelPart right_wing;
    private ModelPart body;
    private ModelPart root;

    public FireSummonModel(ModelPart root) {
        this.root = root.getChild("root");
        this.head = this.root.getChild("head");
        this.body = this.root.getChild("body");
        this.right_arm = this.body.getChild("right_arm");
        this.left_arm = this.body.getChild("left_arm");
        this.right_wing = this.body.getChild("right_wing");
        this.left_wing = this.body.getChild("left_wing");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition partdefinition1 = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        partdefinition1.addOrReplaceChild("head", CubeListBuilder.create().texOffs(23, 12).addBox(1.0F, -7.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(23, 12).addBox(-2.0F, -7.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-2.5F, -5.01F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

        PartDefinition partdefinition2 = partdefinition1.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 10).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 16).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.0F, -4.0F, 0.0F));


        partdefinition2.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(23, 0).addBox(-0.75F, -0.5F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.75F, 0.5F, 0.0F));

        partdefinition2.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(23, 6).addBox(-0.25F, -0.5F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.75F, 0.5F, 0.0F));

        partdefinition2.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(16, 14).addBox(0.0F, -5.0F, 0.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 1.0F, 1.0F));

        partdefinition2.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(16, 14).addBox(0.0F, -5.0F, 0.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 1.0F, 1.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    public void translateToHand(HumanoidArm p_233322_, PoseStack p_233323_) {
        float f = -1.5F;
        float f1 = 1.5F;
        this.root.translateAndRotate(p_233323_);
        this.body.translateAndRotate(p_233323_);
        p_233323_.translate(0.0D, -0.09375D, 0.09375D);
        p_233323_.mulPose(Vector3f.XP.rotation(this.right_arm.xRot + 0.43633232F));
        p_233323_.scale(0.7F, 0.7F, 0.7F);
        p_233323_.translate(0.0625D, 0.0D, 0.0D);
    }

    @Override
    public void setupAnim(FireSummonEntity p_233325_, float p_233326_, float p_233327_, float p_233328_, float p_233329_, float p_233330_) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        float f = p_233328_ * 20.0F * ((float)Math.PI / 180F) + p_233327_;
        float f1 = Mth.cos(f) * (float)Math.PI * 0.15F;
        float f2 = p_233328_ - (float)p_233325_.tickCount;
        float f3 = p_233328_ * 9.0F * ((float)Math.PI / 180F);
        float f4 = Math.min(p_233327_ / 0.3F, 1.0F);
        float f5 = 1.0F - f4;
        float f6 = p_233325_.getHoldingItemAnimationProgress(f2);
        if (p_233325_.isDancing()) {
            float f7 = p_233328_ * 8.0F * ((float)Math.PI / 180F) + p_233327_;
            float f8 = Mth.cos(f7) * 16.0F * ((float)Math.PI / 180F);
            float f9 = p_233325_.getSpinningProgress(f2);
            float f10 = Mth.cos(f7) * 14.0F * ((float)Math.PI / 180F);
            float f11 = Mth.cos(f7) * 30.0F * ((float)Math.PI / 180F);
            this.root.yRot = p_233325_.isSpinning() ? 12.566371F * f9 : this.root.yRot;
            this.root.zRot = f8 * (1.0F - f9);
            this.head.yRot = f11 * (1.0F - f9);
            this.head.zRot = f10 * (1.0F - f9);
        } else {
            this.head.xRot = p_233330_ * ((float)Math.PI / 180F);
            this.head.yRot = p_233329_ * ((float)Math.PI / 180F);
        }
        this.right_wing.xRot = 0.43633232F;
        this.right_wing.yRot = -0.61086524F + f1;
        this.left_wing.xRot = 0.43633232F;
        this.left_wing.yRot = 0.61086524F - f1;
        float f12 = f4 * 0.6981317F;
        this.body.xRot = f12;
        this.root.y += (float)Math.cos((double)f3) * 0.25F * f5;
    }
}
