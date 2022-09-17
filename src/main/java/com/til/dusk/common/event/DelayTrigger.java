package com.til.dusk.common.event;

import com.til.dusk.Dusk;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DelayTrigger {

    public static List<Runnable> setupEventRun = new ArrayList<>();

    @SubscribeEvent
    public static void setupEvent(FMLCommonSetupEvent event) {
        for (Runnable runnable : setupEventRun) {
            runnable.run();
        }
        setupEventRun.clear();
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void gatherDataEvent(GatherDataEvent event) {
        for (Runnable runnable : setupEventRun) {
            runnable.run();
        }
        setupEventRun.clear();
    }

}
