package com.til.dusk.common.register.ore.fluid.fluids;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.ore.fluid.OreFluidSplitting;
import com.til.dusk.common.register.ore.ore.Ore;

/**
 * @author til
 */
public class SplittingSunlightSolutionOreFluid extends OreFluidSplitting {

    public SplittingSunlightSolutionOreFluid(){
        super("splitting_sunlight_solution");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN,"日曜预裂解溶液");
        lang.add(LangType.EN_CH, "Splitting Sunlight Solution");
    }

    @Override
    public void defaultConfig() {
        colorBasics = new Delayed<>(() -> Ore.sunlight.color);
    }
}
