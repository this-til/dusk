package com.til.dusk.common.register.ore.fluid;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.pack.FluidPack;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidType;

import java.util.function.Supplier;


/**
 * @author til
 */
public class OreFluidSplitting extends OreFluid {
    public final Supplier<DuskColor> color;

    public OreFluidSplitting(ResourceLocation name, Supplier<DuskColor> color) {
        super(name);
        this.color = color;
    }

    public OreFluidSplitting(String name, Supplier<DuskColor> color) {
        this(new ResourceLocation(Dusk.MOD_ID, name), color);
    }

    @Override
    public FluidPack create(Ore ore) {
        if (ore.hasConfig(Ore.FluidConfig.FLUID_CONFIG)
            && ore.getConfig(Ore.FluidConfig.FLUID_CONFIG).containsKey(Ore.FluidConfig.FLUID_CONFIG)) {
            return super.create(ore);
        }
        return null;
    }

    @Override
    public FluidType createFluidType(Ore ore) {
        DuskColor color = ore.getConfig(Ore.COLOR).blend(this.color.get());
        return new OreFluidType(FluidType.Properties.create(), ore, this) {
            @Override
            public DuskColor getColor() {
                return color;
            }
        };
    }
}
