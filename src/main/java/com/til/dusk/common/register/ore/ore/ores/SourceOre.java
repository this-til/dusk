package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.ore.OreConfig;
import com.til.dusk.util.DuskColor;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

/**
 * @author til
 */
public class SourceOre extends Ore {
    public SourceOre() {
        super("source");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "源");
        lang.add(LangType.EN_CH, "Source");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(OreConfig.COLOR, new DuskColor(237, 133, 129))
                .setConfigOfV(OreConfig.MANA_LEVEL, ManaLevel.t2)
                .setConfig(OreConfig.FluidConfig.FLUID_CONFIG, () -> new ConfigMap()
                        .setConfigOfV(OreConfig.FluidConfig.SplittingConfig.SPLITTING, new ConfigMap()
                                .setConfigOfV(OreConfig.FluidConfig.SplittingConfig.SUNLIGHT, List.of(
                                        new IShapedOreConfig.FluidOut(new FluidStack(sourceAir.get(OreFluid.solution).source(), 72), 1D)))
                                .setConfigOfV(OreConfig.FluidConfig.SplittingConfig.MOONLIGHT, List.of(
                                        new IShapedOreConfig.FluidOut(new FluidStack(sourceAir.get(OreFluid.solution).source(), 72), 1D)))
                                .setConfigOfV(OreConfig.FluidConfig.SplittingConfig.RAIN, List.of(
                                        new IShapedOreConfig.FluidOut(new FluidStack(sourceAir.get(OreFluid.solution).source(), 72), 1D)))));
    }

}

