package com.til.dusk.common.register.ore.fluid.fluids;

import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.ore.fluid.OreFluidSplitting;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.ore.OreConfig;

/**
 * @author til
 */
public class SplittingMoonlightSolutionOreFluid extends OreFluidSplitting {

    public SplittingMoonlightSolutionOreFluid() {
        super("splitting_moonlight_solution", () -> Ore.moonlight.getConfig(OreConfig.COLOR));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "月辉预裂解溶液");
        lang.add(LangType.EN_CH, "Splitting Moonlight Solution");
    }
}
