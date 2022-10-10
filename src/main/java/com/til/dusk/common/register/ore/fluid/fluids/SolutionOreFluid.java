package com.til.dusk.common.register.ore.fluid.fluids;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.ore.fluid.OreFluid;

/**
 * @author til
 */
public class SolutionOreFluid extends OreFluid {
    public SolutionOreFluid(){
        super("solution");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN,"溶液");
        lang.add(LangType.EN_CH, "Solution");
    }

    @Override
    public void defaultConfig() {

    }
}
