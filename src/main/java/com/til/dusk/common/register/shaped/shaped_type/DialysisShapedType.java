package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author til
 */
public class DialysisShapedType extends ShapedType {

    public DialysisShapedType() {
        super("dialysis", () -> ManaLevelBlock.dialysis);
    }

    @Override
    public void registerShaped() {
        for (Ore ore : Ore.screen(Ore.FLUID_DATA)) {
            if (!ore.getSet(Ore.FLUID_DATA).canCopy) {
                continue;
            }
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInFluid(ore.get(OreFluid.solution).fluidTag(), 72)
                    .addInFluid(Ore.uu.get(OreFluid.solution).fluidTag(), 1)
                    .addOutFluid(new FluidStack(ore.get(OreFluid.joinUUSolution).source(), 72), 1d)
                    .addMultipleSurplusTime(4096L)
                    .addMultipleConsumeMana(44L);
        }
    }
}
