package com.til.dusk.common.event;


import com.til.dusk.Dusk;
import com.til.dusk.common.capability.entity_skill.ISkill;
import com.til.dusk.common.capability.up.IUp;
import com.til.dusk.common.register.CapabilityRegister;
import net.minecraft.world.entity.LivingEntity;
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

/*    @SubscribeEvent
    public static void onEvent(LivingEvent.LivingTickEvent event) {
        LazyOptional<IUp> optional = event.getEntity().getCapability(CapabilityRegister.iUp.capability);
        if (optional.isPresent()) {
            optional.orElse(null).up();
        }
    }*/

    @SubscribeEvent
    public static void onEvent(LivingEquipmentChangeEvent event) {
        LazyOptional<ISkill> iSkill = event.getEntity().getCapability(CapabilityRegister.iSkill.capability);
        if (iSkill.isPresent()) {
            iSkill.orElse(null).up();
        }
    }

}
