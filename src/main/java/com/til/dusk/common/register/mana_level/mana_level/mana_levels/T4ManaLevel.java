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
public class T4ManaLevel extends ManaLevel {
    public T4ManaLevel() {
        super("t4");
    }

    @Override
    public void defaultConfig() {
        level = 4;
        clock = 320;
        parallel = 4;
        maxBind = 4;
        manaLoss = 0.07;
        maxRange = 22;
        color = new DuskColor(150, 100, 175);
        operationBasics = new Delayed<>(() -> List.of(
                new IShapedOreConfig.IShapedOreManaLevelConfig.FluidIn(() ->Ore.coolant.get(OreFluid.solution).fluidTag(), 128),
                new IShapedOreConfig.IShapedOreManaLevelConfig.FluidIn(() ->Ore.culture.get(OreFluid.solution).fluidTag(), 32),
                new IShapedOreConfig.IShapedOreManaLevelConfig.FluidIn(() ->Ore.nutrient.get(OreFluid.solution).fluidTag(), 8),
                new IShapedOreConfig.IShapedOreManaLevelConfig.FluidIn(() ->Ore.supportCell.get(OreFluid.solution).fluidTag(), 256)));
        operation = new Delayed<>(() -> List.of(
                new IShapedOreConfig.IShapedOreManaLevelConfig.FluidIn(() ->Ore.neuronCell.get(OreFluid.solution).fluidTag(), 8)));
    }
}
