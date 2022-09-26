package com.alexvr.tinypals.datagen;

import com.alexvr.tinypals.TinyPals;
import com.alexvr.tinypals.setup.Registration;
import com.alexvr.tinypals.utils.TinyReferences;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(DataGenerator generator, String language) {
        super(generator, TinyPals.MODID,language);

    }

    @Override
    protected void addTranslations() {

        add(Registration.SCRAPE_KNIFE_ITEM.get(), "Scrape Knife" );

        add(Registration.CREEPER_CHARM_ITEM.get(), "Creeper Charm" );

        add(TinyReferences.CREATIVE_TAB_NAME, "Tiny Pals Tab" );

        add(Registration.TRECKING_CREEPER_EGG_ITEM.get(), "Trecking Creeper Spawn Egg");
        add(TinyReferences.TRECKING_CREEPER_GUI, "Trecking Creeper Inventory");
        add(Registration.BABY_GHAST_EGG_ITEM.get(), "Baby Ghast Spawn Egg");


        add(Registration.TRECKING_CREEPER.get(), "Trecking Creeper");
        add(Registration.BABY_GHAST.get(), "Baby Ghast");
    }


}
