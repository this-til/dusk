package com.til.dusk.client;

import com.til.dusk.Dusk;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * @author til
 */
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class InteractProxy {
    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    }

}
