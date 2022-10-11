package com.til.dusk.common.register.ore.fluid;

import com.til.dusk.Dusk;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.pack.FluidPack;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidType;
import org.checkerframework.checker.units.qual.C;

import java.util.function.Supplier;


/**
 * @author til
 */
public abstract class OreFluidSplitting extends OreFluid {

    public OreFluidSplitting(ResourceLocation name) {
        super(name);
    }

    public OreFluidSplitting(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public FluidPack create(Ore ore) {
        if (ore.fluidData != null
            && ore.fluidData.splitting != null) {
            return super.create(ore);
        }
        return null;
    }

    @Override
    public FluidType createFluidType(Ore ore) {
        DuskColor color = ore.color.blend(this.colorBasics.get());
        return new OreFluidType(FluidType.Properties.create(), ore, this) {
            @Override
            public DuskColor getColor() {
                return color;
            }
        };
    }

    @ConfigField
    public Delayed<? extends DuskColor> colorBasics;
}
