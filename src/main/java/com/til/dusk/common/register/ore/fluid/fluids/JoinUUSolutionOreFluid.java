package com.til.dusk.common.register.ore.fluid.fluids;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.pack.FluidPack;

/**
 * @author til
 */
public class JoinUUSolutionOreFluid extends OreFluid {
    public JoinUUSolutionOreFluid() {
        super("join_uu_solution");
    }

    @Override
    public FluidPack create(Ore ore) {
        if (ore.fluidData != null && ore.fluidData.canCopy) {
            return super.create(ore);
        }
        return null;
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "(掺入UU)");
        lang.add(LangType.EN_CH, "(Join UU)");
    }

    @Override
    public void defaultConfig() {

    }
}
