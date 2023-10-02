package com.alexvr.tinypals.setup;

import com.alexvr.tinypals.TinyPals;
import com.alexvr.tinypals.entities.TreckingCreeperEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import static com.alexvr.tinypals.TinyPals.MODID;

@Mod.EventBusSubscriber(modid = TinyPals.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            TreckingCreeperEntity.init();
        });
    }
}
