package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreFluid;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;

/**
 * @author til
 */
public class SplittingShapedType extends ShapedType {

    public SplittingShapedType() {
        super("splitting", () -> ManaLevelBlock.splitting);
    }

    @Override
    public void registerShaped() {
        for (Ore ore : Ore.ORE.get()) {
            if (!ore.hasSet(Ore.FLUID_DATA)) {
                continue;
            }
            OreFluid.FluidData fluidData = ore.getSet(Ore.FLUID_DATA);
            if (fluidData.splitting == null) {
                continue;
            }
            OreFluid.FluidData.SplittingData splittingData = fluidData.splitting;
            if (splittingData.sunlightSplitting != null) {
                new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                        .addInFluid(ore.fluidMap.get(OreFluid.splittingSunlightSolution).fluidTag(), 144)
                        .runThis(s -> splittingData.sunlightSplitting.run(s, null))
                        .addMultipleSurplusTime((long) (1024L * ore.strength))
                        .addMultipleConsumeMana((long) (12 * ore.strength));
            }
            if (splittingData.moonlightSplitting != null) {
                new ShapedOre(this, ShapedDrive.get(1), ore.manaLevel)
                        .addInFluid(ore.fluidMap.get(OreFluid.splittingMoonlightSolution).fluidTag(), 144)
                        .runThis(s -> splittingData.moonlightSplitting.run(s, null))
                        .addMultipleSurplusTime((long) (1024L * ore.strength))
                        .addMultipleConsumeMana((long) (12 * ore.strength));
            }
            if (splittingData.rainSplitting != null) {
                new ShapedOre(this, ShapedDrive.get(2), ore.manaLevel)
                        .addInFluid(ore.fluidMap.get(OreFluid.splittingRainSolution).fluidTag(), 144)
                        .runThis(s -> splittingData.rainSplitting.run(s, null))
                        .addMultipleSurplusTime((long) (1024L * ore.strength))
                        .addMultipleConsumeMana((long) (12 * ore.strength));
            }
        }
    }
}