package com.til.dusk.common.register.ore;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OreFluid extends RegisterBasics.FluidUnitRegister<OreFluid, Ore> {

    public static Supplier<IForgeRegistry<OreFluid>> ORE_FLUID;

    /***
     * 溶液
     */
    public static OreFluid solution;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ORE_FLUID = event.create(new RegistryBuilder<OreFluid>().setName(new ResourceLocation(Dusk.MOD_ID, "ore_fluid")));
        solution = new OreFluid("solution");
    }

    public OreFluid(ResourceLocation name) {
        super(name, ORE_FLUID);
    }

    public OreFluid(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public @Nullable BucketItem createBanner(Ore ore, FlowingFluid fluid) {
        return null;
    }
}
