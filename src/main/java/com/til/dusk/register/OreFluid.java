package com.til.dusk.register;

import com.til.dusk.Dusk;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class OreFluid extends Ore.OreType<OreFluid, Fluid> {

    public static Supplier<IForgeRegistry<OreFluid>> ORE_FLUID;


    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ORE_FLUID = event.create(new RegistryBuilder<OreFluid>().setName(new ResourceLocation(Dusk.MOD_ID, "ore_fluid")));
    }

    public OreFluid(ResourceLocation name) {
        super(name, ORE_FLUID);
    }

    @Override
    public abstract Fluid create(Ore ore);
}
