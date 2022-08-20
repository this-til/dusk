package com.til.dusk.common.register.mana_level;

import com.til.dusk.Dusk;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class ManaLevelFluid extends ManaLevel.ManaLevelType<ManaLevelFluid, Fluid> {

    public static Supplier<IForgeRegistry<ManaLevelFluid>> LEVEL_FLUID;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        LEVEL_FLUID = event.create(new RegistryBuilder<ManaLevelFluid>().setName(new ResourceLocation(Dusk.MOD_ID, "mana_level_fluid")));
    }

    public ManaLevelFluid(ResourceLocation name) {
        super(name, LEVEL_FLUID);
    }



}