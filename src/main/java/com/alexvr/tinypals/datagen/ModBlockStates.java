package com.alexvr.tinypals.datagen;

import com.alexvr.tinypals.TinyPals;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.BiConsumer;

public class ModBlockStates extends BlockStateProvider {
    public ModBlockStates(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, TinyPals.MODID,existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }
    private BiConsumer<Direction, ModelBuilder<BlockModelBuilder>.ElementBuilder.FaceBuilder> addTexture(String texture) {
        return ($, f) -> f.texture(texture).uvs(0,0,16,16).tintindex(0);
    }


    private String name(Block block) {
        return key(block).getPath();
    }
    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

}
