package com.alexvr.tinypals.datagen;

import com.alexvr.tinypals.TinyPals;
import com.alexvr.tinypals.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModels extends ItemModelProvider {
    public ModItemModels(PackOutput generator, ExistingFileHelper existingFileHelper) {
        super(generator, TinyPals.MODID,existingFileHelper);

    }

    @Override
    protected void registerModels() {

        singleTexture(Registration.CREEPER_CHARM_ITEM.getId().getPath(),
                mcLoc("item/handheld"),
                "layer0",modLoc("item/creeper_charm")).texture("layer1",modLoc("item/creeper_charm_overlay"));
        withExistingParent(Registration.TRECKING_CREEPER_EGG_ITEM.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(Registration.BABY_GHAST_EGG_ITEM.getId().getPath(), mcLoc("item/template_spawn_egg"));

        withExistingParent(Registration.FIRE_SUMMON_ITEM.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(Registration.RAIN_SUMMON.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }
}
