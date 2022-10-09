package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.DuskColor;
import net.minecraftforge.fluids.FluidStack;

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
    public void defaultConfig() {
        color = new DuskColor(237, 133, 129);
        manaLevel = ManaLevel.t2;
        fluidData = new FluidData()
                .setSplitting(new FluidData.SplittingData()
                        .addSunlightSplitting(new IShapedOreConfig.FluidOut(() -> new FluidStack(sourceAir.get(OreFluid.solution).source(), 72), 1D))
                        .addMoonlightSplitting(new IShapedOreConfig.FluidOut(() -> new FluidStack(sourceAir.get(OreFluid.solution).source(), 72), 1D))
                        .addRainSplitting(new IShapedOreConfig.FluidOut(() -> new FluidStack(sourceAir.get(OreFluid.solution).source(), 72), 1D)));
    }

}

