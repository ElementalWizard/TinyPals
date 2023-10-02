package com.alexvr.tinypals.datagen;

import com.alexvr.tinypals.TinyPals;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = TinyPals.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        //generator.addProvider(event.includeServer(),new ModRecipes(packOutput));
        //generator.addProvider(event.includeServer(), ModLootTablesProvider.create(packOutput));

        generator.addProvider(event.includeClient(), new ModItemModels(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModLanguageProvider(packOutput, "en_us"));

        //generator.addProvider(event.includeClient(), new ModBlockStates(packOutput, existingFileHelper));
        //ModBlockTags blockTagGenerator = generator.addProvider(event.includeServer(), new ModBlockTags(packOutput, lookupProvider, existingFileHelper));
        //generator.addProvider(event.includeServer(), new ModItemTags(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));
    }


}
