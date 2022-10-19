package com.til.dusk.common.register.ore.ore.fluid.basics;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.DuskColor;

public class SourceAirOre extends Ore {
    public SourceAirOre() {
        super("source_air");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "源空");
        lang.add(LangType.EN_CH, "Source Air");
    }

    @Override
    public void defaultConfig( ) {
        color = new DuskColor(240, 66, 243);
        manaLevel = ManaLevel.t2;
        fluidData = new FluidData();
    }
}

