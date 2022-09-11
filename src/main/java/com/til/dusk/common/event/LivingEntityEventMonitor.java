package com.til.dusk.common.event;


import com.til.dusk.Dusk;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.register.CapabilityRegister;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LivingEntityEventMonitor {

    @SubscribeEvent
    public static void onEvent(LivingAttackEvent event) {

    }

    @SubscribeEvent
    public static void onEvent(LivingDamageEvent event) {
    }

    @SubscribeEvent
    public static void onEvent(LivingEvent.LivingTickEvent event) {
        LazyOptional<IBack> black = event.getEntity().getCapability(CapabilityRegister.iBlack.capability);
        if (black.isPresent()) {
            black.orElse(null).run(IBack.LIVING_TICK_EVENT, event);
            black.orElse(null).run(IBack.UP, null);
        }
    }

    @SubscribeEvent
    public static void onEvent(LivingEquipmentChangeEvent event) {
        LazyOptional<IBack> black = event.getEntity().getCapability(CapabilityRegister.iBlack.capability);
        if (black.isPresent()) {
            black.orElse(null).run(IBack.LIVING_EQUIPMENT_CHANGE_EVENT, event);
        }
    }

}
