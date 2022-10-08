package com.til.dusk.common.register.mana_level.mana_level.mana_levels;

import com.til.dusk.common.config.ConfigMap;
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
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(ManaLevel.LEVEL, 5)
                .setConfigOfV(ManaLevel.CLOCK, 160)
                .setConfigOfV(ManaLevel.PARALLEL, 5)
                .setConfigOfV(ManaLevel.MAX_BIND, 5)
                .setConfigOfV(ManaLevel.MANA_LOSS, 0.06)
                .setConfigOfV(ManaLevel.MAX_RANGE, 24)
                .setConfigOfV(ManaLevel.COLOR,  new DuskColor(175, 100, 150))
                .setConfig(ManaLevel.OPERATION_BASICS, () -> List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.coolant.get(OreFluid.solution).fluidTag(), 128),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.culture.get(OreFluid.solution).fluidTag(), 32),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.nutrient.get(OreFluid.solution).fluidTag(), 16),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.supportCell.get(OreFluid.solution).fluidTag(), 256)))
                .setConfig(ManaLevel.OPERATION, () -> List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.neuronCell.get(OreFluid.solution).fluidTag(), 16)));
    }
}
