package com.alexvr.tinypals.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class RainSummonEntity extends Allay {

    int counter = 0;
    public RainSummonEntity(EntityType<? extends Allay> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);

    }
    public static AttributeSupplier.Builder prepareAttributes() {
        return Mob.createLivingAttributes()
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.FOLLOW_RANGE, 15.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ARMOR, 1.0D)
                .add(Attributes.ARMOR_TOUGHNESS,1.0D)
                .add(Attributes.FLYING_SPEED, (double)0.1F);
    }
    @Override
    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        if(pPlayer.getItemInHand(pHand).is(Items.WATER_BUCKET)){
            pPlayer.setItemInHand(pHand,new ItemStack(Items.BUCKET));
            this.level().getLevelData().setRaining(true);
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(pPlayer, pHand);
    }
    @Override
    public void tick() {
        super.tick();
        counter++;
        if(counter == 20){
            counter = 0;
            boolean flag = Minecraft.getInstance().level != null && Minecraft.getInstance().level.isRaining();
            Minecraft.getInstance().levelRenderer.addParticle(flag ? ParticleTypes.BUBBLE_COLUMN_UP: ParticleTypes.ASH, true, this.getX(), this.getEyeY()+ 0.3, this.getZ(), 0, 0.03, 0);
        }
    }

    @Override
    public float getEyeHeight(Pose pPose) {
        return 0.25f;
    }
}
