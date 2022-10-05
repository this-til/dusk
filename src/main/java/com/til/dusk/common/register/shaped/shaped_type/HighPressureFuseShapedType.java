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
public class HighPressureFuseShapedType extends ShapedType {

    public HighPressureFuseShapedType() {
        super("high_pressure_fuse", () -> ManaLevelBlock.highPressureFuse);
    }

    @Override
    public void registerShaped() {
        for (Ore ore : Ore.screen(Ore.FLUID_DATA)) {
            OreFluid.FluidData fluidData = ore.getSet(Ore.FLUID_DATA);
            if (fluidData.splitting == null) {
                continue;
            }
            new ShapedOre(this, ShapedDrive.get(1), ore.manaLevel)
                    .addInFluid(ore.get(OreFluid.solution).fluidTag(), 72)
                    .addInFluid(Ore.sunlight.get(OreFluid.solution).fluidTag(), 72)
                    .addOutFluid(new FluidStack(ore.get(OreFluid.splittingSunlightSolution).source(), 144), 1d);
            new ShapedOre(this, ShapedDrive.get(1), ore.manaLevel)
                    .addInFluid(ore.get(OreFluid.solution).fluidTag(), 72)
                    .addInFluid(Ore.moonlight.get(OreFluid.solution).fluidTag(), 72)
                    .addOutFluid(new FluidStack(ore.get(OreFluid.splittingMoonlightSolution).source(), 144), 1d);
            new ShapedOre(this, ShapedDrive.get(1), ore.manaLevel)
                    .addInFluid(ore.get(OreFluid.solution).fluidTag(), 72)
                    .addInFluid(Ore.rain.get(OreFluid.solution).fluidTag(), 72)
                    .addOutFluid(new FluidStack(ore.get(OreFluid.splittingRainSolution).source(), 144), 1d);
        }
    }
}
