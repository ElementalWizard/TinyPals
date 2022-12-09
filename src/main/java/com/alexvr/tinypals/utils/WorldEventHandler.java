package com.alexvr.tinypals.utils;

import com.alexvr.tinypals.TinyPals;
import com.alexvr.tinypals.entities.TreckingCreeperEntity;
import com.alexvr.tinypals.setup.Registration;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TinyPals.MODID)
public class WorldEventHandler {
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if(event.getEntity() instanceof Allay allay && event.getSource()  == DamageSource.LIGHTNING_BOLT){
            boolean flag = event.getEntity().level.isRaining();
            Mob allayVariant = flag ? Registration.RAIN_SUMMON.get().create(event.getEntity().level) : Registration.FIRE_SUMMON.get().create(event.getEntity().level);
            allayVariant.moveTo(allay.getX(), allay.getY(), allay.getZ(), allay.getYRot(), allay.getXRot());
            allayVariant.setNoAi(allay.isNoAi());
            if (allay.hasCustomName()) {
                allayVariant.setCustomName(allay.getCustomName());
                allayVariant.setCustomNameVisible(allay.isCustomNameVisible());
            }
            allayVariant.setItemInHand(InteractionHand.MAIN_HAND,new ItemStack(flag? Items.WATER_BUCKET:Items.SPONGE));
            allayVariant.setPersistenceRequired();
            net.minecraftforge.event.ForgeEventFactory.onLivingConvert(allay, allayVariant);
            event.getEntity().level.addFreshEntity(allayVariant);
            allay.discard();
            allayVariant.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE,60));
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onDamage(LivingDamageEvent event){
        if (event.getSource().isExplosion() &&
                event.getEntity() instanceof Player player &&
                event.getSource().getEntity() instanceof TreckingCreeperEntity treckingCreeperEntity &&
                treckingCreeperEntity.isTamed() &&
                treckingCreeperEntity.getOwnerUUID().equals(player.getUUID())){
            event.setCanceled(true);
        }
    }
}
