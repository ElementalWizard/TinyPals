package com.alexvr.tinypals;

import com.alexvr.tinypals.setup.ClientSetup;
import com.alexvr.tinypals.setup.ModConfig;
import com.alexvr.tinypals.setup.ModSetup;
import com.alexvr.tinypals.setup.Registration;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;


@Mod(TinyPals.MODID)
public class TinyPals {

    public static final String MODID = "tinypals";
    public static final Logger LOGGER = LogManager.getLogger();
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, MODID), () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
    private static int messageID = 0;

    public TinyPals() {
        ModConfig.register();
        Registration.init();

        IEventBus event = FMLJavaModLoadingContext.get().getModEventBus();

        event.addListener(ModSetup::init);
        event.addListener(this::addCreative);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> event.addListener(ClientSetup::init));

        ModConfig.loadConfig(ModConfig.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("tinypals-client.toml"));
        ModConfig.loadConfig(ModConfig.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve("tinypals-common.toml"));

    }
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(Registration.FIRE_SUMMON_ITEM);
            event.accept(Registration.RAIN_SUMMON_ITEM);
            event.accept(Registration.BABY_GHAST_EGG_ITEM);
            event.accept(Registration.TRECKING_CREEPER_EGG_ITEM);
        }
    }
    public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder,
                                             BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
        PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
        messageID++;
    }

}
