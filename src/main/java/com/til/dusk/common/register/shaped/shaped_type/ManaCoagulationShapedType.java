package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreFluid;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraftforge.fluids.FluidStack;

import java.util.Map;

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
                .addOutFluid(new FluidStack(Ore.mana.fluidMap.get(OreFluid.solution).source(), 1), 1D)
                .addMultipleSurplusTime(1024L).addMultipleConsumeMana(32L);
    }

}
