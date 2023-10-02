package com.alexvr.tinypals.setup;

import com.alexvr.tinypals.entities.BabyGhastEntity;
import com.alexvr.tinypals.entities.FireSummonEntity;
import com.alexvr.tinypals.entities.RainSummonEntity;
import com.alexvr.tinypals.entities.TreckingCreeperEntity;
import com.alexvr.tinypals.items.CreeperCharm;
import com.alexvr.tinypals.utils.TinyReferences;
import com.alexvr.tinypals.world.inventory.TreckingCreeperMenu;
import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.alexvr.tinypals.TinyPals.MODID;

public class Registration {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static  void init(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        BLOCKS.register(bus);
        ITEMS.register(bus);
        CONTAINERS.register(bus);
        ENTITIES.register(bus);
        TABS.register(bus);
    }

    public static final RegistryObject<EntityType<TreckingCreeperEntity>> TRECKING_CREEPER = ENTITIES.register(TinyReferences.TRECKING_CREEPER_REGNAME, () -> EntityType.Builder.of(TreckingCreeperEntity::new, MobCategory.MONSTER)
            .sized(0.30f, 0.5f)
            .clientTrackingRange(16)
            .setShouldReceiveVelocityUpdates(false)
            .build(TinyReferences.TRECKING_CREEPER_REGNAME));
    public static final RegistryObject<Item> TRECKING_CREEPER_EGG_ITEM = ITEMS.register(TinyReferences.TRECKING_CREEPER_REGNAME, () -> new ForgeSpawnEggItem(TRECKING_CREEPER, 0x6aa84f, 0x000000, new Item.Properties()));

    public static final RegistryObject<MenuType<TreckingCreeperMenu>> TRECKING_CREEPER_MENU = CONTAINERS.register(TinyReferences.TRECKING_CREEPER_GUI, () -> IForgeMenuType.create(TreckingCreeperMenu::new));
    public static final RegistryObject<EntityType<BabyGhastEntity>> BABY_GHAST = ENTITIES.register(TinyReferences.BABY_GHAST_REGNAME, () -> EntityType.Builder.of(BabyGhastEntity::new, MobCategory.MONSTER)
            .sized(0.6f, 0.6f)
            .clientTrackingRange(16)
            .setShouldReceiveVelocityUpdates(false)
            .build(TinyReferences.BABY_GHAST_REGNAME));
    public static final RegistryObject<Item> BABY_GHAST_EGG_ITEM = ITEMS.register(TinyReferences.BABY_GHAST_REGNAME, () -> new ForgeSpawnEggItem(BABY_GHAST, 0xffffff, 0xff0000, new Item.Properties()));

    public static final RegistryObject<EntityType<FireSummonEntity>> FIRE_SUMMON = ENTITIES.register(TinyReferences.FIRE_SUMMON, () -> EntityType.Builder.of(FireSummonEntity::new, MobCategory.CREATURE)
            .sized(0.4f, 0.65f)
            .clientTrackingRange(8)
            .setShouldReceiveVelocityUpdates(false)
            .build(TinyReferences.FIRE_SUMMON));
    public static final RegistryObject<Item> FIRE_SUMMON_ITEM = ITEMS.register(TinyReferences.FIRE_SUMMON, () -> new ForgeSpawnEggItem(FIRE_SUMMON, 0xB21C1C, 0x1C1C1C, new Item.Properties()));
    public static final RegistryObject<EntityType<RainSummonEntity>> RAIN_SUMMON = ENTITIES.register(TinyReferences.RAIN_SUMMON, () -> EntityType.Builder.of(RainSummonEntity::new, MobCategory.CREATURE)
            .sized(0.4f, 0.65f)
            .clientTrackingRange(8)
            .setShouldReceiveVelocityUpdates(false)
            .build(TinyReferences.RAIN_SUMMON));
    public static final RegistryObject<Item> RAIN_SUMMON_ITEM = ITEMS.register(TinyReferences.RAIN_SUMMON, () -> new ForgeSpawnEggItem(RAIN_SUMMON, 0x7ADEFF, 0xDEC02E, new Item.Properties()));

    public static final RegistryObject<CreeperCharm> CREEPER_CHARM_ITEM = ITEMS.register(TinyReferences.CREEPER_CHARM_REGNAME, () -> new CreeperCharm((new Item.Properties().stacksTo(1))));

    public static <B extends  Block>RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(),(new Item.Properties())));
    }
    // Helper method to register since compiler will complain about typing otherwise
    private static <S extends Structure> StructureType<S> typeConvert(Codec<S> codec) {
        return () -> codec;
    }

    public static final RegistryObject<CreativeModeTab> ITEMS_TABS = TABS.register("mhnw",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Registration.CREEPER_CHARM_ITEM.get()))
                    .title(Component.translatable("creativetab.all_items_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(Registration.CREEPER_CHARM_ITEM.get());
                    })
                    .build());
}
