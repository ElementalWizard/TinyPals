package com.alexvr.tinypals.utils;

import com.alexvr.tinypals.TinyPals;
import com.alexvr.tinypals.entities.TreckingCreeperEntity;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TinyPals.MODID)
public class WorldEventHandler {

    @SubscribeEvent
    public static void onEntitySpawn(LivingSpawnEvent event) {
        if (event.getEntity() instanceof TreckingCreeperEntity treckingCreeperEntity && !treckingCreeperEntity.getTypeAssignedDir()){
            //treckingCreeperEntity.setType(Biome.getBiomeCategory(event.getWorld().getBiome(treckingCreeperEntity.getOnPos())));
        }//TODO Creeper
    }
}
