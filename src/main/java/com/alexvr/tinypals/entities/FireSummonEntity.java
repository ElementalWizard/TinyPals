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
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class FireSummonEntity extends Allay {

    int counter = 0;
    public FireSummonEntity(EntityType<? extends Allay> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);

    }

    @Override
    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        if(pPlayer.getItemInHand(pHand).is(Items.SPONGE)){
            pPlayer.getItemInHand(pHand).shrink(1);
            this.level.getLevelData().setRaining(false);
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(pPlayer, pHand);
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
    public void tick() {
        super.tick();
        counter++;
        if(counter == 15){
            counter = 0;
            boolean flag = Minecraft.getInstance().level != null && Minecraft.getInstance().level.isRaining();
            Minecraft.getInstance().levelRenderer.addParticle(flag ? ParticleTypes.ASH: ParticleTypes.FLAME, true, this.getX(), flag ? this.getEyeY() + 0.2:this.getEyeY(), this.getZ(), 0, 0.03, 0);
        }
    }

    @Override
    public float getEyeHeight(Pose pPose) {
        return 0.25f;
    }
}
