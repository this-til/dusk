package com.til.dusk.common.register.ore.ore.fluid.basics;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.DuskColor;

/**
 * @author til
 */
public class IdeaAirOre extends Ore {
    public IdeaAirOre() {
        super("idea_air");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "念空");
        lang.add(LangType.EN_CH, "Idea Air");
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(249, 228, 190);
        manaLevel = ManaLevel.t2;
        fluidData = new FluidData();
    }

}

