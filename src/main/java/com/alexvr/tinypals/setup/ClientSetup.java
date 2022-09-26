package com.alexvr.tinypals.setup;

import com.alexvr.tinypals.TinyPals;
import com.alexvr.tinypals.client.model.BabyGhastModel;
import com.alexvr.tinypals.client.model.TreckingCreeperModel;
import com.alexvr.tinypals.client.renderer.BabyGhastRenderer;
import com.alexvr.tinypals.client.renderer.TreckingCreeperRenderer;
import com.alexvr.tinypals.client.screen.TreckingCreeperScreen;
import com.alexvr.tinypals.items.CreeperCharm;
import com.alexvr.tinypals.utils.KeyBindings;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;

import static com.alexvr.tinypals.utils.KeyBindings.getKey;


@Mod.EventBusSubscriber(modid = TinyPals.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    @SubscribeEvent
    public static void onItemColor(RegisterColorHandlersEvent.Item event) {
        event.getItemColors().register((stack, i) -> ((CreeperCharm)stack.getItem()).getColor(stack,i), Registration.CREEPER_CHARM_ITEM.get());
    }

    @SubscribeEvent
    public static void onKeybindMapping(RegisterKeyMappingsEvent event) {
        KeyBindings.toggleMode = new KeyMapping(getKey("toggle_mode"), KeyConflictContext.IN_GAME , InputConstants.Type.KEYSYM.getOrCreate(GLFW.GLFW_KEY_K), getKey("category"));
        event.register(KeyBindings.toggleMode);
    }
    public static void init(FMLClientSetupEvent event) {

        event.enqueueWork( () -> {
            MenuScreens.register(Registration.TRECKING_CREEPER_MENU.get(), TreckingCreeperScreen::new);
        });
        MinecraftForge.EVENT_BUS.register(ForgeHooksClient.ClientEvents.class);
    }
    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(TreckingCreeperModel.LAYER_LOCATION,TreckingCreeperModel::createBodyLayer);
        event.registerLayerDefinition(TreckingCreeperModel.ARMOR_LAYER_LOCATION,TreckingCreeperModel::createBodyLayer);
        event.registerLayerDefinition(BabyGhastModel.LAYER_LOCATION,BabyGhastModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(Registration.TRECKING_CREEPER.get(), TreckingCreeperRenderer::new);
        event.registerEntityRenderer(Registration.BABY_GHAST.get(), BabyGhastRenderer::new);
    }

}
