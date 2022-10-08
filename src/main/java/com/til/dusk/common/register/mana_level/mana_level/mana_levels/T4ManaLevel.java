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
public class T4ManaLevel extends ManaLevel {
    public T4ManaLevel() {
        super("t4");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(ManaLevel.LEVEL, 4)
                .setConfigOfV(ManaLevel.CLOCK, 320)
                .setConfigOfV(ManaLevel.PARALLEL, 4)
                .setConfigOfV(ManaLevel.MAX_BIND, 4)
                .setConfigOfV(ManaLevel.MANA_LOSS, 0.07)
                .setConfigOfV(ManaLevel.MAX_RANGE, 22)
                .setConfigOfV(ManaLevel.COLOR,  new DuskColor(150, 100, 175))
                .setConfig(ManaLevel.OPERATION_BASICS, () -> List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.coolant.get(OreFluid.solution).fluidTag(), 128),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.culture.get(OreFluid.solution).fluidTag(), 32),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.nutrient.get(OreFluid.solution).fluidTag(), 8),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.supportCell.get(OreFluid.solution).fluidTag(), 256)))
                .setConfig(ManaLevel.OPERATION, () -> List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.neuronCell.get(OreFluid.solution).fluidTag(), 8)));
    }
}
