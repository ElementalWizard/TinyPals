package com.alexvr.tinypals.datagen;

import com.alexvr.tinypals.TinyPals;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTags extends ItemTagsProvider {
    public ModItemTags(DataGenerator generator, ModBlockTags blocktags, ExistingFileHelper existingFileHelper) {
        super(generator,blocktags, TinyPals.MODID,existingFileHelper);
    }

    @Override
    protected void addTags() {

    }

    @Override
    public String getName() {
        return "Tiny Pals Tags";
    }
}
