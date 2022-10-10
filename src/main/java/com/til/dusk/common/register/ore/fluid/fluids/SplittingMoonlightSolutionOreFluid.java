package com.til.dusk.common.register.ore.fluid.fluids;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.ore.fluid.OreFluidSplitting;
import com.til.dusk.common.register.ore.ore.Ore;

/**
 * @author til
 */
public class SplittingMoonlightSolutionOreFluid extends OreFluidSplitting {

    public SplittingMoonlightSolutionOreFluid() {
        super("splitting_moonlight_solution");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "月辉预裂解溶液");
        lang.add(LangType.EN_CH, "Splitting Moonlight Solution");
    }

    @Override
    public void defaultConfig() {
        colorBasics = new Delayed<>(() -> Ore.moonlight.color);
    }
}
