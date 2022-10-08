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
public class T5ManaLevel extends ManaLevel {
    public T5ManaLevel() {
        super("t5");
    }

    @Override
    public void defaultConfig() {
        level = 5;
        clock = 160;
        parallel = 5;
        maxBind = 5;
        manaLoss = 0.06;
        maxRange = 24;
        color = new DuskColor(175, 100, 150);
        operationBasics = new Delayed<>(() -> List.of(
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.coolant.get(OreFluid.solution).fluidTag(), 128),
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.culture.get(OreFluid.solution).fluidTag(), 32),
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.nutrient.get(OreFluid.solution).fluidTag(), 16),
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.supportCell.get(OreFluid.solution).fluidTag(), 256)));
        operation = new Delayed<>(() -> List.of(
                new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.neuronCell.get(OreFluid.solution).fluidTag(), 16)));
    }
}
