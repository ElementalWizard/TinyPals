package com.alexvr.tinypals.setup;

import com.alexvr.tinypals.TinyPals;
import com.alexvr.tinypals.entities.BabyGhastEntity;
import com.alexvr.tinypals.entities.TreckingCreeperEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TinyPals.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRegistrationHelper {


    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(Registration.TRECKING_CREEPER.get(), TreckingCreeperEntity.prepareAttributes().build());
        event.put(Registration.BABY_GHAST.get(), BabyGhastEntity.prepareAttributes().build());
    }
}
