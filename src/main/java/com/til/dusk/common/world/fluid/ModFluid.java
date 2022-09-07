package com.til.dusk.common.world.fluid;

import com.til.dusk.Dusk;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFluid {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Dusk.MOD_ID);

    //public static final RegistryObject<ForgeFlowingFluid.Source> TEST_FLUID_SOURCE = FLUIDS.register("test_fluid", () -> new ForgeFlowingFluid.Source(ModProperties.TEST_FLUID_PROPERTIES));
    //public static final RegistryObject<ForgeFlowingFluid.Flowing> TEST_FLUID_FLOWING = FLUIDS.register("test_fluid_flowing", () -> new ForgeFlowingFluid.Flowing(ModProperties.TEST_FLUID_PROPERTIES));

/*    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        FLUIDS.register(Dusk.instance.modEventBus);
    }

    public static class ModProperties {
        public static final ForgeFlowingFluid.Properties TEST_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(() -> new FluidType(FluidType.Properties.create()), () -> TEST_FLUID_SOURCE.get(), () -> TEST_FLUID_FLOWING.get());

    }*/
}
