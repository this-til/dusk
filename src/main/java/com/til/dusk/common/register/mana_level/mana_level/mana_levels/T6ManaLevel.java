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
public class T6ManaLevel extends ManaLevel {
    public T6ManaLevel() {
        super("t6");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(ManaLevel.LEVEL, 6)
                .setConfigOfV(ManaLevel.CLOCK, 80)
                .setConfigOfV(ManaLevel.PARALLEL, 6)
                .setConfigOfV(ManaLevel.MAX_BIND, 6)
                .setConfigOfV(ManaLevel.MANA_LOSS, 0.05)
                .setConfigOfV(ManaLevel.MAX_RANGE, 26)
                .setConfigOfV(ManaLevel.COLOR,  new DuskColor(200, 150, 120))
                .setConfig(ManaLevel.OPERATION_BASICS, () -> List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.coolant.get(OreFluid.solution).fluidTag(), 128),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.culture.get(OreFluid.solution).fluidTag(), 32),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.nutrient.get(OreFluid.solution).fluidTag(), 32),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.supportCell.get(OreFluid.solution).fluidTag(), 256)))
                .setConfig(ManaLevel.OPERATION, () -> List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelFluidIn(Ore.neuronCell.get(OreFluid.solution).fluidTag(), 32)));
    }
}
