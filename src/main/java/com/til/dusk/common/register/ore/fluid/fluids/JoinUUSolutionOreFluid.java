package com.til.dusk.common.register.ore.fluid.fluids;

import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.pack.FluidPack;

/**
 * @author til
 */
public class JoinUUSolutionOreFluid extends OreFluid {
    public JoinUUSolutionOreFluid(){
        super("join_uu_solution");
    }

    @Override
    public FluidPack create(Ore ore) {
        if (ore.hasConfig(Ore.FluidConfig.FLUID_CONFIG) &&
            ore.getConfig(Ore.FluidConfig.FLUID_CONFIG).containsKey(Ore.FluidConfig.CAN_COPY)) {
            return super.create(ore);
        }
        return null;
    }
}
