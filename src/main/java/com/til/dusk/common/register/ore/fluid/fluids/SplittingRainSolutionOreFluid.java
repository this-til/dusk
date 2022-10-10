package com.til.dusk.common.register.ore.fluid.fluids;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.ore.fluid.OreFluidSplitting;
import com.til.dusk.common.register.ore.ore.Ore;

/**
 * @author til
 */
public class SplittingRainSolutionOreFluid extends OreFluidSplitting {

    public SplittingRainSolutionOreFluid(){
        super("splitting_rain_solution");
    }


    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "雨灵预裂解溶液");
        lang.add(LangType.EN_CH, "Splitting Rain Solution");
    }

    @Override
    public void defaultConfig() {
        colorBasics = new Delayed<>(() -> Ore.rain.color);
    }
}
