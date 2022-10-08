package com.til.dusk.common.register.ore.ore.ores;

import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.DuskColor;

/**
 * @author til
 */
public class ElementAirOre extends Ore {
    public ElementAirOre() {
        super("element_air");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "素空");
        lang.add(LangType.EN_CH, "Element Air");
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(114, 211, 175);
        manaLevel = ManaLevel.t2;
        fluidData = new FluidData();
    }

}

