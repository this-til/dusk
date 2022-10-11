package com.til.dusk.common.register.mana_level.mana_level.mana_levels;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.DuskColor;

import java.util.List;

/**
 * @author til
 */
public class T6ManaLevel extends ManaLevel {
    public T6ManaLevel() {
        super("t6");
    }


    @Override
    public void defaultConfig() {
        level = 6;
        clock = 80;
        parallel = 6;
        maxBind = 6;
        manaLoss = 0.05;
        maxRange = 26;
        color = new DuskColor(200, 150, 120);
        operationBasics = new Delayed.ManaLevelShapedOreConfigDelayed(() -> List.of(
                new IShapedOreConfig.IShapedOreManaLevelConfig.FluidIn(() -> Ore.coolant.get(OreFluid.solution).fluidTag(), 128),
                new IShapedOreConfig.IShapedOreManaLevelConfig.FluidIn(() -> Ore.culture.get(OreFluid.solution).fluidTag(), 32),
                new IShapedOreConfig.IShapedOreManaLevelConfig.FluidIn(() -> Ore.nutrient.get(OreFluid.solution).fluidTag(), 32),
                new IShapedOreConfig.IShapedOreManaLevelConfig.FluidIn(() -> Ore.supportCell.get(OreFluid.solution).fluidTag(), 256)));
        operation = new Delayed.ManaLevelShapedOreConfigDelayed(() -> List.of(
                new IShapedOreConfig.IShapedOreManaLevelConfig.FluidIn(() -> Ore.neuronCell.get(OreFluid.solution).fluidTag(), 32)));
    }
}
