package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author til
 */
public class ManaCoagulationShapedType extends ShapedType {
    public ManaCoagulationShapedType() {
        super("mana_coagulation", () -> ManaLevelBlock.manaCoagulation);
    }

    @Override
    public void registerShaped() {
        new ShapedOre(this, ShapedDrive.get(0), ManaLevel.t1)
                .addOutFluid(new FluidStack(Ore.mana.get(OreFluid.solution).source(), 1), 1D)
                .addMultipleSurplusTime(1024L).addMultipleConsumeMana(32L);
    }

}
