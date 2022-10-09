package com.til.dusk.common.register.mana_level.fluid;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.FluidUnitRegister;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import net.minecraft.resources.ResourceLocation;
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
public abstract class ManaLevelFluid extends FluidUnitRegister<ManaLevelFluid, ManaLevel> {

    public static Supplier<IForgeRegistry<ManaLevelFluid>> LEVEL_FLUID;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        LEVEL_FLUID = RegisterManage.create(ManaLevelFluid.class, new ResourceLocation(Dusk.MOD_ID, "mana_level_fluid"), event);
    }

    public ManaLevelFluid(ResourceLocation name) {
        super(name, LEVEL_FLUID);
    }

}
