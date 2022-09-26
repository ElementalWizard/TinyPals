package com.alexvr.tinypals.setup;

import com.alexvr.tinypals.TinyPals;
import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.nio.file.Path;

@Mod.EventBusSubscriber
public class ModConfig {

    public static final String CATEGORY_GENERAL = "general";
    public static final String MOB = "mob";
    public static ForgeConfigSpec.IntValue TRECKING_CREEPER_WEIGHT;
    public static ForgeConfigSpec.IntValue TRECKING_CREEPER_MIN_GROUP;
    public static ForgeConfigSpec.IntValue TRECKING_CREEPER_MAX_GROUP;
    public static ForgeConfigSpec.IntValue BABY_GHAST_WEIGHT;
    public static ForgeConfigSpec.IntValue BABY_GHAST_MIN_GROUP;
    public static ForgeConfigSpec.IntValue BABY_GHAST_MAX_GROUP;

    public static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec SERVER_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    public static void register() {
        SERVER_BUILDER.comment("General settings").push(CATEGORY_GENERAL);

        setupMobInfo();

        SERVER_BUILDER.pop();

        SERVER_CONFIG = SERVER_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();

        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.CLIENT, CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.SERVER, SERVER_CONFIG);
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {

        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        spec.setConfig(configData);
    }

    private static void setupMobInfo() {
        SERVER_BUILDER.comment("Mob Settings").push(MOB);
        TRECKING_CREEPER_WEIGHT = SERVER_BUILDER.comment("Weight for Trecking Creepers to spawn.")
                .defineInRange("trecking_weight", 45, 0, 100);
        TRECKING_CREEPER_MIN_GROUP = SERVER_BUILDER.comment("Min group number for Trecking Creepers spawn.")
                .defineInRange("trecking_min", 0, 0, 30);
        TRECKING_CREEPER_MAX_GROUP = SERVER_BUILDER.comment("Max group number for Trecking Creepers spawn.")
                .defineInRange("trecking_max", 3, 1, 30);

        BABY_GHAST_WEIGHT = SERVER_BUILDER.comment("Weight for Baby Ghast to spawn.")
                .defineInRange("baby_ghast_weight", 75, 0, 100);
        BABY_GHAST_MIN_GROUP = SERVER_BUILDER.comment("Min group number for Baby Ghast spawn.")
                .defineInRange("baby_ghast_min", 0, 0, 30);
        BABY_GHAST_MAX_GROUP = SERVER_BUILDER.comment("Max group number for Baby Ghast spawn.")
                .defineInRange("baby_ghast_max", 1, 1, 30);

        SERVER_BUILDER.pop();
    }



    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading event)
    {
        if (event.getConfig().getModId().equals(TinyPals.MODID))
        {
            CommentedConfig cfg = event.getConfig().getConfigData();

            if (cfg instanceof CommentedFileConfig)
                ((CommentedFileConfig) cfg).load();
        }

    }

    @SubscribeEvent
    public static void onReload(final ModConfigEvent.Reloading configEvent) {
        if (configEvent.getConfig().getModId().equals(TinyPals.MODID))
        {

            //reload my stuff
            CommentedConfig cfg = configEvent.getConfig().getConfigData();

            if (cfg instanceof CommentedFileConfig)
                ((CommentedFileConfig) cfg).load();
        }
    }

}
