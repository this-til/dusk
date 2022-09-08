package com.til.dusk.common.event;


import com.til.dusk.Dusk;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LivingEntitySkillMonitor {

    @SubscribeEvent
    public static void onEvent(LivingAttackEvent event) {

    }

    @SubscribeEvent
    public static void onEvent(LivingDamageEvent event) {
    }

}
